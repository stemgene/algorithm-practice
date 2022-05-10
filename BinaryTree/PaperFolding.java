package BinaryTree;

public class PaperFolding {
    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    // 递归过程，来到了某一个节点，
    // i是节点的层数，N一共的层数，down == true 代表凹，down == false 代表凸
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
