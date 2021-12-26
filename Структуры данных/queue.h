#pragma once
template<typename T>
struct TNode
{
	T info;                           //èíôî äàííûå ñòðóêòóðû
	TNode<T>* next;                      //Óêàçàòåëü íå ñëåäóþùèé óçåë
	TNode(T _info) :info(_info), next(nullptr) {};
};


template<typename T>
class queue
{
	TNode<T>* head = nullptr, * tail = nullptr;                  //Óêàçàòåëè íà íà÷àëî ñïèñêà è íà êîíåö
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
			while (tmp != nullptr)                   //Ïîêà â î÷åðåäè ÷òî-òî åñòü
			{
				tmp = head->next;                 //Ðåçåðâ àäðåñà íà ñëåäóþùèé ýëåìåíò î÷åðåäè
				delete head;                       //Îñâîáîæäåíèå ïàìÿòè îò ïåðâîé ñòðóêòóðû êàê ýëåìåíòà ñïèñêà
				head = tmp;                       //Ñäâèã íà÷àëà íà ñëåäóþùèé àäðåñ, êîòîðûé áåðåì èç î÷åðåäè
			}
		}
	}
	void push(T x){
		size_q += 1;
		TNode<T>* tmp = new TNode<T>(x);//Âûäåëåíèå ïàìÿòè äëÿ íîâîãî çâåíà ñïèñêà

		if (head != nullptr)                   //Åñëè ñïèñîê íå ïóñò
		{
			tail->next = tmp;//ñìåùàåì óêàçàòåëü ïîñëåäíåãî ýëåìåíòà íà íîâîñîçäàíûé 
			tail = tmp;
		}
		else head = tail = tmp;//Åñëè ñïèñîê íå ïóñò, äîáàâëåíèå ïåðâîãî ýëåìåíòà
	}
	bool pop()
	{
		if (head != nullptr)//Åñëè ñïèñîê íå ïóñòîé
		{
			size_q -= 1;
			TNode<T>* temp = head;//Îáðàùàåìñÿ ê íà÷àëó
			head = head->next;//Ñäâèã íà÷àëà ñïèñêà
			delete temp;
			return true;//Îñâîáîæäåíèå ïàìÿòè îò ïðåäûäóùåãî çâåíà ñïèñêà
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
		else throw "None elements";
	}
	T& back()const{
		if(!isEmpty()) return tail->info;
		else throw "None elements";
	}
	int size()const{
		return size_q;
	}
	void clear(){
		while (pop()) {}
	}
};
