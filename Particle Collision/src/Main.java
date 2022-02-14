import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class Main {
    public static class Particle {
        private static final double INFINITY = Double.POSITIVE_INFINITY;
        private double rx, ry;        // position
        private double vx, vy;        // velocity
        private int count;            // number of collisions so far
        private final double radius;  // radius
        private final double mass;    // mass
        public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
            this.vx = vx;
            this.vy = vy;
            this.rx = rx;
            this.ry = ry;
            this.radius = radius;
            this.mass   = mass;
        }
        public void move(double dt) {
            rx += vx * dt;
            ry += vy * dt;
        }
        public void draw() {
            StdDraw.filledCircle(rx, ry, radius);
        }
        public int count() {
            return count;
        }
        public double timeToHit(Particle that) {
            if (this == that) return INFINITY;
            double dx  = that.rx - this.rx;
            double dy  = that.ry - this.ry;
            double dvx = that.vx - this.vx;
            double dvy = that.vy - this.vy;
            double dvdr = dx*dvx + dy*dvy;
            if (dvdr > 0) return INFINITY;
            double dvdv = dvx*dvx + dvy*dvy;
            if (dvdv == 0) return INFINITY;
            double drdr = dx*dx + dy*dy;
            double sigma = this.radius + that.radius;
            double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
            // if (drdr < sigma*sigma) StdOut.println("overlapping particles");
            if (d < 0) return INFINITY;
            return -(dvdr + Math.sqrt(d)) / dvdv;
        }
        public double timeToHitVerticalWall() {
            double limit =  Math.pow(10, -15);
            if (vx > 0){
                if (vy == 0){
                    if (ry < 0.4 && ry > (0.4 - radius)){
                        if (rx < 0.4){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 0.8 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 0.8 * ry + 0.32 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (1.0 - rx - radius) / vx;
                            }
                        }
                        else return (1.0 - rx - radius) / vx;
                    }
                    else if (ry > 0.6 && ry < 0.6 + radius){
                        if (rx < 0.4){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 1.2 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 1.2 * ry + 0.52 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (1.0 - rx - radius) / vx;
                            }
                        }
                        else return (1.0 - rx - radius) / vx;
                    }
                }
                if (rx < 0.4) {
                    double t = (0.4 - rx - radius) / vx;
                    if (t > 0) {
                        if (((ry + vy * t) <= 0.6) && ((ry + vy * t) >= 0.4)) return t;
                    }
                    double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 0.8 * vy;
                    double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 0.8 * ry + 0.32 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) > (0.4 - (radius * Math.sqrt(2) / 2)) + 0.02 || ((ry + vy * t) <= (0.4 - radius)) || (t2 < 0)) return (1.0 - rx - radius) / vx;
                        return t2;
                    }
                    a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 1.2 * vy;
                    c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 1.2 * ry + 0.52 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
