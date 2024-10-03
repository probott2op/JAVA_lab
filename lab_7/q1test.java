import com.course.structure.House;
import com.course.structure.School;
import com.course.structure.Building;
public class q1test
{
    public static void main(String[] args)
    {
        School s = new School(10000, 4, 5, "Junior High");
		House h = new House(4000, 3, 4, 3);
		Building b = new Building(2000, 2);
		s.display();
		System.out.println();
		h.display();
		System.out.println();
		b.display();
		System.out.println();
    }
}