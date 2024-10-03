import java.util.*;
interface Sports
{
    boolean put_grade(float marks);
}
class Student
{
    int roll;
    float marks;
    public void get_number(int roll)
    {
        this.roll = roll;
    }
    public void get_marks(int marks)
    {
        this.marks = marks;
    }
    public void put_marks()
    {
        System.out.println("The marks are: "+marks);
    }
    public void put_number()
    {
        System.out.println("The roll no is: "+roll);
    }
}
class Result extends Student implements Sports
{
   public boolean put_grade(float marks)
   {
        if (marks > 90)
        {
            System.out.println("Grade A");
            return true;
        }
        else if (marks > 80)
        {
            System.out.println("Grade B");
            return true;
        }
        else if (marks > 70)
        {
            System.out.println("Grade C");
            return true;
        }
        else if (marks > 60)
        {
            System.out.println("Grade D");
            return true;
        }
        else
        {
            System.out.println("Fail :(");
            return false;
        }
   }
   public void result_gen()
   {
        if (put_grade(marks))
            System.out.println("Passed!");
        else
            System.out.println("Failed!");
   }
}
public class result_test
{
    public static void main(String[] args)
   {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        System.out.print("Enter marks: ");
        int marks = sc.nextInt();
        Result obj = new Result();
        obj.get_number(roll);
        obj.get_marks(marks);
        obj.put_number();
        obj.put_marks();
        obj.result_gen();
   }
}