//                        System.out.println((rx + vx * t2));
//                        System.out.println(0.4 - (radius * Math.sqrt(2) / 2));
                        if ((rx + vx * t2) > (0.4 - (radius * Math.sqrt(2) / 2)) + 0.02 || ((ry + vy * t) >= (0.6 + radius)) || (t2 < 0)) return (1.0 - rx - radius) / vx;
                        return t2;
                    }
                }
                return (1.0 - rx - radius) / vx;
            }
            else if (vx < 0){
                if (vy == 0){
                    if (ry < 0.4 && ry > (0.4 - radius)){
                        if (rx > 0.6){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 0.8 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 0.8 * ry + 0.52 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (radius - rx) / vx;
                            }
                        }
                        else return (radius - rx) / vx;
                    }
                    else if (ry > 0.6 && ry < 0.6 + radius){
                        if (rx > 0.6){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 1.2 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 1.2 * ry + 0.72 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (radius - rx) / vx;
                            }
                        }
                        else return (radius - rx) / vx;
                    }
                }
                if (rx > 0.6){
                    double t = (radius - rx + 0.6) / vx;
                    if (t > 0) {
                        if (((ry + vy * t) <= 0.6) && ((ry + vy * t) >= 0.4)) return t;
                    }
                    double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 0.8 * vy;
                    double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 0.8 * ry + 0.52 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) + 0.02 < (0.6 + (radius * Math.sqrt(2) / 2)) || ((ry + vy * t) <= (0.4 - radius)) || (t2 < 0)) return (radius - rx) / vx;
                        return t2;
                    }
                    a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 1.2 * vy;
                    c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 1.2 * ry + 0.72 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) + 0.02 < (0.6 + (radius * Math.sqrt(2) / 2)) || ((ry + vy * t) >= (0.6 + radius)) || (t2 < 0)) return (radius - rx) / vx;
                        return t2;
                    }
                }
                return (radius - rx) / vx;
            }
            else return INFINITY;
        }
        public double timeToHitHorizontalWall() {
            double limit =  Math.pow(10, -15);
            if (vy > 0){
                if (vx == 0){
                    if (rx < 0.4 && rx > (0.4 - radius)){
                        if (ry < 0.4){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 0.8 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 0.8 * ry + 0.32 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (1.0 - ry - radius) / vy;
                            }
                        }
                        else return (1.0 - ry - radius) / vy;
                    }
                    else if (rx > 0.6 && rx < 0.6 +radius){
                        if (ry < 0.4){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 0.8 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 0.8 * ry + 0.52 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (1.0 - ry - radius) / vy;
                            }
                        }
                        else return (1.0 - ry - radius) / vy;
                    }
                }
                if (ry < 0.4){
                    double t = (0.4 - ry - radius) / vy;
                    if (t > 0) {
                        if (((rx + vx * t) >= 0.4) && ((rx + vx * t) <= 0.6)) return t;
                    }
                    double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 0.8 * vy;
                    double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 0.8 * ry + 0.52 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) > (0.6 + (radius * Math.sqrt(2) / 2)) + 0.02 || ((rx + vx * t) >= (0.6 + radius)) || (t2 < 0)) return (1.0 - ry - radius) / vy;
                        return t2;
                    }
                    a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 0.8 * vy;
                    c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 0.8 * ry + 0.32 - Math.pow(radius, 2);
//                    System.out.println(rx);
//                    System.out.println((Math.pow(b, 2) - 4 * a * c));
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
//                        System.out.println(rx + vx * t +0.002);
//                        System.out.println((0.4 - (radius * Math.sqrt(2) / 2)));
//                        System.out.println(t);
                        if ((rx + vx * t2) + 0.02 < (0.4 - (radius * Math.sqrt(2) / 2)) || ((rx + vx * t) <= (0.4 - radius)) || (t2 < 0)) return (1.0 - ry - radius) / vy;
