package BinaryTree;
import java.util.ArrayList;
// check一棵树是否是搜索二叉树
import java.util.Stack;


public class CheckBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }


    // 方法一：递归，创建inOrderList存放所有值，
    public static boolean checkBST2(Node head) {
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        // 利用for loop检查list中内容是否升序
        // for loop
    }

    public static void process2(Node head, List<Node> inOrderList) {
        if(head == null) {
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);  //push to list的命令行写在left和right之间，中序遍历
        process2(head.right, inOrderList);
    }

    // 方法二：动态检查树中各节点是否从小到大排序
    // 创建一个变量记录上一次处理到的节点值，在遍历过程中上一次遇到的数就记下来
    public static long preValue = Long.MIN_VALUE;

    // 输入一棵树，return是否是搜索二叉树
    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = checkBST(head.left);
        if (!isLeftBST) {
            return false;
        }
        if (head.value <= preValue) {
            return false;
        } else {
            // 检查到现在为止，确定左侧所有都比当前的小
            preValue = head.value;
        }
        // 再遍历右子树，判断当前preVlaue
        // 如果右子树是，就是搜索二叉树，否则就不是
        // 最后return check右子树的遍历结果
        return checkBST(head.right);
    }

    // 方法三：非递归方式
    public static boolean checkBST3(Node head) {
        if (head != null) {

            // 创建变量：上次遍历到的值
            int preValue = Integer.MIN_VALUE;
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    //此处是中序遍历的操作位置，如要打印就在此打印，如要做别的操作也在此执行
                    if(head.value <= preValue) {
                        return false;
                    } else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            } 
        }
        return true;
    }
}
