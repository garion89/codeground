import java.util.Scanner;

class _46_BASICS_Discount {
    //N은 정거장의 수 M은 철도의 수 K는 할인권의 가격
    public static int N, M, K;

    public static int[][] adj;

    public static final int INF = 987654321;

    //플로이드 알고리즘 구현
    public static void floyd() {

        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                if(adj[i][k] != INF) {
                    for (int j = 0; j < N; j++)
                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
    }

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int T;
        int test_case;

        T = sc.nextInt();
        for(test_case = 1; test_case <= T; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            //그래프 초기화
            adj = new int[N][N];

            for(int a = 0; a < N; a++) {
                for(int b = 0; b < N; b++) {
                    adj[a][b] = INF;
                }
            }

            for(int n = 0; n < N; n++)
                adj[n][n] = 0;

            //그래프 입력
            int a, b, c;

            for(int m = 0; m < M; m++) {
                a = sc.nextInt()-1;
                b = sc.nextInt()-1;
                c = sc.nextInt();
                adj[a][b] = c;
                adj[b][a] = c;
            }

            floyd();

            int count = 0;

            int P = sc.nextInt();

            for(int p = 0; p < P; p++) {
                a = sc.nextInt()-1;
                b = sc.nextInt()-1;
                if (K < adj[a][b])
                    count++;
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(count);
        }
    }
}