package Recursion;
public class NQueens {
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record[i] -> i行的queen，放在了第几列，用一个值表示就够了。index是第i行，value是第几列
        int[] record = new int[n]; 
        return process1(0, record, n);
    }

    // record[0,...i - 1]的皇后，任何皇后一定都不共行共列，不共斜线
    // i：现在处在第i行，
    // record：之前的皇后都存放在record[0,...i-1]中
    // n：整体一共有n行
    // 返回值：摆完所有的皇后，合理的摆法有多少种
    public static int process1(int i, int[] record, int n) {
        if (i == n) {  // 终止行，比最后一行再往下一行
            return 1;  // 此时record中的记录就是正确的结果
        }
        int res = 0;
        for (int j = 0; j < n; j++) {  // 当前行在i行，尝试i行所有列 -> j
            // 当前i行的皇后，放在j列，会不会和之前(0...i-1)的皇后，共行共列或者公斜线
            // 如果是，认为无效
            // 如果不是，认为有效
            if (isValid(record, i, j)) {
                record[i] = j; // 走一个深度
                res += process1(i + 1, record, n);  // 统计所有的情况累加到一起
            }
        }
        return res;
    }

    // record[0, ... i - 1]需要看，record[i,...]不需要看
    // 返回i行皇后，放在了j列，是否有效
    // 不需要检查共行。只需要看两件事：共不共列，或跟之前的皇后共不共斜线
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {  // 之前的某个k行的皇后
            // 检查共列和共斜线(45度或135度)
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    // 用位运算优化，速度很快
    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果是n皇后问题，就生成二进制的数。
        // 如8皇后，生成一个数后8位都是1，前面都是0，即0...011111111
        // 再如9皇后，生成一个数后9位都是1，前面都是0，即0...0111111111
        // 以上的数不需要使用具体的值，值没有意义，只使用位信息
        int limit = n == 32 ? -1 : (1 << n) - 1; // 0...0 11111111 当n==8时
        return process2(limit, 0, 0, 0);
    }

    // colLim列的限制，1的位置不能放皇后，0的位置可以
    // leftDiaLim左斜线的限制，1的位置不能放皇后，0的位置可以
    // rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // base case：当列上填的皇后==limit，代表所有皇后都填上了，发现了一种有效的填法。
        if (colLim == limit) {
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        // 总限制 = 列的限制 || 左对角线限制 || 右对角线限制。-> 0...0 00111000
        // 总限制取反：1可以放皇后，0不能放皇后 -> 1...1 11000111
        // limit & 总限制取反 0...0 11111111 & 1...1 11000111 -> 0...0 11000111，就是可以放置皇后的位置。
        // 可以看到limit的作用就是把左侧的若干个1给截取掉
        // 所有可以填皇后的列，都在此pos位信息上
        pos = limit & (~(colLim | leftDiaLim | rightDiaLim)); // pos
        int res = 0;
        while (pos != 0) {
            // 79-80行的目的是依次试最右侧的1是否符合皇后要求，然后“剪掉”变成0，接着试次右侧的1，依次循环
            mostRightOne = pos & (~pos + 1); // 位运算：提取出候选皇后位置状态中最右侧的1
            pos = pos - mostRightOne;
            // 试验的步骤：res累加每种尝试
            res += process2(limit, 
                            colLim | mostRightOne,  // 列限制是这两个或
                            (leftDiaLim | mostRightOne) << 1, //左斜线的限制是或完成后统一左移
                            (rightDiaLim | mostRightOne) >>> 1); //右斜线的限制是或完成后统一右移
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start) + "ms");
    }
}
