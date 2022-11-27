import java.util.Map;
import java.util.Scanner;

/*
    고객 클래스
 */
public class Customer implements User{
    private CreditCard card;
    private CouponController coupon;         // 쿠폰 갯수
    private int point;             // 포인트
    private double money;          // 보유 잔액
    private int discount = 0;

    public Customer(CreditCard card, CouponController coupon, int point, double money) {
        this.card = card;
        this.coupon = coupon;
        this.point = point;
        this.money = money;
    }

    @Override
    public boolean hasCoupon() {
        return coupon.checkCoupon();
    }

    @Override
    public void displayCoupon() {
        coupon.showCoupon();
    }

    @Override
    public boolean useCoupon() {
        System.out.println("\n어떤 쿠폰을 사용하시겠습니까?");
        System.out.println("1. 좋은 쿠폰    2. 안좋은 쿠폰");
        int num = new Scanner(System.in).nextInt();
        Coupon success = coupon.use(num);
        if(success == null) {
            return useCoupon();
        }
        System.out.println("쿠폰을 성공적으로 적용 하였습니다.");
        this.discount = success.getDiscount();
        return true;
    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double amount) {
        this.money -= amount;
    }

    @Override
    public CreditCard getCard() {
        return card;
    }

    @Override
    public int getDiscount() {
        return this.discount;
    }

    @Override
    public void displayInformation() {
        System.out.println("============== 현재 고객 정보 ==============");
        System.out.println("현금 잔액: " + getMoney() + "원");
        System.out.println("카드 잔액: " + getCard().getCreditMoney() + "원");
        System.out.println("남은 포인트: " + getPoint() + " points");
    }
}
