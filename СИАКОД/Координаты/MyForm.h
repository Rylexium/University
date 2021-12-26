#pragma once
#include <math.h>
namespace Project1 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
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
			this->KeyPreview = true;
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
			 Pen^ pen;
			 Thread^ thread;//pulse shariki
			 int step =40;
			 int mX, mY, pW, pH;
			 bool grafik = false;
			 int right = 0,left=0, down = 0, up = 0;
	private: System::Windows::Forms::PictureBox^  pictureBox2;

	private: System::Windows::Forms::PictureBox^  pictureBox3;
	private: System::Windows::Forms::PictureBox^  pictureBox4;
	private: System::Windows::Forms::PictureBox^  pictureBox5;
	protected:

	protected:

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
			System::ComponentModel::ComponentResourceManager^  resources = (gcnew System::ComponentModel::ComponentResourceManager(MyForm::typeid));
			this->pictureBox1 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox2 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox3 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox4 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox5 = (gcnew System::Windows::Forms::PictureBox());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox2))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox3))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox4))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox5))->BeginInit();
			this->SuspendLayout();
			// 
			// pictureBox1
			// 
			this->pictureBox1->Location = System::Drawing::Point(13, 13);
			this->pictureBox1->Name = L"pictureBox1";
			this->pictureBox1->Size = System::Drawing::Size(1125, 712);
			this->pictureBox1->TabIndex = 0;
			this->pictureBox1->TabStop = false;
			// 
			// pictureBox2
			// 
			this->pictureBox2->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"pictureBox2.Image")));
			this->pictureBox2->Location = System::Drawing::Point(1144, 13);
			this->pictureBox2->Name = L"pictureBox2";
			this->pictureBox2->Size = System::Drawing::Size(92, 43);
			this->pictureBox2->SizeMode = System::Windows::Forms::PictureBoxSizeMode::StretchImage;
			this->pictureBox2->TabIndex = 1;
			this->pictureBox2->TabStop = false;
			this->pictureBox2->Click += gcnew System::EventHandler(this, &MyForm::pictureBox2_Click);
			// 
			// pictureBox3
			// 
			this->pictureBox3->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"pictureBox3.Image")));
			this->pictureBox3->Location = System::Drawing::Point(1144, 62);
			this->pictureBox3->Name = L"pictureBox3";
			this->pictureBox3->Size = System::Drawing::Size(92, 44);
			this->pictureBox3->SizeMode = System::Windows::Forms::PictureBoxSizeMode::StretchImage;
			this->pictureBox3->TabIndex = 3;
			this->pictureBox3->TabStop = false;
			this->pictureBox3->Click += gcnew System::EventHandler(this, &MyForm::pictureBox3_Click);
			// 
			// pictureBox4
			// 
			this->pictureBox4->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"pictureBox4.Image")));
			this->pictureBox4->Location = System::Drawing::Point(1194, 112);
			this->pictureBox4->Name = L"pictureBox4";
			this->pictureBox4->Size = System::Drawing::Size(42, 36);
			this->pictureBox4->SizeMode = System::Windows::Forms::PictureBoxSizeMode::StretchImage;
			this->pictureBox4->TabIndex = 4;
			this->pictureBox4->TabStop = false;
			this->pictureBox4->Click += gcnew System::EventHandler(this, &MyForm::pictureBox4_Click);
			// 
			// pictureBox5
			// 
			this->pictureBox5->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"pictureBox5.Image")));
			this->pictureBox5->Location = System::Drawing::Point(1144, 112);
			this->pictureBox5->Name = L"pictureBox5";
			this->pictureBox5->Size = System::Drawing::Size(43, 36);
			this->pictureBox5->SizeMode = System::Windows::Forms::PictureBoxSizeMode::StretchImage;
			this->pictureBox5->TabIndex = 5;
			this->pictureBox5->TabStop = false;
			this->pictureBox5->Click += gcnew System::EventHandler(this, &MyForm::pictureBox5_Click);
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(1248, 737);
			this->Controls->Add(this->pictureBox5);
			this->Controls->Add(this->pictureBox4);
			this->Controls->Add(this->pictureBox3);
			this->Controls->Add(this->pictureBox2);
			this->Controls->Add(this->pictureBox1);
			this->Name = L"MyForm";
			this->Text = L"График";
			this->FormClosing += gcnew System::Windows::Forms::FormClosingEventHandler(this, &MyForm::MyForm_FormClosing);
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			this->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &MyForm::MyForm_KeyDown);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox2))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox3))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox4))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox5))->EndInit();
			this->ResumeLayout(false);

		}
