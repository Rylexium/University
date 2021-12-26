#include <iostream>
#include<ctime>
#include<vector>
#include<queue>
#include <string>
#include<fstream>
#define WAY 4
using namespace std;

void Merge(vector<int>& arr, int first, int last)
{

	int*mas = new int[(last - first)];
	int left_it, right_it, middle;

	middle = (first + last) / 2;
	left_it = first;
	right_it = middle + 1;
	for (int j = first; j <= last; j++)
	{
		if ((left_it <= middle) && ((right_it > last) || (arr[left_it] < arr[right_it])))
		{
			mas[j - first] = arr[left_it];
			left_it++;
		}
		else
		{
			mas[j - first] = arr[right_it];
			right_it++;
		}
	}
	for (int j = first; j <= last; j++)
	{
		arr[j] = mas[j - first];
	}
}

void MergeSort(vector<int>& arr, int start, int end)
{
	if (start < end)
	{
		MergeSort(arr, start, (start + end) / 2);//спускаюсь чисто вниз
		MergeSort(arr, (start + end) / 2 + 1, end);//на обратном ходе иду вправо
		Merge(arr, start, end);//объединяю
	}
}


void SortFourFile(int start)
//рекурсивная функция, которая сортирует содержимое файлов в диапозоне от start до 2*WAY (оба включительно)
{
	if (start < 2*WAY+1) 
	{
		ifstream rfile("file" + to_string(start) + ".txt");//открываем файл на текущим уровне рекурсии
		if (rfile.peek() != EOF)
		{
			vector<int>arr;
			while (!rfile.eof())
			{
				int k = INT_MAX;
				rfile >> k;
				if (k != INT_MAX) { arr.push_back(k); }
			}
			MergeSort(arr, 0, arr.size() - 1);
			rfile.close();
			ofstream wfile("file" + to_string(start) + ".txt");
			for (int i = 0; i < arr.size(); i++)
			{
				wfile << arr[i]<<" ";
			}
		}
		SortFourFile(start + 1);//переходим к след.файлу, на обратном ходе ничего не выполняется
	}
}

vector<int> min(int left,int idx1, int right, int idx2)
{
	vector<int> arr;
	if (left < right)
	{
		arr.push_back(left);
		arr.push_back(idx1);
		return arr;
	}
	else
	{
		arr.push_back(right);
		arr.push_back(idx2);
		return arr;
	}
}

bool FilesEof(vector<ifstream> &files,int n)
{
	if (n >= 0)
	{
		return !files[n].eof() || FilesEof(files, n - 1);
	}
	else
	{
		return false;
	}
}
void FourWayMerge(int where, int left, int right)
//объединяет WAY-частей
//where-куда
//left,right-левая и правая граница соответственно
//метод слияние содержимых файлов от left до right(оба включительно)
{
	ofstream wfile("file" + to_string(where) + ".txt");//открываем куда будем записывать данные
	vector<ifstream> files(WAY);
	for (int i = 0; i <WAY; i++)
	{
		files[i].open("file" + to_string(left + i) + ".txt");
	}
	vector<int> arr(WAY,INT_MAX);
	while (FilesEof(files, WAY - 1))
	{
		for (int i = 0; i < WAY; i++)
		{
			if (arr[i] == INT_MAX)
			{
				int k = INT_MAX;
				files[i] >> k;
				if (k != INT_MAX) { arr[i] = k; }
			}
		}
		vector<int> minimum;//Кандидат на минимум
		minimum.push_back(arr[0]);
		minimum.push_back(0);
		for (int i = 1; i < WAY; i++)//смотрю массив очередей
		{
			minimum = min(minimum[0], minimum[1], arr[i], i);
		}
		//нашли минимум всех очередей, записываем в файл и удаляем его из очереди,где он находился
		if (minimum[0] != INT_MAX)
		{
			wfile << minimum[0] << " ";
		}
		arr[minimum[1]] = INT_MAX;
	}
	for (int i = 0; i < WAY; i++)
	{
		files[i].close();
	}
	wfile.close();
}
void DividFile(int file, int first)//функция,которая позволяет содержимое файла раскидать по 4-м файлам
//file-номер файла
//first-все последующие файлы будут называться first+1
//разобьется файл на WAY частей
{
	ifstream TargetFile("file" + to_string(file) + ".txt");
	int count = 0;
	while (!TargetFile.eof())//раскидываю по файлам 4 части
	{
		ofstream wfile;
		int k = INT_MAX;
		TargetFile >> k;
		if (k == INT_MAX) { break; }
		string number = to_string(k);
		string pyt = "file" + to_string(first + count) + ".txt";
		wfile.open(pyt, ios::app);
		wfile << number << " ";
		count += 1;
		if (count == WAY)
		{
			count = 0;
		}
		wfile.close();
	}
}


void Sort()
{
	DividFile(0, 1);//0-номер стартового файла,1- с кого файла начнется запись
	for (int i = 1; i <= WAY; i++)//беру предыдущие файлы и раскидываю их ещё на 4 части
	{
		ofstream wfile;
		for (int i = WAY+1; i <= 2*WAY; i++)
		{
			wfile.open("file" + to_string(i) + ".txt");
			wfile.clear();
			wfile.close();
		}
		DividFile(i, WAY + 1);//разбиваем
		SortFourFile(WAY + 1);//сортируем
		FourWayMerge(i, WAY + 1, 2 * WAY);//объединяем
	}
	FourWayMerge(-1, 1, WAY);
}

int main()
{
	srand(time(NULL));
	cout << "WAY= " << WAY << endl;;
	for (int i = 1; i <= 2*WAY; i++)
	{
		string pyth = "file" + to_string(i) + ".txt";
		remove(pyth.c_str());
	}
	ofstream file("file0.txt");
	vector<int> arr1;
	for (int i = 0; i <200; i++)
	{
		int tmp=rand() % 200 - rand() % 100;
		arr1.push_back(tmp);
		file << tmp<<" ";
	}
	file.close();
	
	int start = clock();
	Sort();
	int end = clock();

	cout << "\n"<<(double)(end - start) / CLOCKS_PER_SEC << " sec";
	MergeSort(arr1, 0, arr1.size() - 1);
	ifstream kek("file-1.txt");
	vector<int>arr2;
	while (!kek.eof())
	{
		int k = INT_MAX;
		kek >> k;
		if (k == INT_MAX) {break;}
		arr2.push_back(k);
	}
	cout << "\narr1 " << arr1.size();
	cout << "\narr2 " << arr2.size();
	bool flag = true;
	for (int i = 0; i < arr2.size()&&flag; i++)
	{
		if (arr1[i] != arr2[i])
		{
			flag = false;
		}
	}
	cout << "\nflag " << flag<<endl;
	for (int i = 0; i < arr1.size(); i++)
	{
		cout << arr1[i] << " ";
	}
	cout << endl << endl;
	for (int i = 0; i < arr2.size(); i++)
	{
		cout << arr2[i] << " ";
	}
}
