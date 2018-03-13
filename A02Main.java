import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class A02Main
{
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args)
	{
			
		//double in = Double.parseDouble(args[0]);
		System.out.println(Arrays.toString(perm(args[0])));
	}
	public static String[] perm(String base)
	{
		String[] perms = new String[fact(base.length())];
		//Arrays.fill(perms,base);
		for(int i = 0; i < perms.length; i++)
		{
			perms[i] = base;
			for(int j = 0; j < base.length(); j++)
			{
				char[] chars = perms[i].toCharArray();
				swap(chars, i%chars.length, j);
			}
		}
		return perms;
	}

	private static void swap(char[] a, int a1, int a2)
	{
		char temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
	}

	private static int fact(int n)
	{
		int retVal = 1;
		while(n > 0)
			retVal *= n--;
		return retVal;
	}
}
