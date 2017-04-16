/*
    회고
    일단 해당 방식을 사용하면 결국에 해당 성질을 만족하는 그래프를 만들 수 있다는 것은 이해가 간다.
    그러나 다음의 사실들을 어떻게 증명하는지 모르겠다.
    1. 하나씩 지워내는 방식으로 해당 성질을 만족하는 그래프를 반드시 만들어낼 수 있는지
    2. 1번 로봇부터 지우는 방식으로 최소한의 로봇들을 작동 불능으로 만들 수 있는지
    3. 1번 로봇부터 지우는 방식으로 사용하지 않는 로봇들의 합이 최소가 되도록 만들 수 있는지

    배울점은 어떤 성질이 주어지면 그 성질을 코드로 옮겨서 이용할 줄 알아야 된다는 것을 배웠다.
 */

import java.util.Scanner;

class _27_NORMAL1_Virus {
    public static int minComm, minLive, N, M;

    public static boolean[] disused;
    public static boolean[][] connected;

    public static int getDegree(int ptr) {
        int count = 0;

        for (int i = 1; i <= N; i++)
            if(connected[ptr][i])
                count++;

        return count;
    }

    public static int getUsedNum() {
        int count = 0;

        for (int i = 1; i <= N; i++)
            if(!disused[i])
                count++;

        return count;
    }

    public static void disuseRobot(int ptr) {
        disused[ptr] = true;

        for (int i = 1; i <= N; i++)
            connected[ptr][i] = connected[i][ptr] = false;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            minComm = sc.nextInt();
            minLive = sc.nextInt();
            N = sc.nextInt();
            M = sc.nextInt();

            // disused[ptr] := ptr번째 로봇이 사용되지 않으면 true, 사용되면 false
            disused = new boolean[N+1];

            // connected[i][j] := i번째 로봇과 j번째 로봇이 연결되어 있으면 true, 아니면 false
            connected = new boolean[N+1][N+1];

            for(int m = 0; m < M; m++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                connected[a][b] = connected[b][a] = true;
            }

            // satisfied := 주어진 성질을 만족하는지 여부
            boolean satisfied = false;

            while (!satisfied) {

                satisfied = true;

                for (int ptr = 1; ptr <= N; ptr++) {
                    // ptr이 가리키는 로봇이 사용되지 않고 있다면 어떠한 작업도 할 수 없다
                    if (disused[ptr]) continue;

                    // ptr이 가리키는 로봇의 차수를 구한다
                    int degree = getDegree(ptr);

                    // 사용되는 로봇의 개수를 구한다.
                    int usedNum = getUsedNum();

                    // ptr이 가리키는 로봇의 차수가 주어진 성질을 만족한다면 작업을 할 필요가 없다.
                    if (minComm <= degree && degree <= (usedNum - minLive - 1))
                        continue;

                    // ptr이 가리키는 로봇을 사용하지 못하게 만든다.
                    disuseRobot(ptr);

                    // 그래프에 조작을 하면 주어진 성질을 만족하는지 알수 없으므로 처음부터 다시 확인한다.
                    satisfied = false;
                    break;
                }
            }

            // 반복문이 종료되었다는 것은 모든 로봇들이 주어진 성질을 만족한다는 것이다
            System.out.println("Case #" + test_case);

            int result = 0;

            for(int i = 1; i <= N; i++)
                if(disused[i])
                    result += i;

            System.out.println(result);
        }
    }
}