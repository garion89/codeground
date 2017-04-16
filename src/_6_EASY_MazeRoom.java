/*
    고쳐야 할 점
    1. 만들려고 하는 것의 최종 형태가 어떤지를 먼저 생각하고 만들어야함. 만들면서 고치다 보면 누더기가 됨.
    2. 굳이 저장하지 않아도 될 것을 배열로 저장하고 있음. XY 값은 굳이 저장할 필요가 없이 바로 바로 방의 번호로 바꾸면 됨.(코드가 필요 이상으로 복잡해짐)
    3. 섣불리 전체 모습을 단정지음. X+Y < N인 경우와 그렇지 않은 경우를 나눠야되는데 그렇게 하지 않았음. 주의를 기울이자!
 */

import java.util.Scanner;

class _6_EASY_MazeRoom {
    public static int N;

    //2k번째 자리는 k번째 X, 2k+1번째 자리는 k번째 Y
    public static int[] XY;

    public static String move;

    public static void moveToXY(int k) {
        char m = move.charAt(k-1);

        switch(m) {
            case 'U':
                XY[2 * k] = XY[2 * k - 2];
                XY[2 * k + 1] = XY[2 * k - 1] - 1;
                break;
            case 'D':
                XY[2 * k] = XY[2 * k - 2];
                XY[2 * k + 1] = XY[2 * k - 1] + 1;
                break;
            case 'L':
                XY[2 * k] = XY[2 * k - 2] - 1;
                XY[2 * k + 1] = XY[2 * k - 1];
                break;
            case 'R':
                XY[2 * k] = XY[2 * k - 2] + 1;
                XY[2 * k + 1] = XY[2 * k - 1];
                break;
        }
    }

    public static long kthXYToNum(int k) {
        int X = XY[2 * k];
        int Y = XY[2 * k + 1];

        int Sum = X + Y;

        if(Sum < N) {
            long Start = (Sum * (Sum + 1) / 2) + 1;

            if (Sum % 2 == 0) { // 아래쪽부터 대각선 오른쪽 위로 숫자가 커지는 경우
                return Start + X;
            } else { // 윗쪽부터 대각선 왼쪽 아래로 숫자가 커지는 경우
                return Start + Y;
            }
        } else {
            int invX = N - X - 1;
            int invY = N - Y - 1;
            int invSum = invX + invY;

            long invStart = (invSum * (invSum + 1) / 2) + 1;

            if (invSum % 2 == 0) {
                return N*N - (invStart + invX) + 1;
            } else {
                return N*N - (invStart + invY) + 1;
            }
        }
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.
            N = sc.nextInt();

            int K = sc.nextInt();

            sc.nextLine();
            move = sc.nextLine();

            XY = new int[2 * K + 2];
            XY[0] = 0;
            XY[1] = 0;

            for(int k = 1; k <= K; k++)
                moveToXY(k);

            long total = 0;

            for(int k = 0; k <= K; k++)
                total += kthXYToNum(k);

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(total);
        }
    }
}