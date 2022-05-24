package LinkedList;

public class BitMap {
    public static void main(String[] args) {
        int a = 0;

        // a 32 bit

        int[] arr = new int[10]; // 32bit * 10 == 320bits

        // arr[0] int 0 ~ 31
        // arr[1] int 32 ~ 63
        // arr[2] int 64 ~ 95

        // 想取得178个bit的状态. 
        int i = 178; 

        // Step 1. 定位第178应该在arr数组的哪一个index上
        int numIndex = 178 / 32;
        // Step 2. 定位在具体的哪一bit上
        int bitIndex = 178 % 32;
        // Step 3. 获得178位的状态. 从头位置向右移动bitIndex位，获得这个数，然后和1与操作。
        // 状态如果为0，返回int 0， 状态如果为1，返回int 1
        int s = ((arr[numIndex] >> (bitIndex)) & 1);

        // 把178位的状态改为1. 先左移，再或运算
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));
        
        // 把178位的状态改为0.
        arr[numIndex] = arr[numIndex] & (~(1 << bitIndex));
    }
}
