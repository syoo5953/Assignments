public class Doughnut implements Item{
    private double price;

    public Doughnut() {
        this.price = 2000;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
}
