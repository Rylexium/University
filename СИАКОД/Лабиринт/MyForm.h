#pragma once
#include"Stack.h"
#include<vector>
#include<ctime>
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
	struct Cell
	{
		char x;
		char y;
		bool Visited = false;
	};
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
	private: System::Windows::Forms::PictureBox^  pictureBox1;
			 Bitmap^ bmp;
			 Graphics^ g;
			 int step,n;
			 Thread ^thread;
			 bool statusThread;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::TextBox^  textBox1;
	private: System::Windows::Forms::Button^  button1;
	private: System::Windows::Forms::TextBox^  textBox2;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::Button^  button2;

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
			this->pictureBox1 = (gcnew System::Windows::Forms::PictureBox());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->textBox2 = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->button2 = (gcnew System::Windows::Forms::Button());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->BeginInit();
			this->SuspendLayout();
			// 
			// pictureBox1
			// 
			this->pictureBox1->Location = System::Drawing::Point(12, 3);
			this->pictureBox1->Name = L"pictureBox1";
			this->pictureBox1->Size = System::Drawing::Size(575, 443);
			this->pictureBox1->TabIndex = 0;
			this->pictureBox1->TabStop = false;
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(593, 16);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(119, 26);
			this->label1->TabIndex = 1;
			this->label1->Text = L"Введите размерность\nлабиринта";
			// 
			// textBox1
			// 
			this->textBox1->Location = System::Drawing::Point(718, 16);
			this->textBox1->Name = L"textBox1";
			this->textBox1->Size = System::Drawing::Size(52, 20);
			this->textBox1->TabIndex = 2;
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(593, 71);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(172, 23);
			this->button1->TabIndex = 3;
			this->button1->Text = L"Выполнить";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->Click += gcnew System::EventHandler(this, &MyForm::button1_Click);
			// 
			// textBox2
			// 
			this->textBox2->Location = System::Drawing::Point(657, 45);
			this->textBox2->Name = L"textBox2";
			this->textBox2->Size = System::Drawing::Size(111, 20);
			this->textBox2->TabIndex = 4;
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(593, 48);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(58, 13);
			this->label2->TabIndex = 5;
			this->label2->Text = L"Задержка";
			// 
			// button2
			// 
			this->button2->Location = System::Drawing::Point(593, 100);
			this->button2->Name = L"button2";
			this->button2->Size = System::Drawing::Size(172, 24);
			this->button2->TabIndex = 6;
			this->button2->Text = L"Стоп";
			this->button2->UseVisualStyleBackColor = true;
			this->button2->Click += gcnew System::EventHandler(this, &MyForm::button2_Click);
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(780, 458);
			this->Controls->Add(this->button2);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->textBox2);
			this->Controls->Add(this->button1);
			this->Controls->Add(this->textBox1);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->pictureBox1);
			this->Name = L"MyForm";
			this->Text = L"Лабиринт";
			this->FormClosed += gcnew System::Windows::Forms::FormClosedEventHandler(this, &MyForm::MyForm_FormClosed);
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
		bmp = gcnew Bitmap(pictureBox1->Width, pictureBox1->Height);
		g = Graphics::FromImage(bmp);
		step = 50;
		n = 10;
		button2->Enabled = false;
		labirint();
	}
	public: void labirint()
	{
		g->Clear(Color::White);
		for (int i = 0; i <= step * n; i += step) g->DrawLine(Pens::Black, i, 0, i, step*n);
		for (int i = 0; i <= step * n; i += step)  g->DrawLine(Pens::Black, 0, i, step*n, i);
		this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
	}
	public:void otrisovka(Bitmap ^t)
	{
		pictureBox1->Image = t;
	}
		   delegate void del_otrisovka(Bitmap ^t);
		   delegate void ButtEnbld(Button^t,bool status);
	public:void ButtonEnbld(Button ^t,bool status)
	{
		t->Enabled = status;
	}
	public:void algoritm()
	{
		srand(time(NULL));
		int sleep = 100;
		if (textBox2->Text != "") 
		{
			try 
			{
				sleep = Convert::ToInt16(textBox2->Text);
			}
			catch (...)
			{
				MessageBox::Show("Проверьте входные данные");
				button1->Enabled = true;
				return;
			}
		}
		int height, width;
		width=height = n;
		Cell **labyrinth = new Cell*[n];//создаю логический массив
		for (int i = 0; i < n; i++) 
		{
			labyrinth[i]= new Cell[n]; 
		}
		//заполняем начальные данные для ячеек
		for (int y = 0; y < n; y++) 
		{
			for (int x = 0; x < n; x++)
			{
				labyrinth[x][y].x = x;
				labyrinth[x][y].y = y;
			}
		}
		for (int i = 1; i < n*n*1/2; i++)//заполнили преграды
		{
			int dx = rand() % n;
			int dy = rand() % n;
			labyrinth[dx][dy].Visited = true;
			g->FillRectangle(Brushes::Black, step * dx, step * dy, step , step);
		}
		//Выбираем первую ячейку откуда начнем движение

		int startX=rand()%n;
		int startY=rand()%n;
		while (labyrinth[startX][startY].Visited == true)
		{
			startX = rand() % n;
			startY = rand() % n;
		}

		labyrinth[startX][startY].Visited = true;
		Thread::Sleep(sleep);
		g->FillRectangle(Brushes::Red, step * startX+step*1/3, step * startY+step*1/3, step * 1 / 3, step * 1 / 3);
		this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);

		//Заносим нашу ячейке в path и начинаем строить путь
		stack<Cell>path;
		path.push(labyrinth[startX][startY]);

		while (path.IsEmpty())
		{
			Cell _cell = path.top();//текущая обрабатываемая ячейка

			//смотрим варианты, в какую сторону можно пойти
			vector<Cell> nextStep;
			if (_cell.x > 0 && (labyrinth[_cell.x - 1][_cell.y].Visited == false) )
				nextStep.push_back(labyrinth[_cell.x - 1][_cell.y]);
			if (_cell.x < width - 1 && (labyrinth[_cell.x + 1][_cell.y].Visited == false) )
				nextStep.push_back(labyrinth[_cell.x + 1][_cell.y]);
			if (_cell.y > 0 && (labyrinth[_cell.x][_cell.y - 1].Visited == false) )
				nextStep.push_back(labyrinth[_cell.x][_cell.y - 1]);
			if (_cell.y < height - 1 && (labyrinth[_cell.x][_cell.y + 1].Visited == false) )
				nextStep.push_back(labyrinth[_cell.x][_cell.y + 1]);

			if (!nextStep.empty())
			{
				//выбираем сторону из возможных вариантов
				Cell next = nextStep[rand() % nextStep.size()];
				labyrinth[next.x][next.y].Visited = true;//помечаем как посещённую 
				Thread::Sleep(sleep);
				g->FillRectangle(Brushes::Red, step * next.x+step*1/3, step * next.y+step*1/3, step*1/3, step*1/3);
				this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
				path.push(next);//кидаем в стек,вдруг предется вернуться
			}
			else
			{
				//если пойти никуда нельзя, возвращаемся к предыдущему узлу
				path.pop();
			}
		}
		MessageBox::Show("Я закончил!!!");
		this->Invoke(gcnew ButtEnbld(this, &MyForm::ButtonEnbld), button2, false);
		this->Invoke(gcnew ButtEnbld(this, &MyForm::ButtonEnbld), button1, true);
	}
