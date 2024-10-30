abstract class Figure
{
    int dim1;
    int dim2;
    Figure(int dim1, int dim2)
    {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }
    abstract void area();
}
class Rectangle extends Figure
{
    Rectangle(int dim1, int dim2)
    {
        super(dim1,dim2);
    }
    void area()
    {
        System.out.println("area is: "+dim1*dim2);
    }
}
class Square extends Figure
{
    Square(int side)
    {
        super(side,side);
    }
    void area()
    {
        System.out.println("area is: "+dim1*dim2);
    }
}
class Triangle extends Figure
{
    Triangle(int dim1, int dim2)
    {
        super(dim1,dim2);
    }
    void area()
    {
        System.out.println("area is: "+0.5*dim1*dim2);
    }
}
public class Test
{
    public static void main(String[] args)
    {
        Figure rect = new Rectangle(2, 3);
        Figure square = new Square(3);
        Figure triangle = new Triangle(2, 3);

        rect.area();
        square.area();
        triangle.area();
    }
}