import java.util.Scanner;
import java.time.Period;
import java.time.LocalDate;
class Mathutility
{
	public static Boolean checkifprime(int n)
	{
		int count=0;
		for(int i=1;i<=n;i++)
			if(n%i==0)
				count++;
		if(count==2)
			return true;
		else
			return false;
	}
	public static int[] primerange(int start,int end)
	{
		int counter=0;
		int[] retarr=new int[end-start];
		for(int j=start;j<=end;j++)
			if(checkifprime(j)==true)
			{
				retarr[counter]=j;
				counter++;
			}
			return retarr;
	}
	public static Boolean checkifpalindrome(int n)
	{
		int rev=0,copy=n,digit;
		while(n>0)
		{
			digit=n%10;
			rev=rev*10+digit;
			n=n/10;
		}
		if(rev==copy)
			return true;
		else
			return false;
	}
	public static int findage(LocalDate birthdate)
	{
		LocalDate currentdate=LocalDate.now();
		return Period.between(birthdate,currentdate).getYears();
	}
	public static int factorial(int number)
	{
		if(number==1)
			return 1;
		return number*factorial(number-1);
	}
}
class mathfunc
{
	public static void main(String[] args) 
	{
		if(Mathutility.checkifprime(17));
			System.out.println("17 is a prime number");
		int[] array=new int[100];
		array=Mathutility.primerange(1,15);
		for(int v:array)
		{
			if(v==0)
				continue;
			System.out.println(v);
		}
		if(Mathutility.checkifpalindrome(1221));
			System.out.println("1221 is a palindrome");
		LocalDate birthdate=LocalDate.of(1995,5,23);
		System.out.println("Your age is:- "+Mathutility.findage(birthdate));
		System.out.println("The factorial of 7 is:- "+Mathutility.factorial(7));
	}
}