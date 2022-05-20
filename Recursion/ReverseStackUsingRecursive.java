package Recursion;

import java.util.Stack;

public class ReverseStackUsingRecursive {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while(!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }


    /**
     * 调试用代码
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        System.out.println("i in reverse = " + i);
        reverse(stack);
        stack.push(i);
        System.out.println("stack.push(i)");
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        System.out.println("int result = stack.pop(), result = " + result);
        if (stack.isEmpty()) {
            System.out.println("Stack is empty, and result = " + result);
            return result;
        } else {
            System.out.println("stack is not null. int last = f(stack)");
            int last = f(stack);
            System.out.println("last = " + last);
            stack.push(result);
            System.out.println("stack.push(result). result = " + result);
            System.out.println("return last. last = " + last);
            return last;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to Online IDE!! Happy Coding :)");
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        // test.push(4);
        // test.push(5);
        reverse(test);
        while(!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
    */
}
