#pragma once
#include <iostream>
using namespace std;

template<typename T>
struct TNode {
	TNode<T>* next;
	int value;
	TNode<T>(T _value) :value(_value), next(nullptr) {};
};

template<typename T>
class TList{
public:
	TList() :head(nullptr), tail(nullptr) {};
	bool isEmpty() { return head == nullptr; }

	void push_front(T value) {
		TNode<T>* new_node = new TNode<T>(value);
		if (size == 0){
			new_node->next = nullptr;
			head = tail = new_node;
			size += 1;
			return;
		}
		new_node->next = head;
		head = new_node;
		size += 1;
	}
	void push_back(T value) {
		TNode<T>* new_node = new TNode<T>(value);
		if (isEmpty()) {
			new_node->next = nullptr;
			head = tail = new_node;
			size += 1;
			return;
		}
		tail->next = new_node;
		tail = new_node;
		size += 1;
	}
	void add(int idx, T _value) {
		if (idx < 0 || idx > size) throw "Out of range";

		if (idx == 0) return push_front(_value);
		else if (idx == size - 1) return push_back(_value);
		
		TNode<T>* new_node = new TNode<T>(_value);
		TNode<T>* tmp = head;
		for (int i = 0; i < idx - 1; i++) 
			tmp = tmp->next;
		
		new_node->next = tmp->next;
		tmp->next = new_node;
		size += 1;
	}
	void add_front_value(T _value, T new_value) {
		if (head->value == _value)push_front(new_value);
		else {
			for (TNode<T>* tmp = head->next; tmp->next != nullptr; tmp = tmp->next) {
				if (tmp->next->value == _value) {
					TNode<T>* new_node = new TNode<T>(new_value);
					new_node->next = tmp->next;		
					tmp->next = new_node;
					size += 1;
					return;
				}
			}
		}
	}
	void add_after_value(T _value, T new_value) {
		for (TNode<T>* tmp = head; tmp != tail; tmp = tmp->next) {
			if (tmp->value == _value) {
				TNode<T>* new_node = new TNode<T>(new_value);
				new_node->next = tmp->next;
				tmp->next = new_node;
				size += 1;
				return;
			}
		}
		if (tail->value == _value)push_back(new_value);
	}

	void remove_first() {
		if (isEmpty())return;
		TNode<T>* tmp = head;
		head = head->next;
		delete tmp;
		size -= 1;
	}
	void remove_last() {
		if (isEmpty())return;

		if (head == tail) return remove_first();

		TNode<T>* tmp = head;
		while (tmp->next != tail) tmp = tmp->next;
		tmp->next = nullptr;
		delete tail;
		tail = tmp;
		size -= 1;
	}
	void remove(T _value) {
		if (isEmpty()) return;
		if (head->value == _value) {
			remove_first();
			size -= 1;
			return;
		}
		else if (tail->value == _value) {
			remove_last();
			size -= 1;
			return;
		}
		TNode<T>* tmp = head;
		TNode<T>* tmpNext = head->next;
		while (tmpNext != nullptr && tmpNext->value != _value) {
			tmpNext = tmpNext->next;
			tmp = tmp->next;
		}
		if (tmpNext == nullptr) {
			cout << "Ёлемент не был найден" << endl;
			return;
		}
		tmp->next = tmpNext->next;
		delete tmpNext;
		size -= 1;
	}
	void clear() {
		TNode<T>* tmp = head;
		while (tmp != nullptr) {
			TNode<T>* kek = tmp;
			tmp = tmp->next;
			delete kek;
		}
		delete tmp;
		head = nullptr;
		tail = nullptr;
		size = 0;
	}

	T& indexOf(int idx) {
		if (idx<0 || idx>size)throw "Out of range";
		TNode<T>* tmp = head;
		for (int i = 0; i < idx; i++) 
			tmp = tmp->next;
		
		return tmp->value;
	}
	void print() {
		if (isEmpty())return;
		TNode<T>* tmp = head;
		while (tmp != nullptr) {
			cout << tmp->value << " ";
			tmp = tmp->next;
		}
		cout << endl;
	}

	int find(int _value) {
		if (isEmpty())return -1;
		TNode<T>* tmp = head;
		int index = 0;
		while (tmp != nullptr) {
			if (tmp->value == _value)return index;
			index += 1;
		}
		return -1;
	}


	T& front() { 
		if(!isEmpty())
			return head->value; 
	}
	T& back() { 
		if(!isEmpty()) 
			return tail->value; 
	}

	int getSize() { return size; }

	~TList() { clear();}
private:
	TNode<T>* head = nullptr;
	TNode<T>* tail = nullptr;
	int size = 0;

};

