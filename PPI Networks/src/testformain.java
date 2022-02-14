import edu.princeton.cs.algs4.Graph;
import java.io.File;
import java.util.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

public class testformain {
	public static void main (String[] args) throws Exception{
		Main a = new Main(20);
		File file = new File("hw2.txt") ;
		Scanner in = new Scanner(file);
		String pro1 = "";		
		while(in.hasNextLine())
		{
			pro1 = in.nextLine();
			String[] pro = pro1.split("\t");
			a.add(pro[0],pro[1]);
		}
		for(String o:a.interactions("protein05",2))
			System.out.println(o);
	}
}