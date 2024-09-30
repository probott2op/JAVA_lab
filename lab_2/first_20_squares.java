class first_20_squares
{
	public static void main(String[] args)
	{
		int i,total=0;
		for(i=1;i<=20;i++)
		{
			System.out.println(i*i);
			total+=i*i;
		}
		System.out.println("The total sum is "+total);
	}
}