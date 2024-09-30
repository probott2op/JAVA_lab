class Building
{
	int SquareFoot;
	int Stories;
	public void display()
	{
		System.out.println("The Building has squarefoot of "+SquareFoot);
		System.out.println("The number of Stories of the building are "+Stories);
	}
	Building (int SquareFoot, int Stories)
	{
		this.SquareFoot = SquareFoot;
		this.Stories = Stories;
	}
}
class House extends Building
{
	int Bedrooms;
	int Baths;
	public void display()
	{
		super.display();
		System.out.println("The number of bathrooms are "+Baths);
		System.out.println("The number of bedrooms are "+Bedrooms);
	}
	House (int SquareFoot, int Stories, int Bedrooms, int Baths)
	{
		super(SquareFoot,Stories);
		this.Bedrooms = Bedrooms;
		this.Baths = Baths;
	}

}
class School extends Building
{
	int Classes;
	String GradeLevel;
	public void display()
	{
		super.display();
		System.out.println("The number of classrooms are: "+Classes);
		System.out.println("The grade level is "+GradeLevel);
	}
	School (int SquareFoot, int Stories, int Classes, String GradeLevel)
	{
		super(SquareFoot,Stories);
		this.Classes = Classes;
		this.GradeLevel = GradeLevel;
	}

}
public class q4
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