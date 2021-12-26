#pragma once
template<typename T>
struct TNode
{
	T info;                           //инфо данные структуры
	TNode* next;                      //Указатель не следующий узел
	TNode(T _info) :info(_info), next(nullptr) {};
};


template<typename T>
class queue
{
	TNode<T>* head = nullptr, * tail = nullptr;                  //Указатели на начало списка и на конец
	int size_q = 0;
public:
	queue(queue<T>& st) :head(nullptr), tail(nullptr)
	{
		if (this != &st)
		{
			int n = st.size() - 1;
			if (n != 0)
			{
				while (n >= 0)
				{
					push(st.front());
					st.push(st.front());
					st.pop();
					n -= 1;
				}
			}
		}
	}
	queue<T>& operator=(queue<T>& st)
	{
		int n = st.size();
		if (n >= 1)
		{
			n -= 1;
			while (n >= 0)
			{
				push(st.front());
				st.push(st.front());
				st.pop();
				n -= 1;
			}
		}
		return *this;
	}
	queue() :head(nullptr), tail(nullptr) {}
	~queue()
	{
		TNode<T>* tmp = head;
		if (head == nullptr)
		{
			while (tmp != nullptr)                   //Пока в очереди что-то есть
			{
				tmp = head->next;                 //Резерв адреса на следующий элемент очереди
				delete head;                       //Освобождение памяти от первой структуры как элемента списка
				head = tmp;                       //Сдвиг начала на следующий адрес, который берем из очереди
			}
		}
	}
	void push(T x){
		size_q += 1;
		TNode<T>* tmp = new TNode<T>(x);//Выделение памяти для нового звена списка

		if (head != nullptr)                   //Если список не пуст
		{
			tail->next = tmp;//смещаем указатель последнего элемента на новосозданый 
			tail = tmp;
		}
		else head = tail = tmp;//Если список не пуст, добавление первого элемента
	}
	bool pop()
	{
		if (head != nullptr)//Если список не пустой
		{
			size_q -= 1;
			TNode<T>* temp = head;//Обращаемся к началу
			head = head->next;//Сдвиг начала списка
			delete temp;
			return true;//Освобождение памяти от предыдущего звена списка
		}
		else
		{
			return false;
		}
	}
	bool isEmpty()const
	{
		return head == nullptr;
	}
	T& front()const{
		if(!isEmpty()) return head->info;
	}
	T& back()const{
		if(!isEmpty()) return tail->info;
	}
	int size()const{
		return size_q;
	}
	void clear(){
		while (pop()) {}
	}
};
