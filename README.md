# Data Structures and Algorithms
2019-1 Practical Data Structures and Algorithms

## Implementation
 * [Read Mapping](#ReadMapping)
 * [Union Find](#UnionFind)
 * [PPI](#PPI)
 * [Deques](#Deques)
 * [Convex Hull](#ConvexHull)
 * [Particle Collision](#ParticleCollision)
 * [Interval Search Tree](#IntervalSearchTree)
 * [Hierarchical Clustering Tree](#HierarchicalClusteringTree)
 * [Critical Distance](#CriticalDistance)

## Implementation description
<h3 id="ReadMapping">Read Mapping</h3>
Report how many reads can be mapped to the reference sequence and how many reads can be mapped more than once (i.e., multiple times).  
In this example, we can see both "CACTG" and "GCCAT" can be mapped to the reference sequence "AGCTGAGCACTGGAGTGGAGTTTTCCTGTGGAGAGGAGCCATGCCCACTGTAGAG", while "CATGT" cannot be   mapped.  
Thus, the first line of the output is two. And the second line of the output is one because "CACTG" can be mapped twice.

<h3 id="UnionFind">Union Find</h3>
The main function of Main class is a real number followed by a finite number of protein interacting pairs in each row: ProteinA ProteinB strength.  
The real number is used as a threshold to determine whether the strength of the protein-protein interaction (PPI) is large enough to be retained in the network.
If the strength is good enough, i.e. strength>=threshold, the PPI is valid, and the pairs of proteins should be connected.  
If the strength of PPI is not good enough, please skip the line. In the end, there will be a set of connected components. If a PPI is not considered, the proteins should be omitted if it is not present in the previous PPI pairs.

**Example input:**  
```
200
9606.ENSP00000000001 9606.ENSP00000000002 490
9606.ENSP00000000001 9606.ENSP00000000004 198
9606.ENSP00000000004 9606.ENSP00000000005 159
9606.ENSP00000000011 9606.ENSP00000000010 606
9606.ENSP00000000010 9606.ENSP00000000012 167
9606.ENSP00000000011 9606.ENSP00000000002 267
9606.ENSP00000000015 9606.ENSP00000000020 201
9606.ENSP00000000099 9606.ENSP00000000087 150
9606.ENSP00000000099 9606.ENSP00000000023 240
9606.ENSP00000000002 9606.ENSP00000000013 271
9606.ENSP00000000024 9606.ENSP00000000029 157
9606.ENSP00000000008 9606.ENSP00000000009 418
9606.ENSP00000000014 9606.ENSP00000000020 278
9606.ENSP00000000022 9606.ENSP00000000024 185

Connected components:
(9606.ENSP00000000001 9606.ENSP00000000002 9606.ENSP00000000010 9606.ENSP00000000011 9606.ENSP00000000013)
(9606.ENSP00000000015 9606.ENSP00000000020 9606.ENSP00000000014)
(9606.ENSP00000000023 9606.ENSP00000000099)
(9606.ENSP00000000008 9606.ENSP00000000009)
```

**Example output:**
```
12
4
```

<h3 id="PPI">PPI (Protein-Protein Interaction)</h3>
Construct a network (graph) using a adjacency list and explore the network using a stack or queue.  

Functions:

```
public Main(int n){}
// constructor, n is the number of proteins in
the network (the adjacency list)

public void add(String, String){};
// add a pair of proteins as an
edge of the network
// throw an `IndexOutOfBoundsException` if adding
more than n proteins

public int size(){};
// return the number of unique edges of the
network

public String[] neighbors(String){};
// return all the neighbors of a
protein as an array, where the proteins in the array are sorted
alphabetically
// throw an `IllegalArgumentException` if protein not
found

public String[] interactions(String, int){};
// return all the
interacting proteins (including both direct or indirect interactions)
of a protein through at most k edges, where the proteins in the array
are sorted alphabetically. The parameter k could be 0 or any positive
integers.
// throw an `IllegalArgumentException` if protein not found
```

<h3 id="Deques">Deques</h3>
Implement elementary data structures using arrays, linked lists, generics and iterators.
`Dequeue`, a double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure.

**Performance requirements**:
Deque implementation support each deque operation in constant worst-case time and use space proportional to the number of items currently in the deque. Additionally, iterator implementation must support each operation (including construction) in constant worst-case time.

**Sliding window problem**:
Main function should read in a number k and a series of numbers, and then consider a sliding window of size k, which is going to move from the very left of the array (the series of numbers) to the very right. Each time you can only see the k numbers in the window, and the sliding window moves right by one position at a time. Get the max value in each sliding window and then print out the results

API:

```
public class Main implements Iterable {

    public Main() // construct an empty deque

    public boolean isEmpty() // is the deque empty?

    public int size() // return the number of items on the deque

    public void addFirst(Item item) // add the item to the front

    public void addLast(Item item) // add the item to the end

    public Item removeFirst() // remove and return the item from the front

    public Item removeLast() // remove and return the item from the end

    public Item peekFirst() //return the fist item of the deque

    public Item peekLast() //return the last item of the deque

    public Iterator iterator() // return an iterator over items in order from front to end

    public static void main throws FileNotFoundException(String[] args){

        File file = new File(args[0]) ;// file name assigned



        // File file = new File("test.in") ; // filename for local test (delete this part when uploading to onlinejudge)

        // file reading 

        // implement sliding window problem

    }
}
```

<h3 id="ConvexHull">Convex Hull</h3>
Take an array of N points as the input, and return its convex hull vertices.
The main function read an input file, of which the first line specifies the maximum distance (d) to union two points, the second line specifies the number of points (N). According to the coordinates of the N points, outputing the number of points in N serving as a convex hull vertex for any connected components.  

<img width=40% src="/figure/convexhull_ex.png"/>

```
public class Main {

    public static int[] ConvexHullVertex(Point2D[] a) {

        // return ConvexHullVertex的index set，

        // index in a：0, 1, 2, 3, 4, ....a.length-1

    }

    public static void main(String[] args) {

        File file = new File(args[0]) ;// file name assigned

        // File file = new File("test.in") ; // filename for local test (delete this part when uploading to onlinejudge)



        // 1. read in the file containing N 2-dimentional points

        // 2. create an edge for each pair of points with a distance <= d

        // 3. find connected components (CCs) with a size >= 3

        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])

        // 5. count the number of points in N serving as a convex hull vertex, print it

    }
}
```


<h3 id="ParticleCollision">Particle Collision</h3>
Calculate the particle collision with obstacles by Event-Driven Simulation using priority queue.
There are several initial conditions:
The space is bounded in (0,0), (0,1), (1,0), (1,1)
In the middle, there is a square obstacle (0.4,0.4), (0.4,0.6), (0.6,0.4), (0.6,0.6)
Each particle has own mass, radius, initial position(rx,ry), initial viscosity vector(vx,vy) at T = 0
The program return every collision count of each particle in the given time T.

**Input example:**
```
2
100
0.015 0.015 0.01 0.001 0.02 1
0.885 0.885 0.01 0.013 0.02 1
```

**Output example:**
```
1
7
```

<h3 id="IntervalSearchTree">Interval Search Tree</h3>
Implement an interval search tree data structure which has the following API:

```
public class Main <Key extends Comparable<Key>, Value> {

    Main() // create interval search tree

    void put(Key lo, Key hi, Value val) // put interval-value pair into ST

    Value get(Key lo, Key hi) // value paired with given interval

    void delete(Key lo, Key hi) // delete the given interval

    Iterable<Value> intersects(Key lo, Key hi) // all intervals that intersect the given interval

    Iterable<Value> contains(Key lo, Key hi) // all intervals that contain the given interval

    Iterable<Value> containedBy(Key lo, Key hi) // all intervals that are contained by the given interval

    Iterable<Value> inorderTraversal() // inorder traversal of the tree

}
```

<h3 id="HierarchicalClusteringTree">Hierarchical Clustering Tree</h3>
Implement a clustering algorithm called 'centroid hierarchical clustering algorithm' to hierarchically group N 2-dimensional points in the plane and generate a clustering tree, which has the following API:

```
public class Main {

    	Main(Point2D[]) // create clustering tree

    	int[] cluster (int k) // k is the number of clusters. return the number of nodes in each cluster (in ascending order)
}
```

<h3 id="CriticalDistance">Critical Distance</h3>
The program reads in N 2-dimensional points.
An edge is defined as: v is pointing to w, if and only if the distance between v and w is smaller than d, and x(v) < x(w) and y(v) < y(w).

We further define the source s as the point with the smallest x(s)+y(s), and target t as the point with the largest x(t)+y(t).
The program reports the smallest d, which generates at least one path from s to t.

<img width=40% src="/figure/CriticalDistance_ex.png"/>
