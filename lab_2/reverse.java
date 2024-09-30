class reverse
{
	public static int rev(int n)
	{
		int r=0;
		while(n>0)
		{
			r=(r*10)+(n%10);
			n=n/10;
		}
		return r;
	}
	public static void main(String[] args)
	{
		int reve=rev(2422);
		System.out.println("The reverse is"+reve);
	}
}