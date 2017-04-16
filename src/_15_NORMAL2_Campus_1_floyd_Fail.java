import java.util.Scanner;

class _15_NORMAL2_Campus_1_floyd_Fail {
    public static int N, M;
    public static int[][] distance, via;
    public static final int INF = 987_654_321;

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();

            distance = new int[N+1][N+1];
            via = new int[N+1][N+1];

            // floyd-warshall algorithm
            // 1. distance의 초기값을 모두 무한대(여기선 의사적으로 INF값)로 함. 자기 자신으로 갈때는 0
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++)
                    distance[i][j] = INF;

            for(int i = 1; i <= N; i++)
                distance[i][i] = 0;

            // 2. 각 간선의 거리를 입력함. 양방향 그래프이므로 우상삼각형만 사용.
            for(int i = 0; i < M; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int cost = sc.nextInt();
                distance[v1][v2] = cost;
                distance[v2][v1] = cost;
            }

            // 3. 각 정점과 정점을 잇는 최단 거리와 최단 거리 상의 경로를 저장함.
            for (int k = 1; k <= N; k++) {
                for(int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (distance[i][j] > distance[i][k] + distance[k][j]) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                            via[i][j] = k;
                        }
                    }
                }
            }

            // 통행료를 받을 수 있는 대학들만 체크함.
            boolean[] toll = new boolean[N+1];

            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++)
                    toll[via[i][j]] = true;

            int count = 0;

            for(int i = 1; i <= N; i++)
                if(!toll[i]) count++;

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.print(count);

            for(int i = 1; i <= N; i++)
                if(!toll[i]) System.out.print(" " + i);

            System.out.println();
        }
    }
}