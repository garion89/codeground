/*
    고쳐야할 점
    1. 시간 복잡도를 간단하게라도 생각해볼 필요가 있음. 처음 고안한 풀이법은 O(NH)로 최악의 경우 10^14의 시간이 걸릴 수 있음.
       문제 풀 시간이 극도로 없고 다른 방법이 생각이 나지 않을 경우 이런 풀이법으로 라도 풀어야하긴 하지만 잘 생각해보길 바람.
    2. 조금 더 수학적 센스를 발휘할 필요가 있음. 필요하다면 도형에 관한 내용이나 간단한 수열, 조합 등은 공부하면 좋을 듯 함.
 */

import java.util.Scanner;

class _8_NORMAL1_Block {
    public static int N;

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {

            int N = sc.nextInt();

            //맨 오른쪽과 맨 왼쪽의 예외 경우를 없애기 위해서 +2를 하고 0과 N+1자리에 0을 넣음
            int[] height = new int[N+2];
            height[0] = 0;
            height[N+1] = 0;

            for (int n = 1; n <= N; n++)
                height[n] = sc.nextInt();

            //해당 줄에서 높이가 커지는 이등변 직각 삼각형의 최대 높이를 구하는 부분
            int before;
            int[] incHeight = new int[N+2];
            incHeight[0] = 0;
            incHeight[N+1] = 0;

            for(int i = 1; i <= N; i++){
                before = incHeight[i-1] + 1;

                if(height[i] <= before)
                    incHeight[i] = height[i];
                else
                    incHeight[i] = before;
            }

            //해당 줄에서 높이가 작아지는 이등변 직각 삼각형의 최대 높이를 구하는 부분
            int after;
            int[] decHeight = new int[N+2];
            decHeight[0] = 0;
            decHeight[N+1] = 0;

            for(int i = N; 1 <= i; i--){
                after = decHeight[i+1] + 1;

                if(height[i] <= after)
                    decHeight[i] = height[i];
                else
                    decHeight[i] = after;
            }

            //해당 줄에서 각 이등변 직각 삼각형의 높이 중 작은 값을 temp값으로 두어서 모든 줄에서 가장 큰 temp값을 max로 만듦
            int max = 0;
            int temp;

            for(int i = 1; i <= N; i++) {
                temp = Math.min(incHeight[i], decHeight[i]);
                max = temp > max ? temp : max;
            }

            System.out.println("Case #" + test_case);
            System.out.println(max);

            /* 이 방식은 최악의 경우 O(NH)의 시간이 걸려서 timeout에 걸림. 위의 방법은 O(n)의 시간복잡도.
                N = sc.nextInt();
                blocks = new int[N+2];
                blocks[0] = 0;
                blocks[N+1] = 0;

                for(int n = 1; n <= N; n++)
                    blocks[n] = sc.nextInt();

                int count = 0;

                int left = 1;
                int right = N;

                while(left <= N) {
                    count++;

                    int previous, current = 0;
                    //외부 블락 제거
                    for(int n = left; n <= right; n++) {
                        previous = current;
                        current = Math.min(blocks[n] - 1, Math.min(blocks[n-1], blocks[n+1]));

                        if(current < 0)
                            current = 0;

                        blocks[n-1] = previous;
                    }

                    blocks[right] = current;

                    for( ; left <= N+1; left++) {
                        if(blocks[left] != 0)
                            break;
                    }

                    for( ; 0 <= right; right--) {
                        if(blocks[right] != 0)
                            break;
                    }
                }

                // 이 부분에서 정답을 출력하십시오.
                System.out.println("Case #" + test_case);
                System.out.println(count);
            */
        }
    }
}