//                        System.out.println(t);
                        return t2;
                    }
                }
                return (1.0 - ry - radius) / vy;
            }
            else if (vy < 0){
                if (vx == 0){
                    if (rx < 0.4 && rx > (0.4 - radius)){
                        if (ry > 0.6){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 1.2 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 1.2 * ry + 0.52 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (radius - ry) / vy;
                            }
                        }
                        else return (radius - ry) / vy;
                    }
                    else if (rx > 0.6 && rx < 0.6 +radius){
                        if (ry > 0.6){
                            double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                            double b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 1.2 * vy;
                            double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 1.2 * ry + 0.72 - Math.pow(radius, 2);
                            if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                                double t = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                                if (t > limit)  return t;
                                else return (radius - ry) / vy;
                            }
                        }
                        else return (radius - ry) / vy;
                    }
                }
                if (ry > 0.6){
                    double t = (radius - ry + 0.6) / vy;
                    if (t > 0) {
                        if (((rx + vx * t) >= 0.4) && ((rx + vx * t) <= 0.6)) return t;
                    }
                    double a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    double b = 2 * rx * vx - 0.8 * vx + 2 * ry * vy - 1.2 * vy;
                    double c = Math.pow(rx, 2) + Math.pow(ry, 2) - 0.8 * rx - 1.2 * ry + 0.52 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) + 0.02 < (0.4 - (radius * Math.sqrt(2) / 2)) || ((rx + vx * t) <= (0.4 - radius)) || (t2 < 0)) return (radius - ry) / vy;
                        return t2;
                    }
                    a = Math.pow(vx, 2)+Math.pow(vy, 2);
                    b = 2 * rx * vx - 1.2 * vx + 2 * ry * vy - 1.2 * vy;
                    c = Math.pow(rx, 2) + Math.pow(ry, 2) - 1.2 * rx - 1.2 * ry + 0.72 - Math.pow(radius, 2);
                    if ((Math.pow(b, 2) - 4 * a * c) > 0) {
                        double t2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
                        if ((rx + vx * t2) > (0.6 + (radius * Math.sqrt(2) / 2)) + 0.02 || ((rx + vx * t) >= (0.6 + radius)) || (t2 < 0)) return (radius - ry) / vy;
                        return t2;
                    }
                }
                return (radius - ry) / vy;
            }
            else return INFINITY;
        }
        public void bounceOff(Particle that) {
            double dx  = that.rx - this.rx;
            double dy  = that.ry - this.ry;
            double dvx = that.vx - this.vx;
            double dvy = that.vy - this.vy;
            double dvdr = dx*dvx + dy*dvy;             // dv dot dr
            double dist = this.radius + that.radius;   // distance between particle centers at collison

            // magnitude of normal force
            double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

            // normal force, and in x and y directions
            double fx = magnitude * dx / dist;
            double fy = magnitude * dy / dist;

            // update velocities according to normal force
            this.vx += fx / this.mass;
            this.vy += fy / this.mass;
            that.vx -= fx / that.mass;
            that.vy -= fy / that.mass;

            // update collision counts
            this.count++;
            that.count++;
        }
        public void bounceOffVerticalWall() {
            vx = -vx;
            count++;
        }
        public void bounceOffHorizontalWall() {
            vy = -vy;
            count++;
        }
    }
    private static class Event implements Comparable<Event> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation


        // create a new event to occur at time t involving a and b
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
    }
    public static class CollisionSystem {
        private static final double HZ = 0.5;    // number of redraw events per clock tick
        private MinPQ<Event> pq;          // the priority queue
        private double t  = 0.0;          // simulation clock time
        private Particle[] particles;     // the array of particles
        public CollisionSystem(Particle[] particles) {
            this.particles = particles.clone();   // defensive copy
        }
        private void predict(Particle a, double limit) {
            if (a == null) return;

            // particle-particle collisions
            for (int i = 0; i < particles.length; i++) {
                double dt = a.timeToHit(particles[i]);
                if (t + dt <= limit)
                    pq.insert(new Event(t + dt, a, particles[i]));
            }

            // particle-wall collisions
            double dtX = a.timeToHitVerticalWall();
//            System.out.println(dtX);
            double dtY = a.timeToHitHorizontalWall();
//            System.out.println(dtY);
            if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
            if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
        }
        private void redraw(double limit) {
            StdDraw.clear();
            StdDraw.line(0.4,0.4,0.4,0.6);
            StdDraw.line(0.4,0.6,0.6,0.6);
            StdDraw.line(0.6,0.6,0.6,0.4);
            StdDraw.line(0.4,0.4,0.6,0.4);
            for (int i = 0; i < particles.length; i++) {
                particles[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(20);
            if (t < limit) {
                pq.insert(new Event(t + 1.0 / HZ, null, null));
            }
        }
        public void simulate(double limit) {

            // initialize PQ with collision events and redraw event
            pq = new MinPQ<Event>();
            for (int i = 0; i < particles.length; i++) {
                predict(particles[i], limit);
            }
            pq.insert(new Event(0, null, null));        // redraw event


            // the main event-driven simulation loop
            while (!pq.isEmpty()) {

                // get impending event, discard if invalidated
                Event e = pq.delMin();
                if (!e.isValid()) continue;
                Particle a = e.a;
                Particle b = e.b;

                // physical collision, so update positions, and then simulation clock
                for (int i = 0; i < particles.length; i++)
                    particles[i].move(e.time - t);
                t = e.time;

                // process event
                if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
                else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
                else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
                else if (a == null && b == null) redraw(limit);               // redraw event

                // update the priority queue with new collisions involving a or b
                predict(a, limit);
                predict(b, limit);
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File(args[0]) ;// file name assigned
        File file = new File("testin.txt"); // filename for local test (delete this part when uploading to onlinejudge)
        Scanner in = new Scanner(file);
        int N = Integer.parseInt(in.nextLine());
        Particle[] pars = new Particle[N];
        double T = Double.parseDouble(in.nextLine());
        for (int i = 0; i < N; i++){
            double rx = Double.parseDouble(in.next());
            double ry = Double.parseDouble(in.next());
            double vx = Double.parseDouble(in.next());
            double vy = Double.parseDouble(in.next());
            double r = Double.parseDouble(in.next());
            double m = Double.parseDouble(in.next());
            pars[i] = new Particle(rx, ry, vx, vy, r, m);
        }
        CollisionSystem sys = new CollisionSystem(pars);
        sys.simulate(T);
        for(int i = 0; i < N; i++){
            System.out.println(pars[i].count());
        }
    }
}