#pragma endregion

	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
		SystemOfCordinat();
		numbCordinat(0,0);
	}
			 void numbCordinat(int right,int up)
			 {
				 int a = 0;
				 for (int i = mX; i < 10000; i += step)
				 {
					 g->DrawString(Convert::ToString(a), gcnew System::Drawing::Font("Times New Roman", 12), Brushes::Black, i + 2-right, mY + 5-up);
					 ++a;
				 }
				 a = 1;
				 for (int i = mX - step; i > -10000; i -= step)
				 {
					 g->DrawString(Convert::ToString(-a), gcnew System::Drawing::Font("Times New Roman", 12), Brushes::Black, i - 3-right, mY - 20-up);
					 ++a;
				 }
				 a = 1;
				 for (int i = mY + step; i < 10000; i += step)
				 {
					 g->DrawString(Convert::ToString(-a), gcnew System::Drawing::Font("Times New Roman", 12), Brushes::Black, mX - 20-right, i + 3-up);
					 ++a;
				 }
				 a = 1;
				 for (int i = mY - step; i > -10000; i -= step)
				 {
					 g->DrawString(Convert::ToString(a), gcnew System::Drawing::Font("Times New Roman", 12), Brushes::Black, mX + 3-right, i + 3-up);
					 ++a;
				 }
			 }
			 void SystemOfCordinat()
			 {
				 bmp = gcnew Bitmap(pictureBox1->Width, pictureBox1->Height);
				 g = Graphics::FromImage(bmp);
				 pen = gcnew Pen(Color::FromArgb(255, 0, 0, 0));
				 pW = pictureBox1->Width;
				 pH = pictureBox1->Height;
				 //рисуем сетку:
				 g->Clear(Color::White);
				 for (int i = 0; i < pW; i += step) g->DrawLine(Pens::Blue, i, 0, i, pH);
				 for (int i = 0; i < pH; i += step)  g->DrawLine(Pens::Blue, 0, i, pW, i);
				 //находим середину и рисуем линии осей:
				 mX =int( pW / 2 - pW / 2 % step);
				 mY =int(pH / 2 - pH / 2 % step);
				 g->DrawLine(Pens::Red, mX , 0, mX, pH);
				 g->DrawLine(Pens::Red, mX + 1  , 0, mX + 1 , pH);
				 g->DrawLine(Pens::Red, mX - 1  , 0, mX - 1, pH);
				 g->DrawLine(Pens::Red, 0, mY , pW, mY );
				 g->DrawLine(Pens::Red, 0, mY + 1 , pW, mY + 1);
				 g->DrawLine(Pens::Red, 0, mY - 1, pW, mY - 1);
				 this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
			 }
			 delegate void del_otrisovka(Bitmap ^t);
	public:void otrisovka(Bitmap ^t)
	{
		pictureBox1->Image = t;
	}
private: System::Void MyForm_FormClosing(System::Object^  sender, System::Windows::Forms::FormClosingEventArgs^  e) {
	if (thread) { thread->Abort(); }
}
	private: System::Void pictureBox2_Click(System::Object^  sender, System::EventArgs^  e) {
		if (!grafik)
		{
			graf(right, up);
		}
	}
			 void graf(int right,int up)
			 {
				 int x, y, x1, y1;
				 grafik = true;
				 for (int i = 0; i < 1000; i++)
				 {
					 x = i;
					 y = x * x / step;
					 x1 = x + 1;
					 y1 = x1 * x1 / step;
					 g->DrawLine(pen, mX - x-right,  mY - y - up,  mX - x1 - right,  mY - y1 - up);
					 g->DrawLine(pen, mX + x - right, mY - y  - up, mX + x1 - right , mY - y1 - up);

					 g->DrawLine(pen, mX - x - right+1, mY - y  - up, mX - x1 - right+1, mY - y1 - up);
					 g->DrawLine(pen, mX + x - right+1, mY - y  - up, mX + x1 - right+1, mY - y1 - up);
					 this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);

				 }
			 }
