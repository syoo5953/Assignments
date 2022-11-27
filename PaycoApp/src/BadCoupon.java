public class BadCoupon implements Coupon{
    private int discount;

    public BadCoupon() {
        this.discount = 5;
    }

    @Override
    public int getDiscount() {
        return this.discount;
    }
}
