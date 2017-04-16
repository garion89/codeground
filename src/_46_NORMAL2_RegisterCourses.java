import java.util.Scanner;

class _46_NORMAL2_RegisterCourses {
    public static int N, M;
    public static int[] credits, dpSum;

    public static void main(String args[]) throws Exception	{

        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();

            credits = new int[N];

            //i에 가장 가까운 합
            dpSum = new int[M+1];

            for(int i = 0; i < N; i++)
                credits[i] = sc.nextInt();

            // dpSum[i]은 모든 수를 중복 사용 허용하여 i에 가장 가깝게 만들수 있는 수이다.
            // dpSum[1]은 1에 가장 가깝게 만들수 있는 수,
            // dpSum[2]는 2에 가장 가깝게 만들수 있는 수,
            // .. dpSum[M]은 M에 가장 가깝게 만들수 있는 수가 들어간다.
            // 이 문제의 부분 문제 구조는 dpSum[i] =  max(dpSum[i - credtis[j]] + credits[j]) forall i, j라는 것이다. (dpSum[i] = -INF for i < 0, dpSum[0] = 0)
            // credits[j] >= 1이므로 i - credits[j] < i 이므로 i보다 작은 크기의 문제가 이미 풀렸을 때 그 값을 이용하여 i크기의 문제를 선형 시간 안에 해결할 수 있다.
            // 선형 시간 안에 해결할 수 있는 문제를 MN회 수행하므로 시간 복잡도는 O(M * N)이다.
            for(int i = 1; i <= M; i++) {
                for(int j = 0; j < N; j++) {
                    if(i - credits[j] < 0) continue;
                    dpSum[i] = Math.max(dpSum[i], dpSum[i - credits[j]] + credits[j]);
                }
            }

            System.out.println("Case #" + test_case);
            System.out.println(dpSum[M]);
        }
    }
}