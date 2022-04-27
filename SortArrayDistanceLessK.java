import java.util.PriorityQueue;

public class SortArrayDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        //默认min-heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for(; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }


    public static void main(String[] args) {
        int[] arr = {2,3,4,2,4,64,24,2324,45,324,2,345,2,45,1};
        int k = 3;
        sortedArrDistanceLessK(arr, k);
    }
}
