import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int r;
    private int c;

    private Square[][] board;

    private ArrayList<Battleship> shipList = new ArrayList<>();

    public Board(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public ArrayList<Battleship> getShipList() {
        return shipList;
    }

    public void addShips(Type shipType) {
        if(shipType == Type.SmallBattleShip) {
            shipList.add(new SmallBattleShip());
        } else if(shipType == Type.MediumBattleShip) {
            shipList.add(new MediumBattleShip());
        } else if(shipType == Type.LargeBattleShip) {
            shipList.add(new LargeBattleShip());
        }
    }

    public void init() {
        this.board = new Square[r][c];
        for(int i = 0; i < this.r; i++) {
            for(int j = 0; j < this.c; j++) {
                this.board[i][j] = new Square();
            }
        }
    }

    public void populateBattleship(int total) {
        Random random = new Random();
        int[][] field = new int[r][c];
        for (int i = total - 1; i >= 0; i--) {
            Battleship ship = this.shipList.get(i);
            int shipSize = this.shipList.get(i).getSize();
            int x = random.nextInt(field.length);
            int y = random.nextInt(field.length);
            boolean vertical = random.nextBoolean();

            // Place the ships inside the boundary
            if (vertical) {
                if (y + shipSize > c) { y -= shipSize; }
            } else if (x + shipSize > r) { x -= shipSize; }

            // Check for overlapping between the ships
            boolean isFree = true;

            if (vertical) {
                for (int m = y; m < y + shipSize; m++) {
                    if (field[m][x] != 0) {
                        isFree = false;
                        break;
                    }
                }
            } else {
                for (int n = x; n < x + shipSize; n++) {
                    if (field[y][n] != 0) {
                        isFree = false;
                        break;
                    }
                }
            }
            if (!isFree) {
                i++;
                continue;
            }

            for (int j = 0; j < shipSize; j++) {
                field[y][x] = shipSize;
                board[y][x].setShip(true);
                board[y][x].setBattleship(ship);
                if (vertical) { y++; } else { x++; }
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    public boolean fireTheBattleship(int x, int y, Player player) {
        Battleship ship = this.board[x][y].getBattleship();
        if(validateHit(x, y)) {
            ship.setHealth(ship.getHealth() - 1);
            System.out.println("You have hit the battleship!");

            if(ship.getHealth() == 0) {
                ship.setSunk(true);
                this.shipList.remove(ship);
                player.setScore(player.getScore() + 1);
                System.out.println("You have sunk the battleship");
            }
            return true;
        }
        return false;
    }

    public boolean validateHit(int x, int y) {
        if(this.board[x][y].getBattleship() == null) {
            this.board[x][y].setHit(true);
            System.out.println("\nYou missed the hit!!");
            return false;
        }
        if(!this.board[x][y].getHit() && !this.board[x][y].getBattleship().isSunk()) {
            this.board[x][y].setHit(true);
            return true;
        }
        if(this.board[x][y].getHit()) {
            System.out.println("\nThe place has already been fired...");
            return false;
        }
        return false;
    }

    // For normal case
    public void addNormalShips() {
        addShips(Type.MediumBattleShip);
        addShips(Type.MediumBattleShip);
        addShips(Type.MediumBattleShip);
        addShips(Type.MediumBattleShip);
        addShips(Type.MediumBattleShip);
    }

    // For extended version
    public void addExtendedShips() {
        addShips(Type.SmallBattleShip);
        addShips(Type.SmallBattleShip);
        addShips(Type.SmallBattleShip);
        addShips(Type.MediumBattleShip);
        addShips(Type.MediumBattleShip);
        addShips(Type.LargeBattleShip);
    }

    public enum Type {
        SmallBattleShip, MediumBattleShip, LargeBattleShip
    }
}