private: System::Void pictureBox3_Click(System::Object^  sender, System::EventArgs^  e) {
	g->Clear(Color::White);
	step = 50;
	left = right = up = down = 0;
	SystemOfCordinat();
	numbCordinat(0,0);
	grafik = false;
}
private: System::Void pictureBox5_Click(System::Object^  sender, System::EventArgs^  e) {
	step -= 10;
	if (step <= 0) { MessageBox::Show("Размер клетки не может быть ниже 0!!!","Внимание"); step = 10; }
	g->Clear(Color::White);
	left = right = up = down = 0;
	SystemOfCordinat();
	numbCordinat(0,0);
	if (grafik) { graf(right, up); }
}
private: System::Void pictureBox4_Click(System::Object^  sender, System::EventArgs^  e) {
	step += 10;
	if(step>100){ MessageBox::Show("Woww!!!\nОстановись с уменьшением масштаба", "Внимание"); step = 100; }
	g->Clear(Color::White);
	left = right = up = down = 0;
	SystemOfCordinat();
	numbCordinat(0, 0);
	if (grafik) { graf(right, up); }
}
	private: System::Void MyForm_KeyDown(System::Object^  sender, System::Windows::Forms::KeyEventArgs^  e) {
		if (e->KeyCode == Keys::Escape)
		{
			this->Close();
		}
		if (e->KeyCode == Keys::Right)
		{
			right += step;
			left -= step;
			g->Clear(Color::White);
			for (int i = 0; i < pW; i += step) g->DrawLine(Pens::Blue, i , 0, i, pH);
			for (int i = 0; i < pH; i += step)  g->DrawLine(Pens::Blue, 0, i, pW, i);
			g->DrawLine(Pens::Red, mX - right, 0, mX - right, pH);
			g->DrawLine(Pens::Red, mX + 1 - right, 0, mX + 1 - right, pH);
			g->DrawLine(Pens::Red, mX - 1 - right, 0, mX - 1 - right, pH);
			g->DrawLine(Pens::Red, 0, mY-up, pW, mY-up);
			g->DrawLine(Pens::Red, 0, mY + 1-up, pW, mY + 1-up);
			g->DrawLine(Pens::Red, 0, mY - 1-up, pW, mY - 1-up);
			numbCordinat(right,up);
			if (grafik) { graf(right, up); }
			this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
		}
		if (e->KeyCode == Keys::Left)
		{
			right -= step;
			left += step;
			g->Clear(Color::White);
			for (int i = 0; i < pW; i += step) g->DrawLine(Pens::Blue, i, 0, i , pH);
			for (int i = 0; i < pH; i += step)  g->DrawLine(Pens::Blue,0, i, pW, i);
			g->DrawLine(Pens::Red, mX +left, 0, mX + left, pH);
			g->DrawLine(Pens::Red, mX + 1 + left, 0, mX + 1 + left, pH);
			g->DrawLine(Pens::Red, mX - 1 + left, 0, mX - 1 + left, pH);
			g->DrawLine(Pens::Red, 0, mY-up, pW, mY-up);
			g->DrawLine(Pens::Red, 0, mY + 1-up, pW, mY + 1-up);
			g->DrawLine(Pens::Red, 0, mY - 1-up, pW, mY - 1-up);
			if (grafik) { graf(right, up); }
			numbCordinat(right, up);
			this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
		}
		if (e->KeyCode == Keys::Down)
		{
			up += step;
			down -= step;
			g->Clear(Color::White);
			for (int i = 0; i < pW; i += step) g->DrawLine(Pens::Blue, i, 0, i, pH);
			for (int i = 0; i < pH; i += step)  g->DrawLine(Pens::Blue, 0, i, pW, i);
			g->DrawLine(Pens::Red, mX-right , 0, mX-right, pH);
			g->DrawLine(Pens::Red, mX + 1-right, 0, mX + 1-right, pH);
			g->DrawLine(Pens::Red, mX - 1-right, 0, mX - 1-right, pH);
			g->DrawLine(Pens::Red, 0, mY+down, pW, mY + down);
			g->DrawLine(Pens::Red, 0, mY + 1 + down, pW, mY + 1 + down);
			g->DrawLine(Pens::Red, 0, mY - 1 + down, pW, mY - 1 + down);
			if (grafik) { graf(right, up); }
			numbCordinat(right, up);
			this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
		}
		if (e->KeyCode == Keys::Up)
		{
			up -= step;
			down += step;
			g->Clear(Color::White);
			for (int i = 0; i < pW; i += step) g->DrawLine(Pens::Blue, i, 0, i, pH);
			for (int i = 0; i < pH; i += step)  g->DrawLine(Pens::Blue, 0, i, pW, i);
			g->DrawLine(Pens::Red, mX-right, 0, mX-right, pH);
			g->DrawLine(Pens::Red, mX + 1-right, 0, mX + 1-right, pH);
			g->DrawLine(Pens::Red, mX - 1-right, 0, mX - 1-right, pH);
			g->DrawLine(Pens::Red, 0, mY - up, pW, mY - up);
			g->DrawLine(Pens::Red, 0, mY + 1-up, pW, mY + 1 - up);
			g->DrawLine(Pens::Red, 0, mY - 1 - up, pW, mY - 1 - up);
			if (grafik) { graf(right,up); }
			numbCordinat(right, up);
			this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
		}
}
};
}
