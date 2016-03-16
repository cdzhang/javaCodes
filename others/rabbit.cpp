#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
using namespace std;

const int S = 3600 * 24 ;
int G , W , H ,B , X, D ;

bool isok(int t) {
    t = (t + S) % S ;
    if(W<=t && t<H) return false ;
    if(t >= B) return false ;
    if(t < G) return false ;
    return true ;
}

int read_time() {
    int a,b,c;
    scanf("%d:%d:%d", &a , &b, &c) ;
    return a*3600 + b*60 + c ;
}


int main()
{
    freopen("D-small-attempt0.in", "r" , stdin);
    freopen("small.out", "w", stdout) ;

    int T ,cas = 1 ;

    scanf("%d", &T) ;
    while(T--) {
        G = read_time() , W = read_time() , H = read_time() , B = read_time() , X = read_time()  ;
        scanf("%d", &D) ;

        int limit = D * S ;
        int now = 0 , ans = 0;
        while(now + X < limit) {
            int next = now + X ;
            while(!isok(next) && next > now) next -- ;
            if(next > now) {
                ans ++ ;
                now = next ;
            }
            else {
                ans = -1 ;
                break ;
            }
        }

        printf("Case #%d: %d\n" , cas++ , ans) ;

    }

    return 0;
}
