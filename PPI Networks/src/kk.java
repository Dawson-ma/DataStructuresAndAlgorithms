import java.util.Iterator;
import java.util.*;
import java.io.*;

public class Main<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int count;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    private class ListItera tor implements Iterator<Item> {
        private Node current;
        public ListIterator(Node initialNode) {
            current = initialNode;
        }
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public Main() {
        // construct an empty deque
        count = 0;
        first = null;
        last = null;
    }
    public boolean isEmpty() {
        // is the deque empty?
        return first == null;
    }

    public Item peekFirst() {
        if(isEmpty()) return null;
        return first.item;
    }
    public Item peekLast() {
        if(isEmpty()) return null;
        return last.item;
    }
    public int size() {
        // return the number of items on the deque
        return count;
    }
    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (count == 0) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        count++;
    }
    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node newnode = new Node();
        newnode.item = item;
        if (last != null) {
            newnode.prev = last;
            last.next = newnode;
        }
        last = newnode;
        if (first == null) {
            first = last;
        }
        count++;
    }
    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        if (count == 1) {
            last = null;
            first = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        count--;
        return item;
    }
    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        if (last.prev == null) {
            last = null;
            first = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }
        count--;
        return item;
    }
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new ListIterator(first);
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]) ;
        Scanner in = new Scanner(file);

        int k = Integer.parseInt(in.nextLine());
        LinkedList<Integer> list = new LinkedList();
        while(in.hasNextLine()){
            list.add(Integer.parseInt(in.nextLine()));
        }
        Integer[] num = list.toArray(new Integer[list.size()]);
        
        Main<Integer> deque =new Main();
        for (int i = 0; i < num.length; i++) {
            
            while (!deque.isEmpty() && num[deque.last.item] < num[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
          
            if (deque.first.item == i - k) {
                deque.removeFirst();
            }
            
            if (i >= k - 1) {
                System.out.println(num[deque.first.item]);
            }
        }
    }
}