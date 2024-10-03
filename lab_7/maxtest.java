import mypackages.p1.Maximum;
public class maxtest 
{
    public static void main(String[] args) 
    {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("Max of int: " + Maximum.max(1, 2, 3));
        System.out.println("Max of float: " + Maximum.max(1.8, 0.3, 3.7));
        System.out.println("Max of 1-D arr: " + Maximum.max(arr1));
        System.out.println("Max of 2-D arr: " + Maximum.max(arr2));
    }
}