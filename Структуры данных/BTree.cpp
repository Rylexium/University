#include <iostream>
using namespace std;

struct node{
	int value;
	node *left;
	node *right;
};

class BTree{
public:
	BTree(){
		root = nullptr;
	}
	~BTree(){
		destroy_tree();
	}

	void insert(int key) {
		if(root != nullptr){
			insert(key, root);
		}else{
			root = new node;
			root->value = key;
			root->left = nullptr;
			root->right = nullptr;
		}
	}
	node *search(int key){
		return search(key, root);
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
	void insert(int key, node *leaf){
		if(key < leaf->value){
			if(leaf->left != nullptr){
				insert(key, leaf->left);
			}else{
				leaf->left = new node;
				leaf->left->value = key;
				leaf->left->left = nullptr;
				leaf->left->right = nullptr;
			}
		}else if(key >= leaf->value){
			if(leaf->right != nullptr){
				insert(key, leaf->right);
			}else{
				leaf->right = new node;
				leaf->right->value = key;
				leaf->right->right = nullptr;
				leaf->right->left = nullptr;
			}
		}
	}
	node *search(int key, node *leaf) {
		if(leaf != nullptr){
			if(key == leaf->value){
				return leaf;
			}
			if(key < leaf->value){
				return search(key, leaf->left);
			}else{
				return search(key, leaf->right);
			}
		}else{
			return nullptr;
		}
	}
	void inorder_print(node *leaf){
		if(leaf != nullptr){
			inorder_print(leaf->left);
			cout << leaf->value << " ";
			inorder_print(leaf->right);
		}
	}
	void postorder_print(node *leaf) {
		if(leaf != nullptr){
			postorder_print(leaf->left);
			postorder_print(leaf->right);
			cout << leaf->value << " ";
		}
	}
	void preorder_print(node *leaf) {
		if(leaf != nullptr){
			cout << leaf->value << " ";
			preorder_print(leaf->left);
			preorder_print(leaf->right);
		}
	}

	node *root;
};

int main(){
	BTree *tree = new BTree();

	tree->insert(10);
	tree->insert(6);
	tree->insert(14);
	tree->insert(5);
	tree->insert(8);
	tree->insert(11);
	tree->insert(18);

	tree->preorder_print();
	tree->inorder_print();
	tree->postorder_print();

	delete tree;

}
