import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File(args[0]) ;// file name assigned
        File file = new File("testin.txt"); // filename for local test (delete this part when uploading to onlinejudge)
        Scanner in = new Scanner(file);
        int N = Integer.parseInt(in.nextLine());
        Point2D[] point2Ds = new Point2D[N];
        Digraph digraph = new Digraph(N);
        int down = 0;
        int up = 0;
        double max = 0;
        double min = Double.POSITIVE_INFINITY;
        MinPQ<edge> minpq = new MinPQ<edge>();
        for (int i = 0; i < N; i++) {
            double x = Double.parseDouble(in.next());
            double y = Double.parseDouble(in.next());
            if ((x + y) < min) {
                min = (x + y);
                down = i;
            }
            if ((x + y) > max) {
                max = (x + y);
                up = i;
            }
            point2Ds[i] = new Point2D(x, y);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((point2Ds[j].x() > point2Ds[i].x()) && point2Ds[j].y() > point2Ds[i].y())   minpq.insert(new edge(i, j, point2Ds[i].distanceTo(point2Ds[j])));
                if ((point2Ds[j].x() < point2Ds[i].x()) && point2Ds[j].y() < point2Ds[i].y())   minpq.insert(new edge(j, i, point2Ds[j].distanceTo(point2Ds[i])));
            }
        }
        double d = 0;
        DepthFirstDirectedPaths ddp = new DepthFirstDirectedPaths(digraph, down);
        while (!ddp.hasPathTo(up)){
            edge x = minpq.delMin();
            d = x.dis;
            ddp = new DepthFirstDirectedPaths(digraph, down);
            digraph.addEdge(x.a ,x.b);
        }
        System.out.printf("%5.5f\n", d);
    }
    public static class edge implements Comparable<edge>{
        private int a;
        private int b;
        private double dis;
        public edge (int a, int b, double dis) {
            this.a = a;
            this.b = b;
            this.dis = dis;
        }
        @Override
        public int compareTo(edge o) {
            return Double.compare(this.dis, o.dis);
        }
    }
}
