/* Codeground 15. 캠퍼스와 도로(1) (level NORMAL 2)
input
2
6 7
6 3 3
6 2 3
1 2 2
2 5 8
5 3 9
2 4 2
3 1 1
7 10
6 7 7
7 4 4
1 2 3
3 4 4
6 4 10
7 3 4
3 1 8
2 3 9
3 5 3
6 3 8

output
Case #1
3 4 5 6
Case #2
6 1 2 4 5 6 7
 */

/*
    회고
    1. 다익스트라 알고리즘에서 큐에 Vertex를 넣을때 실수로 목적지를 u로 설정했다. s-v의 거리가 s-u-v의 거리로 바뀌는 것이기 때문에 목적지를 v로 설정해야하는 것인데 경로인 u를 목적지로 만들어서 엄청나게 헤맸다.
    2. 플로이드 알고리즘과 다익스트라 알고리즘에서 경로를 저장하는 방식이 조금 다르다. 플로이드 알고리즘은 새로운 경로를 추가하는 방식이고 다익스트라 알고리즘은 새로운 정점을 추가하는 방식이라서 플로이드에서는 경로를 저장하게 되고 다익스트라에서는 이전 정점을 저장하게 된다. 이것에 유념하여 경로를 구할때 주의를 해야 한다.
    3. (중요!) 문제를 제대로 보지 않아서 점수가 계속 깎였다. 문제에서 요구 조건이 목적지로 가는 모든 최단 거리를 갖는 경로를 요구했는데 내가 처음 구현한 것은 하나의 경로만을 저장했다. 따라서 두개 이상의 최단 거리 경로를 갖는 문제에서는 문제가 생길수밖에 없었다. 이걸 너무 늦게 깨달았다.
 */

/* 아래 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
   단, 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class _15_NORMAL2_Campus_1 {
    static class Near implements Comparable<Near> {
        int destination, cost;

        public Near(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Near o) {
            return this.cost - o.cost;
        }
    }

    public static int N, M;
    public static ArrayList<ArrayList<Near>> adjacencyList;
    public static final int INF = 987_654_321;
    public static boolean[] via;

    public static void dijkstra(int s) {
        PriorityQueue<Near> Q = new PriorityQueue<>();
        ArrayList<ArrayList<Integer>> viaList = new ArrayList<>();

        // s부터 i까지의 최소거리를 저장할 d[]를 초기화
        int[] d = new int[N+1];

        for(int i = 1; i <= N; i++)
            d[i] = INF;

        d[s] = 0;

        // 시작점을 제외한 자신 위치로 가는 최단 경로에서 자기 이전의 길을 저장하는 리스트 초기화
        for(int i = 0; i <= N; i++)
            viaList.add(new ArrayList<Integer>());

        // 시작점으로 자기 자신을 큐에 삽입
        Q.add(new Near(s, d[s]));

        while(!Q.isEmpty()) {
            Near U = Q.poll();
            int u = U.destination;

            for(Near V : adjacencyList.get(u)) {
                int v = V.destination;
                ArrayList<Integer> Via = viaList.get(v);
                int alt = d[u] + V.cost;
                if(alt < d[v]) { // 새로운 최단거리를 발견하면 d[v]를 업데이트하고 Via를 처음부터 다시 만들고 경로 u를 추가한다.
                    d[v] = alt;
                    Via.clear();
                    if(s != u) Via.add(u);
                    Q.add(new Near(v, d[v]));
                } else if(alt == d[v]) { //  기존의 최단 거리와 같은 경로를 발견하면 Via에 경로 u를 추가한다.
                    Via.add(u);
                }
            }
        }

        //중복된 길을 포함해서 경로에 있는 대학 번호를 추가
        for(ArrayList<Integer> P : viaList)
            for(Integer p : P)
                via[p] = true;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);
        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            via = new boolean[N+1];
            adjacencyList = new ArrayList<>(N + 1);

            for(int i = 0; i <= N; i++)
                adjacencyList.add(new ArrayList<Near>());

            for(int i = 0; i < M; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int cost = sc.nextInt();
                adjacencyList.get(v1).add(new Near(v2, cost));
                adjacencyList.get(v2).add(new Near(v1, cost));
            }

            for(int i = 1; i <= N; i++)
                dijkstra(i);

            int count = 0;

            for(int i = 1; i <= N; i++)
                if(!via[i]) count++;

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.print(count);

            for(int i = 1; i <= N; i++)
                if(!via[i]) System.out.print(" " + i);

            System.out.println();
        }
    }
}