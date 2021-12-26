#pragma once
#include <string>
using namespace std;
struct T_Trie
{
	string key;
	string info;
	T_Trie *left = nullptr;
	T_Trie *right = nullptr;
	T_Trie(string _key, string _info) :key(_key), info(_info) {}
	bool isleaf() const// является ли он листом, т.е left=right=nullptr
	{
		return (left == right) && (left == nullptr);
	}
};

short min(short a, short b)
//Данная функция возвращает минимальное число из 2-х параметров
{
	if (a > b)return b;
	return a;
}
string StringToLower(string &str)
//s    принимаемая строка в которой нужно преобразовать все символы к нижнему регистру
//     принимаем по ссылке,чтобы не создавать копию внутри функции
{
	string tmps = "";//вспомогательная переменная
	for (short i = 0; i < str.size(); ++i)
	{
		tmps += (char)tolower(str[i]);//преобраз. к char, т.к tolower вернет целое число
	}
	return tmps;
}
short LevenshteinDistance(string txt, string pattern)
//txt     1-ая строка (исходный текст)
//pattern 2-ая строка (шаблон)
//        обе строки получаем по значению,что позволит создать копию строк в этой функции, 
//        а так как мы собираемся менять их значения,это будет как раз кстати ,т.к не рекомендуется портить начальные данные
//flag    если true,то мы считаем одну и ту же большую и маленькую букву разными символами,
//        если false,то одна и та же большая и маленькая буква это 1 символ
{
	if (txt.size() > pattern.size())
	{
		return LevenshteinDistance(pattern, txt);
	}
	//Преобразовываем в 1-й и 2-й строке все буквы к нижнему регистру.
	txt = StringToLower(txt);
	pattern = StringToLower(pattern);

	short min_size = txt.size();//запоминаем значения размеров строк,чтобы не вызывать один и тот же метод по несколько раз
	short max_size = pattern.size();
	vector<short> lev_dist(min_size + 1);//вспомогательная структура для вычесления разницы слов

	for (short i = 0; i <= min_size; ++i)
	{
		//инициализируем значениями нашу вспом.структуру
		lev_dist[i] = i;
	}

	for (short j = 1; j <= max_size; ++j)
	{
		short previous_diagonal = lev_dist[0];
		++lev_dist[0];
		for (short i = 1; i <= min_size; ++i)
		{
			short previous_diagonal_save = lev_dist[i];
			if (txt[i - 1] == pattern[j - 1])
			{
				lev_dist[i] = previous_diagonal;
			}
			else
			{
				lev_dist[i] = min(min(lev_dist[i - 1], lev_dist[i]), previous_diagonal) + 1;
			}
			previous_diagonal = previous_diagonal_save;
		}
	}

	return lev_dist[min_size];
}

class Trie
{
public:
	~Trie()
	{
		clear_t(root);
		size = 0;
	}
	void clear()
	{
		clear_t(root);
		size = 0;
	}
	void add(string key, string info)
	{
		insert(root, key, info);
		size += 1;
	}
	bool remove(string key)
	{
		return remove_r(root, key);
	}
	string KeySearchLevenshteinDistance(string key)
	{
		min_error = INT_MAX;
		int precision = ceil(key.length()/4);
		key_search_LevenshteinDistance(root, key,precision);
		if (target != "")
		{
			string CopyTarget = target;
			target = "";
			return CopyTarget;
		}
	}
	string KeySearch(string key)
	{
		key_search_r(root, key);
		if (target != "") {
			string CopyTarget = target;
			this->target = "";
			return CopyTarget;
		}
		return "";
	}
	string InfoSearch(string info)
	{
		info_search_r(root, info);
		if (target != "") {
			string CopyTarget = target;
			this->target = "";
			return CopyTarget;
		}
		return "";
	}
private:

	T_Trie *root = nullptr;
	string target = "";
	int size = 0;
	int min_error = INT_MAX;

