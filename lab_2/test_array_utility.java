import java.util.Scanner;
class ArrayUtility
{
	public static void getMinmaxValueFrom1DArray(int[] array)
	{
		int largest=array[0];
		int smallest=array[0];
		for(int v:array)
		{
			if(v>largest)
				largest=v;
		}
		for(int v:array)
		{
			if(v<smallest)
				smallest=v;
		}
		System.out.println("The largest number is:- "+largest);
		System.out.println("The smallest number is:- "+smallest);
	}
	public static int[] reverse(int[] nonrevarray)
	{
		int[] revarray=new int[nonrevarray.length];
		int i=nonrevarray.length-1;
		for(int v:nonrevarray)
		{
			revarray[i]=v;
			i--;
		}
		return revarray;		
	}
	public static void useOfIterator(int[] remove_array)
	{
		int counter=0;
		int[] remove_arr2=new int[remove_array.length];
		System.out.println("The removed elements are:-");
		for(int v:remove_array)
		{
			if((v%2==0) || (v>50))
				System.out.println(v);
		}	
		for(int v:remove_array)
		{
			if(!((v%2==0) || (v>50)))
			{
				remove_arr2[counter]=v;
				counter++;
			}
		}
		System.out.println("The updated array is:-");
		for(int i=0;i<counter;i++)
			System.out.println(remove_arr2[i]);
	}
	public static void sumOfTwoArrayOfSameSize(int[] sum_arr1,int[] sum_arr2)
	{
		for(int i=0;i<sum_arr2.length;i++)
		{
			sum_arr1[i]=sum_arr1[i]+sum_arr2[i];
		}
		System.out.println("The sum of 1D arrays is:-");
		for(int v:sum_arr1)
			System.out.println(v);
	}
	public static void sumOfTwo2DArrayOfSameSize(int[][] sum_arr1,int[][] sum_arr2)
	{
		for(int i=0;i<sum_arr1.length;i++)
			for(int j=0;j<sum_arr1[i].length;j++)
				sum_arr1[i][j]=sum_arr1[i][j]+sum_arr2[i][j];
		System.out.println("The sum of 2D arrays is:-");
		for(int[] x:sum_arr1)
		{
			for(int v:x)
				System.out.print(v+"\t");
			System.out.println();
		}
	}
}
class test_array_utility
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter how many elements in the array:-");
		int noof_ele=sc.nextInt();
		int[] arr=new int[noof_ele];
		for(int i=0;i<noof_ele;i++)
			arr[i]=sc.nextInt();
		ArrayUtility.getMinmaxValueFrom1DArray(arr);
		int[] reversed_array=new int[noof_ele];
		reversed_array=ArrayUtility.reverse(arr);
		System.out.println("The reversed array is:-");
		for(int v:reversed_array)
			System.out.println(v);
		ArrayUtility.useOfIterator(arr);
		int[] arr2=new int[noof_ele];
		System.out.println("Enter the elements of the second array:-");
		for(int i=0;i<noof_ele;i++)
			arr2[i]=sc.nextInt();
		ArrayUtility.sumOfTwoArrayOfSameSize(arr,arr2);
		System.out.println("Enter the size of the matrix:-");
		int m=sc.nextInt();
		int n=sc.nextInt();
		int[][] arr2d1=new int[m][n];
		int[][] arr2d2=new int[m][n];
		System.out.println("Enter the elements of the 1st 2-D array:-");
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				arr2d1[i][j]=sc.nextInt();
		System.out.println("Enter the elements of the 2nd 2-D array:-");
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				arr2d2[i][j]=sc.nextInt();
		ArrayUtility.sumOfTwo2DArrayOfSameSize(arr2d1,arr2d2);
	}
}