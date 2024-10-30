import java.util.ArrayList;

class Random
{
    public static void randomfunc(ArrayList<?> num)
    {
        for (Object x: num)
            System.out.println(x);
    }
}
public class Test 
{
    public static void main(String[] args) 
    {
        ArrayList<Integer> obj = new ArrayList<>();
        obj.add(1);
        obj.add(2);
        obj.add(3);
        Random.randomfunc(obj);
    }
}