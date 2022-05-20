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
    node *deleteNode(int key){
        return deleteNode(root, key);
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
        return nullptr;
    }

    node * findMinimum(node *currentNode){
        if(currentNode->left == nullptr)
            return currentNode;

        return findMinimum(currentNode->left);
    }

    node * deleteNode(node *currentNode, int value)
    {
        if(currentNode == nullptr)
            return nullptr;
        else if(value < currentNode->key)
            currentNode->left = deleteNode(currentNode->left, value);
        else if(value > currentNode->key)
            currentNode->right = deleteNode(currentNode->right, value);
        else
        {
            if(currentNode->left == nullptr && currentNode->right == nullptr)
                currentNode = nullptr;
            else if(currentNode->left == nullptr)
                currentNode = currentNode->right;
            else if(currentNode->right == nullptr)
                currentNode = currentNode->left;
            else
            {
                node *tempNode = findMinimum(currentNode->right);
                currentNode->key = tempNode->key;
                currentNode->right = deleteNode(currentNode->right, tempNode->key);
            }

        }

        return currentNode;
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

    tree->preorder_print();
    tree->inorder_print();
    tree->postorder_print();

    tree->deleteNode(10);

    tree->insert(9, "mazafaka");

    tree->inorder_print();



    cout<<(tree->searchByKey(5) != nullptr)<<endl;
    cout<<(tree->searchByInfo("orange") != nullptr)<<endl;
    delete tree;
}
