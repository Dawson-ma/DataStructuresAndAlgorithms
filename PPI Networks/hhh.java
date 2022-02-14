import edu.princeton.cs.algs4.Graph;
import java.io.File;
import java.util.*;

public class Main {
	LinkedList<String>[] adj;
	Hashtable<String,Integer> ht;
	
	int size;
	int v,count;

	public Main(int n){	
		size = n;
		adj =(LinkedList<String>[]) new LinkedList[n];
		for(int i = 0; i<n; i++)
		{
			adj[i] = new LinkedList<String>();
		}
		ht = new Hashtable<String, Integer>();
		v = 0;
		count = 0;
	}
	
	// add a pair of proteins as an edge of the network
	public void add(String protein1, String protein2)
	{
		if(!ht.containsKey(protein1))
		{
			if (v >= size){
				throw new IndexOutOfBoundsException();
			}
			ht.put(protein1,v);
			v++;
		}
		if(!ht.containsKey(protein2))
		{
			if (v >= size){
				throw new IndexOutOfBoundsException();
			}
			ht.put(protein2,v);
			v++;
		}	
		if (!adj[ht.get(protein1)].contains(protein2))	{	
			adj[ht.get(protein1)].add(protein2);
			count++;
		}
		if (!adj[ht.get(protein2)].contains(protein1))	{
			adj[ht.get(protein2)].add(protein1);
			count++;
		}
	}
	//private int proteinID(String e)	{return ht.get(e);}
	// return the size of the network, i.e. number of vertex in the network
	public int size() 
	{
		return count/2;
	}
	// list all the neighbors (direct linked) of a protein as a linked list, where the proteins in the list are sorted alphabetically
	public String[] neighbors (String protein) throws IllegalArgumentException
	{
		if (!ht.containsKey(protein)){
		  throw new IllegalArgumentException();
		}
		LinkedList<String> a = adj[ht.get(protein)];
		a.add(protein);
		String[] get = a.toArray(new String[0]);
		Arrays.sort(get);
		return get;
	}
	// list all the interacting proteins (including both direct or indirect interactions) of a protein through at most k edges, where the proteins sorted alphabetically. The parameter k could be any positive integers. 
	public String[] interactions(String protein, int k) throws IllegalArgumentException
	{	
		if (!ht.containsKey(protein)){
		  throw new IllegalArgumentException();
		}
		String[] read;
		String nowpro = protein;
		int nowproID = ht.get(nowpro);  
		Set<String> set = new HashSet<String>();
		Stack<String> st = new Stack<String>();
		int[] mark = new int[size];
		st.push(nowpro);
		set.add(nowpro);
		mark[nowproID] = 1;
		while(!st.empty())
		{	
			nowpro = st.pop();
			set.add(nowpro);
			//System.out.println(nowpro);
			nowproID = ht.get(nowpro);
			if (mark[nowproID] == k+1) {
				
				continue;
			}
			read = neighbors(nowpro); // neighbor proteins
			
			for (int i = read.length-1; i >= 0;i--){
				if(mark[ht.get(read[i])] == 0 || mark[nowproID]+1 < mark[ht.get(read[i])]){
					mark[ht.get(read[i])] = mark[nowproID]+1;
					st.push(read[i]);
				}

			}
		}
		//System.out.println(set);
		String[] ans = new String[set.size()];
		int i = 0;
		for(String o: set){
			ans[i] = o;
			i++;
		} 
		Arrays.sort(ans);
		return ans;
	}

	// public static void main (String[] args) throws Exception{
	// 	//Scanner (use this scanner when uploading your code)
	// 	//Scanner in = new Scanner(System.in);
		

	// 	//scanner for local test (delete this part when uploading to onlinejudge)
	// 	//Scanner in2 = new Scanner(System.in);
	// 	//String filename = in2.nextLine();
	// 	File file = new File("HW2.txt") ;
	// 	Scanner in = new Scanner(file);
	// 	//int threshold=Integer.parseInt(in.nextLine());
	// 	String pro1 = "";
		
	// 	while(in.hasNextLine())
	// 	{
	// 		pro1 = in.nextLine();
	// 		String[] pro = pro1.split("\t");

			
	// 	}
	
	// 	//System.out.println(UF.count());
	// }	
}