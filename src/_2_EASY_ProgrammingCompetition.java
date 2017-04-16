import java.util.Arrays;
import java.util.Scanner;

class _2_EASY_ProgrammingCompetition {

    public static int N;
    public static int[] scores;

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;


        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            scores = new int[N];

            int minVictory = 0;


            for(int n = 0; n < N; n++)
                scores[n] = sc.nextInt();

            Arrays.sort(scores);

            int temp;
            //temp = scores[n] + N - n; 에서 + N 을 빼도 된다.
            for(int n = 0; n < N; n++) {
                temp = scores[n] + N - n;
                minVictory = minVictory > temp ? minVictory : temp;
            }

            int count = 0;

            //if(scores[n] + N) 에서 + N 을 빼도 된다.
            for(int n = 0; n < N; n++) {
                if(scores[n] + N >= minVictory)
                    count++;
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(count);
        }
    }
}