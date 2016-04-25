//start of jonathanirvings' template v2.0.0 (BETA)

#define jonathan using
#define ganteng namespace
#define banget std
#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <deque>
#include <string>
#include <string.h>
#include <vector>
#include <stack>
#include <queue>
#include <math.h>
#include <stdlib.h>
#include <map>
#include <set>
#include <time.h>
#include <bitset>
#include <list>
jonathan ganteng banget;

typedef long long LL;
typedef pair<int,int> pii;
typedef pair<pii,pii> ppi;
typedef pair<LL,LL> pll;
typedef pair<string,string> pss;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef vector<pii> vii;
typedef vector<LL> vl;
typedef vector<vl> vvl;
typedef vector<string> vstr;
typedef vector<char> vc;

double EPS = 1e-9;
int INF = 2000000000;
long long INFF = 8000000000000000000LL;
double PI = acos(-1);
int dirx[8] = {-1,0,0,1,-1,-1,1,1};
int diry[8] = {0,1,-1,0,-1,1,-1,1};

#ifdef TESTING
  #define DEBUG fprintf(stderr,"====TESTING====\n")
  #define VALUE(x) cerr << "The value of " << #x << " is " << x << endl
  #define debug(...) fprintf(stderr, __VA_ARGS__)
#else
  #define DEBUG 
  #define VALUE(x)
  #define debug(...)
#endif

#define FOR(a,b,c) for (int (a)=(b);(a)<(c);++(a))
#define FORN(a,b,c) for (int (a)=(b);(a)<=(c);++(a))
#define FORD(a,b,c) for (int (a)=(b);(a)>=(c);--(a))
#define FORSQ(a,b,c) for (int (a)=(b);(a)*(a)<=(c);++(a))
#define FORL(a,b,c) for (LL (a)=(b);(a)<=(c);++(a))
#define FORLSQ(a,b,c) for (int (a)=(b);(LL)(a)*(LL)(a)<=(c);++(a))
#define FORC(a,b,c) for (char (a)=(b);(a)<=(c);++(a))
#define REP(i,n) FOR(i,0,n)
#define REPN(i,n) FORN(i,1,n)
#define REPD(i,n) FORD(i,n,1)
#define MAX(a,b) a = max(a,b)
#define MIN(a,b) a = min(a,b)
#define SQR(x) ((x) * (x))
#define RESET(a,b) memset(a,b,sizeof(a))
#define fi first
#define se second
#define mp make_pair
#define pb push_back
#define ALL(v) v.begin(),v.end()
#define ALLA(arr,sz) arr,arr+sz
#define SIZE(v) (int)v.size()
#define SORT(v) sort(ALL(v))
#define REVERSE(v) reverse(ALL(v))
#define SORTA(arr,sz) sort(ALLA(arr,sz))
#define REVERSEA(arr,sz) reverse(ALLA(arr,sz))
#define PERMUTE next_permutation
#define TC(t) while(t--)
#define READ(n,data) {scanf("%d",&n); REPN(i,n) scanf("%d",&data[i]);}

inline string IntToString(int a){
    char x[100];
    sprintf(x,"%d",a); string s = x;
    return s;
}

inline int StringToInt(string a){
    char x[100]; int res;
    strcpy(x,a.c_str()); sscanf(x,"%d",&res);
    return res;
}

inline string GetString(void){
  char x[1000005];
  scanf("%s",x); string s = x;
  return s;
}

inline string uppercase(string s){
  int n = SIZE(s); 
  REP(i,n) if (s[i] >= 'a' && s[i] <= 'z') s[i] = s[i] - 'a' + 'A';
  return s;
}

inline string lowercase(string s){
  int n = SIZE(s); 
  REP(i,n) if (s[i] >= 'A' && s[i] <= 'Z') s[i] = s[i] - 'A' + 'a';
  return s;
}

inline void OPEN (string s) {
    freopen ((s + ".in").c_str (), "r", stdin);
    freopen ((s + ".out").c_str (), "w", stdout);
}

//end of jonathanirvings' template v2.0.0 (BETA)

int T;
int dp[1<<17];
int n,m,nn;
bool teman[20][20];
int k[20],e,K,b;
vi nyala[1<<17];
vi mungkin[1<<17];

int jahja(int bit,int tahap)
{
  if (__builtin_popcount(bit) == 1) return 1;
  int &ret = dp[bit];
  if (ret >= 0) return ret;
  ret = 0;
  REP(i,SIZE(nyala[bit]))
  {
    int x = nyala[bit][i];
    if (k[x] < tahap) continue;
    REP(j,n) if(teman[x][j] && (bit & (1 << j)))
    {
      return ret = 0;
    }
  }
  REP(i,SIZE(mungkin[bit]))
  {
    int temp1 = mungkin[bit][i];
    int temp2 = bit ^ temp1;
    ret |= (jahja(temp1,tahap-1) & jahja(temp2,tahap-1));
    if (ret > 0) return ret;
  }
  return ret;
}

int main()
{
  REP(i,(1<<16))
  {
    REP(j,16) if(i & (1 << j)) nyala[i].pb(j);
    REP(j,1 << SIZE(nyala[i]))
    {
      if (__builtin_popcount(j) == SIZE(nyala[i])/2)
      {
        int temp = 0;
        REP(k,SIZE(nyala[i])) if(j & (1 << k)) temp += (1 << nyala[i][k]);
        mungkin[i].pb(temp);
      }
    }
  }
  scanf("%d",&T);
  REPN(cases,T)
  {
    printf("Case #%d: ",cases);
    scanf("%d %d",&nn,&m);
    n = 1 << nn;
    RESET(teman,0);
    REP(i,n) k[i] = 0;
    TC(m)
    {
      scanf("%d %d %d",&e,&K,&b);
      k[e-1] = K;
      TC(b)
      {
        int x;
        scanf("%d",&x);
        teman[e-1][x-1] = 1;
      }
    }
    RESET(dp,-1);
    int risan = jahja((1 << n) - 1, nn);
    puts(risan ? "YES" : "NO");
  }
  return 0;
}









