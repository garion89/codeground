/*
    회고
    균일수가 두자리인 경우를 빠르게 하는 코드를 잘 이해하질 못했다. 추천을 가장 많이 받은 풀이의 방식은 이해가 되는데 내가 따라한 코드는 이해가 잘 안간다.
    추천을 가장 많이 한 코드는 방법이 꽤나 신기하다. 일단 1111...1을 만든 후에 이 값이 N보다 커질 경우 최상단 1을 지운 후에 N을 111..1을 기저 b로 나타낸 수로 나눠서 나누어 떨어지면
    이때 사용된 기저 b의 균일수가 있다는 것이다. 내 방법과는 상당히 다른 접근 방법이라 할 수 있다.
    나는 그냥 나눠서 나온 나머지들이 계속 같은 경우에만 반복을 진행하다가 마지막에 나눠질 때 끝에서 나눈 수와 나머지가 같은지를 확인하는 방식으로 두번째로 추천이 많은 방식과 유사하다.
    여기까지가 일단 균일수가 세개 이상일때 답을 구하는 방식이다. 균일수가 두자리수일 경우네는 다른 방식을 사용해야 한다.
    왜냐하면 제약 조건하에서 두개 이상인 경우를 위와 같은 방식으로 구하면 경우의 수가 너무 많기 때문에 시간 안에 풀 수 없기 때문이다.
    그래서 일단 두개라고 가정하고 문제를 푼다. 1,1인경우부터 sqrt(N),sqrt(N)경우까지 구한다. 이경우에도 최대 32000에 비례하는 시간에 풀수 있다.
 */

import java.util.Scanner;

class _13_NORMAL1_UniformNumber {
    public static int minUniform(int N) {
        int b = 2;
        int n = N;
        int rest = n % b;

        int result = -1;

        //균일수가 세자리 이상인 경우: N을 b로 나누어 나머지가 같으면 계속 b로 나누고 나눈 수가 b보다 작아졌을때 몫이 나머지와 같으면 그때의 b가 균일수의 최소 기저가 된다.
        while(b * b <= N) {
            if(n < b  && n == rest)
                result = b;

            if(n % b == rest) {
                n /= b;
                continue;
            }

            b++;
            n = N;
            rest = N % b;
        }

        //균일수가 두자리인 경우: b를 기저로 하여 해당 N을 두자리의 균일수로 만든다. 큰 b부터 작은 b까지 내려가면서 구하여 맨 마지막에 구한 값이 두자리수 균일수의 최저 기저가 된다.
        if (result == -1) {
            for (int i = 1; i * i <= N; i++) {

                if (N % i == 0) {
                    b = N/i - 1;

                    if (b > i) {
                        result = b;
                    }
                }
            }
        }

        //균일수가 한자리인 경우: 균일수가 두자리 이상이 아니면 무조건 한자리인데 그때의 기저는 항상 N+1이 된다.
        if (result == -1)
            result = N+1;

        return result;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            int N = sc.nextInt();

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(minUniform(N));
        }
    }
}