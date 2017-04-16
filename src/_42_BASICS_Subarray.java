/*
    고쳐야할 부분
    1. 출력에서 합이 S보다 크거나 같은 부분배열이 없는 경우 0을 출력하라고 했는데 이 부분을 아예 신경도 안썼다. 즉 에러 케이스 처리에 신경을 써야 한다.
    2. 맨 처음에 완전 탐색 알고리즘을 이용하여 각 위치마다 S보다 크거나 같은 부분 배열의 최소 크기를 저장하게 했었는데 이 부분에서 시간 문제로 문제가 생겼다
    3. 배열을 다루다가 특정 경우에서 배열의 크기를 넘는 경우 들이 왕왕 생겼다. 이러한 부분들을 조심해야겠다. 반복문의 시작 조건 종료 조건 등을 잘 생각해보자.
 */
import java.util.Scanner;

class _42_BASICS_Subarray {

    public static int N, S;

    public static int[] array;

    public static int minLength() {
        int total = 0;
        int min = 0;

        int front = 0, back = 0;

        for(int i = 0; i < N; i++) {
            total += array[i];
            min++;
            if(S <= total) {
                front = 0;
                back = i + 1;
                break;
            }
        }

        if(total < S)
            return 0;

        while(front < back) {
            if(S <= total - array[front]) {
                total -= array[front];
                front++;
                min--;
            } else if(back < N) {
                total += array[back++] - array[front++];
            } else {
                break;
            }
        }

        return min;
    }

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int T;
        int test_case;

        T = sc.nextInt();
        for(test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            S = sc.nextInt();

            array = new int[N];

            for(int n = 0; n < N; n++)
                array[n] = sc.nextInt();

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("#testcase" + test_case);
            System.out.println(minLength());
        }
    }
}