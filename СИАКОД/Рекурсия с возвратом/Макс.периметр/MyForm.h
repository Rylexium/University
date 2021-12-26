#pragma once
#include<vector>
#include<ctime>
#include <algorithm>
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
	struct point
	{
		point operator =(point& p)
		{
			this->x = p.x;
			this->y = p.y;
			return *this;
		}
		bool operator ==(point&p)
		{
			return (this->x == p.x&&this->y == p.y);
		}
		point() {}
		point(int x, int y)
		{
			this->x = x;
			this->y = y;
		}
		int x = 0;
		int y = 0;
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
	private:
			 vector<point>*st;
			 vector<point>*ArrPoints;
			 Bitmap^ bmp;
			 Graphics^ g;
			 Thread ^thread;
			 float MAX=0;
			 int N = 0;
	private: System::Windows::Forms::PictureBox^  pictureBox1;
	private: System::Windows::Forms::Button^  button1;
	private: System::Windows::Forms::Label^  label1;

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
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->label1 = (gcnew System::Windows::Forms::Label());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->BeginInit();
			this->SuspendLayout();
			// 
			// pictureBox1
			// 
			this->pictureBox1->Location = System::Drawing::Point(13, 13);
			this->pictureBox1->Name = L"pictureBox1";
			this->pictureBox1->Size = System::Drawing::Size(680, 461);
			this->pictureBox1->TabIndex = 0;
			this->pictureBox1->TabStop = false;
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(700, 13);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(93, 62);
			this->button1->TabIndex = 1;
			this->button1->Text = L"Сгенерировать";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->Click += gcnew System::EventHandler(this, &MyForm::button1_Click);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(699, 92);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(64, 13);
			this->label1->TabIndex = 2;
			this->label1->Text = L"Периметр: ";
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(805, 486);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->button1);
			this->Controls->Add(this->pictureBox1);
			this->Name = L"MyForm";
			this->Text = L"Рекурсия с возвратом";
			this->FormClosing += gcnew System::Windows::Forms::FormClosingEventHandler(this, &MyForm::MyForm_FormClosing);
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
		bmp = gcnew Bitmap(pictureBox1->Width, pictureBox1->Height);
		g = Graphics::FromImage(bmp);
		st = new vector<point>;
		ArrPoints = new vector<point>;
	}
	private:int random(int a, int b)
	{
		srand(time(NULL));
		if (a > 0) return a + rand() % (b - a);
		else return a + rand() % (abs(a) + b);
	}
	public:void otrisovka(Bitmap ^t)
	{
		pictureBox1->Image = t;
	}
		   delegate void del_otrisovka(Bitmap ^t);
		   delegate void enblButt(Button ^butt,bool enbld);
		   delegate void prntLab();
		   public:void prntlabel()
		   {
			   label1->Text = "Периметр:" + Convert::ToString(MAX);
		   }
	public:void enblBut(Button ^butt,bool enbld)
	{
		butt->Enabled = enbld;
	}
	private: System::Void button1_Click(System::Object^  sender, System::EventArgs^  e) {
		this->Invoke(gcnew enblButt(this, &MyForm::enblBut), button1, false);
		thread = gcnew Thread(gcnew ThreadStart(this, &MyForm::rachet));
		thread->Start();
	}
	private:void rachet()
	{
		MAX = 0;
		srand(time(NULL));
		g->Clear(Color::White);
		N = random(3,8);
		for (int i = 0; i < N; i++)
		{
			int x =  rand() % 650;
			int y =  rand() % 430;
			g->FillEllipse(Brushes::Black, x,y , 12, 12);
			ArrPoints->push_back(point(x + 6, y + 6));
		}

		Backtracking(0);

		for (int i = 1; i <N+1; i++)
		{
			g->DrawLine(Pens::Black, st->at(i - 1).x, st->at(i - 1).y, st->at(i).x, st->at(i).y);
		}
		ArrPoints->clear();
		st->clear();
		this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
		this->Invoke(gcnew enblButt(this, &MyForm::enblBut),button1,true);
		this->Invoke(gcnew prntLab(this, &MyForm::prntlabel));
	}
	private: bool twins(vector<point> A) { //чтобы убрать перестановки с одинаковыми элементами
		int U = 0;
		for (int i = 0; i < N + 1; i++)
		{
			for (int k = 1; (k + i < N + 1); k++)
			{
				if ((A[i] == A[i + k]) && (A[i] == A[i + k]))
				{
					U += 1;
				}
			}
		}
		if (U > 1) return true;//потому что первый и последний элемент я сделал одинаковыми
		else return false;
	}

	private:bool cross(point a, point b, point c, point d) { //пересечения
		float dx1 = b.x - a.x;
		float dy1 = b.y - a.y;
		float dx2 = d.x - c.x;
		float dy2 = d.y - c.y;

		float a1 = -dy1;
		float b1 = +dx1;
		float d1 = -(a1*a.x + b1 * a.y);

		float a2 = -dy2;
		float b2 = +dx2;
		float d2 = -(a2*c.x + b2 * c.y);

		float seg1_st = a2 * a.x + b2 * a.y + d2;
		float seg1_end = a2 * b.x + b2 * b.y + d2;
		float seg2_st = a1 * c.x + b1 * c.y + d1;
		float seg2_end = a1 * d.x + b1 * d.y + d1;

		if ((((c.x - a.x)*dy1 - (c.y - a.y)*dx1) == 0) && (c.x <= max(a.x, b.x))
			&& (c.x >= min(a.x, b.x)) && (c.y <= max(a.y, b.y)) && (c.y >= min(a.y, b.y))) return true;
		if ((((d.x - a.x)*dy1 - (d.y - a.y)*dx1) == 0) && (d.x <= max(a.x, b.x))
			&& (d.x >= min(a.x, b.x)) && (d.y <= max(a.y, b.y)) && (d.y >= min(a.y, b.y))) return true;
		if ((((a.x - c.x)*dy2 - (a.y - c.y)*dx2) == 0) && (a.x <= max(c.x, d.x))
			&& (a.x >= min(c.x, d.x)) && (a.y <= max(c.y, d.y)) && (a.y >= min(c.y, d.y))) return true;
		if ((((b.x - c.x)*dy2 - (b.y - c.y)*dx2) == 0) && (b.x <= max(c.x, d.x))
			&& (b.x >= min(c.x, d.x)) && (b.y <= max(c.y, d.y)) && (b.y >= min(c.y, d.y))) return true;

		if (seg1_st*seg1_end >= 0 || seg2_st * seg2_end >= 0) return false;

		return true;
	}

	private:		 double Per(vector<point> A) { //периметр

		double P = 0;
		for (int i = 0; i < N; i++)
			P += sqrt(pow((A[i + 1].x - A[i].x), 2) + pow((A[i + 1].y - A[i].y), 2));
		return P;
	}

	private:	 void Backtracking(int k) {
		static vector<point> A;
		A.resize(N+1);
		if (k == 0)
		{
			A[0] = ArrPoints->at(0);
			Backtracking(k + 1);
		}
		else if (k == N) {
			A[N] = ArrPoints->at(0);
			int a = 0;
			for (int i = 0, j = 1; (i < N + 1 && j < N + 1); i += 1, j += 1) {
				for (int k = 2; (k + i < N + 1 && k + j < N + 1); k += 1) 
				{
					if ((cross(A[i], A[j], A[i + k], A[j + k])) == 1) a += 1;
				}
				if (i == 0) a -= 1;
			}

			if (twins(A) == 1) a += 1;

			if (a == 0)
			{
				float tmp = Per(A);
				if (MAX <= tmp)
				{
					st->clear();
					MAX = tmp;
					//Вывод линий
					for (int i = 0; i <N + 1; i++)
					{
						st->push_back(A[i]);
					}
				}
			}
		}
		else {
			for (int i = 1; i < N; i++) 
			{
				A[k] = ArrPoints->at(i);
				Backtracking(k + 1);
			}
		}
	}
	private: System::Void MyForm_FormClosing(System::Object^  sender, System::Windows::Forms::FormClosingEventArgs^  e) {
		if (thread)
		{
			thread->Abort();
		}
	}
};
};
