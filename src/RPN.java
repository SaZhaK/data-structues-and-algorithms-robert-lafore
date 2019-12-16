import java.util.Scanner;

public class RPN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        String postfixStr = "";

        int maxSize = inputStr.length();
        Stack stack = new Stack(maxSize);

        for (int i = 0; i < inputStr.length(); i++) {
            char curChar = inputStr.charAt(i);
            if (curChar >= 'A' && curChar <= 'Z') {
                postfixStr += curChar;
            } else if (curChar == '(') {
                stack.push(curChar);
            } else if (curChar == ')') {
                while (!stack.isEmpty()) {
                    if (stack.peek() != '(') {
                        postfixStr += (char)stack.pop();
                    } else if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                }
            } else if (curChar == '+' || curChar == '-' || curChar == '*' || curChar == '/') {
                if (stack.isEmpty()) {
                    stack.push(curChar);
                } else {
                    while (!stack.isEmpty()) {
                        if (stack.peek() == '(') {
                            break;
                        } else {
                            if (stack.peek() == '*' || stack.peek() == '/') {
                                postfixStr += (char)stack.pop();
                            } else if ((stack.peek() == '+' || stack.peek() == '-') && (curChar == '+' || curChar == '-')) {
                                postfixStr += (char)stack.pop();
                            } else {
                                break;
                            }
                        }
                    }
                    stack.push(curChar);
                }
            }
        }

        while (!stack.isEmpty()) {
            postfixStr += (char)stack.pop();
        }

        System.out.println(postfixStr);
    }
}
