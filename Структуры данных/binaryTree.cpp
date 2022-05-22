#include <iostream>
using namespace std;

struct node{
    int key;
    string info;
    node *left;
    node *right;
    node(int _key, string _value) : key(_key), info(_value), left(nullptr), right(nullptr){};
};

class BinaryTree{
public:
    BinaryTree(){
        root = nullptr;
    }
    ~BinaryTree(){
        destroy_tree();
    }

    void insert(int key, string value) {
        if(root != nullptr) insert(key, value, root);
        else root = new node(key, value);
    }

    node *searchByKey(int key){
        return searchKey(key, root);
    }

    node *searchByInfo(string value){
        target = nullptr;
        searchInfo(value, root);
        return target;
    }
    node *deleteNode(int key) {
        if(root == nullptr
            || (root->key == key && root->left == nullptr && root->right == nullptr) ) {
            delete root;
            root = nullptr;
            return root;
        }
        else return deleteNode(root, key);
    }
    void destroy_tree() {
        destroy_tree(root);
    }
    void inorder_print() {
        inorder_print(root);
        cout << "\n";
    }
    void postorder_print() {
        postorder_print(root);
        cout << "\n";
    }
    void preorder_print() {
        preorder_print(root);
        cout << "\n";
    }

private:
    void destroy_tree(node *leaf){
        if(leaf != nullptr){
            destroy_tree(leaf->left);
            destroy_tree(leaf->right);
            delete leaf;
        }
    }
    void insert(int key, string value, node *leaf){
        if(key < leaf->key) {
            if(leaf->left != nullptr)
                insert(key, value, leaf->left);
            else
                leaf->left = new node(key, value);

        }else if(key >= leaf->key) {
            if(leaf->right != nullptr)
                insert(key, value, leaf->right);
            else
                leaf->right = new node(key, value);
        }
    }
    node *searchKey(int key, node *leaf) {
        if(leaf != nullptr){
            if(key == leaf->key)
                return leaf;

            if(key < leaf->key)
                return searchKey(key, leaf->left);
            else
                return searchKey(key, leaf->right);

        } else
            return nullptr;

    }

    void searchInfo(string value, node *leaf){
        if(leaf != nullptr){
            if(leaf->info == value) target = leaf;
            searchInfo(value, leaf->left);
            searchInfo(value, leaf->right);
        }
    }

    node * findMinimum(node *currentNode){
        if(currentNode->left == nullptr)
            return currentNode;

        return findMinimum(currentNode->left);
    }

    node * deleteNode(node *root, int key){
        node *par = root, *cur = root;

            while(cur && cur->key != key){
                if(key < cur->key){
                    par = cur;
                    cur = cur->left;
                }else{
                    //key > cur->val
                    par = cur;
                    cur = cur->right;
                }
            }

            //cannot find key in tree
            if(!cur) return root;

            if(!cur->left && !cur->right){
                if(cur == root) return nullptr;
                if(par->left == cur){
                    delete par->left;
                    par->left = nullptr;
                }
                else {
                    delete par->right;
                    par->right = nullptr;
                }
            }else if(cur->left){
                //predecessor of deleted node
                node *pred = cur->left;
                //parent of predecessor
                par = pred;
                while(pred->right){
                    par = pred;
                    pred = pred->right;
                }
                cur->key = pred->key;
                /*
                pred->right is always empty,
                when predecessor is the left child of the node to be deleted,
                we move pred's left subtree one level up
                (set left subtree's parent as cur),
                if not, pred is then it's parent's right child,
                here we also move pred's left subtree one level up
                (set left subtree's parent as par)
                */
                if(cur->left == pred) cur->left = pred->left;
                else par->right = pred->left;
            }else{
                node *succ = cur->right;
                par = succ;
                while(succ->left){
                    par = succ;
                    succ = succ->left;
                }
                cur->key = succ->key;
                if(cur->right == succ) cur->right = succ->right;
                else par->left = succ->right;
            }
            return root;
    }
    void inorder_print(node *leaf){
        if(leaf != nullptr){
            inorder_print(leaf->left);
            cout << leaf->key << " ";
            inorder_print(leaf->right);
        }
    }
    void postorder_print(node *leaf) {
        if(leaf != nullptr){
            postorder_print(leaf->left);
            postorder_print(leaf->right);
            cout << leaf->key << " ";
        }
    }
    void preorder_print(node *leaf) {
        if(leaf != nullptr){
            cout << leaf->key << " ";
            preorder_print(leaf->left);
            preorder_print(leaf->right);
        }
    }

    node *root{};
    node *target = nullptr;
};

int main(){
    BinaryTree *tree = new BinaryTree();

    tree->insert(10, "apple");
    tree->insert(6, "potato");
    tree->insert(14, "milk");
    tree->insert(5, "pear");
    tree->insert(8, "banana");
    tree->insert(11, "orange");
    tree->insert(18, "peach");
    tree->inorder_print();
    tree->deleteNode(10);
    tree->inorder_print();
    tree->deleteNode(8);
    tree->inorder_print();



    cout<<(tree->searchByKey(5) != nullptr)<<endl;
    cout<<(tree->searchByInfo("orange") != nullptr)<<endl;
    delete tree;
}
