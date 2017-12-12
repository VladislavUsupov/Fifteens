public class Program {



    public static void main(String[] args) {

        Integer[] data = new Integer[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 0, 15};

        Game myGame = new Game(data);

        Printer.Printing(myGame.Field, myGame.Side());
        myGame.Shift(15);

        Printer.Printing(myGame.Field, myGame.Side());

    }


}