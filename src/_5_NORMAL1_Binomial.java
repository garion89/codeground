/*
 * 일단 주어진 식이 N+M+2 개 중에서 N+1개를 고르는 경우의 수를 구하는 문제로 바뀌는 부분까지 도달했다고 하자.
 *
 * 그렇다면, Combination의 값을 구하는 것에 대해서 이야기해보자.
 * 일단, n개중에서 r개를 뽑는 경우의 수를 nCr이라고 표기하겠다. (n, r은 0이상의 정수이고 r <= n이다)
 * 그렇다면, nCr = n! / ((n-r)! * r!)이고, 우리가 정말로 구하려는 값은 nCr % 1,000,007이다.(이하 A := n!, B := (n-r)! * r!, P := 1,000,007)
 * 그런데, 여기서 문제는 모듈러 연산은 덧셈 곱셈에 대해선 분배 법칙이 성립하지만 나눗셈에 대해서는 성립하지 않기 때문에 현재 nCr은 factorial의 나눗셈을 한 후에 % 연산을 해야한다는 것이다.
 * 그러나, 우리는 제한된 bit수의 정수형을 사용해야하기 때문에 factorial 자체에 % 연산을 적용한 것을 저장할 수밖에 없다.
 * 그렇다면, 일단 나눗셈을 곱셈 형태로 바꾸어야 하는데 B를 일반적인 나눗셈의 역원인 1/B로 바꾸는 것이 모듈러 연산에서는 통하지 않기 때문에 페르마의 소정리를 이용해야 한다.
 *
 * 페르마의 소정리란, p가 소수이고, a가 정수이며, a와 p가 서로소(공약수가 1뿐인 것을 뜻함)라고 하자. 페르마의 소정리에 따르면, 법 p에서 a^p a는 서로 합동(p로 나눈 나머지가 같음을 뜻함)이다.
 * a^p == a (mod p) 이 참일때 a^p와 a를 합동이라고 한다.( A == B (mod C) := A와 B를 각각 C로 나누었을때 나머지가 같다는 뜻)
 * a^p와 a가 합동이면, a^(p-1) == 1 (mod p) 도 참이다.
 *
 * 이걸 위의 사례에 적용을 하면,
 * B^(P-1) == 1 (mod P)
 * A * B^(P-1) == A (mod P) // 양변에 A를 곱해주었다.
 * A * B^(P-2) == A/B (mod P) // 양변에 B를 나누어주었다.
 * (A * B^(P-2)) % P == (A/B) % P (mod P) // 양변에 % P 를 해주었다.
 * (A % P) * (B^(P-2) % P) == (A/B) % P (mod P) // 좌변에 곱셈 분배법칙을 적용하였다.
 *
 * # B로 양변을 나누기 전에 A를 곱해준 이유는 B로 먼저 나눌 경우에 우변의 값은 정수가 아닌데 좌변의 값은 정수가 되는 문제가 발생하기 때문이다.
 *
 * 이제 실제 계산해보면 좌변의 값이 P보다 커질 수 있기 때문에 % P를 한번 더 해주면 우변의 값과 같다.
 * 이제 문제는 B^(P-2) % P의 값을 구하는 것으로 바뀌었다.
 * 그런데 앞서 설명했듯이, 곱셈의 분배 법칙이 성립하므로 a^b == (a^(b/2) % c)^2 (mod c) (b가 짝수인 경우, 홀수이면 우측에 a만큼을 곱해주면 된다)를 이용하면
 * O(lg b)의 시간으로 B^(P-2) % P의 값을 구할 수 있다.
 */

import java.util.Scanner;

class _5_NORMAL1_Binomial {
    public static int N, M;

    public static final long P = 1000000007;

    public static long[] fact = new long[2000003];

    public static long combination(int n, int r) {
        long A = fact[n];
        long B = fact[n-r] * fact[r] % P;

        return A * findMMI(B, P-2) % P;
    }

    public static long findMMI(long B, long p) {
        if(p == 0)
            return 1;
        else if(p == 1)
            return B;
        else {
            long half = findMMI(B, p/2);

            if(p % 2 == 0)
                return (half * half) % P;
            else
                return  ((half * half) % P * B) % P;
        }
    }

    public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);

        long TC;
        long test_case;

        fact[0] = 1;

        for(int i = 1; i < 2000003; i++)
            fact[i] = i * fact[i-1] % P;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.
            N = sc.nextInt();
            M = sc.nextInt();

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(combination(M+N+2, N+1) - 1);
        }
    }
}