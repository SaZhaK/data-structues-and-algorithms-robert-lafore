package Structures;

public class HanoiTower {
    public static void main(String[] args) {
        int amountOfDiscs = 3;
        char  kernel1 = 'A', kernel2 = 'B', kernel3 = 'C';
        moveDiscs(amountOfDiscs, kernel1, kernel2, kernel3);
    }

    static void moveDiscs(int topN, char from, char inter, char to) {
        if (topN == 1) System.out.println("Move disc 1 from " + from + " to " + to);
        else {
            moveDiscs(topN - 1, from, to, inter);
            System.out.println("Move disc " + topN + " from " + from + " to " + to);
            moveDiscs(topN-1, inter, from, to);
        }
    }
}
