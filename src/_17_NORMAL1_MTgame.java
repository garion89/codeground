/* Codeground 17. MT 게임 (level NORMAL 1)
input
3
1 1 3
10 3
9 3
13 3
2 2 1
10 3
3 2 1
10 3

output
Case #1
abb
Case #2
b
Case #3
a
 */

import java.util.Scanner;

class _17_NORMAL1_MTgame {
    public static int a, b, c, N, K;

    public static char victory() {
        // controlA는 a가 통제가능한 MOD연산의 경우
        for(int controlA = a + b * K; controlA <= a * K + b; controlA++) {
            for(int possibleB = 1; possibleB <= b; possibleB++) {
                // victoryFirst는 a의 첫시행때 마지막 사람이 부르면 무조건 a가 게임을 이기게 만드는 수
                int victoryFirst = (N - possibleB) % controlA;
                if (a <= victoryFirst && victoryFirst <= a * K)
                    return 'a';
            }
        }

        return 'b';
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            System.out.println("Case #" + test_case);

            for(int i = 0; i < c; i++) {
                N = sc.nextInt();
                K = sc.nextInt();
                System.out.print(victory());
            }

            System.out.println();
        }
    }
}