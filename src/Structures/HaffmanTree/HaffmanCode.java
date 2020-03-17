package Structures.HaffmanTree;

import java.util.*;

public class HaffmanCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();

        List<Symbol> symbols = getSymbols(inputText);
        List<HaffmanTree> trees = new ArrayList<>();

        int idx = 0;
        for (Symbol symbol : symbols) {
            trees.add(new HaffmanTree());
            trees.get(idx++).insert(symbol.getFrequency(), symbol.getChar());
        }

        PriorityQueue<HaffmanTree> priorityQueueTrees = new PriorityQueue<>();
        for (int i = 0; i < trees.size(); i++) {
            priorityQueueTrees.add(trees.get(i));
        }

        while (priorityQueueTrees.size() > 1) {
            HaffmanTree tree1 = priorityQueueTrees.poll();
            HaffmanTree tree2 = priorityQueueTrees.poll();

            HaffmanTree newTree = new HaffmanTree();
            newTree.insert(tree1.getRoot().getKey() + tree2.getRoot().getKey(), "");
            newTree.getRoot().setLeftChild(tree1.getRoot());
            newTree.getRoot().setRightChild(tree2.getRoot());
            priorityQueueTrees.add(newTree);
        }

        HaffmanTree codes = priorityQueueTrees.poll();

        codes.generateCodes(codes.getRoot(), "0", symbols);

        System.out.println("Input message is:");
        System.out.println(inputText);
        System.out.println("Coded message is:");
        System.out.println(codeMessage(inputText, symbols));
    }

    private static String codeMessage(String stringToEncode, List<Symbol> codes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stringToEncode.length(); i++) {
            for (int j = 0; j < codes.size(); j++) {
                if (stringToEncode.charAt(i) == codes.get(j).getChar()) {
                    result.append(codes.get(j).getCode());
                }
            }
        }

        return result.toString();
    }

    private static List<Symbol> getSymbols(String inputString) {
        char[] parsedString = inputString.toCharArray();
        List<Symbol> symbols = new ArrayList<>();

        Arrays.sort(parsedString);

        int idx = 0;
        symbols.add(new Symbol(parsedString[0]));
        for (int i = 1; i < parsedString.length; i++) {
            if (symbols.get(idx).getChar() == parsedString[i]) {
                symbols.get(idx).addFrequency();
            } else {
                symbols.add(new Symbol(parsedString[i]));
                ++idx;
            }
        }

        return symbols;
    }
}
