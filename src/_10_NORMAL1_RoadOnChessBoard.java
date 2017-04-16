/*
    회고
    솔직히 푸는 법이 이해가 안간다. 지금도 대충 따라 만들어서 코드를 만들었는데 왜 하나의 장애물에 의해서 막히게 되는 길의 수가 장애물까지 장애물을 피해서 가는 거리 * 장애물 부터 끝까지 장애물 무시하고 가는 거리인지 모르겠다.
    배울 점은 경우의 수를 구하는 법을 외워야한다는 것 정도이다. 경우의 수를 빠르게 구하는 법은 이곳 저곳에서 사용될 것 같다.
    그리고 또 한 가지는 문제를 잘 읽고 문제에서 예외로 처리해야할 부분을 잘 처리하는 것이다. 생각 없이 하다가 이 부분 때문에 감점 되거나 문제가 틀리는 경우가 있을 수 있다.
    차라리 푸는 방법을 모르면 완전 탐색을 이용해서라도 풀도록 하는게 좋을 것 같다.
 */

import java.util.Arrays;
import java.util.Scanner;

class _10_NORMAL1_RoadOnChessBoard {
    static class Obstacle implements Comparable<Obstacle>{
        int x;
        int y;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Obstacle o) {
            int diffX = x - o.x;
            int diffY = y - o.y;

            if(diffX != 0)
                return diffX;

            if(diffY != 0)
                return diffY;

            return 0;
        }
    }

    public static int N, M, K;

    public static final long P = 1_000_000_007;

    public static final int INF = 987_654_321;

    public static long[] fact = new long[2_000_003];

    public static Obstacle[] Obs;

    public static long combination(int n, int r) {
        long A = fact[n];
        long B = fact[n-r] * fact[r] % P;

        return A * findMMI(B, P-2) % P;
    }

    public static long findMMI(long B, long p) {
        if(p == 0)
            return 1;
        else if(p == 1)
            return B;
        else {
            long half = findMMI(B, p/2);

            if(p % 2 == 0)
                return (half * half) % P;
            else
                return  ((half * half) % P * B) % P;
        }
    }

    public static long[][] withObs;
    public static long[][] withoutObs;

    public static long goWithObs(int i, int j) {
        if(withObs[i][j] != -1)
            return withObs[i][j];

        if(i == j)
            return 1;

        if(goWithoutObs(i, j) == 0)
            return withObs[i][j] = 0;

        long result = 0;

        for (int k = 1; k < j; k++) {
            result += goWithObs(i, k) * goWithoutObs(k, j) % P;
            result %= P;
        }

        return withObs[i][j] = (goWithoutObs(i, j) - result + P) % P;
    }

    public static long goWithoutObs(int i, int j) {
        if(withoutObs[i][j] != -1)
            return withoutObs[i][j];

        if(i == j)
            return 1;

        if(N < Obs[i].x || Obs[i].x <= 0 || M < Obs[i].y || Obs[i].y <= 0)
            return withoutObs[i][j] = 0;

        if(N < Obs[j].x || Obs[j].x <= 0 || M < Obs[j].y || Obs[j].y <= 0)
            return withoutObs[i][j] = 0;

        int diffX = Obs[j].x - Obs[i].x;
        int diffY = Obs[j].y - Obs[i].y;

        if(diffX < 0 || diffY < 0)
            return withoutObs[i][j] = 0;

        return withoutObs[i][j] = combination(diffX + diffY, diffX);
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        fact[0] = 1;

        for(int i = 1; i < 2000003; i++)
            fact[i] = i * fact[i-1] % P;

        TC = sc.nextInt();

        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            withObs = new long[K+2][K+2];
            withoutObs = new long[K+2][K+2];

            for(int i = 0; i < K+2; i++)
                for(int j = 0; j < K+2; j++)
                    withObs[i][j] = -1;

            for(int i = 0; i < K+2; i++)
                for(int j = 0; j < K+2; j++)
                    withoutObs[i][j] = -1;

            Obs = new Obstacle[K+2];
            Obs[0] = new Obstacle(1, 1);
            Obs[K+1] = new Obstacle(N, M);
            
            for(int k = 1; k <= K; k++) {
                int X = sc.nextInt();
                int Y = sc.nextInt();

                if(X <= N && Y <= M)
                    Obs[k] = new Obstacle(X, Y);
                else
                    Obs[k] = new Obstacle(INF, 0);
            }

            Arrays.sort(Obs);

            for(int i = 0; i < K+2; i++) {
                if(Obs[i].x == N && Obs[i].y == M) {
                    K = i - 1;
                    break;
                }
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(goWithObs(0, K+1));
        }
    }
}