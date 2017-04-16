import java.util.Scanner;

class _1_EASY_Picknumber {
    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;
        int N;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.

            int result = 0;

            N = sc.nextInt();

            for(int n = 0; n < N; n++)
                result ^= sc.nextInt();

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);

            System.out.println(result);

        }
    }
}