/*
    회고
    1. max값은 2^K이다. 그런데 long형의 최대값은 2^63 - 1이라서 K = 63일때 2^63을 표현하지 못한다. 따라서 이 경우에는 BigInteger를 써야하는데 그러지 못해서 풀지 못했다.
    2. 이 문제는 min값과 max값만 구하면 된다. 처음에 풀이법은 1부터 시작하여 K번 실행으로 1을 만들 수 있는 집합을 구하여 구 집합에서 최대 최소를 구했다. 그런데 이 방법을 쓰면 시간복잡도가 커져서 시간 제한에 걸린다.
    3. 좀 더 개념적으로 생각할 필요가 있다. 한번에 무언가를 만드려고 하면 머리가 복잡해져서 이상한 잘못을 계속한다. 이번 경우에도 getK와 같이 일반화하면 당연한 것을 괜히 이것저것 시도하다가 시간을 많이 잡아먹었다.
 */

import java.math.BigInteger;
import java.util.Scanner;

class _25_EASY_ThreeNPlusOne {

    public static int getK(long n) {
        int k = 0;

        while(true) {
            if(n % 2 == 0) // n is even
                n /= 2;
            else if(n != 1) // n is odd
                n = 3*n+1;
            else // n == 1인 경우
                break;
            k++;
        }

        return k;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {

            int K = sc.nextInt();

            long min = -1;

            for(long n = 1; n <= Long.MAX_VALUE ; n++) {
                int k = getK(n);

                if(k == K) {
                    min = n;
                    break;
                }
            }

            BigInteger max = new BigInteger("1");
            for(int i=1; i <= K; i++)
                max = max.multiply(BigInteger.valueOf(2));

            System.out.println("Case #" + test_case);
            System.out.println(min + " " + max);
        }
    }
}