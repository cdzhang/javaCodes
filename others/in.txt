#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#include <stack>
#include <map>
#include <set>
#include <deque>
#include <cstring>
#include <functional>
#include <climits>
#include <list>
#include <ctime>
#include <complex>
 
#define F1(x,y,z) for(int x=y;x<z;x++)
#define F2(x,y,z) for(int x=y;x<=z;x++)
#define F3(x,y,z) for(int x=y;x>z;x--)
#define F4(x,y,z) for(int x=y;x>=z;x--)
#define pb push_back
#define LL long long
#define co complex<double>
 
#define MAX 100005
#define AMAX 1500
#define MOD 1000000007

#define f(c,d) ((1<<(c))*(d))

using namespace std;

int t,n,ta,tb,tc,td,te;
LL tx;
vector<int> v[2];
vector<bool> done;
bool tt;

void go(LL a,int b,int c,int e){
	if(done[e])return;
	if(b>c){
		done[e]=1;
		return;
	}
	tt=a&(1<<(32-b));
	if(v[tt][e]==0){
		v[tt][e]=v[0].size();
		v[0].pb(0);
		v[1].pb(0);
		done.pb(0);
	}
	go(a,b+1,c,v[tt][e]);
	if(v[0][e]&&v[1][e]&&done[v[0][e]]&&done[v[1][e]])done[e]=1;
}

void goo(LL a,int b,int c){
	if(done[c]){
		if(c==0)return;
		td=a&((1<<8)-1);
		a>>=8;
		tc=a&((1<<8)-1);
		a>>=8;
		tb=a&((1<<8)-1);
		a>>=8;
		ta=a&((1<<8)-1);
		printf("%d.%d.%d.%d/%d\n",ta,tb,tc,td,b);
		return;
	}
	if(v[0][c])goo(a,b+1,v[0][c]);
	if(v[1][c])goo(a|(1<<(32-b-1)),b+1,v[1][c]);
}

int main(){
	freopen("in.txt","r",stdin);
	freopen("out.txt","w",stdout);
	scanf("%d",&t);
	F2(a,1,t){
		v[0].clear();
		v[1].clear();
		done.clear();
		v[0].pb(0);
		v[1].pb(0);
		done.pb(0);
		printf("Case #%d:\n",a);
		scanf("%d",&n);
		while(n--){
			scanf("%d.%d.%d.%d/%d",&ta,&tb,&tc,&td,&te);
			tx=ta;
			tx<<=8;
			tx+=tb;
			tx<<=8;
			tx+=tc;
			tx<<=8;
			tx+=td;
			go(tx,1,te,0);
		}
	 	goo(0,0,0);
	}
	//system("pause");
    return 0;
}
