/*
    회고
    뭘 잘못했는지는 모르겠지만 점수가 32/200정도 밖에 나오질 않는다. 시간을 너무 많이 잡아먹어서 일단 포기했다.
    되게 간단해보이는 문제이고 문제의 핵심은 이해를 한거 같은데 답이 안나온다. 답답하다.ㅠㅠ
    문제의 핵심은 주어진 문제를 어떻게 테이블로 옮길 것인지이다. 테이블의 모양은 M+1 * N+1 * 11 크기의 3차원 배열로 볼 수 있다.
    A[i][j][k]를 해석하는 방법은 (0, 0)에서 (i, j)까지 식수원을 k번 들렀을 때 (고도 차이 합)의 최소값이다.
    그런데 K의 최대값이 10이므로 10번 이상은 의미가 없기 때문에 모두 k = 10에 저장한다.
    따라서 우리가 구할 값은 A[M][N][k] (k >= K) 중에서 최소값을 구하면 된다.
 */

import java.util.Scanner;

class _18_NORMAL2_MarathonCourse_Fail {

    public static int[][][] minWell;
    public static int[][] height;
    public static final int INF = 987_654_321;
    public static int M, N, K;

    public static int absDiffHeightFrom(String type, int x, int y) {
        if(type == "Left")
            return Math.abs(Math.abs(height[x][y]) - Math.abs(height[x-1][y]));
        if(type == "Down")
            return Math.abs(Math.abs(height[x][y]) - Math.abs(height[x][y-1]));
        return INF;
    }

    public static int minSumFrom(String type, int x, int y, int z) {
        if(type == "Left") {
            return x == 0 ? INF : minWell[x-1][y][z] + absDiffHeightFrom(type, x, y);
        } else if(type == "Down") {
            return y == 0 ? INF : minWell[x][y-1][z] + absDiffHeightFrom(type, x, y);
        } return INF;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();

            minWell = new int [M+1][N+1][10+1];
            height = new int[M+1][N+1];

            for (int j = 0; j <= N; j++)
                for (int i = 0; i <= M; i++)
                    height[i][j] = sc.nextInt();

            for (int i = 0; i <= M; i++)
                for (int j = 0; j <= N; j++)
                    for (int k = 0; k <= 10; k++)
                    minWell[i][j][k] = INF;

            for (int i = 0; i <= M; i++) {
                for (int j = 0; j <= N; j++) {
                    for (int k = 0; k <= 10; k++) {
                        if(i == 0 && j == 0 && k == 0) {
                            minWell[i][j][k] = 0;
                        } else {
                            int up = 0;

                            if (k != 10 && height[i][j] < 0)
                                up = 1;

                            int minDown = minSumFrom("Down", i, j, k);
                            int minLeft = minSumFrom("Left", i, j, k);

                            minWell[i][j][k + up] = Math.min(minDown, minLeft);
                        }
                    }
                }
            }

            /*

            int wellNum = 0;

            for(int i = 1; i <= M; i++) {
                int diff = absDiffHeightFrom("Left", i, 0);
                if(height[i][0] < 0 && wellNum < 10) {
                    minWell[i][0][wellNum+1] = minWell[i-1][0][wellNum] + diff;
                    wellNum++;
                } else {
                    minWell[i][0][wellNum] = minWell[i-1][0][wellNum] + diff;
                }
            }

            wellNum = 0;

            for(int i = 1; i <= N; i++) {
                int diff = absDiffHeightFrom("Down", 0, i);
                if(height[0][i] < 0 && wellNum < 10) {
                    minWell[0][i][wellNum+1] = minWell[0][i-1][wellNum] + diff;
                    wellNum++;
                } else {
                    minWell[0][i][wellNum] = minWell[0][i-1][wellNum] + diff;
                }
            }

            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    int diffFromDown = absDiffHeightFrom("Down", i, j);
                    int diffFromLeft = absDiffHeightFrom("Left", i, j);

                    for (int k = 0; k <= 10; k++) {
                        int minDiff = Math.min(minWell[i-1][j][k] + diffFromLeft, minWell[i][j-1][k] + diffFromDown);
                        if(height[i][j] < 0 && k < 10) minWell[i][j][k+1] = minDiff < INF ? minDiff : INF;
                        else minWell[i][j][k] = minDiff < INF ? minDiff : INF;
                    }
                }
            }
            */

            System.out.println("Case #" + test_case);

            int min = INF;

            for(int i = K; i <= 10; i++)
                min = min < minWell[M][N][i] ? min : minWell[M][N][i];

            System.out.println(min);
        }
    }
}