import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.ClosestPair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.HashMap;

public class Main {
    private int time;
    private int N;
    private cluster root;
    private HashMap<Integer, cluster> hm;
    private LinkedList<Integer> ls;
    private ClosestPair cp;
    private LinkedList<Point2D> pls;
    private LinkedList<Point2D> ppls;
    public Main(Point2D[] ps) {// create clustering tree
        time = 0;
        N = ps.length;
        hm = new HashMap<Integer, cluster>();
        ls = new LinkedList<Integer>();
        pls = new LinkedList<Point2D>();
        ppls = new LinkedList<Point2D>();
        for (int i = 0; i < N; i++) {
            pls.add(ps[i]);
            ppls.add(ps[i]);
            hm.put(i, new cluster(ps[i],null, null, 1, time));
        }
        while (hm.size() > 1) {
            cp = new ClosestPair(pls.toArray(new Point2D[0]));
            pls.remove(pls.indexOf(cp.either()));
            pls.remove(pls.indexOf(cp.other()));
            int indexa = ppls.indexOf(cp.either());
            int indexb = ppls.indexOf(cp.other());
            int aw = hm.get(indexa).w;
            int bw = hm.get(indexb).w;
            double x = (((hm.get(indexa).p.x() * aw) + (hm.get(indexb).p.x() * bw)) / (aw + bw));
            double y = (((hm.get(indexa).p.y() * aw) + (hm.get(indexb).p.y() * bw)) / (aw + bw));
            Point2D newp = new Point2D(x, y);
            pls.add(newp);
            ppls.add(newp);
            cluster newone = new cluster(newp, hm.get(indexa), hm.get(indexb), (aw + bw), ++time);
            hm.put((N + time - 1), newone);
            hm.remove(indexa);
            hm.remove(indexb);
            root = newone;
        }
    }
    int[] cluster (int k) {// k is the number of clusters. return the number of nodes in each cluster (in descending order)
        int[] clus = new int[k];
        LinkedList<Integer> result = new LinkedList<Integer>();
        int t = (N - k);
        cluster(root, t, result);
        for (int i = 0; i < k; i++) {
            clus[i] = result.get(i);
        }
        Arrays.sort(clus);
        return clus;
    }
    void cluster (cluster x, int t, LinkedList<Integer> list) {
        if (x == null)  return;
        if (x.t <= t) {
            list.add(x.w);
            return;
        }
        if (x.left != null) cluster(x.left, t, list);
        if (x.right != null) cluster(x.right, t, list);
    }
    public static class cluster {
        private cluster left;
        private cluster right;
        private int t;
        private int w;
        private Point2D p;
        public cluster (Point2D p,cluster a, cluster b, int w, int t) {
            this.p = p;
            this.left = a;
            this.right = b;
            this.w = w;
            this.t = t;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("testin.txt"); // filename for local test (delete this part when uploading to onlinejudge)
        Scanner in = new Scanner(file);
        int N = Integer.parseInt(in.nextLine());
        Point2D[] point2Ds = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = Double.parseDouble(in.next());
            double y = Double.parseDouble(in.next());
            point2Ds[i] = new Point2D(x, y);
        }
        Main main = new Main (point2Ds);
        int k = 3;
        int[] result = main.cluster(k); // the input points will cluster as shown in figure3
        for (int i = 0; i < k; i++) {
            System.out.println(result[i]);
        }
        // return [4,5,11]
    }
}