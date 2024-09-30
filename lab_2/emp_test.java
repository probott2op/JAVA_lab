import java.util.Scanner;
class employee
{
	String Ename;
	int Eid;
	float basic=0,da=0,gross_sal=0,net_sal=0,it=0;
	public void read(String Ename,float basic,int Eid)
	{
		this.Ename=Ename;
		this.basic=basic;
		this.Eid=Eid;
	}
	public void display()
	{
		System.out.println("The net salary of "+Ename+" "+"With id "+Eid+" "+"is "+net_sal);
	}
	public void compute_net_sal()
	{
		da=(float)0.52*basic;
		gross_sal=(float)basic+(float)da;
		it=(float)0.3*gross_sal;
		net_sal=gross_sal-it;
	}
}
class emp_test
{
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the number of employees");
	int noof_emp=sc.nextInt();
	employee[] emp_no=new employee[noof_emp];
	for(int i=0;i<noof_emp;i++)
	{
		emp_no[i] = new employee();
		System.out.println("Enter the Employee Id");
		int employee_id=sc.nextInt();
		System.out.println("Enter the name of the Employee");
		String d_name=sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter the basic salary");
		float basic_sal=sc.nextFloat();
		emp_no[i].read(name,basic_sal,employee_id);
	}
	for(int i=0;i<noof_emp;i++)
	{
		emp_no[i].compute_net_sal();
		emp_no[i].display();
	}
	}
}