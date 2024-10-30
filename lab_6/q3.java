import java.util.*;
class Person
{
    private String name;
    private GregorianCalendar dob;
    Person (String name, GregorianCalendar dob)
    {
        this.name = name;
        this.dob = dob;
    }
    void display()
    {
        System.out.println("The persons name is: "+name);
        System.out.println("The persons year of birth is: "+dob.get(GregorianCalendar.YEAR));
    }
}
class Graduate extends Person
{
    private float GPA;
    private int grad_year;
    Graduate (String name, GregorianCalendar dob,float GPA, int grad_year)
    {
        super(name,dob);
        this.GPA = GPA;
        this.grad_year = grad_year;
    }
    void display()
    {
        super.display();
        System.out.println("The persons GPA is: "+GPA);
        System.out.println("The persons year of graduation is: "+grad_year);
    }
}
public class Test
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your dob YYYY MM DD: ");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();
        GregorianCalendar dob = new GregorianCalendar(year, month - 1, day);
        System.out.print("Enter your GPA: ");
        float gpa = sc.nextFloat();
        System.out.print("Enter graduation year: ");
        int grad_year = sc.nextInt();
        Graduate stu = new Graduate(name, dob, gpa, grad_year);
        System.out.println();
        stu.display();
    }
}