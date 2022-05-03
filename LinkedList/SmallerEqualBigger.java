package LinkedList;
public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一：创建array比对方法
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }

    // 高级方法：空间复杂度O(1)
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;  // small head
        Node sT = null;  // small tail
        Node eH = null;  // equal head
        Node eT = null;  // equal tail
        Node mH = null;  // big head
        Node mT = null;  // big tail
        Node next = null;  // save next node
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            // 小于区域头尾
            if (head.value < pivot) {
                if (sH == null) { // 如果是第一个节点
                    sH = head;
                    sT = head;
                } else { // 如果不是第一个节点
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {  // 等于区域
                if (eH == null) {  // 如果是第一个节点
                    eH = head;
                    eT = head;
                } else { // 不是第一个节点
                    eT.next = head;
                    eT = head;
                }
            } else {  // 大于区域
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mH.next = head;
                    mH = head;
                }
            }
            head = next;
        }
        // small and equal reconnect
        if (sT != null) {  // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT;  // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 上面的if，不管跑了没有，et
        // all reconnect
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.println("Linked List: ");
        while (node != null) {
            System.out.println(node.value + " ");
            node = node.next;
        }
    }
}
