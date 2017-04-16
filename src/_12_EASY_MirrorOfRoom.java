import java.util.Scanner;

class _12_EASY_MirrorOfRoom {
    static class Light {
        char direction;
        int X;
        int Y;
        public static char[][] room;
        public static boolean[][] visited;

        int execute(int N) {

            while(canGo(N)) {
                nextLocation();
            }


            int result = 0;

            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(visited[i][j] && room[i][j] != '0')
                        result++;

            return result;
        }

        public Light(int N) {
            direction = 'R';
            X = 0;
            Y = 0;
            room = new char[N][N];
            visited = new boolean[N][N];
        }

        void nextLocation() {
            visited[X][Y] = true;

            switch(room[X][Y]) {
                case '0':
                    break;
                case '1':
                    switch(direction) {
                        case 'U':
                            direction = 'R';
                            break;
                        case 'D':
                            direction = 'L';
                            break;
                        case 'L':
                            direction = 'D';
                            break;
                        case 'R':
                            direction = 'U';
                            break;
                    }
                    break;
                case '2':
                    switch(direction) {
                        case 'U':
                            direction = 'L';
                            break;
                        case 'D':
                            direction = 'R';
                            break;
                        case 'L':
                            direction = 'U';
                            break;
                        case 'R':
                            direction = 'D';
                            break;
                    }
                    break;
            }

            switch(direction) {
                case 'U':
                    X--;
                    break;
                case 'D':
                    X++;
                    break;
                case 'L':
                    Y--;
                    break;
                case 'R':
                    Y++;
                    break;
            }
        }

        boolean canGo(int N) {
            if(X < 0 || N <= X)
                return false;
            if(Y < 0 || N <= Y)
                return false;

            return true;
        }
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            int N = sc.nextInt();
            sc.nextLine();

            Light l = new Light(N);

            String temp;


            for(int i = 0; i < N; i++) {
                temp = sc.nextLine();
                for(int j = 0; j < N; j++)
                    l.room[i][j] = temp.charAt(j);
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(l.execute(N));

        }
    }
}