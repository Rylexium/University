#pragma once
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
			this->KeyPreview = true;
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
			 int pW ,pH;
			 int depth = 0;
	private: System::Windows::Forms::TextBox^  textBox1;
	private: System::Windows::Forms::Label^  label1;
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
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->label1 = (gcnew System::Windows::Forms::Label());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->BeginInit();
			this->SuspendLayout();
			// 
			// pictureBox1
			// 
			this->pictureBox1->Location = System::Drawing::Point(12, 2);
			this->pictureBox1->Name = L"pictureBox1";
			this->pictureBox1->Size = System::Drawing::Size(594, 446);
			this->pictureBox1->TabIndex = 0;
			this->pictureBox1->TabStop = false;
			// 
			// textBox1
			// 
			this->textBox1->Location = System::Drawing::Point(612, 38);
			this->textBox1->Name = L"textBox1";
			this->textBox1->Size = System::Drawing::Size(67, 20);
			this->textBox1->TabIndex = 1;
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(625, 9);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(54, 26);
			this->label1->TabIndex = 2;
			this->label1->Text = L"Глубина\nрекурсии";
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(691, 460);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->textBox1);
			this->Controls->Add(this->pictureBox1);
			this->Name = L"MyForm";
			this->Text = L"Рекурсия";
			this->FormClosing += gcnew System::Windows::Forms::FormClosingEventHandler(this, &MyForm::MyForm_FormClosing);
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			this->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &MyForm::MyForm_KeyDown);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->pictureBox1))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {
		bmp = gcnew Bitmap(pictureBox1->Width, pictureBox1->Height);
		g = Graphics::FromImage(bmp);
		pW = pictureBox1->Width / 2;
		pH = pictureBox1->Height / 2;
	}
			 delegate void del_otrisovka(Bitmap ^t);
	public:void otrisovka(Bitmap ^t)
	{
		pictureBox1->Image = t;
	}
	private:int* LineCoord(int angleIn, int radius, int centerX,int centerY)
	{
		int *coord = new int[2];
		angleIn %= 360;
		angleIn *= 1;
		if (angleIn > 0 && angleIn <= 180)
		{
			coord[0] = centerX + (int)(radius*Math::Sin(Math::PI*angleIn / 180));
			coord[1] = centerY - (int)(radius*Math::Cos(Math::PI*angleIn / 180));
		}
		else
		{
			coord[0] = centerX - (int)(radius*-Math::Sin(Math::PI*angleIn / 180));
			coord[1] = centerY - (int)(radius*Math::Cos(Math::PI*angleIn / 180));
		}
		return coord;
	}
	private: void recursion_r(int d, int count, int a)
	{
		if (count) 
		{
			array<Point>^points = gcnew array<Point>(5);
			for (int i = 0, angle = 0; i < 5; i++, angle += 90)
			{
				int *arp = LineCoord(a + angle, d, pW, pH);
				points[i] = Point(arp[0], arp[1]);
				delete arp;
			}
			points[4] = points[0];
			for (int i = 0; i < 4; i++) 
			{
				g->DrawLine(Pens::Black, points[i], points[i+1]);
			}
			this->Invoke(gcnew del_otrisovka(this, &MyForm::otrisovka), bmp);
			recursion_r(d +=(-a) / 10+d/(-a)/90, count - 1, a - 4);
		}
	}
	private:void recursion(int count)
	{
		g->Clear(Color::White);
		recursion_r(10, count-1,-10);
	}
	private: System::Void MyForm_FormClosing(System::Object^  sender, System::Windows::Forms::FormClosingEventArgs^  e) {
	}
private: System::Void MyForm_KeyDown(System::Object^  sender, System::Windows::Forms::KeyEventArgs^  e) {
	if (e->KeyCode==Keys::Enter)
	{
		if (textBox1->Text != "")
		{
			try
			{
				depth = Convert::ToInt16(textBox1->Text);
				if (depth > 100)
				{
					MessageBox::Show("Слишком большая глубина рекурсии!!!");
					return;
				}
			}
			catch (...)
			{
				MessageBox::Show("Проверьте исходные данные", "Внимание");
				return;
			}
			recursion(depth);
		}
	}
}
};
}