import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;
import java.util.Queue;

public class Main {
	private int count1;
	public int count2;
	private int v;
	private Hashtable<String,Integer> ht;
	private LinkedList<String> list[];
	public Main(int n)  
	{
		ht = new Hashtable<String,Integer>();
		list = new LinkedList[n];
		for(int i = 0; i < n; i++)
		{
			list[i] = new LinkedList<String>();
		}
		v = n;
		count1 = 0;
		count2 = 0;
	}
	public void add(String a, String b)  
	{
		if(!ht.containsKey(a))
		{
			if((ht.size() + 1) > v) 
			{
				throw new IndexOutOfBoundsException();
			}
			ht.put(a,count1);
			count1++;
		}
		if(!ht.containsKey(b))
		{
			if((ht.size() + 1) > v) 
			{
				throw new IndexOutOfBoundsException();
			}
			ht.put(b,count1);
			count1++;
		}
		if (!list[ht.get(a)].contains(b))
		{
			list[ht.get(a)].add(b);
			count2++;
		}
		if (!list[ht.get(b)].contains(a))
		{
			list[ht.get(b)].add(a);
			count2++;
		}
	}
	public int size()
	{
		return (count2/2);
	}
	public String[] neighbors(String pro)
	{
		if (ht.containsKey(pro))
		{
			LinkedList<String> tein = list[ht.get(pro)];
			tein.add(pro);
			String[] ans = tein.toArray(new String[0]);
			Arrays.sort(ans);
			return ans;
		}
		else
		{
			throw new IllegalArgumentException();
		} 
	}
	public String[] interactions(String p, int k)
	{
		Queue<Comparable> queue = new LinkedList();
		List<String> o = new LinkedList<String>();
		if(ht.containsKey(p))
		{
			queue.offer(p);
			queue.offer(k);
			while(!queue.isEmpty())
			{
				String nd = (String)queue.poll();
				int edge = (int)queue.poll();
				if(!o.contains(nd))
				{
					o.add(nd);
				}
				if(edge > 0)
				{
					edge -= 1;
					LinkedList tmp = list[ht.get(nd)];
					for(int i = 0; i < tmp.size(); i++)
					{
						queue.add((String)tmp.get(i));
						queue.add(edge);
					}
				}
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		String[] ans = o.toArray(new String[o.size()]);
		Arrays.sort(ans);
		return ans;
	}
}