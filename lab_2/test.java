import java.util.Scanner;
class student
{
	String sname;
	int[] marksarray;
	int total=0;
	float avg;
	public void assign(String sname,int[] marksarray)
	{
		this.sname=sname;
		this.marksarray=marksarray;
		
	}
	public void display()
	{
		System.out.println("The student name is:- "+sname);
		System.out.println("The marks of the student are:-");
		for(int v:marksarray)
			System.out.println(v);
		System.out.println("The total marks are:- "+total);
		System.out.println("The avg marks is:- "+avg);
	}
	public void compute()
	{
		for( int i:marksarray)
			total+=i;
		avg=(float)total/(float)marksarray.length;
	}
}
class test
{
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	student student_1=new student();
	System.out.println("Enter the name of the student");
	String name=sc.nextLine();
	System.out.println("Enter the total number of subjects");
	int sub_no=sc.nextInt();
	System.out.println("Enter the marks of the student");
	int[] arr=new int[sub_no];
	for(int x=0;x<sub_no;x++)
		arr[x]=sc.nextInt();
	student_1.assign(name,arr);
	student_1.compute();
	student_1.display();
	}
}