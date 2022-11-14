public class Square {
    private int px = 0;
    private int py = 0;
    private boolean isShip = false;
    private Battleship battleship = null;
    private boolean isHit = false;

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setShip(boolean ship) {
        isShip = ship;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    public boolean getHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String toString() {
        if(this.isShip && isHit) {
            return "X";
        } else if(!this.isShip && isHit) {
            return "O";
        } else{
            return "-";
        }
    }
}
