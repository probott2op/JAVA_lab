class triangular
{
    public static void main(String[] args)
    {
        int[][] arr = new int[10][];
        for (int i = 0; i < 10; i++)
        {
            if (i < 5)
            {
                arr[i] = new int[i+1];
                for (int j = 0; j < i + 1; j++ )
                    arr[i][j] = i + 1 + j + 1;
            }
            if (i >= 5)
            {
                arr[i] = new int[10 - i - 1];
                for (int j = 0; j < 10 - i - 1; j++ )
                    arr[i][j] = i + 1 + j + 1;
            }
        }
        for(int[] x : arr)
        {
            for(int v : x)
                System.out.print(v+" ");
            System.out.println();
        }
    }
}