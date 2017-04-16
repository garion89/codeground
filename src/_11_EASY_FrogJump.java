import java.util.Scanner;

class _11_EASY_FrogJump {

    public static int N, K;
    public static int[] stone;

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();

            stone = new int[N+1];

            stone[0] = 0;

            for(int n = 1; n <= N; n++)
                stone[n] = sc.nextInt();

            K = sc.nextInt();

            int count = 0;
            int curr = 0;

            for(int n = 0; n <= N; n++) {
                if(stone[n] - stone[curr] <= K)
                    continue;
                else if(curr == n-1) {
                    count = -1;
                    break;
                } else {
                    curr = n-1;
                    count++;
                    n--;
                }
            }

            if(count != -1)
                count++;

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(count);
        }
    }
}