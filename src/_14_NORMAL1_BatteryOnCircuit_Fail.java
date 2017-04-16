/* Codeground 14. 회로판 위의 배터리(level NORMAL 1)
input
2
2
1 1 10 1
4 2 5 2
8
3 6 3 10
4 7 10 7
7 2 7 9
11 1 11 10
1 5 13 5
4 3 8 3
2 2 4 2
9 2 15 2

output
Case #1
1.5
Case #2
5
 */
/*
    회고
    결국 다 못했다. 어떻게 푸는지 잘 모르겠다. 너무 특정 수학 공식과 연관되어있는 문제인것 같다.
    180점까지 됐는데 여기서 뭘 더 어떻게 해야될지는 모르겠다.
 */
/* 아래 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
   단, 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */
import java.util.Scanner;

class _14_NORMAL1_BatteryOnCircuit_Fail {

    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        int distanceX(int x) {
            return Math.min(Math.abs(this.x1 - x), Math.abs(this.x2 - x));
        }

        int distanceY(int y) {
            return Math.min(Math.abs(this.y1 - y), Math.abs(this.y2 - y));
        }
    }

    public static int N;
    public static Line[] lines;

    public static int minMaxDistance() {

        int minDx = 100_000_000;
        int minDy = 100_000_000;

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] midX = new int[4];
                int temp, maxX = 0;
                int minX = 100_000_000;

                midX[0] = (lines[i].x1 + lines[j].x1) / 2;
                midX[1] = (lines[i].x1 + lines[j].x2) / 2;
                midX[2] = (lines[i].x2 + lines[j].x1) / 2;
                midX[3] = (lines[i].x2 + lines[j].x2) / 2;
                
                for(int k = 0; k < 4; k++) {

                    for(int n = 0; n < N; n++) {
                        temp = lines[n].distanceX(midX[k]);
                        maxX = maxX > temp ? maxX : temp;
                    }
                        
                    minX = minX < maxX ? minX : maxX;
                }
                    
                minDx = minDx < minX ? minDx : minX;
            }
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] midY = new int[4];
                int temp, maxY = 0;
                int minY = 100_000_000;

                midY[0] = (lines[i].y1 + lines[j].y1) / 2;
                midY[1] = (lines[i].y1 + lines[j].y2) / 2;
                midY[2] = (lines[i].y2 + lines[j].y1) / 2;
                midY[3] = (lines[i].y2 + lines[j].y2) / 2;

                for(int k = 0; k < 4; k++) {

                    for(int n = 0; n < N; n++) {
                        temp = lines[n].distanceY(midY[k]);
                        maxY = maxY > temp ? maxY : temp;
                    }

                    minY = minY < maxY ? minY : maxY;
                }

                minDy = minDy < minY ? minDy : minY;
            }
        }

        return minDx > minDy ? minDx : minDy;
    }

    public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.
            N = sc.nextInt();
            lines = new Line[N];
            for(int n = 0; n < N; n++)
                lines[n] = new Line(2 * sc.nextInt(), 2 * sc.nextInt(), 2 * sc.nextInt(), 2 * sc.nextInt());

            int result = minMaxDistance();

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);

            if(result % 2 == 0)
                System.out.println(result/2);
            else {
                System.out.print(result/2);
                System.out.println(".5");
            }

        }
    }
}