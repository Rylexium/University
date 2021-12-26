#pragma once

template<typename T>
struct TNode //структура для описания узла в стеке
{
	T info;
	TNode<T> *next;
	TNode(T x) :info(x), next(nullptr) {};
};

template<typename T>
class stack
{
public:
	stack() : first(nullptr) {};
	~stack(){
		clear();
	};
	void clear() {
		TNode<T>* tmp = nullptr;
		while (first != nullptr)
		{
			tmp = first;
			first = first->next;
			delete tmp;
		}
		first = nullptr;
	}
	void push(T x){
		TNode<T> *tmp = new TNode<T>(x);
		if (first == 0) first = tmp;
		else tmp->next = first;
		size++;
		first = tmp;
	};
	int get_size()const{
		return size;
	};
	T& top()const
	{
		if (first != 0) return first->info;
	};
	bool IsEmpty()const{
		return first == nullptr;
	};
	bool pop()
	{
		if (first == 0) { return false; }
		size--;
		TNode<T> *tmp = first;
		first = first->next;
		delete tmp;
		return true;
	};
private:
	TNode<T>* first = nullptr; //изменить на топ
	int size = 0;
};
