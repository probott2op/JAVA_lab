interface Series
{
    void gen_next();
    void reset();
    void set_start(int val);
}
// by default all interface methods are public
class by_twos implements Series
{
    int series = 0, set = 0;
    public void set_start(int val)
    {
        set = val;
        series = set;
    }
    public void gen_next()
    {
        series += 2;
        System.out.print(series+" ");
    }
    public void reset()
    {
        series = set;
    }
}
public class bytwotest
{
    public static void main(String[] args)
    {
        by_twos obj = new by_twos();
        obj.set_start(5);
        for (int i = 0; i < 4; i++)
            obj.gen_next();
        obj.reset();
        obj.gen_next();
    }
}