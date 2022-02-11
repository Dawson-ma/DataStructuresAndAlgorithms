import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main<Item> implements Iterable<Item> {
    private LinkedList<Item> list;
    public Main() {
        list = new LinkedList<Item>(); // construct an empty deque
    }
    public boolean isEmpty() {
        return list.isEmpty();  // is the deque empty?
    }
    public int size() {
        return list.size();  // return the number of items on the deque
    }
    public void addFirst(Item item) {
        if (item == null)   // add the item to the front
            throw new NullPointerException();
        else
            list.addFirst(item);
    }
    public void addLast(Item item) {
        if (item == null)   // add the item to the end
            throw new NullPointerException();
        else
            list.addLast(item);
    }
    public Item removeFirst() {
        if (list.isEmpty())   // remove and return the item from the front
            throw new NoSuchElementException();
        else
            return list.removeFirst();
    }
    public Item removeLast() {
        if (list.isEmpty())   // remove and return the item from the end
            throw new NoSuchElementException();
        else
            return list.removeLast();
    }
    public Item peekFirst() {
        return list.peekFirst();  //return the fist item of the deque
    }
    public Item peekLast() {
        return list.peekLast();  //return the last item of the deque
    }
    public Iterator<Item> iterator() {
        return new LT(list);  // return an iterator over items in order from front to end
    }
    private class LT<Item> implements Iterator<Item> {
        private LinkedList<Item> deist;
        private Iterator current;
        public LT(LinkedList<Item> first) {
            deist = new LinkedList<Item>();
            deist = first;
            current = deist.iterator();
        }
        public boolean hasNext() {
            return (current.hasNext());
        }
        public Item next() {
            if (deist.isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            Item item =(Item)current.next();
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        //File file = new File(args[0]) ;// file name assigned
        File file = new File("testin.txt") ; // filename for local test (delete this part when uploading to onlinejudge)
        Scanner in = new Scanner(file);
        int k = Integer.parseInt(in.nextLine());
        LinkedList<Integer> read = new LinkedList();
        int result = Integer.parseInt(in.nextLine());
        read.add(result);
        while(in.hasNext()) {
            int num = Integer.parseInt(in.nextLine());
            if (num > result)
                result = num;
            read.add(num);
            while(read.size() == k) {
                System.out.println(result);
                if(result == read.get(0)) {
                    read.removeFirst();
                    result = (int) Collections.max(read);
                    break;
                }
                read.removeFirst();
            }
        }
    }
}