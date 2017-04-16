import java.util.Arrays;
import java.util.Scanner;

class _19_NORMAL2_IntervalOverlapping {
    public static int[] rightestOf;
    public static Interval[] intervals;
    public static int N;

    static class Interval implements Comparable<Interval> {
        int left;
        int right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Interval o) {
            if(this.left == o.left)
                return o.right - this.right;
            else
                return this.left - o.left;
        }
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {

            N = sc.nextInt();

            // 완전 중첩된 집합에서 가장 왼쪽에 있는 end를 leftestEnd라 하자.
            // rightestOf[i]는 i개의 원소를 갖고 있는 완전 중첩 집합들의 leftestEnd 중에서 가장 오른쪽에 있는 값이다.
            rightestOf = new int[N];
            intervals = new Interval[N];

            for(int i = 0; i < N; i++)
                intervals[i] = new Interval(sc.nextInt(), sc.nextInt());

            Arrays.sort(intervals);

            int max = 0;

            for (int i = 0; i < N; ++i) {
                // 이 부분은 intervals[i]를 포함할 수 있는 완전 중첩 집합 원소 갯수의 최대값을 찾는 과정이다
                // possibleMin과 possibleMax는 intervals[i]를 포함할 수 있는 완전 중첩 집합의 원소 개수의 가능한 최소값과 최대값을 의미한다.
                // binarySearch를 사용하여 가능한 최소값과 최대값의 범위를 절반으로 줄이다가 두 값이 같아지면 반복을 멈춘다.
                int possibleMin = 0, possibleMax = max;
                while (possibleMin < possibleMax) {
                    int mid = (possibleMin + possibleMax) / 2;
                    // intervals[i]의 우측보다 rightestOf[mid]가 같거나 더 오른쪽에 있다는 것은
                    // intervals[i]를 포함할 수 있고 mid개 이상의 원소를 포함하는 완전 중첩함수가 존재한다는 것이다.
                    // 그리고 left가 작은순으로 정렬되어 있기 때문에
                    // (1 <= j <= i)를 만족하는 모든 정수 j에 대하여 intervals[j].left <= intervals[i].left
                    // 따라서 leftestOf[mid]를 정의한다면, 항상 leftestOf[mid] <= intervals[i].left를 만족하게 된다.
                    if (intervals[i].right <= rightestOf[mid]) possibleMin = mid + 1;
                    // intervals[i]의 우측보다 rightestOf[mid]가 왼쪽에 있다는 것은
                    // intervals[i]를 포함할 수 있고 mid개 이상의 원소를 포함하는 완전 중첩함수가 존재하지 않는다는 것이다.
                    else possibleMax = mid;
                }

                // 이 시점에서 possibleMax는 intervals[i]를 포함할 수 있는 완전 중첩 집합의 최대 원소의 개수가 된다.
                // rightestOf[possibleMax]를 rightest leftestOf[possibleMax]를 leftest라고 하자.
                // TODO: rightest <= intervals[i].right 증명
                rightestOf[possibleMax] = intervals[i].right;

                // possibleMax와 max가 같으면 max값을 증가시켜야 한다.
                // 왜냐하면 intervals[i]를 포함할수 있는 max개의 원소를 갖는 완전 중첩 집합에
                // intervals[i]를 포함시키면 원소의 수가 1증가하여 최대값이 증가하기 때문이다.
                if (possibleMax == max) max++;
            }

            System.out.println("Case #" + test_case);
            System.out.println(max);
        }
    }
}