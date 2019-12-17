import Structures.Stack;

import java.util.Scanner;

public class RPN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();

        String rpn = getPostfixStr(inputStr);
        System.out.println("Reversed polish notation is: " + rpn);

        System.out.println("Result of the equation is: " + getResult(rpn));
    }

    public static int getResult(String postfixString) {
        Stack stack = new Stack(postfixString.length());

        for (int i = 0; i < postfixString.length(); i++) {
            int curSymbol = postfixString.charAt(i);
            if (curSymbol >= '0' && curSymbol <= '9') {
                stack.push(curSymbol-'0');
            } else {
                int result = 0;
                int num2 = stack.pop();
                int num1 = stack.pop();
                if ((char) curSymbol == '+') {
                    result = num1 + num2;
                } else if ((char) curSymbol == '-') {
                    result = num1 - num2;
                } else if ((char) curSymbol == '*') {
                    result = num1 * num2;
                } else if ((char) curSymbol == '/') {
                    result = num1 / num2;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static String getPostfixStr(String inputStr) {
        String postfixStr = "";

        int maxSize = inputStr.length();
        Stack stack = new Stack(maxSize);

        for (int i = 0; i < inputStr.length(); i++) {
            char curChar = inputStr.charAt(i);
            if (curChar >= '0' && curChar <= '9') {
                postfixStr += curChar;
            } else if (curChar == '(') {
                stack.push(curChar);
            } else if (curChar == ')') {
                while (!stack.isEmpty()) {
                    if (stack.peek() != '(') {
                        postfixStr += (char) stack.pop();
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
                                postfixStr += (char) stack.pop();
                            } else if ((stack.peek() == '+' || stack.peek() == '-') && (curChar == '+' || curChar == '-')) {
                                postfixStr += (char) stack.pop();
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
            postfixStr += (char) stack.pop();
        }

        return postfixStr;
    }
}
