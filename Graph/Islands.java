package Graph;

public class Islands {
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length;  // 行
        int M = m[0].length; // 列
        int res = 0;
        // 从左往右再从上往下遍历
        for (int i = 0; i < N; i++) {   // 行
            for (int j = 0; j < M; j++) {  // 列
                if (m[i][j] == 1) { // 如果当前位置是1
                    res++;  // 岛的数量++
                    infect(m, i, j, N, M);  // 进入感染过程
                }
            }
        }
        return res;
    }

    // 参数中只有i和j是可变的，代表当前位置，其余的都是固定参数
    public static void infect(int[][] m, int i, int j, int N, int M) {
        // 行 < 0 || 行到了终止位置 || 列 < 0 || 列到了终止位置 || 当前位置不是1
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        // i, j没越界，并且当前位置值是1
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i i 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 0, 1, 1, 1, 0},
                        {0, 0, 1, 1, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }
}
