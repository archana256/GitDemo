package files.JavaTraining;

public class MultiDimensionalArray {

    public static void main(String args[]) {
        int a[] = new int[2];//traditional way of assigning memory
        a[0] = 1;
        a[1] = 2;

        int b[] = {1, 2}; //to dynamically assign memory


        //multidimensional arrays
        int c[][] = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(c.length);

        for(int i=0;i<c.length;i++) //for row parsing
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(c[i][j]+ " ");
            }
            System.out.println("");
        }
    }
}
