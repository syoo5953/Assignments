import java.util.Scanner;

//Payment 객체와 유저 객체를 받아 행동을 정하는 Strategy 패턴
public class PaymentStrategy {

    private Payment payment;
    private User customer;

    public PaymentStrategy(Payment payment, User customer) {
        this.payment = payment;
        this.customer = customer;
    }

    public void payAmount(double amount) {
        if(customer.getDiscount() > 0) {
            amount -= (amount/customer.getDiscount());
        }
        System.out.println("결제할 금액은 : " + amount + "원 입니다.");

        if(customer.getPoint() > 0) {
            System.out.println("\n 포인트가 남아있습니다. 사용하시겠습니까?");
            System.out.println("1. 예    2. 아니오");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if(input == 1) {
                System.out.println("사용할 포인트를 입력해 주세요");
                int points;
                while(true) {
                    points = sc.nextInt();
                    if(points > customer.getPoint()) {
                        System.out.println("입력된 가격이 남아있는 포인트보다 큽니다. 다시 입력해주세요.");
                        continue;
                    } else {
                        amount -= points;
                        System.out.println("결제할 금액은 : " + amount + "원 입니다.");
                        break;
                    }
                }
            }
        }
        System.out.println();
        payment.pay(customer, amount);
    }
}