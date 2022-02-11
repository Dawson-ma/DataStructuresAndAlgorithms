import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class Main {
	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("input_HW1.txt") ;
		Scanner in = new Scanner(file);	
		Scanner in = new Scanner(System.in);
		int treshold = Integer.parseInt(in.next());
        ArrayList List = new ArrayList();
		Set read = new HashSet();
        ArrayList bondlist = new ArrayList();
		while (in.hasNext()) 
		{
			String fi = in.next();
			String se = in.next();
			int bond = Integer.parseInt(in.next());
			if (bond >= treshold)
			{
				List.add(fi);
				List.add(se);
				read.add(fi);
				read.add(se);
				bondlist.add(bond);
			}
		}
		ArrayList result = new ArrayList(read);
		Hashtable tal = new Hashtable();
		UF co = new UF(result.size());
		for(int i = 0; i < result.size(); i++)
		{
			tal.put(result.get(i),i);
		}
		for(int i = 0; i < List.size(); i+=2)
		{
			int k1 = (Integer)tal.get(List.get(i));
			int k2 = (Integer)tal.get(List.get(i+1));
			if(co.connected(k1, k2))
			{
				continue;
			}
			co.union(k1, k2);
		}
		System.out.println(result.size());
		System.out.println(co.count);
		in.close();
	}
	public static class UF
	{
		private int[] id;
		private int[] sz;
		public int count;
		public UF(int N)
		{
			count = N;
			id = new int[N];
			sz = new int[N];
			for (int i = 0; i < N; i++)
			{
				id[i] = i;
				sz[i] = 0;
			}
		}
		private int root(int i)
		{
			while (i != id[i])
			{
				id[i] = id[id[i]];
				i = id[i];
			}
			return i;
		}
		public boolean connected(int p, int q)
		{
			return root(p) == root(q);
		}
		public void union(int p, int q)
		{
			int i = root(p);
			int j = root(q);
			if (i == j) return;
			if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
			else { id[j] = i; sz[i] += sz[j]; }
			count--;
		}
	}
}