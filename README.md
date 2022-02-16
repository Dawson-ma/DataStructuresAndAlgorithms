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

<h3 id="PPI">PPI (Protein-Protein Interaction)</h3>
Construct a network (graph) using a adjacency list and explore the network using a stack or queue.

<h3 id="Deques">Deques</h3>
Implement elementary data structures using arrays, linked lists, generics and iterators.
`Dequeue`, a double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure.

**Performance requirements**:
Deque implementation support each deque operation in constant worst-case time and use space proportional to the number of items currently in the deque. Additionally, iterator implementation must support each operation (including construction) in constant worst-case time.

**Sliding window problem**:
Main function should read in a number k and a series of numbers, and then consider a sliding window of size k, which is going to move from the very left of the array (the series of numbers) to the very right. Each time you can only see the k numbers in the window, and the sliding window moves right by one position at a time. Get the max value in each sliding window and then print out the results

<h3 id="ConvexHull">Convex Hull</h3>
Take an array of N points as the input, and return its convex hull vertices.
The main function read an input file, of which the first line specifies the maximum distance (d) to union two points, the second line specifies the number of points (N). According to the coordinates of the N points, outputing the number of points in N serving as a convex hull vertex for any connected components.  
<!--![Sample image]()-->

<h3 id="ParticleCollision">Particle Collision</h3>
Calculate the particle collision with obstacles by Event-Driven Simulation using priority queue.
There are several initial conditions:
The space is bounded in (0,0), (0,1), (1,0), (1,1)
In the middle, there is a square obstacle (0.4,0.4), (0.4,0.6), (0.6,0.4), (0.6,0.6)
Each particle has own mass, radius, initial position(rx,ry), initial viscosity vector(vx,vy) at T = 0
The program return every collision count of each particle in the given time T.

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
