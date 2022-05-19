package Recursion;

import java.util.ArrayList;
import java.util.List;

public class AllSubsquences {
    public static void allSubsquences(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }


    // 省空间做法
    // str出发，到i位置时，要或不要，走两条路
    // 之前的选择所形成的结果，是str。str是可以改的
    public static void process1(char[] chs, int i) {
        if (i == chs.length) {  //i来到终止位置
            System.out.println(String.valueOf(chs));
            return;
        }
        process1(chs, i + 1); // 要当前字符的路
        char tmp = chs[i]; // 把当前字符记一下
        chs[i] = 0;  // 把当前字符改为0，ASCII chart表中，0代表null，32代表空格，65代表A
        process1(chs, i + 1); // 不要当前字符的路
        chs[i] = tmp; // 走完不要字符这条路后，再把当前字符改回来
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0, new ArrayList<Character>());
    }

    // 经典算法，从左往右每个字符要跟不要做决策
    // str出发，到i位置时，要或不要，走两条路
    // res：之前的选择所形成的char类型的列表
    public static void process2(char[] str, int i, List<Character> res) {
        if (i == str.length) { // i来到终止位置
            printList(res); // 打印之前的选择
            return;
        }
        List<Character> resKeep = copyList(res); // 把之前的选择copy一份出来赋给resKeep
        resKeep.add(str[i]); // 把当前字符加进去
        process2(str, i+1, resKeep); // 要当前字符的路
        List<Character> resNoInclude = copyList(res); 
        process2(str, i+1, resNoInclude); // 不要当前字符的路
    }

    public static void printList(List<Character> res) {

    }

    public static List<Character> copyList(List<Character> list) {
        return null;
    }
}
