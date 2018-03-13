import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class A02Main
{
	static int J;
	private static ArrayList<int[]> permList;
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		ArrayList<int[]> genPerms = getPerms(n);
		for(int i = 0; i < genPerms.size(); i++)
		{	

			for(int j = 0; j < genPerms.get(0).length; j++)
			{	
				System.out.print(genPerms.get(i)[j]);
			}

		}

	}

	private static int[] getBasePerm(int n)
	{
		int[] basePerm = new int[n];

		for(int i = 0; i < n; i++)
		{	
			basePerm[i] = i;
		}
		return basePerm;
	}

	public static ArrayList<int[]> getPerms(int n)
	{
		permList = new ArrayList<>();
		perm(getBasePerm(n),0,n-1);
		return permList;

	}

	private static void perm(int[] num, int l, int r)
	{
		if(l == r)
		{
			permList.add(num);
		}

		for(int i = l; i <= r; i++)
		{	
			num = swap(num,l,i);
			perm(num,l+1,r);
			num = swap(num,l,i);	
		}
		//return permList;
	}

	private static int[] swap(int[] a, int a1, int a2)
	{
		a = Arrays.copyOf(a,a.length);
		int temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
		return a;
	}

	private static int fact(int n)
	{
		int retVal = 1;
		while(n > 0)
			retVal *= n--;
		return retVal;
	}
}
