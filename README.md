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

<h2 id="ReadMapping">Read Mapping</h2>
Report how many reads can be mapped to the reference sequence and how many reads can be mapped more than once (i.e., multiple times).  
In this example, we can see both "CACTG" and "GCCAT" can be mapped to the reference sequence "AGCTGAGCACTGGAGTGGAGTTTTCCTGTGGAGAGGAGCCATGCCCACTGTAGAG", while "CATGT" cannot be   mapped.  
Thus, the first line of the output is two. And the second line of the output is one because "CACTG" can be mapped twice.

<h2 id="UnionFind">Union Find</h2>
The main function of Main class is a real number followed by a finite number of protein interacting pairs in each row: ProteinA ProteinB strength.  
The real number is used as a threshold to determine whether the strength of the protein-protein interaction (PPI) is large enough to be retained in the network.
If the strength is good enough, i.e. strength>=threshold, the PPI is valid, and the pairs of proteins should be connected.  
If the strength of PPI is not good enough, please skip the line. In the end, there will be a set of connected components. If a PPI is not considered, the proteins should be omitted if it is not present in the previous PPI pairs.

<h2 id="PPI">PPI (Protein-Protein Interaction)</h2>
Construct a network (graph) using a adjacency list and explore the network using a stack or queue.

<h2 id="Deques">Deques</h2>


<h2 id="ConvexHull">Convex Hull</h2>
Take an array of N points as the input, and return its convex hull vertices.
The main function read an input file, of which the first line specifies the maximum distance (d) to union two points, the second line specifies the number of points (N). According to the coordinates of the N points, outputing the number of points in N serving as a convex hull vertex for any connected components.  
<!--![Sample image]()

<h2 id="ParticleCollision">Particle Collision</h2>

<h2 id="IntervalSearchTree">Interval Search Tree</h2>

<h2 id="HierarchicalClusteringTree">Hierarchical Clustering Tree</h2>

<h2 id="CriticalDistance">Critical Distance</h2>
-->
