import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CouponController coupon = new CouponController();
        coupon.addCoupon("Good");
        coupon.addCoupon("Bad");
        CreditCard creditCard = new CreditCard(2000, "1234");
        User customer = new Customer(creditCard, coupon, 500, 1000);
        customer.displayInformation();
        System.out.println();
        // 결제 할 아이템 선택
        Scanner sc = new Scanner(System.in);
        System.out.println("무엇을 구매하시겠습니까?");
        System.out.println("1. 빵    2. 치즈    3. 도넛");
        int item_no = sc.nextInt();
        Item item = sortItem(item_no);
        System.out.println("가격은 " + item.getPrice() + "원 입니다.\n");

        // 결제 방식 선택
        System.out.println("결제 방식을 선택해 주세요.");
        System.out.println("1. 현금 결제    2. 카드 결제");
        int userInput = sc.nextInt();
        Payment payment = sortPayment(userInput);

        //쿠폰 있는지 확인, 1 = 사용, 2 = 스킵
        if(customer.hasCoupon()) {
            System.out.println("쿠폰이 있습니다. 사용하시겠습니까?");
            customer.displayCoupon();
            System.out.println("\n 1. 예    2. 아니오");
            if(sc.nextInt() == 1) {
                customer.useCoupon();
            }
        }

        //디자인 패턴 중 Strategy 패턴 적용
        PaymentStrategy strategy = new PaymentStrategy(payment, customer);
        strategy.payAmount(item.getPrice());
    }

    //아이템 선택 메소드
    private static Item sortItem(int item_no) {
        switch(item_no) {
            case 1:
                return new Bread();
            case 2:
                return new Cheese();
            case 3:
                return new Doughnut();
            default:
                System.out.println("1번 ~ 3번 중 하나를 선택해 주세요.");
                return sortItem(new Scanner(System.in).nextInt());
        }
    }

    //결제 방식 선택 메소드
    private static Payment sortPayment(int userInput) {
        switch(userInput) {
            case 1:
                return new CashPayment();
            case 2:
                return new CardPayment();
            default:
                System.out.println("1 ~ 2번 중 하나를 선택해 주세요.");
                return sortPayment(new Scanner(System.in).nextInt());
        }
    }
}
