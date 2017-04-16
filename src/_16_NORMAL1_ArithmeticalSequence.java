import java.util.Scanner;

class _16_NORMAL1_ArithmeticalSequence {
    public static int M;

    public static long getGCD(long p, long q) {
        if (q == 0) return p;
        return getGCD(q, p % q);
    }

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 문제의 M보다 1작은 M을 사용(차이를 사용할 것이기 때문에)
            M = sc.nextInt();

            long gcd = 0;
            long prev = sc.nextLong();
            long curr;

            for(int i = 0; i < M-1; i++) {
                curr = sc.nextLong();
                gcd = getGCD(curr - prev, gcd);
                prev = curr;
            }

            int count = 0;

            for(int i = 1; i <= gcd; i++)
                if(gcd % i == 0)
                    count++;


            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(gcd == 0 ? 1 : count);
        }
    }
}