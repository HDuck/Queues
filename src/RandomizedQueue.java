import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size = 0;
    private int last = -1;
    private int first = -1;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        if (isEmpty()) {
            queue[0] = item;
            last++;
            first++;
        }
        else {
            int nextIndex = last + 1;
            int prevIndex = first - 1;
            if (nextIndex < queue.length) {
                queue[++last] = item;
            }
            else if (prevIndex >= 0) {
                queue[--first] = item;
            }
        }
        size++;
    }

    private void resize(int newCapacity) {
        if (newCapacity == 0) {
            queue = (Item[]) new Object[1];
            return;
        }
        Item[] queueCopy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            queueCopy[i] = queue[i];
        }
        queue = queueCopy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(first, last + 1);
        Item item = queue[index];
        int nextIndex = index;
        while (nextIndex < last) {
            queue[nextIndex] = queue[++nextIndex];
        }
        queue[nextIndex] = null;
        size--;
        last--;
        if (isEmpty()) {
            first--;
        }
        if (size <= queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(first, last + 1);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    private class RandomListIterator implements Iterator<Item> {
        private boolean[] iteratedItems;
        private int iteratedCount = 0;


        public RandomListIterator() {
            iteratedItems = new boolean[queue.length];

            for (int i = 0; i < queue.length; i++) {
                iteratedItems[i] = false;
            }
        }

        public boolean hasNext() {
            return size > iteratedCount;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index;
            do {
                index = StdRandom.uniform(first, last + 1);
            } while (iteratedItems[index]);
            iteratedItems[index] = true;
            iteratedCount++;
            return queue[index];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> whoQ = new RandomizedQueue<>();
        RandomizedQueue<String> whatQ = new RandomizedQueue<>();

        StdOut.println(String.format("sizes who/what: %s / %s ", whoQ.size(), whatQ.size()));
        whatQ.enqueue("@");
        whoQ.enqueue("ti");
        whoQ.enqueue("ya");
        whatQ.enqueue("^");
        whatQ.enqueue("Ъ");
        whatQ.enqueue("Ь");
        whoQ.enqueue("tverdiy gnumme");

        int minSize = Math.min(whoQ.size(), whatQ.size());
        for (int i = 0; i < minSize; i++) {
            StdOut.println(String.format("samples who/what: %s / %s", whoQ.sample(), whatQ.sample()));
            StdOut.println(String.format("sizes who/what: %s / %s ", whoQ.size(), whatQ.size()));

            StdOut.println(String.format("dequeued who/what queue items: %s / %s", whoQ.dequeue(), whatQ.dequeue()));
            StdOut.println(String.format("sizes who/what: %s / %s ", whoQ.size(), whatQ.size()));

            StdOut.print(String.format("%s list: ", "who queue"));
            for (String j : whoQ) {
                StdOut.print(j + " ");
            }
            StdOut.println();

            StdOut.print(String.format("%s list: ", "what queue"));
            for (String j : whatQ) {
                StdOut.print(j + " ");
            }
            StdOut.println();
        }
    }
}
