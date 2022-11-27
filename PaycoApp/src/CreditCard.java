public class CreditCard {
    private double creditMoney;
    private String password;

    public CreditCard(double creditMoney, String password) {
        this.creditMoney = creditMoney;
        this.password = password;
    }

    public double getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(double creditMoney) {
        this.creditMoney = creditMoney;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
