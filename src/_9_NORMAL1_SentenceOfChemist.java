/*
    회고
    맨처음 이 문제를 풀 때 잘못한 것은 이 문제가 동적 프로그래밍을 활용하는 문제라고 생각한 점이다. 그래서 재귀함수와 cache를 이용해서 문제를 풀려고 했다.
    그러다 보니 스택 오버플로우 문제가 생겼다. 재귀함수를 이용할 경우 그 함수의 최대깊이를 생각해보고 그것과 스택의 용량을 비교할 필요가 있는데 그러지 못했다.
    사실상 동적프로그래밍을 사용하는 문제가 맞긴 하다. 일단 문자의 맨처음 문자부터 살피면서 하나짜리로 없애든가 두개짜리로 없애면서 그 위치까지가 가지고 있는
    단어풀로 해결 가능한지를 확인하는 일이어서 크기가 하나 작거나 두개 작은 문제로 문제를 변환시킬수 있기 때문이다.
    이렇게 크기를 어떻게 하면 작게 하는지 생각하는 것이 중요하다.
    그런데 여기서는 굳이 그걸 재귀함수로 할 필요 없이 앞에서 부터 하나짜리로 하거나 두개짜리로 해서 해결되는지 확인하기만 하면 된다.
    또 하나의 포인트는 비효율적인 연산을 하지 않는 것이다. 자바에서 정렬된 배열을 대상으로 바이너리 서치를 제공하고 있다. 이런 API를 잘 알아두는 것도 문제 풀이에 유용하다.
 */

/* 문제를 푸실 때, 아래 "화학원소 기호"를 활용하시기 바랍니다.
   "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al",
   "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe",
   "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr",
   "Y","Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb",
   "Te", "I", "Xe", "Cs", "Ba", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au",
   "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Rf", "Db", "Sg",
   "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Fl", "Lv", "La", "Ce", "Pr", "Nd",
   "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Ac",
   "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md",
   "No", "Lr"   */

import java.util.Arrays;
import java.util.Scanner;

class _9_NORMAL1_SentenceOfChemist {
    public static String[] words = {
        "ac", "ag", "al", "am", "ar", "as", "at", "au", "ba", "be",
        "bh", "bi", "bk", "br", "ca", "cd", "ce", "cf", "cl", "cm",
        "cn", "co", "cr", "cs", "cu", "db", "ds", "dy", "er", "es",
        "eu", "fe", "fl", "fm", "fr", "ga", "gd", "ge", "he", "hf",
        "hg", "ho", "hs", "in", "ir", "kr", "la", "li", "lr", "lu",
        "lv", "md", "mg", "mn", "mo", "mt", "na", "nb", "nd", "ne",
        "ni", "no", "np", "os", "pa", "pb", "pd", "pm", "po", "pr",
        "pt", "pu", "ra", "rb", "re", "rf", "rg", "rh", "rn", "ru",
        "sb", "sc", "se", "sg", "si", "sm", "sn", "sr", "ta", "tb",
        "tc", "te", "th", "ti", "tl", "tm", "xe", "yb", "zn", "zr",
    };

    public static char[] chars = {
        'b', 'c', 'f', 'h', 'i', 'k', 'n',
        'o', 'p', 's', 'u', 'v', 'w', 'y'
    };

    public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

        int TC;
        int test_case;

        TC = sc.nextInt();
        sc.nextLine();
        for(test_case = 1; test_case <= TC; test_case++) {
            // 이 부분에서 알고리즘 프로그램을 작성하십시오.

            String str = sc.nextLine();

            int length = str.length();

            boolean[] one = new boolean[length];
            boolean[] two = new boolean[length];
            boolean[] make = new boolean[length];

            for(int i = 0; i < length; i++)
                if(0 <= Arrays.binarySearch(chars, str.charAt(i)))
                    one[i] = true;

            for(int i = 0; i < length-1; i++)
                if(0 <= Arrays.binarySearch(words, str.substring(i, i+2)))
                    two[i] = true;

            make[0] = one[0];
            make[1] = (one[0] && one[1]) || two[0];

            for(int i = 2; i < length; i ++) {
                make[i] = (one[i] && make[i - 1]) || (two[i - 1] && make[i - 2]);
            }

            // 이 부분에서 정답을 출력하십시오.
            System.out.println("Case #" + test_case);
            System.out.println(make[length - 1] ? "YES" : "NO");
        }
    }
}