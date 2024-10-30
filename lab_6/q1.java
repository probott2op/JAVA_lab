import java.util.Scanner;
class Student
{
	String sname;
	int[] marksarray;
	int total=0;
	float avg;
	Student(String sname,int[] marksarray)
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
	public void initials()
	{
		String[] words = sname.split("\\s+");
		StringBuilder initials_string = new StringBuilder();
		for (String word : words)
		{
			if(word.length() > 0)
			{
				initials_string.append(word.charAt(0));
			}
		}
		System.out.println("The initials are: "+initials_string.toString().trim());
	}
	public void RemoveWhiteSpace()
	{
		String[] words = sname.split("\\s+");
		StringBuilder white_string = new StringBuilder();
		for (String word : words)
		{
			if(word.length() > 0)
			{
				white_string.append(word);
			}
		}
		System.out.println("The name without white space is: "+white_string.toString().trim());
	}
	public void particular_string(String sub_string)
	{
		String[] words = sname.split("\\s+");
		for(String word : words)
			if(word == sub_string)
				System.out.println("sub string matched");
	}
}
class ScienceStudent extends Student
{
    private int practicalMarks;
    ScienceStudent(String sname,int[] marksarray, int practicalMarks)
	{
	    super(sname,marksarray);
		this.sname=sname;
		this.marksarray = new int[marksarray.length];
		this.marksarray=marksarray;
		this.practicalMarks = practicalMarks;
	}
    public void compute()
	{
		super.compute();
		total += practicalMarks;
		avg=(float)total/(float)(marksarray.length + 1);
	}
	public void displayPracticalMarks()
	{
	    System.out.println("The practical Marks are "+practicalMarks);
	}
}
class ArtsStudent extends Student
{
    private String electiveSubject;
    ArtsStudent(String sname,int[] marksarray, String electiveSubject)
	{
	    super(sname,marksarray);
		this.sname=sname;
		this.marksarray = new int[marksarray.length];
		this.marksarray=marksarray;
		this.electiveSubject = electiveSubject;
	}
	public void displayelec()
	{
	    System.out.println("The elective is: "+electiveSubject);
	}
}
public class test
{
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	Student[] students=new Student[3];
	for(int i = 0; i < 3; i++)
	{
	    System.out.print("Enter 1 for Science and 2 for arts and 3 for student: ");
	    int option = sc.nextInt();
	    sc.nextLine();
		System.out.print("Enter the name of the student: ");
		String name = sc.nextLine();
		System.out.print("Enter the total number of subjects: ");
		int sub_no = sc.nextInt();
		System.out.println("Enter the marks of the student:");
		int[] arr = new int[sub_no];
		for(int x = 0; x < sub_no; x++)
			arr[x] = sc.nextInt();
		sc.nextLine();
		switch (option)
		{
		    case 3:
		        students[i] = new Student(name,arr);
		        students[i].compute();
		        break;
		    case 1:
		        System.out.print("Enter the practical marks: ");
		        int p_marks = sc.nextInt();
		        sc.nextLine();
		        students[i] = new ScienceStudent(name,arr,p_marks);
		        students[i].compute();
		        break;
		    case 2:
		        System.out.print("Elective Subject name: ");
		        String elec = sc.nextLine();
		        students[i] = new ArtsStudent(name,arr,elec);
		        break;
		}
		System.out.println();
	}
	for (Student student:students)
	{
	    student.display();
	    if (student instanceof ScienceStudent)
	        ((ScienceStudent) student).displayPracticalMarks();
	    else if (student instanceof ArtsStudent)
	        ((ArtsStudent) student).displayelec();
	    System.out.println();
	}
}
}