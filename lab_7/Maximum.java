package mypackages.p1;

public class Maximum 
{
    public static int max(int a, int b, int c) 
    {
        int largest = a > b ? (a > c ? a : c) : (b > c ? b : c);
        return largest;
    }

    public static double max(double a, double b, double c)
    {
        double largest = a > b ? (a > c ? a : c) : (b > c ? b : c);
        return largest;
    }

    public static int max(int[] arr) 
    {
        int largest = arr[0];
        for (int v : arr) 
        {
            if (v > largest) 
            {
                largest = v;
            }
        }
        return largest;
    }

    public static int max(int[][] arr) 
    {
        int largest = arr[0][0];
        for (int[] x : arr) 
        {
            for (int y : x) 
            {
                if (y > largest) 
                {
                    largest = y;
                }
            }
        }
        return largest;
    }
}