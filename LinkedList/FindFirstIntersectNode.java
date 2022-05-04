package LinkedList;

public class FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);   // 先调用链表1，看是否有loop并得到第一个节点loop1
        Node loop2 = getLoopNode(head2);   // 同上
        // 两个无环链表相交问题
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 两个有环链表的相交问题
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一个为空，另一个不为空，不相交
        return null;
    }

    // 找到链表第一个入环点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;  // n1 -> slow
        Node n2 = head.next.next;  // n2 -> fast
        while(n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;  // 一次走两步
            n1 = n1.next;  // 一次走一步
        }
        n2 = head;  // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;  // length
        while (cur1.next != null) {  // 来到最后一个节点停下，而不是走到null才停
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {  // 同上
            n--;
            cur2 = cur2.next;
        }
        // 如果链表1的最后一个节点 ！= 链表2的最后一个节点，一定不相交
        if (cur1 != cur2) {  
            return null;
        }
        // n == head1.length - head2.length
        cur1 = n > 0 ? head1 : head2;  // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1;  // 谁短，谁的头变成cur2
        n = Math.abs(n);
        // 长链表先走差值步
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        // 长短链表共同比对
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交节点，如果不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 情况二：具有相同的入环点
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {  // loop1位终止节点
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {  // 同上
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {  // loop1 != loop2
            cur1 = loop1.next;  // cur1来到loop1.next的位置，然后一直往下走
            while (cur1 != loop1) {  // cur1在回到loop1之前
                if (cur1 == loop2) {  // 如果遇到loop2
                    return loop1;  // 返回loop1或loop2都行，都叫第一个相交节点
                }
                cur1 = cur1.next;
            }
            return null;  // 如果转回到自己都没遇到loop2，不相交
        }
    }
}
