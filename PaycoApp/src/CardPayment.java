import java.util.Scanner;

/*
    카드 결제 클래스
 */
public class CardPayment implements Payment{
    @Override
    public void pay(User customer, double amount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n비밀번호를 입력해 주세요.");
        String password = sc.next();
        if(password.equals(customer.getCard().getPassword())) {
            if(customer.getCard().getCreditMoney() > amount) {
                System.out.println("\n카드 결제에 성공하셨습니다.");
                customer.getCard().setCreditMoney(customer.getCard().getCreditMoney() - amount);
                System.out.println("남은 카드 잔액: " + customer.getCard().getCreditMoney());
                System.out.println("남은 포인트 : " + customer.getPoint());
            } else {
                System.out.println("잔액이 부족합니다. 결제를 종료합니다.");
            }
        } else {
            System.out.println("비밀번호가 틀렸습니다. 결제를 종료합니다.");
        }
    }
}
