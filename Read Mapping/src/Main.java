import java.util.Scanner;
// import java.io.File;
// import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) /*throws FileNotFoundException*/ {
		int total = 0, retotal = 0;
		// File file = new File("input_Read Mapping.txt") ;
        // Scanner in = new Scanner(file);
        Scanner in = new Scanner(System.in);
		int readCount=Integer.parseInt(in.nextLine());
		int count[] = new int[readCount];
		String[] data = new String[readCount + 1];
		for (int i=0; i<=readCount; i++)
		{
			String read = in.nextLine();
			data[i] = read;
		}
		int sample = data[readCount].length();
		for(int i = 0; i<readCount; i++)
		{
			char c = data[i].charAt(0);
			int k = data[i].length();
			for(int j = 0; j <= (sample - k); j++)
			{
				if(c == data[readCount].charAt(j))
				{
					if(data[readCount].substring(j, j+k).equals(data[i]))
					{	
						count[i]++;
					}
				}
			}
		}
		for(int i = 0; i<readCount; i++)
		{
			if(count[i] != 0)
			{
				total++;
			}
			if(count[i] > 1)
			{
				retotal++;
			}
		}
		System.out.println(total);
		System.out.println(retotal);
	}
}