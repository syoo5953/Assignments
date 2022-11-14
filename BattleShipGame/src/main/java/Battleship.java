public class Battleship {
    private boolean isSunk = false;
    private int health = 0;
    private int size   = 0;
    private boolean isHit = false;

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void checkHit(boolean isHit, int health) {
        this.isHit = isHit;
        this.health -= health;
    }
}
