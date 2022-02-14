import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.UF;
import java.util.Hashtable;

public class Main {
    public static int[] ConvexHullVertex(Point2D[] a) {
        LinkedList<Point2D> result = new LinkedList<Point2D>();
        Hashtable<Point2D,Integer> ht = new Hashtable<Point2D,Integer>();
        for(int i = 0; i < a.length; i++){
            ht.put(a[i],i);
        }
        Arrays.sort(a, Point2D.Y_ORDER);
        Comparator<Point2D> pol = a[0].polarOrder();
        Arrays.sort(a,pol);
        for(int i = 0; i <= a.length;){
            if(result.size() < 3){
                result.add(a[i]);
                i++;
                continue;
            }
            int size = result.size();
            if(Point2D.ccw(result.get(size - 3),result.get(size - 2),result.get(size - 1)) <= 0){
                result.remove(size - 2);
            }
            else {
                if(i == a.length){
                    if(Point2D.ccw(result.get(size - 2),result.get(size - 1),a[0]) <= 0) result.removeLast();
                    break;
                }
                result.add(a[i]);
                i++;
            }
        }
        int[] re = new int[result.size()];
        for (int i = 0; i < result.size();i++){
            re[i]=ht.get(result.get(i));
        }
        return re;
    }
    public static void main(String[] args) throws FileNotFoundException {
        //File file = new File(args[0]) ;// file name assigned
        File file = new File("testin.txt") ; // filename for local test (delete this part when uploading to onlinejudge)
        Scanner in = new Scanner(file);
        double dis = Double.parseDouble(in.nextLine());
        int N = Integer.parseInt(in.nextLine());
        Point2D[] points = new Point2D[N];
        for(int i = 0; i < N; i++) {
            points[i] = new Point2D(Double.parseDouble(in.next()), Double.parseDouble(in.next()));
        }
        in.close();
        UF uf = new UF(N);
        LinkedList<Integer> list[] = new LinkedList[N];
        for(int i = 0; i < N; i++){
            list[i] = new LinkedList<Integer>();
            for(int j = (i + 1); j < N; j++) {
                if (points[i].distanceTo(points[j]) < dis)
                {
                    uf.union(i,j);
                }
            }
        }
        for(int i = 0; i < N; i++){
            list[uf.find(i)].add(i);
        }
        int count = 0;
        for(int i = 0; i < N; i++){
            if (list[i].size() >= 3) {
                Point2D[] tmp = new Point2D[list[i].size()];
                for(int j = 0; j < list[i].size(); j++){
                    tmp[j] = points[list[i].get(j)];
                }
                int[] cvx = ConvexHullVertex(tmp);
                count += cvx.length;
            }
        }
        System.out.println(count);
    }
}