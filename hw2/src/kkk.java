import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

public class Main {

    static HashMap<Point2D, Integer> map = new HashMap<>();
    static int current;

    public static Point2D[] sortbyY(Point2D[] a) {
        Arrays.sort(a, Point2D.Y_ORDER);
        return a;
    }

    public static Point2D[] sortbyangle(Point2D[] a, int low) {
        Comparator<Point2D> cmp = a[low].atan2Order();
        Arrays.sort(a, cmp);
        return a;
    }

    public static boolean ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
        if (area2 < 0) {
            return false; // clockwise
        } else if (area2 > 0) {
            return true; // counter-clockwise
        } else{
            return false;
        }
    }

    public static int[] ConvexHullVertex(Point2D[] b) {
        Point2D[] a = b.clone();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        a = sortbyY(a);
        a = sortbyangle(a, 0);

//        for (int i = 0; i < a.length; i++) {
//            StdDraw.text(a[i].x(), a[i].y() + 0.03, Integer.toString(i));
//        }

        // covexhull
        Stack<Integer> cvh = new Stack<>();
        cvh.push(0);
        cvh.push(1);
        current = 2;
        while (1 > 0) {
            int p1 = current;
            int p2 = cvh.pop();
            int p3 = cvh.pop();

            if (ccw(a[p3], a[p2], a[p1])) {
                cvh.push(p3);
                cvh.push(p2);
                cvh.push(p1);
                current++;

                //
                if (p1 == 0) {
                    cvh.pop();

                    break;
                }
            }
            else{
                cvh.push(p3);
            }
            if (current == a.length) {
                current = 0;
            }
        }

        int[] an = new int[cvh.size()];
        int num = cvh.size();
        for (int i = 0; i < num; i++) {
            an[i] = map.get(a[cvh.pop()]);
        }
        return an;

    }

    public static void main(String[] args) throws FileNotFoundException {

        //
        File file = new File(args[0]) ;

        Scanner in = new Scanner(file);
        double k = Double.parseDouble(in.nextLine());
        int N = Integer.parseInt(in.nextLine());

        // 
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            String[] coor = in.nextLine().split(" ");
            points[i] = new Point2D(Double.parseDouble(coor[0]), Double.parseDouble(coor[1]));
//            StdDraw.filledCircle(points[i].x(), points[i].y(), 0.003);
//            StdDraw.text(points[i].x(), points[i].y() + 0.03, Integer.toString(i));
        }
        in.close();

        // CC
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(points.length);
        Map<Integer, LinkedList> map = new HashMap<>();
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(points[i].distanceTo(points[j]) <= k){
                    uf.union(i,j);
                }
            }
        }

        for(int i = 0; i < points.length; i++){
            int rootIndex = uf.find(i);
            if(map.containsKey(rootIndex)){
                map.get(rootIndex).add(points[i]);
            } else{
                LinkedList<Point2D> lst = new LinkedList<>();
                lst.add(points[i]);
                map.put(rootIndex, lst);
            }
        }


//        StdDraw.setPenColor(StdDraw.RED);

        int count = 0;
        for(LinkedList<Point2D> lst: map.values()){
            if(lst.size()>=3){
                Point2D[] connectedComponent = lst.toArray(new Point2D[lst.size()]);
                int[] convexHullVertex = ConvexHullVertex(connectedComponent);
                for(int i=0; i<convexHullVertex.length; i++){
                    Point2D p1 = connectedComponent[convexHullVertex[i]];
                    Point2D p2 = connectedComponent[convexHullVertex[(i+1) % convexHullVertex.length] ];
//                    StdDraw.filledCircle(p1.x(), p1.y(), 0.006);
//                    StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
                    count ++;
                }
            }
        }

        System.out.println(count);


    }
}