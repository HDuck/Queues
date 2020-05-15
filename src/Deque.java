import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next = null;
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
        Node oldNode = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldNode;
        first = newNode;
        if (oldNode == null) {
            last = first;
        }
        else {
            oldNode.prev = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldNode = last;
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = oldNode;
        last = newNode;
        if (oldNode == null) {
            first = last;
        }
        else {
            oldNode.next = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldNode = first;
        first = oldNode.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return oldNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldNode = last;
        if (oldNode.prev == null) {
            last = null;
        }
        else {
            last = oldNode.prev;
            last.next = null;
        }
        size--;
        if (isEmpty()) {
            first = null;
        }
        return oldNode.item;
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
        Deque<String> strDeck = new Deque<>();

        StdOut.println(String.format("size: %s", strDeck.size()));
        strDeck.addLast("odin");
        strDeck.addFirst("dva");
        strDeck.addLast("tri");
        strDeck.addLast("cheshire");
        strDeck.addFirst("piat");
        StdOut.println(String.format("size: %s", strDeck.size()));
        StdOut.println(String.format("removed item: %s", strDeck.removeFirst()));
        StdOut.println(String.format("removed item: %s", strDeck.removeFirst()));
        StdOut.println(String.format("removed item: %s", strDeck.removeLast()));
        StdOut.println(String.format("size: %s", strDeck.size()));
        strDeck.addFirst("siest");
        strDeck.addLast("siem");
        StdOut.println(String.format("size: %s", strDeck.size()));

        for (String i : strDeck) {
            StdOut.println("iteration-item: " + i);
            StdOut.println(
                String.format("removed item: %s, is empty: %s",
                    strDeck.removeFirst(),
                    strDeck.isEmpty()
                ));
        }

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.removeLast();
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());

        for (Integer i : deque) {
            StdOut.println("iteration-item: " + i);
        }
    }
}
