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
		TNode<T>*tmp = 0;//вспомг.переменная
		while (first != 0)//пока стек не пуст
		{
			tmp = first;//запоминаем удаляемую вершину
			first = first->next;//смещаем вершину стека на след.вершину
			delete tmp;//удаляем
		}
		first = 0;//обязательно, иначе не сможем узнать, пуст ли стек
	};
	void push(T x)
	{
		TNode<T> *tmp = new TNode<T>(x);//иницилизировали узел
		if (first == 0)//если стек пуст, то вместо first новосозданный узел
		{
			first = tmp;
		}
		else//стек не пуст
		{
			tmp->next = first;//смещаем указатель на новосозданый элемент
		}
		size++;//размерность стека +1
		first = tmp;//связываем с предыдущими элементами стека
	};
	int get_size()const//узнать размерность стека
	{
		return size;
	};
	T top()const//получить данные из вершины
	{
		if (first != 0) return first->info; //если стек не пуст, то будут данные
	};
	bool empty()const//проверка на пустоту
	{
		return first == 0;//если пуст вернет true
	};
	bool pop()//удаление вершины
	{
		if (first == 0) { return false; }//стек пуст-удаление не требуется
		size--;//понижаем размерность
		TNode<T> *tmp = first;//запоминаем удаляемый элемент
		first = first->next;//смещаем маркет вершины на след.элемент
		delete tmp;//удаляем предыдущую вершину
		return true;//удаление произошло успешно
	};
private:
	TNode<T>* first;//сама вершина
	int size = 0;//размерность стека
};
