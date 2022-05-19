package Recursion;

public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "left", "right", "center");
        }
    }

    // 1～n 圆盘 目标是from -> to, other是另外一个（中间的那个）
    public static void func(int i, String start, String end, String other) {
        if ( i == 1) { // base case: 只剩下最上面的圆盘，直接移到to上
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            func(i - 1, start, other, end);
            System.out.println("Move " + i + " from " + start + " to " + end);
            func(i - 1, other, end, start);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Start----");
        hanoi(n);
    }
}
