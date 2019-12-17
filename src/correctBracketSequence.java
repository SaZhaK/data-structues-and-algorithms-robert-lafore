import Structures.Stack;

import java.util.Scanner;

public class correctBracketSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int maxSize = str.length();
        Stack stack = new Stack(maxSize);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')' && stack.pop() != '(') {
                System.out.println("Error: " + " ( " + " bracket is missing at " + (i + 1));
                return;
            } else if (str.charAt(i) == ']' && stack.pop() != '[' ) {
                System.out.println("Error: " + " [ " + " bracket is missing at " + (i + 1));
                return;
            } else if (str.charAt(i) == '}' && stack.pop() != '{') {
                System.out.println("Error: " + " { " + " bracket is missing at " + (i + 1));
                return;
            }
        }

        System.out.println("Sequence is correct");
    }
}