private: System::Void MyForm_FormClosed(System::Object^  sender, System::Windows::Forms::FormClosedEventArgs^  e) {
	if (thread)
	{
		thread->Abort();
	}
}
private: System::Void button1_Click(System::Object^  sender, System::EventArgs^  e) {
	if (textBox1->Text != "")
	{
		try
		{
			n = Convert::ToInt16(textBox1->Text);
			if (n > 25) { MessageBox::Show("Не может быть больше 25"); return; }
			else if (n <= 1) { MessageBox::Show("Нельзя брать число меньше или равное 1!!!"); return; }
			step = pictureBox1->Height / n;
			labirint();
			button1->Enabled = false;
			button2->Enabled = true;
			thread = gcnew Thread(gcnew ThreadStart(this, &MyForm::algoritm));
			statusThread = true;
			thread->Start();
		}
		catch (...)
		{
			button1->Enabled = true;
			MessageBox::Show("Должно быть число!!!");
		}
	}
}
private: System::Void button2_Click(System::Object^  sender, System::EventArgs^  e) {
	if (thread) 
	{
		if (statusThread)
		{
			thread->Suspend();
			button2->Enabled = true;
			statusThread = false;
		}
		else
		{
			thread->Resume();
			statusThread = true;
		}
	}
}
};
};
