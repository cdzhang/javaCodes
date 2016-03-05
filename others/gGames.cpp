#include <iostream>
#include <iomanip>
#include <vector>
#include <string>
#include <cstring>
using namespace std;

template <typename T>
ostream &operator <<(ostream &out, const vector<T> &v) {
	out<<'(';
	for (auto &x: v)
		out<<x<<',';
	out<<')';
	return out;
}

int n;
vector<vector<pair<int,int>>> friends;
int prohibited[16][16];
int occupied[5][16];

bool dfs(int num) {
	if (num == (1<<n))
		return true;
	int pos = 0;
	while (pos < (1<<n)) {
		if (occupied[0][pos] || prohibited[num][pos]) {
			++pos;
			continue;
		}

		for (int i = 0; i <= 4; ++i)
			++occupied[i][pos>>i];
		for (auto &x: friends[num]) {
			int rounds = x.first;
			int pat = pos>>rounds<<rounds;
			int fri = x.second;
			for (int i = 0; i < (1<<rounds); ++i)
				++prohibited[fri][pat+i];
		}

		if (dfs(num+1))
			return true;

		for (int i = 0; i <= 4; ++i)
			--occupied[i][pos>>i];
		for (auto &x: friends[num]) {
			int rounds = x.first;
			int pat = pos>>rounds<<rounds;
			int fri = x.second;
			for (int i = 0; i < (1<<rounds); ++i)
				--prohibited[fri][pat+i];
		}

		int step = 0;
		for (int i = 1; i <= 4; ++i)
			if (!occupied[i][pos>>i])
				step = i;
		pos += 1<<step;
	}

	return false;
}

void process(int casenum) {
	memset(prohibited, 0, sizeof(prohibited));
	memset(occupied, 0, sizeof(occupied));
	int m;
	cin >> n >> m;
	friends.clear();
	friends.resize(1<<n);
	for (int i = 0; i < m; ++i) {
		int num, rounds, k;
		cin >> num >> rounds >> k;
		--num;
		while (k--) {
			int x;
			cin >> x;
			--x;
			friends[num].push_back(make_pair(rounds, x));
			friends[x].push_back(make_pair(rounds, num));
		}
	}

	cout << "Case #" << casenum << ": " << (dfs(0) ? "YES" : "NO") << endl;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int ncases;
	cin >> ncases;
	for (int icase = 1; icase <= ncases; ++icase)
		process(icase);
}
