import java.util.Arrays;
import java.util.Scanner;

class _3_EASY_ExamStudy {
    public static int N, K;
    public static int[] scores;

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.
            N = sc.nextInt();
            K = sc.nextInt();

            scores = new int[N];

            for(int n = 0; n < N; n++)
                scores[n] = sc.nextInt();

            Arrays.sort(scores);
            int total = 0;

            for(int k = 0; k < K; k++)
                total += scores[N - k - 1];

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(total);
        }
    }
}