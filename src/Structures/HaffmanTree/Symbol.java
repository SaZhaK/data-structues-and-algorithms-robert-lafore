package Structures.HaffmanTree;

public class Symbol{
    private char ch;
    private int frequency;
    private String code;

    public Symbol(char ch) {
        this.ch = ch;
        this.frequency = 1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getChar() {
        return ch;
    }

    public void setChar(char ch) {
        this.ch = ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public void addFrequency() {
        this.frequency++;
    }
}