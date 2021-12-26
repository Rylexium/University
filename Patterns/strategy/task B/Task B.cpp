#include <iostream>
#include <vector>
using namespace std;

struct ParamsOfGame { //структура параметры игры
	ParamsOfGame(long long h, long long k, long long n, vector<long long> d, vector<long long> r) {
		_h = h;
		_k = k;
		_n = n;
		_d = d;
		_r = r;
	}
	long long _h, _k, _n;
	vector<long long> _d, _r;
};

struct Dragon;
class Strategy { //абстрактный класс стратегия
public:
	virtual void execute(Dragon&, ParamsOfGame&) = 0;
	virtual ~Strategy() {};
};
struct Dragon {
public:
	long long health_dragon;//здоровье дракона
	long long reward;//сумма наград
	long long count_of_dragon;//количество побежденных драконов
	long long level;//текущий уровень
	Dragon(long long h) {
		_strategy = nullptr;
		health_dragon = h;//здоровье дракона
		reward = 0;//сумма наград
		count_of_dragon = 0;//количество побежденных драконов
		level = 0;//текущий уровень
	}
	void setStrategy(Strategy* strategy) {
		_strategy = strategy;
	}
	void go(ParamsOfGame& params) {
		_strategy->execute(*this, params);
	}
private:
	Strategy* _strategy; //текущая стратегия
};

class StrategyOfCoward : public Strategy {//стратегия трус
public:
	void execute(Dragon& dragon, ParamsOfGame& params) override {
		for (long long i = 0; i < params._n; i++) {
			dragon.health_dragon -= long long(params._d[i] / 2);
			if (dragon.health_dragon > 0) {
				dragon.count_of_dragon += 1;
				dragon.reward += long long(params._r[i] / 2);
			}
			if (dragon.health_dragon < 0)break;
			if (dragon.reward >= params._k) {
				dragon.level += long long(dragon.reward / params._k);
				dragon.health_dragon = params._h;
				dragon.reward = dragon.reward % params._k;
			}
		}
	}
};

class StrategyOfExperienced : public Strategy {
public:
	void execute(Dragon& dragon, ParamsOfGame& params) override {
		for (long long i = 0; i < params._n; i++) {
			dragon.health_dragon -= params._d[i];
			if (dragon.health_dragon > 0) {
				dragon.count_of_dragon += 1;
				dragon.reward += params._r[i];
			}
			if (dragon.health_dragon < 0)break;
			if (dragon.reward >= params._k) {
				dragon.level += long long(dragon.reward / params._k);
				dragon.health_dragon = params._h;
				dragon.reward = dragon.reward % params._k;
			}
		}
	}
};

class StrategyOfFool :public Strategy {
public:
	void execute(Dragon& dragon, ParamsOfGame& params) override {
		for (long long i = 0; i < params._n; i++) {
			dragon.health_dragon -= 2 * params._d[i];
			if (dragon.health_dragon > 0) {
				dragon.count_of_dragon += 1;
				dragon.reward += 2 * params._r[i];
			}
			if (dragon.health_dragon < 0)break;
			if (dragon.reward >= params._k) {
				dragon.level += long long(dragon.reward / params._k);
				dragon.health_dragon = params._h;
				dragon.reward = dragon.reward % params._k;
			}
		}
	}
};




int main() {
	long long h, k;
	cin >> h >> k;
	int s;
	long long n;
	cin >> s >> n;
	vector<long long> d(n), r(n);
	for (long long i = 0; i < n; i++)
		cin >> d[i];
	for (long long i = 0; i < n; i++)
		cin >> r[i];
	ParamsOfGame params(h, k, n, d, r);//параметры игры
	Dragon dragon(h);//дракон
	if (s == 0) //трус
		dragon.setStrategy(new StrategyOfCoward());
	else if (s == 1) //бывалый
		dragon.setStrategy(new StrategyOfExperienced());
	else if (s == 2) //балбес
		dragon.setStrategy(new StrategyOfFool());

	dragon.go(params);
	cout << dragon.count_of_dragon << " " << dragon.level << " " << dragon.reward;
}