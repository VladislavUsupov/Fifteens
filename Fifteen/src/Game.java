import java.util.*;

public class Game
{
    public int[][] Field;

    public Map<Integer, Coordinate> Coordinates;



    public Game()
    {
        Field = new int[4][4];
        Coordinates = new HashMap<>();
        writeData(getRandomSequence());
    }



    public Game(Integer[] numbers)
    {
        try
        {
            isCorrectData(numbers);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        double size = Math.sqrt(numbers.length);
        Field = new int[(int)size][(int)size];
        Coordinates = new HashMap<>();
        writeData(numbers);
    }



    public int Side() {
        return Field[0].length;
    }

    public int GetValue(int x, int y)
    {
        return Field[x][y];
    }



    public void Shift(int value)
    {
        if (!IsFinished()) {
            if (value <= 0 || value > Side() * Side() - 1) {
                throw new IllegalArgumentException("The value does not exist");
            }

            Coordinate a = new Coordinate();
            Coordinate b = new Coordinate();
            a = Coordinates.get(value);
            b = Coordinates.get(0);

            if ((Math.abs(a.X - b.X) + Math.abs(a.Y - b.Y)) == 1) {
                Coordinates.put(Field[a.X][a.Y], b);
                Coordinates.put(Field[b.X][b.Y], a);
                int x = Field[a.X][a.Y];
                Field[a.X][a.Y] = Field[b.X][b.Y];
                Field[b.X][b.Y] = x;
            } else {
                throw new IllegalArgumentException("The value must be next to the empty block");
            }
        } else {
            throw new IllegalArgumentException("Game is end");
        }
    }



    private void isCorrectData(Integer[] numbers)
    {
        double size = Math.sqrt(numbers.length);
        int flag = 0;

        if ((size % 1) > 0) {
            throw new IllegalArgumentException("Wrong count of number in parameter 'numbers'");
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                flag++;
            }
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] == numbers[j]) {
                    throw new IllegalArgumentException("Repeat number in parameter 'numbers'");
                }
            }
        }

        if (flag == 0) {
            throw new IllegalArgumentException("Need empty block in parameter 'numbers'");
        }
    }



    private Integer[] getRandomSequence()
    {
        List<Integer> fill = new ArrayList<>();
        for ( int i = 1; i < Side() * Side(); i++ ) {
            fill.add(i);
        }
        Collections.shuffle(fill);

        Integer[] numbers = null;
        numbers = fill.toArray(new Integer[fill.size()]);

        int count = 0;
        for (int i = 0; i < numbers.length - 1; i++)
        {
            for (int j = i + 1; j < numbers.length; j++)
            {
                if (numbers[i] > numbers[j])
                {
                    count++;
                }
            }
        }

        if (count % 2 != 0)
        {
            for (int i = 0; i < numbers.length; i++)
            {
                if (numbers[i] == numbers.length - 1)
                {
                    numbers[i] = numbers.length - 2;
                }
                else if (numbers[i] == numbers.length - 2)
                {
                    numbers[i] = numbers.length - 1;
                }
            }
        }
        List<Integer> temp = new ArrayList<>();
        temp = Arrays.asList(numbers);
        fill.add(0);
        Integer[] result = null;
        result = fill.toArray(new Integer[fill.size()]);

        return result;
    }



    private boolean IsFinished()
    {
        if (Field[Side() - 1][Side() - 1] == 0)
        {
            int count = 1;
            for (int i = 0; i < Side(); i++)
            {
                for (int j = 0; j < Side(); j++)
                {
                    if (Field[i][j] != count && Field[i][j] != 0)
                    {
                        return false;
                    }
                    count++;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }



    private void writeData(Integer[] numbers)
    {
        int index = 0;
        for (int i = 0; i < Field[0].length; i++)
        {
            for (int j = 0; j < Field[0].length; j++)
            {
                Field[i][j] = numbers[index];
                Coordinate temp = new Coordinate();
                temp.X = i;
                temp.Y = j;
                Coordinates.put(numbers[index], temp);
                index++;
            }
        }
    }
}