import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev = null;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (last == null) {
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (last == null) {
            addFirst(item);
            return;
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node next = first.next;
        Item item = first.item;
        first = next;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        last.next = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> intDeck = new Deque<>();

        StdOut.println(String.format("size: %s", intDeck.size()));
        intDeck.addLast("odin");
        intDeck.addFirst("dva");
        intDeck.addLast("tri");
        intDeck.addLast("cheshire");
        intDeck.addFirst("piat");
        StdOut.println(String.format("size: %s", intDeck.size()));
        StdOut.println(String.format("removed item: %s", intDeck.removeFirst()));
        StdOut.println(String.format("removed item: %s", intDeck.removeFirst()));
        StdOut.println(String.format("removed item: %s", intDeck.removeLast()));
        StdOut.println(String.format("size: %s", intDeck.size()));
        intDeck.addFirst("siest");
        intDeck.addLast("siem");
        StdOut.println(String.format("size: %s", intDeck.size()));

        for (String i : intDeck) {
            StdOut.println("iteration-item: " + i);
            StdOut.println(
                String.format("removed item: %s, is empty: %s",
                    intDeck.removeFirst(),
                    intDeck.isEmpty()
                ));
        }
    }
}
