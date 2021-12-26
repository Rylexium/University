#include <iostream>
#include <ctime>
using namespace std;

int sr1 = 0;
int pr1 = 0;
// Функция быстрой сортировки
void quick_sort(int *arr, int left, int right)
{
	/*Выбрать элемент из массива. Назовём его опорным.
	Разбиение: перераспределение элементов в массиве таким образом,
	что элементы меньше опорного помещаются перед ним, а больше или равные после.
	Рекурсивно применить первые два шага к двум подмассивам слева и справа от опорного элемента
	Рекурсия не применяется к массиву, в котором только один элемент или отсутствуют элементы.*/
	int pivot = arr[left]; // разрешающий элемент
	int left_it = left; //левая граница
	int right_it = right; // правая граница
	pr1 += 1;
	while (left < right) // пока границы не сомкнутся
	{
		while ((arr[right] >= pivot) && (left < right))
		{
			right--; // сдвигаем правую границу пока элемент [right] больше [pivot]
			sr1 += 1;
		}
		if (left != right) // если границы не сомкнулись
		{
			arr[left] = arr[right]; // перемещаем элемент [right] на место разрешающего
			left++; // сдвигаем левую границу вправо
			pr1 += 1;
		}
		while ((arr[left] <= pivot) && (left < right)) {
			sr1 += 1;
			left++; // сдвигаем левую границу пока элемент [left] меньше [pivot]
		}
		if (left != right) // если границы не сомкнулись
		{
			arr[right] = arr[left]; // перемещаем элемент [left] на место [right]
			right--; // сдвигаем правую границу вправо
			pr1 += 1;
		}
	}
	arr[left] = pivot; // ставим разрешающий элемент на место
	pivot = left;
	left = left_it;
	right = right_it;
	pr1 +=1;
	if (left < pivot) // Рекурсивно вызываем сортировку для левой и правой части массива
		quick_sort(arr, left, pivot - 1);
	if (right > pivot)
		quick_sort(arr, pivot + 1, right);
};

void Merge(int *arr, int first, int last)
{

	int*mas = new int[(last - first)];
	int left_it, right_it, middle;

	middle = (first + last) / 2;
	left_it = first;
	right_it = middle + 1;
	for (int j = first; j <= last; j++)
	{
		sr1 += 1;
		if ((left_it <= middle) && ((right_it > last) || (arr[left_it] < arr[right_it])))
		{
			mas[j - first] = arr[left_it];
			left_it++;
			pr1 += 1;
		}
		else
		{
			mas[j - first] = arr[right_it];
			right_it++;
			pr1 += 1;
		}
	}
	for (int j = first; j <= last; j++)
	{
		arr[j] = mas[j - first];
		pr1 += 1;
	}
}

void MergeSort(int *arr, int start, int end)
{
	if (start < end)
	{
		MergeSort(arr, start, (start + end) / 2);//спускаюсь чисто вниз
		MergeSort(arr, (start+end)/2+1, end);//на обратном ходе иду вправо
		Merge(arr, start, end);//объединяю
	}
}

void quick_sort_for_sportman(int **arr, int size)
{
	//Указатели в начало и в конец массива
	int i = 0;
	int j = size - 1;
	//Центральный элемент массива
	int mid = arr[size / 2][0];
	//Делим массив
	while (i <= j)
	{
		//Пробегаем элементы, ищем те, которые нужно перекинуть в другую часть

		//В левой части массива пропускаем(оставляем на месте) элементы, которые меньше центрального

		while (arr[i][0] < mid) {
			i++;
		}

		//В правой части пропускаем элементы, которые больше центрального

		while (arr[j][0] > mid)
		{
			j--;
		}
		//Меняем элементы местами
		if (i <= j) {
			int tmp1 = arr[i][0];
			int tmp2 = arr[i][1];
			arr[i][0] = arr[j][0];
			arr[i][1] = arr[j][1];
			arr[j][0] = tmp1;
			arr[j][1] = tmp2;
			i++;
			j--;
		}
	}
	//Рекурсивные вызовы, если осталось, что сортировать
	if (j > 0) {
		//"Левая часть"
		quick_sort_for_sportman(arr, j + 1);
	}
	if (i < size) {
		//"Правая часть"
		quick_sort_for_sportman(&arr[i], size - i);
	}

}


int main()
{
	srand(time(NULL));
	setlocale(0, "");
	int n=20; 
	//cin >> n;
	int *arr = new int[n];
	int *mas = new int[n];
	for (int i = 0; i < n; i++)
	{
		int a = rand() % 30 - rand() % 30;
		arr[i] = a;
		mas[i] = a;
		cout << arr[i] << " ";
	}
	quick_sort(arr,0, n-1);
	cout << "\nДля размерности массива " << n << endl;
	cout << "Быстрой сортировка\tсравнений:" << sr1 << "\tприсваиваний:" << pr1 << endl;
	cout << "\n\n";
	sr1 = 0; pr1 = 0;
	MergeSort(mas, 0, n - 1);
	cout << "\nДля размерности массива " << n << endl;
	cout << "Сортировка слиянием\tсравнений:" << sr1 << "\tприсваиваний:" << pr1 << endl;

	n = rand() % 10;
	int**mass = new int*[n];
	for (int i = 0; i < n; i++)
	{
		mass[i] = new int[2];
	}
	for (int i = 0; i < n; i++)
	{
		int popitka1 = rand() % 20;
		int popitka2 = rand() % 20;
		mass[i][0] = popitka1 > popitka2 ? popitka1 : popitka2;
		mass[i][1] = i + 1;
		cout << "\nУ спортсмена " << i + 1 << " лучший результат: " << mass[i][0];
	}
	quick_sort_for_sportman(mass, n);
	for (int i = 0; i < n; i++) {
		cout << "\nСпортсмену " << mass[i][1] << " соотвествует результат " << mass[i][0];
	}
	cout << "\n====================================================================";
	for (int i = n - 1,count=0;count<3 &&i >= 0;count++, i--)
	{
		cout << "\nСпортсмену " << mass[i][1] << " соотвествует результат " << mass[i][0];
	}

}