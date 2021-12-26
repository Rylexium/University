#include <iostream>
#include "stack.h"
#include <string>
using namespace std;
int Eval(const string & left, char oper, const string & right);
bool IsOperator(char inch);
int Priority(string inch);
std::string ReversePolishNotation(const string& instr);
int Result(const string & postfix);
string operator_to_string(char op);
int main()
{
	setlocale(0, "");
	cout << "Введите выражение: ";
	string infixStr = ""; cin >> infixStr;
	cout << "Инфиксная форма: " << infixStr << endl;
	string postfixStr = ReversePolishNotation(infixStr);
	cout << "Постфиксная форма: " << postfixStr << endl;
	cout << "Результат: " << Result(postfixStr) << endl;
	system("Pause");
}
int Eval(const string & left, char oper, const  string & right)
//производится арифм.операция над 2-мя числами
{
	int a = stoi(left);//преобразовываю числа из string в int
	int b = stoi(right);
	switch (oper)
	{
	case '*':
		return a * b;
	case '/':
		return a / b;
	case '+':
		return a + b;
	case '-':
		return a - b;
	default:
		return 0;
	}

}

bool IsOperator(char inch)
//является ли символ оператором
{
	return inch == '*' || inch == '/' || inch == '+' || inch == '-';
}

int Priority(string inch)
//вычисление приоритета операции
{
	if (inch == "*" || inch == "/") return 2;
	if (inch == "+" || inch == "-") return 1;
	return 0;
}
string ReversePolishNotation(const string& instr)
{
	string postfix;
	//струтура хранения 33 9 +, пробелы нужно для того,чтобы можно было разглядеть n-значные числа
	stack<string> mstack;//будут храниться скобки и арифм.операции
	for (int i = 0; i < instr.length(); i++)//проход по всей исходной строке
	{
		string number = "";//здесь будут храняться числа
		//  Константы и переменные кладутся в формируемую запись в порядке их появления в исходном массиве.
		while (isdigit(instr[i]))//пока число, записываю
		{
			number += instr[i];
			++i;
		}
		postfix += number + " ";
		//  При появлении операции в исходном массиве :
		if (IsOperator(instr[i]))
		{
			//если в стеке нет операций или верхним элементом
			//стека является открывающая скобка, операции кладётся в стек;

			if (mstack.empty() || mstack.top() == "(")
				mstack.push(operator_to_string(instr[i]));
			//  если новая операции имеет больший* приоритет, чем верхняя операции в стеке, то новая операции кладётся в стек;
			else if (Priority(mstack.top()) < Priority(operator_to_string(instr[i])))
				mstack.push(operator_to_string(instr[i]));
			//  если новая операция имеет меньший или равный приоритет, чем верхняя операции в стеке, то операции, находящиеся в стеке,
			//до ближайшей открывающей скобки или до операции с приоритетом меньшим, чем у новой операции, перекладываются в формируемую запись, а новая операции кладётся в стек.
			else if (Priority(mstack.top()) >= Priority(operator_to_string(instr[i])))
			{
				while (!mstack.empty() && mstack.top() != "(" && !(Priority(mstack.top()) < Priority(operator_to_string(instr[i]))))
				{
					postfix += mstack.top() + " ";
					mstack.pop();
				}
				mstack.push(operator_to_string(instr[i]));
			}
		}

		//  Открывающая скобка кладётся в стек.
		if (instr[i] == '(')
			mstack.push(operator_to_string(instr[i]));

		//  Закрывающая скобка выталкивает из стека в формируемую запись все операции до ближайшей открывающей скобки, открывающая скобка удаляется из стека.
		if (instr[i] == ')')
		{
			while (!mstack.empty() && mstack.top() != "(")
			{
				postfix += mstack.top() + " ";
				mstack.pop();
			}
			if (mstack.top() == "(")
				mstack.pop();
		}
	}
	//  После того, как мы добрались до конца исходного выражения, операции, оставшиеся в стеке, перекладываются в формируемое выражение.
	while (!mstack.empty())
	{
		postfix += mstack.top() + " ";
		mstack.pop();
	}
	return postfix;
}

int Result(const string & postfix)
{
	stack<string> mstack;//будут храниться числа
	for (int i = 0; i < postfix.length(); i++)//по всей строке, записанной в постф.форме
	{
		string number = "";//здесь будет храниться само число
		if (isdigit(postfix[i]))//это число
		{
			number += postfix[i];//записываем число
			++i;//переход к след.элементы массива
			while (isdigit(postfix[i]))//пока попадаются числа
			{
				number += postfix[i];//записываем в строку очередное число
				++i;
			}
			mstack.push(number);//попался пробел,следовательно в number хранится полученно число
		}
		else if (IsOperator(postfix[i]))//попался оператор, нужно применить к 2-м числам арифм.операцию
		{
			string right = mstack.top();//получение правого числа
			mstack.pop();//удаляем из стека, т.к больше не нужно это число
			string left = mstack.top();//получение левого числа
			mstack.pop();
			int res = Eval(left, postfix[i], right);//вычислим результат операции над 2-мя числами
			mstack.push(to_string(res));//снова добавляем в стек, преобразов к string
		}
	}
	return stoi(mstack.top());//в итоге в стеке останется 1 элемент это и будет наш ответ, преобразовываем из string в int функцией stoi
}
string operator_to_string(char op)
//преобразует арифм.операцию из char в string
{
	switch (op)
	{
	case '+':return "+";
	case '-':return "-";
	case '*':return "*";
	case '/':return "/";
	case '(':return "(";
	case ')':return ")";
	}
	return "";
}