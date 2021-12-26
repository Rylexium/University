#pragma once
#include<string>
#include<vector>
#include <ctime>
#include<fstream>
#include "Trie.h"
using namespace std;
namespace Project2 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace System::Collections;
	using namespace System::Threading;

	/// <summary>
	/// Сводка для MyForm
	/// </summary>

	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: Trie *dict;
			 vector<pair<string, string>> *Words;
			 vector<pair<string, string>> *SortedWords;
			 bool FuzzySearch=false;
			 bool SearchInTrie = true;
			 bool LinearSearch = false;
			 bool BinarySearch = false;
			 Thread ^th;
	private: System::Windows::Forms::TextBox^  textBox1;
	private: System::Windows::Forms::Button^  button1;
	private: System::Windows::Forms::RadioButton^  radioButton1;
	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::RadioButton^  radioButton2;
	private: System::Windows::Forms::RadioButton^  radioButton3;
	private: System::Windows::Forms::RadioButton^  radioButton4;
	private: System::Windows::Forms::GroupBox^  groupBox1;
	private: System::Windows::Forms::Button^  button2;
	private: System::Windows::Forms::TabControl^  tabControl1;
	private: System::Windows::Forms::TabPage^  tabPage1;
	private: System::Windows::Forms::TabPage^  tabPage2;
	private: System::Windows::Forms::Label^  label4;


	protected:

	private:
		/// <summary>
		/// Обязательная переменная конструктора.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Требуемый метод для поддержки конструктора — не изменяйте 
		/// содержимое этого метода с помощью редактора кода.
		/// </summary>
		void InitializeComponent(void)
		{
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->radioButton1 = (gcnew System::Windows::Forms::RadioButton());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->radioButton2 = (gcnew System::Windows::Forms::RadioButton());
			this->radioButton3 = (gcnew System::Windows::Forms::RadioButton());
			this->radioButton4 = (gcnew System::Windows::Forms::RadioButton());
			this->groupBox1 = (gcnew System::Windows::Forms::GroupBox());
			this->button2 = (gcnew System::Windows::Forms::Button());
			this->tabControl1 = (gcnew System::Windows::Forms::TabControl());
			this->tabPage1 = (gcnew System::Windows::Forms::TabPage());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->tabPage2 = (gcnew System::Windows::Forms::TabPage());
			this->groupBox1->SuspendLayout();
			this->tabControl1->SuspendLayout();
			this->tabPage1->SuspendLayout();
			this->SuspendLayout();
			// 
			// textBox1
			// 
			this->textBox1->Font = (gcnew System::Drawing::Font(L"Times New Roman", 15.75F, System::Drawing::FontStyle::Italic, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->textBox1->Location = System::Drawing::Point(36, 20);
			this->textBox1->Multiline = true;
			this->textBox1->Name = L"textBox1";
			this->textBox1->Size = System::Drawing::Size(249, 42);
			this->textBox1->TabIndex = 0;
			// 
			// button1
			// 
			this->button1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->button1->Location = System::Drawing::Point(36, 91);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(249, 45);
			this->button1->TabIndex = 1;
			this->button1->Text = L"Перевод английского слова";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->Click += gcnew System::EventHandler(this, &MyForm::button1_Click);
			// 
			// radioButton1
			// 
			this->radioButton1->AutoSize = true;
			this->radioButton1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->radioButton1->Location = System::Drawing::Point(36, 68);
			this->radioButton1->Name = L"radioButton1";
			this->radioButton1->Size = System::Drawing::Size(108, 17);
			this->radioButton1->TabIndex = 2;
			this->radioButton1->TabStop = true;
			this->radioButton1->Text = L"Неточный поиск";
			this->radioButton1->UseVisualStyleBackColor = true;
			this->radioButton1->Click += gcnew System::EventHandler(this, &MyForm::radioButton1_Click);
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->label3->Location = System::Drawing::Point(37, 408);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(110, 18);
			this->label3->TabIndex = 7;
			this->label3->Text = L"Время поиска:";
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->label1->Location = System::Drawing::Point(37, 338);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(140, 18);
			this->label1->TabIndex = 4;
			this->label1->Text = L"Английское слово:";
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->label2->Location = System::Drawing::Point(37, 374);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(99, 18);
			this->label2->TabIndex = 5;
			this->label2->Text = L"Его перевод:";
			// 
			// radioButton2
			// 
			this->radioButton2->AutoSize = true;
			this->radioButton2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->radioButton2->Location = System::Drawing::Point(41, 25);
			this->radioButton2->Name = L"radioButton2";
			this->radioButton2->Size = System::Drawing::Size(135, 22);
			this->radioButton2->TabIndex = 0;
			this->radioButton2->TabStop = true;
			this->radioButton2->Text = L"Поиск в дереве";
			this->radioButton2->UseVisualStyleBackColor = true;
			this->radioButton2->MouseClick += gcnew System::Windows::Forms::MouseEventHandler(this, &MyForm::radioButton2_MouseClick);
			// 
			// radioButton3
			// 
			this->radioButton3->AutoSize = true;
			this->radioButton3->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->radioButton3->Location = System::Drawing::Point(41, 51);
			this->radioButton3->Name = L"radioButton3";
			this->radioButton3->Size = System::Drawing::Size(141, 22);
			this->radioButton3->TabIndex = 1;
			this->radioButton3->TabStop = true;
			this->radioButton3->Text = L"Линейный поиск";
			this->radioButton3->UseVisualStyleBackColor = true;
			this->radioButton3->MouseClick += gcnew System::Windows::Forms::MouseEventHandler(this, &MyForm::radioButton3_MouseClick);
			// 
			// radioButton4
			// 
			this->radioButton4->AutoSize = true;
			this->radioButton4->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 11.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->radioButton4->Location = System::Drawing::Point(41, 79);
			this->radioButton4->Name = L"radioButton4";
			this->radioButton4->Size = System::Drawing::Size(140, 22);
			this->radioButton4->TabIndex = 2;
			this->radioButton4->TabStop = true;
			this->radioButton4->Text = L"Бинарный поиск";
			this->radioButton4->UseVisualStyleBackColor = true;
			this->radioButton4->MouseClick += gcnew System::Windows::Forms::MouseEventHandler(this, &MyForm::radioButton4_MouseClick);
			// 
			// groupBox1
			// 
			this->groupBox1->Controls->Add(this->radioButton4);
			this->groupBox1->Controls->Add(this->radioButton3);
			this->groupBox1->Controls->Add(this->radioButton2);
			this->groupBox1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->groupBox1->Location = System::Drawing::Point(40, 194);
			this->groupBox1->Name = L"groupBox1";
			this->groupBox1->Size = System::Drawing::Size(245, 107);
			this->groupBox1->TabIndex = 6;
			this->groupBox1->TabStop = false;
			this->groupBox1->Text = L"Выберите поиск";
			// 
			// button2
			// 
			this->button2->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->button2->Location = System::Drawing::Point(36, 142);
			this->button2->Name = L"button2";
			this->button2->Size = System::Drawing::Size(249, 45);
			this->button2->TabIndex = 3;
			this->button2->Text = L"Перевод русского слова";
			this->button2->UseVisualStyleBackColor = true;
			// 
			// tabControl1
			// 
			this->tabControl1->Controls->Add(this->tabPage1);
			this->tabControl1->Controls->Add(this->tabPage2);
			this->tabControl1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 12, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->tabControl1->Location = System::Drawing::Point(13, 13);
			this->tabControl1->Name = L"tabControl1";
			this->tabControl1->SelectedIndex = 0;
			this->tabControl1->Size = System::Drawing::Size(336, 496);
			this->tabControl1->TabIndex = 8;
			// 
			// tabPage1
			// 
			this->tabPage1->Controls->Add(this->label4);
			this->tabPage1->Controls->Add(this->button2);
			this->tabPage1->Controls->Add(this->label3);
			this->tabPage1->Controls->Add(this->textBox1);
			this->tabPage1->Controls->Add(this->groupBox1);
			this->tabPage1->Controls->Add(this->button1);
			this->tabPage1->Controls->Add(this->label2);
			this->tabPage1->Controls->Add(this->radioButton1);
			this->tabPage1->Controls->Add(this->label1);
			this->tabPage1->Location = System::Drawing::Point(4, 29);
			this->tabPage1->Name = L"tabPage1";
			this->tabPage1->Padding = System::Windows::Forms::Padding(3);
			this->tabPage1->Size = System::Drawing::Size(328, 463);
			this->tabPage1->TabIndex = 0;
			this->tabPage1->Text = L"Перевод";
			this->tabPage1->UseVisualStyleBackColor = true;
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->ForeColor = System::Drawing::Color::DarkRed;
			this->label4->Location = System::Drawing::Point(16, 309);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(0, 20);
			this->label4->TabIndex = 8;
			// 
			// tabPage2
			// 
			this->tabPage2->Location = System::Drawing::Point(4, 29);
			this->tabPage2->Name = L"tabPage2";
			this->tabPage2->Padding = System::Windows::Forms::Padding(3);
			this->tabPage2->Size = System::Drawing::Size(328, 463);
			this->tabPage2->TabIndex = 1;
			this->tabPage2->Text = L"Удалить";
			this->tabPage2->UseVisualStyleBackColor = true;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(363, 517);
			this->Controls->Add(this->tabControl1);
			this->Name = L"MyForm";
			this->Text = L"Англо-Русский словарь";
			this->FormClosing += gcnew System::Windows::Forms::FormClosingEventHandler(this, &MyForm::MyForm_FormClosing);
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			this->groupBox1->ResumeLayout(false);
			this->groupBox1->PerformLayout();
			this->tabControl1->ResumeLayout(false);
			this->tabPage1->ResumeLayout(false);
			this->tabPage1->PerformLayout();
			this->ResumeLayout(false);

		}
#pragma endregion

	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
		dict = new Trie;
		Words = new vector<pair<string, string>>;
		SortedWords = new vector<pair<string, string>>;
		ifstream file("DataBase.txt");
		char row[256];
		while (!file.eof())
		{
			file.getline(row, 256);
			string str(row);
			string first = "", second = "";
			for (int i = 0; i < str.length() && str[i] != ' '; i++)
			{
				first += str[i];
			}
			for (int i = str.length() - 1; i >= 0 && str[i] != '—'; i--)
			{
				second += str[i];
			}
			reverse(second.begin(), second.end());
			dict->add(first, second);
			Words->push_back(std::make_pair(first,second));
			SortedWords->push_back(std::make_pair(first, second));
		}
		radioButton2->Checked = true;
	}


private: System::Void radioButton1_Click(System::Object^  sender, System::EventArgs^  e) {
	if (FuzzySearch)
	{
		radioButton1->Checked = false;
		FuzzySearch = false;
		return;
	}
	else
	{
		radioButton1->Checked = true;
		FuzzySearch = true;
		return;
	}
}
private: System::Void radioButton2_MouseClick(System::Object^  sender, System::Windows::Forms::MouseEventArgs^  e) {
	if(!SearchInTrie)
	{
		SearchInTrie = true;
		LinearSearch = false;
		BinarySearch = false;
		return;
	}
}
private: System::Void radioButton3_MouseClick(System::Object^  sender, System::Windows::Forms::MouseEventArgs^  e) {
	if (!LinearSearch)
	{
		LinearSearch = true;
		SearchInTrie = false;
		BinarySearch = false;
		return;
	}
}
private: System::Void radioButton4_MouseClick(System::Object^  sender, System::Windows::Forms::MouseEventArgs^  e) {
	if (!BinarySearch)
	{
		BinarySearch = true;
		SearchInTrie = false;
		LinearSearch = false;
		return;
	}
}
private: System::Void button1_Click(System::Object^  sender, System::EventArgs^  e) {
	if (textBox1->Text == "") { return; }
	string word = "";
	for (int i = 0; i < textBox1->Text->Length; i++)
	{
		word += textBox1->Text[i];
	}
	if (FuzzySearch)
	{
		double start_time = clock();
		string MayBeNeeded = dict->KeySearchLevenshteinDistance(word);
		if (MayBeNeeded == "") { return; }
		int n = LevenshteinDistance(MayBeNeeded, word);
		if (n != 0)
		{
			label4->Text = "Возможно, вы имели в виду: " + gcnew String(MayBeNeeded.c_str());
		}
		double end_time = clock();
		if (!th)
		{
			th = gcnew Thread(gcnew ThreadStart(this, &MyForm::Invite));
			th->Start();
		}
		else
		{
			th->Abort();
			th = gcnew Thread(gcnew ThreadStart(this, &MyForm::Invite));
			th->Start();
		}

		label3->Text = "Время поиска: " + gcnew String(to_string((double)(end_time - start_time) / CLOCKS_PER_SEC).c_str()) + " sec";
	}
	return;
	if (SearchInTrie)
	{
		label1->Text = "Английское слово: " + textBox1->Text;
		string translate_word = "";
		double start_time = clock();
		translate_word = dict->KeySearch(word);
		double end_time = clock();
		label2->Text = "Его перевод: " + gcnew String(translate_word.c_str());
		label3->Text="Время поиска: "+gcnew String(to_string((double)(end_time - start_time) / CLOCKS_PER_SEC).c_str()) + " sec";
	}
	else if (LinearSearch)
	{
		bool flag = false;
		string translate_word = "";
		double start_time = clock();
		for (int i = 0; i < Words->size()&&!flag; i++)
		{
			if (Words->at(i).first == word)
			{
				translate_word = Words->at(i).second;
				flag = true;
			}
		}
		double end_time = clock();
		label1->Text = "Английское слово: " + textBox1->Text;
		label2->Text = "Его перевод: " + gcnew String(translate_word.c_str());
		label3->Text = "Время поиска: " + gcnew String(to_string((double)(end_time - start_time) / CLOCKS_PER_SEC).c_str()) + " sec";
	}
	else
	{

	}
	label4->Text = "";
}
		 delegate void _Invite();
		 void clearLabel4()
		 {
			 label4->Text = "";
		 }
		 void Invite()
		 {
			 Thread::Sleep(4000);
			 this->Invoke(gcnew _Invite(this, &MyForm::clearLabel4));
		 }
private: System::Void MyForm_FormClosing(System::Object^  sender, System::Windows::Forms::FormClosingEventArgs^  e) {
	if (th)
	{
		th->Abort();
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
};
};
