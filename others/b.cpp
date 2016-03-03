#include <cstdio>
#include <climits>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>
#include <cstring>
#include <string>
#include <set>
#include <deque>
#include <thread>

using namespace std;

long long gcd(long long a, long long b){
	while(b){
		auto t = a % b;
		a = b;
		b = t;
	}
	return a;
}

int main()
{
	int T;
	scanf("%d",&T);
	for (int testcase = 1; testcase <= T; testcase++)
	{
		fprintf(stderr, "Case #%d processing\n", testcase);
		printf("Case #%d:\n",testcase);
		int n1,n2,n3;
		scanf("%d%d%d",&n1,&n2,&n3);
		vector<long long> val1(n1), val2(n2), val3(n3);
		for (int i = 0; i < n1; i++) scanf("%lld",&val1[i]);
		for (int i = 0; i < n2; i++) scanf("%lld",&val2[i]);
		for (int i = 0; i < n3; i++) scanf("%lld",&val3[i]);
		int M;
		scanf("%d",&M);
		set<pair<long long ,long long > > midratio;
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < n2; j++) {
				if (i == j) continue;
				auto g = gcd(val2[i], val2[j]);
				midratio.insert(make_pair(val2[i]/g, val2[j]/g));
			}
		}
		while(M--){
			int p,q;
			bool found = false;
			scanf("%d%d",&p,&q);
			for (int i = 0; i < n1; i++) {
				for (int k = 0; k < n3; k++) {
					long long r1, r2;
					// p/q = val1[i]/val3[k]*midratio
					// midratio = p*val3[k]/(q*val1[i])
					r1 = p*val3[k];
					r2 = q*val1[i];
					long long g = gcd(r1,r2);
					r1 /= g;
					r2 /= g;
					if (midratio.count(make_pair(r1,r2))) {
						found = true;
						break;
					}
				}
				if (found)break;
			}
			puts(found?"Yes":"No");
		}
	}
	return 0;
}
