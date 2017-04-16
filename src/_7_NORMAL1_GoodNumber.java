/*
 * 풀이
 * 기본적으로 좋은 수라는 것은 자기 이전에 나온 수들을 중복을 포함해서 3번 골랐을 때 나올 수 있는 수라고 할 수 있습니다.
 * 따라서 먼저 나온 수들이 등장한 횟수와 위치가 상관이 없습니다. 따라서 자연스럽게 이전의 수들을 모으는 자료구조를 Set으로 고를 수 있습니다.
 * OneSet은 하나를, TwoSet은 둘을, ThreeSet은 셋을 골랐을때 나올수 있는 수의 집합입니다.
 * 당연히 좋은 수를 판별하는 그 순간에 이전 수들에 의해 만들어진 ThreeSet에 현재의 수가 포함되어있다면 현재의 수는 좋은 수입니다.
 * 이걸 판별한 이후에는 현재의 수를 각각의 Set에 넣는 작업이 필요합니다.
 * OneSet에는 그냥 집어 넣으면 됩니다.
 * TwoSet에는 OneSet에 있는 모든 수에 자기 자신을 더해서 넣으면 됩니다.
 * ThreeSet에는 TwoSet에 있는 모든 수에 자기 자신을 더해서 넣으면 됩니다.
 * Set 자료구조의 특성상 자동으로 중복이 제거됩니다.
 * 그리고 자기 자신을 중복해서 넣는 경우도 OneSet부터 수를 넣는 것을 통해 포함됩니다.
 *
 * 그러나, 시간의 한계로 모두를 Set으로 만드는 경우에는 시간 제한에 걸리고 맙니다. 아마도 C++로 만들 경우에는 이렇게 만들어도 시간 제한에 안걸릴 것으로 보입니다만 자바에서는 시간 제한에 걸립니다.
 * 그래서 속도를 줄이기 위한 테크닉으로 마지막 ThreeSet대신에 Three라는 boolean 배열을 만드는 것입니다.
 * 나올 수 있는 수는 -100,000 ~ 100,000 이므로 200,001의 크기의 배열을 선언하여 해당 위치의 수가 좋은 수에 포함되는지를 나타냅니다.
 * 이로써 시간 제한 안에 풀 수 있었습니다.
 */

import java.util.HashSet;
import java.util.Scanner;

class _7_NORMAL1_GoodNumber {
    public static HashSet<Integer> OneSet;
    public static HashSet<Integer> TwoSet;
    //public static HashSet<Integer> ThreeSet;

    public static boolean[] Three;

    public static void insert(int num) {
        if(OneSet.contains(num))
            return;

        OneSet.add(num);

        int temp;

        for(Integer one : OneSet) {
            temp = one + num;
            TwoSet.add(temp);
        }

        for(Integer two : TwoSet) {
            temp = two + num;
            if(-100000 <= temp && temp <= 100000)
                Three[temp + 100000] = true;

            //ThreeSet.add(temp);
        }
    }

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.
            OneSet = new HashSet<>();
            TwoSet = new HashSet<>();
            //ThreeSet = new HashSet<>();
            Three = new boolean[200001];

            int N = sc.nextInt();

            int count = 0;

            int[] num = new int[N];

            for(int n = 0; n < N; n++)
                num[n] = sc.nextInt();

            for(int n = 0; n < N; n++) {
                //if(ThreeSet.contains(num[n]))
                if(Three[num[n] + 100000])
                    count++;

                insert(num[n]);
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(count);
        }
    }
}