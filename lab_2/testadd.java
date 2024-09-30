import java.util.Scanner;

class intarr
{
    int[] arr;
    intarr()
    {
        System.out.println("Default constructor");
    }
    intarr(int... data)
    {
        arr = new int[data.length];
        for(int i = 0; i < data.length; i++)
        {
            arr[i]=data[i];
        }
    }
    void display()
    {
        for(int v: arr)
            System.out.println(v);
    }
    int search (int key)
    {
        int count = 0;
        for(int v : arr)
        {
            if(v == key)
            {
                return count+1;
            }
            count++;
        }
        return -1;
    }
    public static boolean compare(intarr ob1, intarr ob2)
    {
        if(ob1.arr.length != ob1.arr.length)
            return false;
        for(int i = 0; i < ob1.arr.length; i++)
        {
            if(ob1.arr[i] != ob2.arr[i])
                return false;
        }
        return true;
    }
}
class testadd
{
    public static void main(String[] args)
    {
        intarr ob1 = new intarr();
        intarr ob2 = new intarr(1,2,3,4);
        intarr ob3 = new intarr(1,42,3,21);
        intarr ob4 = new intarr(1,2,3,4);
        System.out.println("ELement found at: " + ob3.search(42));
        ob3.display();
        if(intarr.compare(ob2,ob4) == true)
            System.out.println("ob2 and ob4 are equal!");
        if(intarr.compare(ob2,ob3) == false)
            System.out.println("ob2 and ob3 are not equal!");
    }
}