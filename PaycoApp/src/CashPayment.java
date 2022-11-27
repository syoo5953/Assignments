/*
    현금 결제 클래스
 */
public class CashPayment implements Payment {
    @Override
    public void pay(User customer, double amount) {
        if(customer.getMoney() >= amount) {
            System.out.println("\n현금 결제에 성공하셨습니다.");
            customer.setMoney(customer.getMoney() - amount);
            System.out.println("남은 현금 잔액: " + customer.getMoney());
            System.out.println("남은 포인트 : " + customer.getPoint());
        } else {
            System.out.println("잔액이 부족합니다. 결제를 종료합니다.");
        }
    }
}
