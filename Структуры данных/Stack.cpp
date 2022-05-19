#include <iostream>
#include <windows.h>
using namespace std;

template<typename T>
struct TNode 
{
    T info;
    TNode<T> *next;
    TNode(T x) :info(x), next(nullptr) {};
};

template<typename T>
class Stack
{
public:
    Stack() : first(nullptr) {};
    ~Stack(){
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
    int getSize()const{
        return size;
    };
    T& top()const
    {
        if (first != 0) return first->info;
    };
    bool isEmpty()const{
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
    TNode<T>* first = nullptr;
    int size = 0;
};


int main(){
    SetConsoleOutputCP(CP_UTF8);
    Stack<string>* stack = new Stack<string>();
    stack->push("lel");
    stack->push("abram");

    while(!stack->isEmpty()){
        cout<<stack->top()<<endl;
        stack->pop();
    }

    stack->push("abara");
    stack->clear();

    stack->push("gad");
    cout<<stack->top();

}
