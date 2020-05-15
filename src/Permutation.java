import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int printElementsCount = Integer.parseInt(args[0]);
        RandomizedQueue<String> elementsQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            elementsQueue.enqueue(StdIn.readString());
        }

        int printedElements = 0;
        for (String element : elementsQueue) {
            if (printedElements >= printElementsCount) {
                break;
            }
            StdOut.println(element);
            printedElements++;
        }
    }
}
