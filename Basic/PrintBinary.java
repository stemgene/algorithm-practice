package Basic;

public class PrintBinary {

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            // 遍历num，从最左边第31位开始和1左移31位对比，相同是1，否则是0
            // 一个数字<<1，左移一位，右面用0填补，等同于*2
            System.out.print((num & (1<<i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int test = 1;
        print(test); // 00000000000000000000001
        print(test << 1); // 00000000000000000000010
        print(test << 2); // 00000000000000000000100

        // 32位
        int num = 83928328;

        print(num);


    }
}
