import java.util.Scanner;
import java.time.Period;
import java.time.LocalDate;
import java.util.Scanner;

class libraryclass
{
    /*Total library books count*/
    static int book_count = 100;
    /*no.of books the customer took*/
    int count = 0;
    LocalDate borrow_date;
    libraryclass (int i)
    {
        borrow(i);
    }
    void borrow (int i)
    {
        if (book_count - i >= 0)
        {
            book_count-=i;
            count+=i;
        }
        else
            System.out.println("Insufficient books!");
    }
    void returning (int j)
    {
        book_count+=j;
        count-=j;
    }
    void penalty ()
    {
        LocalDate curr = LocalDate.now();
        int x = Period.between(borrow_date,curr).getDays();
        if(x <= 3)
        {
            System.out.println("No Penalty!");
            return;
        }
        System.out.println("Your Penalty is: Rs"+(x-3)*1.5);
    }
    void books_left()
    {
        System.out.println(count+"books to be returned");
    }
}
class library
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of customers: ");
        int no_c = sc.nextInt();
        int no_b;
        libraryclass[] customers = new libraryclass[no_c];
        for(int i = 0; i < no_c; i++)
        {
            System.out.println("Customer: "+(i+1));
            System.out.println("Enter the number of books you would like to borrow: ");
            no_b  = sc.nextInt();
            customers[i]=new libraryclass(no_b);
        }
        while (true)
        {
            System.out.println("Press 1 for borrowing: ");
            System.out.println("Press 2 for returning: ");
            System.out.println("Press 3 for checking how many books left in lib: ");
            System.out.println("Press 4 for checking how many books the customer has presently: ");
            System.out.println("Press 5 for checking any penalty: ");
            System.out.println("Press 6 for Exiting: ");
            int option = sc.nextInt();
            switch (option)
            {
                case 1:
                    {
                        System.out.println("Enter customer number: ");
                        int cust_no = sc.nextInt();
                        System.out.println("Enter the date(DD MM YYYY) of borrowing: ");
                        int day = sc.nextInt();
                        int month = sc.nextInt();
                        int year = sc.nextInt();
                        customers[cust_no-1].borrow_date = LocalDate.of(year,month,day);
                        System.out.println("Enter how many books borrowing: ");
                        int borr = sc.nextInt();
                        customers[cust_no-1].borrow(borr);
                        break;
                    }
                case 2:
                    {
                        System.out.println("Enter customer number: ");
                        int cust_no = sc.nextInt();
                        System.out.println("Enter how many books returning: ");
                        int ret = sc.nextInt();
                        customers[cust_no-1].returning(ret);
                        break;
                    }
                case 3:
                    System.out.println(libraryclass.book_count+" "+"books left");
                    break;
                case 4:
                    {
                        System.out.println("Enter customer number: ");
                        int cust_no = sc.nextInt();
                        System.out.println(customers[cust_no-1].count+" "+"books borrowed");
                        break;
                    }
                case 5:
                    {
                        System.out.println("Enter customer number: ");
                        int cust_no = sc.nextInt();
                        customers[cust_no-1].penalty();
                        break;
                    }
                case 6:
                    return;
            }
        }
    }
}