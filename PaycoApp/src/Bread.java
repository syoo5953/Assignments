public class Bread implements Item{
    private double price;

    public Bread() {
        this.price = 1000;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
