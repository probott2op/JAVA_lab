import com.course.maths.MathUtility;
import java.time.LocalDate;
public class mathtest 
{
    public static void main(String[] args) 
    {
        MathUtility obj = new MathUtility();

        System.out.println("Random Number: " + obj.numberGenerator(5, 7));
        System.out.println("Interest is: " + obj.calculateSimpleInterest(10000, 5, 0.082));
        obj.printTriangle();
        LocalDate date = LocalDate.of(2024,10,1);
        obj.printMonthFromDate(date);
    }
}