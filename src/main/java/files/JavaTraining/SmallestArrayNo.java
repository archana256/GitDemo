package files.JavaTraining;

public class SmallestArrayNo {
    public static void main(String args[]) {
        int c[][] = {{2, 4, 5}, {3, 0, 7}, {1, 10, 9}};
        int min = c[0][0];
        int minNoColumn=0;

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (c[i][j] < min) {
                    min = c[i][j];
                    minNoColumn = j;
                }
            }

        }

        int k = 0;

        int max = c[0][minNoColumn];
        while (k < 3) {
            if (c[k][minNoColumn] > max) {
                max = c[k][minNoColumn];
            }
            k++;
        }
        System.out.println(max);

    }

}
