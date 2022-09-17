package Misc.ConsoleApplications;

import java.util.Stack;

/**
 * "()" dictate what should be switched
 *
 * @author Jacob Swineford
 */
public class rev {

    public static void main(String[] args) {
        String s = "(dog(dog))";
        char[] se = s.toCharArray();

        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        for (int i = 0; i < se.length; i++) {
            if (se[i] == '(') left.push(i);
            else if (se[i] == ')') right.push(i);

            if (left.size() != 0 && right.size() != 0) {
                int l = left.pop();
                int r = right.pop();
                reverseFrom(l + 1, r - 1, se);
            }
        }
        System.out.println(ParenTrim(se));
    }

    static String ParenTrim(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ')' && arr[i] != '(')
                sb.append(arr[i]);
        }
        return sb.toString();
    }

    static void reverseFrom(int start, int end, char[] arr) {
        while (start != end && start < end) {
            char c = arr[start];
            arr[start] = arr[end];
            arr[end] = c;
            start++;
            end--;
        }
    }
}
