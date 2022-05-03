package LinkedList;
import java.util.Stack;

public class IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    public static boolean IsPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        // 压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 遍历原链表和栈内弹出数据做比较
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    public static boolean IsPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;  //慢指针
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean IsPalindrome3(Node head) {
        if (head == null && head.next == null) {
            return true;
        }
        Node n1 = head; // 慢指针
        Node n2 = head; // 快指针
        // find the mid node
        while (n2.next != null && n2.next.next != null) { // 短路逻辑，当其中一方为null时，后面不断直接返回，没有空指针异常
            n1 = n1.next;  // n1 -> mid
            n2 = n2.next.next;  // n2 -> mid
        }
        // reverse the second half linked list
        n2 = n1.next;  // n2 -> right part first node
        n1.next = null;  // mid.next -> null
        Node n3 = null;
        while (n2 != null) {  //right part convert
            n3 = n2.next;  // n3 -> save next node
            n2.next = n2;  // next of right node convert
            n1 = n2;  // n1 move
            n2 = n3;  // n2 move
        }
        n3 = n1;  // n3 -> save last node
        n2 = head;  // n2 -> left first node
        boolean res = true;  // 先定义res为true
        // check palindrome
        while (n1 != null  && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;   // left to mid 此时n1和n2变为分别代表两侧的指针，每次向中间走一步
            n2 = n2.next;   // right to mid
        }
        // recover list
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {  
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
