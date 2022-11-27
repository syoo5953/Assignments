import java.util.LinkedList;
import java.util.Queue;

/*
    쿠폰 관리 클래스
 */
public class CouponController {
    private Queue<Coupon> goodCouponList = new LinkedList<>();
    private Queue<Coupon> badCouponList = new LinkedList<>();

    public void addCoupon(String coupon) {
        if(coupon.equalsIgnoreCase("good")) {
            goodCouponList.add(new GoodCoupon());
        } else if(coupon.equalsIgnoreCase("bad")){
            badCouponList.add(new BadCoupon());
        }
    }

    public Coupon use(int num) {
        if(num == 1) {
            if(goodCouponList.size() > 0) {
                return goodCouponList.poll();
            } else {
                System.out.println("좋은 쿠폰을 보유하고 계시지 않습니다.");
                return null;
            }
        } else if(num == 2) {
            if(badCouponList.size() > 0) {
                return badCouponList.poll();
            } else {
                System.out.println("안좋은 쿠폰을 보유하고 계시지 않습니다.");
                return null;
            }
        } else {
            System.out.println("1 ~ 2번 중에서 선택해 주세요.");
            return null;
        }
    }

    public void showCoupon() {
        System.out.println("현재 보유중인 쿠폰");
        System.out.println("좋은 쿠폰 : " + this.goodCouponList.size());
        System.out.println("안좋은 쿠폰 : " + this.badCouponList.size());
    }

    //쿠폰을 보유하고 있는지 체크
    public boolean checkCoupon() {
        if(this.goodCouponList.size() > 0 || this.badCouponList.size() > 0) {
            return true;
        }
        return false;
    }
}
