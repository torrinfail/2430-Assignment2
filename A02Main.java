import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * CSIS 2430 Programming Assignment 2
 * @author Aidan Hubert
 */
public class A02Main
{
	private static ArrayList<Integer[]> permList; //Stores permuations.

	//Returns the base permutation of a given n, EX n=3 returns {0,1,2}
	private static Integer[] getBasePerm(int n)
	{
		Integer[] basePerm = new Integer[n];

		for(int i = 0; i < n; i++)
		{	
			basePerm[i] = i;
		}
		return basePerm;
	}
	
	
	//Returns all possible permuations of a given n.
	public static ArrayList<Integer[]> getPerms(int n)
	{
		permList = new ArrayList<>();
		perm(getBasePerm(n),0,n-1);
		return permList;
	}
	
	//Generates all possible permuations of a given n.
	private static void perm(Integer[] num, int l, int r)
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
	}

	//Swap two Integers in an array.
	private static Integer[] swap(Integer[] a, int a1, int a2)
	{
		a = Arrays.copyOf(a,a.length);
		int temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
		return a;
	}
	
	//Returns average number of comparisons for given result set.
	public static int average(Result[] results)
	{
		int sum = 0;
		for(Result r : results)
			sum += r.compareCount;
		return sum / results.length;
	}

	//Prints 10 best and worst cases, as well as average for given result set. 
	public static void getStats(Result[] results)
	{
		int average = average(results);

		System.out.println("10 Best Cases:");
		Arrays.sort(results);
		for(int i = 0; i < 10; i++)
		{	
			System.out.println(results[i]);
		}

		System.out.println("Average: " + average);
		
		System.out.println("10 Worst Cases:");
		Arrays.sort(results,Result.byWorstCase());
		for(int i = 0; i < 10; i++)
		{	
			System.out.println(results[i]);
		}
	}



	public static void main(String[] args)
	{
		int n;
		if(args.length == 0)
		{
			System.out.print("n = :");
			n = Integer.parseInt(new Scanner(System.in).nextLine());
		}
		else
	       		n = Integer.parseInt(args[0]);
		ArrayList<Integer[]> genPerms = getPerms(n);
		//Test Mergesort
		Result[] mergeResults = new Result[genPerms.size()];
		{
			int i = 0;
			for(Integer[] current : genPerms)
			{
				Merge.sort(Arrays.copyOf(current,current.length));
				mergeResults[i++] = new Result(Merge.numOfCompares, current);
				Merge.numOfCompares = 0;
			}
		}

		//Test Quicksort
		Result[] quickResults = new Result[genPerms.size()];
		{
			int i = 0;
			for(Integer[] current : genPerms)
			{
				Quick.sort(Arrays.copyOf(current,current.length));
				quickResults[i++] = new Result(Quick.numOfCompares, current);
				Quick.numOfCompares = 0;
			}
		}

		//Test Heapsort
		Result[] heapResults = new Result[genPerms.size()];
		{
			int i = 0;
			for(Integer[] current : genPerms)
			{

				Heap.sort(Arrays.copyOf(current,current.length));
				heapResults[i++] = new Result(Heap.numOfCompares, current);
				Heap.numOfCompares = 0;
			}
		}	

		System.out.println("Mergesort:");
		getStats(mergeResults);
		System.out.println();
		System.out.println("Quicksort:");
		getStats(quickResults);
		System.out.println();
		System.out.println("Heapsort:");
		getStats(heapResults);

	}

	//class to store both compareCount and the original unsortedArray in one object.
	private static class Result implements Comparable
	{
		public int compareCount;
		public Integer[] unsortedArray;
		public Result(int compareCount, Integer[] unsortedArray)
		{
			this.compareCount = compareCount;
			this.unsortedArray = unsortedArray;
		}

		@Override
		public int compareTo(Object o1)
		{
			Result other = (Result)o1;
			return compareCount - other.compareCount;
		}

		@Override
		public String toString()
		{
			return Arrays.toString(unsortedArray) + " " + compareCount + " comparisons";
		}

		public static Comparator<Result> byWorstCase()
		{
			return new ByWorstCase();
		}

		//This Comparator class is used to sort the results so the worst cases are at the beginning.
		private static class ByWorstCase implements Comparator<Result>
		{
			public int compare(Result r1, Result r2)
			{
				return -(r1.compareTo(r2));
			}
		}
	}
}
