#pragma once

template<typename T>
struct TNode //структура для описания узла в стеке
{
	T info;
	TNode<T> *next;
	TNode(T x)
	{
		info = x;
		next = 0;
	}
};
template<typename T>
class stack
{
public:
	stack()
	{
		first = 0;
	};
	~stack()
	{
		TNode<T>*tmp = 0;
		while (first != 0)
		{
			tmp = first;
			first = first->next;
			delete tmp;
		}
		first = 0;
	};
	void push(T x)
	{
		TNode<T> *tmp = new TNode<T>(x);
		if (first == 0)
		{
			first = tmp;
		}
		else
		{
			tmp->next = first;
		}
		size++;
		first = tmp;
	};
	int get_size()const
	{
		return size;
	};
	T top()const
	{
		if (first != 0) return first->info;
	};
	bool IsEmpty()const
	{
		return first != 0;
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
	TNode<T>* first; //изменить на топ
	int size = 0;
};
