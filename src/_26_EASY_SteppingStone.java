import java.util.Scanner;

class _26_EASY_SteppingStone {
    public static final int MOD = 1_000_000_009;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T;
        int test_case;

        T = sc.nextInt();
        for (test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int L = sc.nextInt();

            int last[][] = new int[N+1][K+1];
            int total[] = new int[N+1];
            boolean bomb[] = new boolean[N+1];

            // bomb[n] := 폭탄이 n에 있으면 true, 없으면 false
            for (int i = 1; i <= L; i++)
                bomb[sc.nextInt()] = true;

            // last[n][k]에서 n = k인 경우 초기화
            // k번째 폭탄이 없는 경우에 k칸 점프를 통해 k번째 돌에 오는 경우의 수는 1가지이다
            for(int k = 1; k <= K; k++)
                if(!bomb[k]) last[k][k] = 1;

            for (int n = 1; n <= N; n++) {
                // n번째에 폭탄이 있는 경우에 n번째로 도착하는 경우의 수는 0이다.
                // 따라서 total[n]과 last[n][k](1 <= k <= K) 모두 0으로 만들면 된다.
                // 그런데 int배열에서 초기값이 0이므로 continue를 하면 위의 값들을 건드리지 않아서 결과상 같다.
                if (bomb[n]) continue;

                // last[n][k] := n으로 도착할때 k칸 점프를 통해 도착하는 경우의 수
                // n-k 번째에 도달하는 모든 경우에서 k 점프를 하면 n번째에 도달할 수 있다.
                // 그러나 같은 수의 점프를 하면 안되기 때문에 n-k번째에 k번 점프를 통해 도달하는 경우만큼 빼주어야 한다.
                for (int k = 1; k <= K; k++)
                    // k칸 점프를 하기 때문에 n이 k보다 커야한다. 그리고 n과 k가 같은 경우는 초기화를 통해 처리했다.
                    if (n > k)
                        // n >= k 인 모든 정수 n, k에 대해서 기존의 정의상 total[n] >= last[n][k]의 관계가 성립한다.
                        // 그러나 문제에서는 실제 total값이 아니라 모듈러 연산을 한 total값을 요구하고 있다.
                        // 이럴 경우 실제 값은 total이 last보다 큰데 모듈러 연산에 걸려서 total이 last보다 작아지는 문제가 발생할 수 있다.
                        // 이럴때 모듈러 연산에 사용하는 MOD값 만큼을 더해주면 된다. 왜냐하면 모듈러 연산은 덧셈에 관하여 분배법칙이 성립하기 때문이다.
                        last[n][k] = (total[n - k] - last[n - k][k] + MOD) % MOD;

                // total[n] := n으로 도착하는 총 경우의 수
                // n으로 도착하는 총 경우는 1칸 점프를 통해 n으로 오는 경우, 2칸 점프를 통해 n으로 오는 경우, .. k칸 점프를 통해 n으로 오는 경우의 합이다.
                for (int k = 1; k <= K; k++)
                    total[n] = (total[n] + last[n][k]) % MOD;
            }

            System.out.println("Case #" + test_case);
            System.out.println(total[N]);
        }
    }
}