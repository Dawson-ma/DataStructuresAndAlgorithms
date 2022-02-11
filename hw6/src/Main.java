import java.util.*;
import java.util.Queue;
import java.util.LinkedList;

public class Main <Key extends Comparable<Key>, Value> {
    public Node root;
    Main(){}
    void put(Key lo, Key hi, Value val) { // put interval-value pair into ST
        if (lo == null || hi == null || val == null) throw new IllegalArgumentException();
        root = put(root, lo, hi, val);
    }
    private Node put(Node x, Key lo, Key hi, Value val){
        if (x == null) return new Node(lo, hi, val);
        int cmp = lo.compareTo(x.lo);
        if      (cmp < 0) x.left  = put(x.left, lo, hi, val);
        else if (cmp > 0) x.right = put(x.right, lo , hi, val);
        else              x.val   = val;
        if(hi.compareTo(x.max) > 0)  x.max = hi;
        if(lo.compareTo(x.min) < 0)  x.min = lo;
        return x;
    }
    private class Node {
        private Key lo;
        private Key hi;
        private Value val;
        private Node left, right;
        private Key max;
        private Key min;

        public Node(Key lo, Key hi, Value val) {
            this.lo = lo;
            this.hi = hi;
            this.val = val;
            this.max = hi;
            this.min = lo;
        }
    }
    Value get(Key lo, Key hi) { // value paired with given interval
        return get(root, lo ,hi);
    }
    private Value get(Node x, Key lo, Key hi){
        if (lo == null || hi == null) throw new IllegalArgumentException();
        if (x == null) return null;
        int cmp = lo.compareTo(x.lo);
        if      (cmp < 0) return get(x.left, lo, hi);
        else if (cmp > 0) return get(x.right, lo, hi);
        else{
            if (hi.compareTo(x.hi) == 0) return x.val;
            else return null;
        }
    }
    void delete(Key lo, Key hi) { // delete the given interval
        if (lo == null || hi == null) throw new IllegalArgumentException();
        root = delete(root, lo, hi);
    }
    private Node delete(Node x, Key lo, Key hi) {
        if (x == null) return null;
        int cmp = lo.compareTo(x.lo);
        if      (cmp < 0) x.left  = delete(x.left, lo, hi);
        else if (cmp > 0) x.right = delete(x.right, lo, hi);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        resortMax(x);
        resortMin(x);
        return x;
    }
    private void resortMax(Node x) {
        if (x == null) return;
        if (x.left == null && x.right == null) x.max = x.hi;
        else if (x.left == null) {
            if (x.right.max.compareTo(x.hi) > 0) x.max =x.right.max;
            else x.max = x.hi;
        }
        else if (x.right == null) {
            if (x.left.max.compareTo(x.hi) > 0) x.max = x.left.max;
            else x.max = x.hi;
        }
        else if (x.left.max.compareTo(x.right.max) > 0){
                if (x.left.max.compareTo(x.hi) > 0) x.max = x.left.max;
                else x.max = x.hi;
            }
        else {
            if (x.right.max.compareTo(x.hi) > 0) x.max = x.right.max;
            else x.max = x.hi;
        }
    }
    private void resortMin(Node x) {
        if (x == null) return;
        if (x.left == null && x.right == null) x.min = x.lo;
        else if (x.left == null)  {
            if (x.right.min.compareTo(x.lo) < 0) x.min = x.right.min;
            else x.min = x.lo;
        }
        else if (x.right == null) {
            if (x.left.min.compareTo(x.lo) < 0) x.min = x.left.min;
            else x.min = x.lo;
        }
        else if (x.left.min.compareTo(x.right.min) < 0){
            if (x.left.min.compareTo(x.lo) < 0) x.min = x.left.min;
            else x.min = x.lo;
        }
        else {
            if (x.right.min.compareTo(x.lo) < 0) x.min = x.right.min;
            else x.min = x.lo;
        }
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }
    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        resortMax(x);
        resortMin(x);
        return x;
    }
    Iterable<Value> intersects(Key lo, Key hi) { // all intervals that intersect the given interval
        LinkedList<Value> its = new LinkedList<Value>();
        intersects(root, its, lo, hi);
        return its;
    }
    private boolean itsection(Node x, Key lo, Key hi) {
        if (x.hi.compareTo(lo) < 0) return false;
        if (x.lo.compareTo(hi) > 0) return false;
        return true;
    }
    private void intersects(Node x, Queue<Value> its, Key lo, Key hi) {
        if (x == null) return;
        if (itsection(x, lo, hi)) its.add(x.val);
        if (x.left == null) intersects(x.right, its, lo, hi);
        if (x.right == null) intersects(x.left, its, lo, hi);
        if (x.left != null && x.right != null && x.left.max.compareTo(lo) >= 0) intersects(x.left, its, lo ,hi);
        if (x.left != null && x.right != null && x.right.min.compareTo(hi) <= 0) intersects(x.right, its, lo, hi);
    }
    Iterable<Value> contains(Key lo, Key hi) { // all intervals that contain the given interval
        LinkedList<Value> cts = new LinkedList<Value>();
        contains(root, cts, lo, hi);
        return cts;
    }
    private void contains(Node x, Queue<Value> cts, Key lo, Key hi) {
        if (x == null) return;
        if (contains(x, lo, hi)) cts.add(x.val);
//        if (x.left == null) contains(x.right, cts, lo, hi);
//        if (x.right == null) contains(x.left, cts, lo, hi);
        if (x.left != null /*&& x.right != null*/ && x.left.max.compareTo(hi) >= 0) contains(x.left, cts, lo ,hi);
        if (/*x.left != null &&*/ x.right != null && x.right.min.compareTo(lo) <= 0) contains(x.right, cts, lo, hi);
    }
    private boolean contains(Node x, Key lo, Key hi) {
        if (x.hi.compareTo(hi) > 0 && x.lo.compareTo(lo) < 0) return true;
        else return false;
    }
    Iterable<Value> containedBy(Key lo, Key hi) { // all intervals that are contained by the given interval
        LinkedList<Value> ctb = new LinkedList<Value>();
        containedBy(root, ctb, lo, hi);
        return ctb;
    }
    private void containedBy(Node x, Queue<Value> ctb, Key lo, Key hi) {
        if (x == null) return;
        if (containedBy(x, lo, hi)) ctb.add(x.val);
        if (x.left == null) containedBy(x.right, ctb, lo, hi);
        if (x.right == null) containedBy(x.left, ctb, lo, hi);
        if (x.left != null && x.right != null && x.left.max.compareTo(lo) >= 0) containedBy(x.left, ctb, lo ,hi);
        if (x.left != null && x.right != null && x.right.min.compareTo(hi) <= 0) containedBy(x.right, ctb, lo, hi);
    }
    private boolean containedBy(Node x, Key lo, Key hi) {
        if (x.lo.compareTo(lo) >= 0 && x.hi.compareTo(hi) <= 0) return true;
        else return false;
    }
    Iterable<Value> inorderTraversal() { // inorder traversal of the tree
        LinkedList<Value> IT = new LinkedList<Value>();
//        values(root, IT, min(root).lo, max(root).lo);
        values(root, IT);
        return IT;
    }
