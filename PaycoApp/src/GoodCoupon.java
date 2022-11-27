public class GoodCoupon implements Coupon{
    private int discount;

    public GoodCoupon() {
        this.discount = 10;
    }

    @Override
    public int getDiscount() {
        return this.discount;
    }
}
