class rowcalc implements Runnable
{
    Thread thr;
    int[] arr;
    int sum;
    public Thread getThread()
    {
        return thr;
    }
    rowcalc(int[] arr)
    {
        this.arr = arr;
        thr = new Thread(this);
        thr.start();
    }
    public void run()
    {
        for (int v: arr)
            sum += v;
    }
    public int getSum()
    {
        return sum;
    }
}
public class test
{
    public static void main(String[] args) 
    {
        int sum = 0;
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        rowcalc[] rowarr = new rowcalc[arr.length];
        for (int i = 0; i < arr.length; i++)
            rowarr[i] = new rowcalc(arr[i]);
        try
        {
            rowarr[arr.length - 1].getThread().join();   
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
        for (int i = 0; i < arr.length; i++)
        {
            sum += rowarr[i].getSum();   
        }
        System.out.println("The sum is: "+sum);
    }
}