//    private void values(Node x, Queue<Value> IT, Key lo, Key hi) {
//        if (x == null) return;
//        int cmplo = lo.compareTo(x.lo);
//        int cmphi = hi.compareTo(x.lo);
//        if (cmplo < 0) values(x.left, IT, lo, hi);
//        if (cmplo <= 0 && cmphi >= 0) IT.add(x.val);
//        if (cmphi > 0) values(x.right, IT, lo, hi);
//    }
    private void values(Node x, Queue<Value> IT) {
        if (x == null) return;;
        if (x.left != null) values(x.left, IT);
        IT.add(x.val);
        if (x.right != null)  values(x.right, IT);

    }

    public static void main(String[] args) {
        Main<Integer, String> main = new Main<>();
        main.put(0, 3, "ACAC");
        main.put(2, 7, "TCAATG");
        main.put(5, 6, "AT");
        main.delete(2, 7);
        main.put(-1, 2, "A");
        main.put(8, 40, "B");
        main.put(1, 5, "K");
        main.delete(-1,2);
        main.put(25, 65, "G");
        main.put(-2, 9, "U");
        main.put(6, 10, "AC");
        main.put(17, 19, "A");
        main.put(21, 23, "C");
        main.put(4, 7, "D");
        main.delete(17,19);
        main.put(9, 13, "H");
        main.delete(5, 8);
//        main.put(5, 8, "B");
//        main.put(2, 3, "E");
//        main.put(8, 9, "F");
//        main.put(6, 10, "G");
//        System.out.println("intersects");
//        for (String s : main.intersects(1,4)){
//            System.out.println(s);
//        }
        System.out.println("contains");
        for (String s : main.contains(8, 9)){
            System.out.println(s);
        }
//        System.out.println("containedBy");
//        for (String s : main.containedBy(0, 10)){
//            System.out.println(s);
//        }
        System.out.println("Iterator");
        for (String s : main.inorderTraversal()){
            System.out.println(s);
        }
//        System.out.println(main.root.left.right.max);
    }
}