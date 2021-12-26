#include <iostream>
using namespace std;

class Hero;
class StrategyHero {
public:
	virtual void execute(long long&, bool&, long long&, long long&, long long&) = 0;
	virtual ~StrategyHero() {};
};
class StrategyHeroWarrior : public StrategyHero {
public:
	void execute(long long& level, bool& u, long long& d, long long& r, long long& Sum) override {
		if (u == false) {//сокровище
			Sum += d * r;
		}
		else {//чудовище
			if (d <= level + level / 2)
				Sum += r;
			else
				Sum -= r / 2;
		}
	}
};

class StrategyHeroAltruist : public StrategyHero {
public:
	void execute(long long& level, bool& u, long long& d, long long& r, long long& Sum) override {
		if (u == 0)
			Sum += (d * r) / 2;
		else {
			if (d <= level * 2)
				Sum += r;
		}
	}
};

class Hero {
public:
	Hero(long long level) :_level(level) { _strategy = nullptr; }
	void setStrategyHero(StrategyHero* strategy) {
		_strategy = strategy;
	}
	StrategyHero* getStrategyHero(StrategyHero* strategy) {
		return _strategy;
	}
	void getPrize(bool& u, long long& d, long long& r, long long& Sum) {
		_strategy->execute(_level, u, d, r, Sum);
	}
	long long getLevel() { return _level; }

private:
	long long _level;
	StrategyHero* _strategy;
};




int main() {
	long long t, l, n;
	cin >> t >> l >> n;
	Hero hero(l);
	long long S = 0;
	bool u;
	long long d, r;
	if (t == 0) {//воин
		hero.setStrategyHero(new StrategyHeroWarrior());
		for (long long i = 0; i < n; i++) {
			long long d, r;
			bool u;
			cin >> u >> d >> r;
			hero.getPrize(u, d, r, S);
		}
	}
	else if (t == 1) {//альтруист
		hero.setStrategyHero(new StrategyHeroAltruist());
		for (long long i = 0; i < n; i++) {
			long long d, r;
			bool u;
			cin >> u >> d >> r;
			hero.getPrize(u, d, r, S);
		}
	}
	cout << S;
}