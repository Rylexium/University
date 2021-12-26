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
	bool isleaf() const// �������� �� �� ������, �.� left=right=nullptr
	{
		return (left == right) && (left == nullptr);
	}
};

short min(short a, short b)
//������ ������� ���������� ����������� ����� �� 2-� ����������
{
	if (a > b)return b;
	return a;
}
string StringToLower(string &str)
//s    ����������� ������ � ������� ����� ������������� ��� ������� � ������� ��������
//     ��������� �� ������,����� �� ��������� ����� ������ �������
{
	string tmps = "";//��������������� ����������
	for (short i = 0; i < str.size(); ++i)
	{
		tmps += (char)tolower(str[i]);//��������. � char, �.� tolower ������ ����� �����
	}
	return tmps;
}
short LevenshteinDistance(string txt, string pattern)
//txt     1-�� ������ (�������� �����)
//pattern 2-�� ������ (������)
//        ��� ������ �������� �� ��������,��� �������� ������� ����� ����� � ���� �������, 
//        � ��� ��� �� ���������� ������ �� ��������,��� ����� ��� ��� ������ ,�.� �� ������������� ������� ��������� ������
//flag    ���� true,�� �� ������� ���� � �� �� ������� � ��������� ����� ������� ���������,
//        ���� false,�� ���� � �� �� ������� � ��������� ����� ��� 1 ������
{
	if (txt.size() > pattern.size())
	{
		return LevenshteinDistance(pattern, txt);
	}
	//��������������� � 1-� � 2-� ������ ��� ����� � ������� ��������.
	txt = StringToLower(txt);
	pattern = StringToLower(pattern);

	short min_size = txt.size();//���������� �������� �������� �����,����� �� �������� ���� � ��� �� ����� �� ��������� ���
	short max_size = pattern.size();
	vector<short> lev_dist(min_size + 1);//��������������� ��������� ��� ���������� ������� ����

	for (short i = 0; i <= min_size; ++i)
	{
		//�������������� ���������� ���� �����.���������
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
		// ���� �������� �����, �� ���� � ������ ������ �� ����������

		if (MyTrie == NULL)
			return 0;
		// ���� ���� �������� ���� ������ ��������
		int cond = strcmp(MyTrie->key.c_str(), key.c_str());
		if (cond > 0)
		{
			// ���� �� ������ �����
			MyTrie->right = remove_r(MyTrie->right, key);
			return MyTrie;
		}
		else if (cond < 0)// ���� ���� �������� ���� ������ ��������
		{
			// ���� �� ����� �����
			MyTrie->left = remove_r(MyTrie->left, key);
			return MyTrie;
		}
		// ���� �������� ���� ����� ��������
		return destroy(MyTrie);
	}
	T_Trie* destroy(T_Trie *MyTrie)
	{
		// ������ ������� �� ����� �������
		if (MyTrie->isleaf())
		{
			delete root;
			root = NULL;
			size -= 1;
		}
		else// ����� ��������� �����
			if (MyTrie->left == 0)
			{
				// ��������� ��������� �� ������ ���������
				T_Trie* r = MyTrie->right;
				// �������� ��������� ���� ������������ ������
				*MyTrie = *r;
				// ������� ����
				r->left = 0;
				r->right = 0;
				r = 0;
				delete r;
				size -= 1;
			}
			else// ����� ��������� �� �����
			{
				// ���� ���� ���������� ����� ������ � ����� ���������
				// ����� ������ ����
				T_Trie *c = MyTrie->left;
				// �������� ������ ������� ����
				T_Trie *p = MyTrie->left;
				// ��������� �� ������ ����� ������ ���������
				while (c->right)
				{
					p = c;
					c = c->right;
				}
				// ����� ��������� ������ ������� ���� ����������� ������ ���������� ��������
				p->right = c->left;
				// ���� ���������
				c->left = NULL;
				// ��������� ����
				MyTrie->key = c->key;
				// ������� ����� ������ ����
				c = 0;
				delete c;
				size -= 1;
			}
		return MyTrie;
	}

	void clear_t(T_Trie *&Trie) {

		if (Trie != NULL) //���� �� ���������� ������ �����
		{
			clear_t(Trie->left); //����������� ������� ������� �� ������ ���������
			clear_t(Trie->right); //����������� ������ ��� ������� �� ������� ���������
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

