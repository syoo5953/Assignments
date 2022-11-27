public interface User {
    public boolean hasCoupon();
    public void displayCoupon();
    public boolean useCoupon();
    public int getPoint();
    public double getMoney();
    public void setMoney(double amount);
    public CreditCard getCard();
    public int getDiscount();
    public void displayInformation();
}
