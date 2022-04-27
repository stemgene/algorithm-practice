import java.util.PriorityQueue;

public class PriorityQueueExample {

    public static class AComp implements Comparator<integer> {
        // 如果返回负数，认为第一个参数应该放在上面
        // 如果返回正数，认为第二个参数应该放在上面
        // 如果返回0， 认为谁放在上面都行
        @Override
        public int compare(Integer arg0, Integer arg1) {
            return arg1 - arg0;
        }
    }
    public static void main(String[] args) {
        // 默认是min-heap，但如果放上自定义的比较器AComp，可以返回max-heap
        PriorityQueue<String> heap = new PriorityQueue<>();

        heap.add("abcd");
        heap.add("1234");
        heap.add("qfew");
        heap.add("hww");
        heap.add("bsdfs");
        heap.add("242rf");

        while (!heap.isEmpty()) { // 每poll弹出一次，heap的size就减少1，直到empty
            System.out.println(heap.poll());
        }

    }
}

/*
1234
242rf
abcd
bsdfs
hww
qfew
*/