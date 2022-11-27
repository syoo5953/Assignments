public class Cheese implements Item{
    private double price;

    public Cheese() {
        this.price = 1500;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
}
