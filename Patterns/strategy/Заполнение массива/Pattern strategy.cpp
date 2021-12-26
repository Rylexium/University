#include <iostream>
#include <ctime>
#include <fstream>
using namespace std;

class ArrayInt;
class Strategy {
public:
	virtual void execute(ArrayInt&) = 0;
	virtual ~Strategy() {};
};
class StrategyFillFromFile;
class StrategyFillDefaultValue;
class StrategyFillRandomValues;

class ArrayInt {
public:
	ArrayInt(int length) {
		lenght_ = length;
		arr = new int[lenght_];
	}
	ArrayInt(int length, Strategy* strategy) {
		lenght_ = length;
		arr = new int[length];
		strategy_ = strategy;
	}
	ArrayInt& fillArray() {
		strategy_->execute(*this);
		return *this;
	}
	int size() { return lenght_; }
	void print() {
		for (int i = 0; i < lenght_; i++)
			cout << arr[i] << " ";
		cout << endl;
	}
	int& operator[](int i) {
		return arr[i];
	}
	void setItem(int index, int value) {
		arr[index] = value;
	}
	ArrayInt& setStrategy(Strategy* strategy) {
		delete strategy_;
		strategy_ = strategy;
		return *this;
	}
	~ArrayInt() {
		delete[] arr;
		delete strategy_;
	}
private:
	Strategy* strategy_ = nullptr;
	int lenght_ = 0;
	int* arr = nullptr;
};


class StrategyFillFromFile : public Strategy {
public:
	StrategyFillFromFile(std::string path) : path_(path) {};
	void execute(ArrayInt& arr) override {
		ifstream file(path_.c_str());
		if (!file.is_open())return;
		for (int i = 0; !file.eof(); i++) {
			int elem;
			file >> elem;
			arr.setItem(i, elem);
		}
	};
private:string path_;
};
class StrategyFillDefaultValue : public Strategy {
public:
	StrategyFillDefaultValue() {};
	StrategyFillDefaultValue(int default_value) :default_value_(default_value) {};
	void execute(ArrayInt& arr) override {
		for (int i = 0; i < arr.size(); i++) 
			arr.setItem(i, default_value_);
	};
private: int default_value_ = 0;
};
class StrategyFillRandomValues : public Strategy {
public:
	StrategyFillRandomValues() {};
	StrategyFillRandomValues(int RandMax) {
		if (RandMax != 0)RandMax_ = RandMax;
	};
	void execute(ArrayInt& arr) override {
		srand(time(0));
		for (int i = 0; i < arr.size(); i++) {
			arr.setItem(i, rand() % RandMax_);
		}
	};
private: int RandMax_ = 2;
};


int main(){
	ArrayInt arr(5); 
	arr.setStrategy(new StrategyFillFromFile("array.txt")).fillArray().print();
	arr.setStrategy(new StrategyFillDefaultValue(0)).fillArray().print();
	arr.setStrategy(new StrategyFillRandomValues(15)).fillArray().print();
}