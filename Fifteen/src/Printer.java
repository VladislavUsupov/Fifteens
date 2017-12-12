public class Printer {

    public static void Printing(int[][] field, int side)
    {
        for(int i=0; i<side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}
