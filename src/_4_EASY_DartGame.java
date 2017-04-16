/*
    잘못한 점
    getDegree에서 X, Y가 0보다 큰지 아닌지에 따라서 다루는 방식이 다른데 그걸 간과하고 있었다. 도형 관련된 것에서는 이런 부분에 조금 더 주의하도록 하자.
 */

import java.util.Scanner;

class _4_EASY_DartGame {

    public static int A, B, C, D, E, N;

    public static double getRadius(int X, int Y) {
        return Math.sqrt(X * X + Y * Y);
    }

    public static double getDegree(int X, int Y) {
        if(0 <= X && 0 <= Y)
            return Math.toDegrees(Math.atan(Y / (double) X));
        else if(0 <= X && Y < 0)
            return 360 + Math.toDegrees(Math.atan(Y / (double) X));
        else
            return 180 + Math.toDegrees(Math.atan(Y / (double) X));
    }

    public static int degreeToPoint(double degree) {
        int[] score = {6,13,4,18,1,20,5,12,9,14,11,8,16,7,19,3,17,2,15,10};

        degree += 9;
        degree %= 360;
        degree /= 18;

        return score[(int) degree];
    }

    public static int getPoint(int X, int Y) {
        double radius = getRadius(X, Y);
        double degree = getDegree(X, Y);

        if(radius < A) { //case5 BULL
            return 50;
        } else if(E < radius) { //case4 OUT BOARD
            return 0;
        }

        int point = degreeToPoint(degree);
        int mult;

        if(B < radius && radius < C) { //case3 TRIPLE
            mult = 3;
        } else if(D < radius && radius < E) { //case2 DOUBLE
            mult = 2;
        } else { //case1 SINGLE
            mult = 1;
        }

        return mult * point;
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();

        for(test_case = 1; test_case <= TC; test_case++) {
            A = sc.nextInt();
            B = sc.nextInt();
            C = sc.nextInt();
            D = sc.nextInt();
            E = sc.nextInt();

            N = sc.nextInt();

            int total = 0;

            for(int n = 0; n < N; n++)
                total += getPoint(sc.nextInt(), sc.nextInt());

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(total);
        }
    }
}