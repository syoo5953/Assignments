import java.util.Arrays;
import java.util.Random;

public class Test {
    static int FIELD_SIZE = 10;

    public void fillField() {
        Random random = new Random();
        int[][] field = new int[FIELD_SIZE][FIELD_SIZE];
        for (int i = 5; i > 0; i--) {
            System.out.println("Placing ship with length: " + i);
            // start point of the ship and direction
            int x = random.nextInt(field.length);
            int y = random.nextInt(field.length);
            boolean vertical = random.nextBoolean();

            // correct start point so that the ship could fit in the field
            if (vertical) {
                if (y + 2 > FIELD_SIZE) {
                    y -= 2;
                }
            } else if (x + 2 > FIELD_SIZE) {
                x -= 2;
            }
            System.out.println("Start point: " + x + ", " + y + "; dir: " + (vertical ? "VERTICAL" : "HORIZONTAL"));
            System.out.println("\n\n");
            boolean isFree = true;
            // check for free space
            if (vertical) {
                for (int m = y; m < y + 2; m++) {
                    if (field[m][x] != 0) {
                        System.out.println("Neighbors found, " + x + ", " + m);
                        isFree = false;
                        break;
                    }
                }
            } else {
                for (int n = x; n < x + 2; n++) {
                    if (field[y][n] != 0) {
                        System.out.println("Neighbors found, " + n + ", " + y);
                        isFree = false;
                        break;
                    }
                }
            }
            if (!isFree) {  // no free space found, retry
                i++;
                continue;
            }

            // fill in the adjacent cells
            if (vertical) {
                for (int m = Math.max(0, x - 1); m < Math.min(FIELD_SIZE, x + 2); m++) {
                    for (int n = Math.max(0, y - 1); n < Math.min(FIELD_SIZE, y + i + 1); n++) {
                        field[n][m] = 1;
                    }
                }
            } else {
                for (int m = Math.max(0, y - 1); m < Math.min(FIELD_SIZE, y + 2); m++) {
                    for (int n = Math.max(0, x - 1); n < Math.min(FIELD_SIZE, x + i + 1); n++) {
                        field[m][n] = 1;
                    }
                }
            }
            // fill in the ship cells
            for (int j = 0; j < 2; j++) {
                field[y][x] = 2;
                if (vertical) {
                    y++;
                } else {
                    x++;
                }
            }
        }
        // build char map
        char[][] map = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field[i][j] + " ");
                map[i][j] = field[i][j] == 2 ? 'O' : '.';
            }
            System.out.println();
        }
        // print map
        Arrays.stream(map)
                .forEach(m -> System.out.println(Arrays.toString(m).replace(",", "")));
    }
}
