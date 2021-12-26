#pragma once
template<typename T>
struct TNode
{
	T info;                           //���� ������ ���������
	TNode* next;                      //��������� �� ��������� ����
	TNode(T _info) :info(_info), next(nullptr) {};
};


template<typename T>
class queue
{
	TNode<T>* head = nullptr, * tail = nullptr;                  //��������� �� ������ ������ � �� �����
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
			while (tmp != nullptr)                   //���� � ������� ���-�� ����
			{
				tmp = head->next;                 //������ ������ �� ��������� ������� �������
				delete head;                       //������������ ������ �� ������ ��������� ��� �������� ������
				head = tmp;                       //����� ������ �� ��������� �����, ������� ����� �� �������
			}
		}
	}
	void push(T x){
		size_q += 1;
		TNode<T>* tmp = new TNode<T>(x);//��������� ������ ��� ������ ����� ������

		if (head != nullptr)                   //���� ������ �� ����
		{
			tail->next = tmp;//������� ��������� ���������� �������� �� ������������ 
			tail = tmp;
		}
		else head = tail = tmp;//���� ������ �� ����, ���������� ������� ��������
	}
	bool pop()
	{
		if (head != nullptr)//���� ������ �� ������
		{
			size_q -= 1;
			TNode<T>* temp = head;//���������� � ������
			head = head->next;//����� ������ ������
			delete temp;
			return true;//������������ ������ �� ����������� ����� ������
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
