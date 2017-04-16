import java.util.ArrayList;
import java.util.Scanner;

class _44_NORMAL1_OnlyKimHappy {
    public static int N;
    public static int[] vertices;
    public static SimpleQueue q;
    public static ArrayList<ArrayList<Integer>> adjacencyList;

    public static class SimpleQueue {
        ArrayList<Integer> sq;

        public SimpleQueue() {
            sq = new ArrayList<>();
        }

        int dequeue() {
            return sq.remove(0);
        }

        void enqueue(int e) {
            sq.add(e);
        }

        boolean isEmpty() {
            return sq.isEmpty();
        }
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            int I = sc.nextInt();

            adjacencyList = new ArrayList<>();

            for(int i = 0; i < N; i++)
                adjacencyList.add(new ArrayList<Integer>());

            for(int i = 0; i < I; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                adjacencyList.get(a).add(b);
                adjacencyList.get(b).add(a);
            }

            // vertices[i] := 0이면 다스리는 사람이 없는 vertex이고, 1이면 A가문이, -1이면 B가문이 다스리는 vertex
            vertices = new int[N];
            q = new SimpleQueue();
            boolean revolt = false;

            // 모든 vertex로부터 시작 가능하도록 만들었다.
            // 테스트 케이스 중에 그래프가 나뉘어져 있는 경우가 있어서 이 경우를 잡기 위해 이렇게 만들었다.
            for(int s = 0; s < N; s++) {

                // s지역을 다스리는 사람이 없는 경우에만 큐에 해당 지역을 넣고 A가문이 다스리도록 한다.
                // bfs방식을 사용하기 때문에 하나의 연결된 그래프는 모두 가문이 배정되므로
                // s지역을 다스리는 사람이 없는 경우 s를 포함하는 연결된 그래프에 소속된 지역 모두에는 다스리는 가문이 없다
                if (vertices[s] == 0) {
                    q.enqueue(s);
                    vertices[s] = 1;
                }

                // 기본적으로 Breadth-first search방식을 사용하여 가문을 부여한다
                // Depth-first search방식으로도 구현 가능하지만 비효율적이기 때문이다.
                while (!q.isEmpty() && !revolt) {
                    int current = q.dequeue();
                    ArrayList<Integer> adjacent = adjacencyList.get(current);

                    for (int near : adjacent) {
                        // 현재 지역의 주변 지역이 아무도 다스리지 않는 지역의 경우,
                        // 현재 지역을 다스리는 가문이 아닌 다른 가문을 부여한다.
                        if (vertices[near] == 0) {
                            vertices[near] = (-1) * vertices[current];
                            q.enqueue(near);
                        } else {
                            // 주변 지역을 다스리는 가문이 있는 경우,
                            // 현재 지역의 가문과 주변 지역의 가문이 같으면 반란의 위험이 있다.
                            revolt = (vertices[near] == vertices[current]);
                        }
                    }
                }

                // 반란 가능성이 있는 지역이 하나라도 있으면 반란 가능성이 있는 것이기 때문에 바로 종료한다.
                if(revolt) break;
            }

            System.out.println("Case #" + test_case);
            System.out.println(revolt ? 0 : 1);
        }
    }
}