	void insert(T_Trie *MyTrie, string key, string info)
	{
		if (root == nullptr)
		{
			root = new T_Trie(key, info);
			MyTrie = root;
		}
		else
		{
			int cond = strcmp(MyTrie->key.c_str(), key.c_str());
			if (cond < 0)
			{
				if (MyTrie->left != nullptr) { insert(MyTrie->left, key, info); }
				else
				{
					MyTrie->left = new T_Trie(key, info);
					MyTrie->left->left = MyTrie->left->right = nullptr;
				}
			}
			else if (cond > 0)
			{
				if (MyTrie->right != nullptr) { insert(MyTrie->right, key, info); }
				else
				{
					MyTrie->right = new T_Trie(key, info);
					MyTrie->right->left = MyTrie->right->right = nullptr;
				}
			}
		}
	}
	T_Trie* remove_r(T_Trie *MyTrie, string key)
	{
		// если достигли листа, то узла с данным ключем не существует

		if (MyTrie == NULL)
			return 0;
		// если ключ текущего узла меньше искомого
		int cond = strcmp(MyTrie->key.c_str(), key.c_str());
		if (cond > 0)
		{
			// идем по правой ветке
			MyTrie->right = remove_r(MyTrie->right, key);
			return MyTrie;
		}
		else if (cond < 0)// если ключ текущего узла больше искомого
		{
			// идем по левой ветке
			MyTrie->left = remove_r(MyTrie->left, key);
			return MyTrie;
		}
		// ключ текущего узла равен искомому
		return destroy(MyTrie);
	}
	T_Trie* destroy(T_Trie *MyTrie)
	{
		// дерево состоит из одной вершины
		if (MyTrie->isleaf())
		{
			delete root;
			root = NULL;
			size -= 1;
		}
		else// левое поддерево пусто
			if (MyTrie->left == 0)
			{
				// сохраняем указатель на правое поддерево
				T_Trie* r = MyTrie->right;
				// копируем состояние узла находящегося справа
				*MyTrie = *r;
				// удаляем узел
				r->left = 0;
				r->right = 0;
				r = 0;
				delete r;
				size -= 1;
			}
			else// левое поддерево не пусто
			{
				// ищем узел являющийся самым правым в левом поддереве
				// самый правый узел
				T_Trie *c = MyTrie->left;
				// родитель самого правого узла
				T_Trie *p = MyTrie->left;
				// двигаемся по правой ветви левого поддерева
				while (c->right)
				{
					p = c;
					c = c->right;
				}
				// левое поддерево самого правого узла становиться правым поддеревом родителя
				p->right = c->left;
				// рвем отношение
				c->left = NULL;
				// переносим ключ
				MyTrie->key = c->key;
				// удаляем самый правый узел
				c = 0;
				delete c;
				size -= 1;
			}
		return MyTrie;
	}

	void clear_t(T_Trie *&Trie) {

		if (Trie != NULL) //Пока не встретится пустое звено
		{
			clear_t(Trie->left); //Рекурсивная функция прохода по левому поддереву
			clear_t(Trie->right); //Рекурсивная функци для прохода по правому поддереву
			delete Trie;
			Trie = NULL;
		}
	}

	void key_search_LevenshteinDistance(T_Trie *MyTrie, string key,int precision)
	{
		if (MyTrie != nullptr)
		{
			int n = LevenshteinDistance(MyTrie->key, key);
			if (n <= precision &&n!=0)
			{
				if (n < min_error) 
				{
					target = MyTrie->key;
					min_error = n;
				}
			}
			else
			{
				int cond = strcmp(MyTrie->key.c_str(), key.c_str());
				if (cond < 0) { return key_search_LevenshteinDistance(MyTrie->left, key, precision); }
				else if (cond > 0) { return key_search_LevenshteinDistance(MyTrie->right, key, precision); }
			}
		}
	}
	void key_search_r(T_Trie *MyTrie, string key)
	{
		if (MyTrie != nullptr)
		{
			if (MyTrie->key==key){
				target = MyTrie->info;
				return;
			}
			else
			{
				int cond = strcmp(MyTrie->key.c_str(), key.c_str());
				if (cond < 0) { return key_search_r(MyTrie->left, key); }
				else if (cond > 0) { return key_search_r(MyTrie->right, key); }
			}
		}
	}
	void info_search_r(T_Trie *MyTrie, string inf)
	{
		if (MyTrie != nullptr)
		{
			info_search_r(MyTrie->left, inf);
			if (MyTrie->info == inf) { target = MyTrie->key; return; }
			info_search_r(MyTrie->right, inf);
		}
	}

};

