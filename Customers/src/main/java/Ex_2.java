/*
 * 샵에는 기본적으로 캐셔에게 오더, 그리고 키친 직원에게 오더하는 형식. Coffeeshop이 아는 다른 shop도 이 interface를 활용할 수 있다.
 * */
interface Shop {
    void orderToCasher(Buyer b, String name);
    void orderToKitchen(Buyer b, String product);
}


//가격은 main method가 아닌, 별도 객체에 선언되어야 한다.
class CoffeeShop implements Shop{
    private int AMARICANO = 1, AMARICANO_COST = 4000;
    private int LATTE = 2, LATTE_COST = 4500;
    private int GREEN_TEA = 3, GREENTEA_COST = 4500;
    private int YUJU_TEA = 4, YUJUTEA_COST = 5000;
    private int salesVolume;

    //Coffee 샵에서 일하는 직원
    Barista barista;
    Casher casher;

    public CoffeeShop(Barista barista, Casher casher) {
        this.barista = barista;
        this.casher = casher;
    }

    @Override
    public void orderToCasher(Buyer b, String name) {
        String product = casher.buy(b.get_Money(), name);
        this.salesVolume += getPrice(name);
        orderToKitchen(b, product);
    }

    @Override
    public void orderToKitchen(Buyer b, String product) {
        //주문이 성공하면
        if(barista.Make_Tea(product))
        {
            System.out.println("커피제작완료");
            String str = b.get_Product(getPrice(product), "아메리카노");
            printOutResult(str);
            printOutResult(getSalesVolume());
        } else {    //주문이 실패하면
            String str = "주문 실패";
            printOutResult(str);
        }
    }

    //각 음료의 가격
    private int getPrice(String product) {
        int price = 0;
        switch(product) {
            case "아메리카노" :
                price = AMARICANO_COST;
                break;
            case "라떼" :
                price = LATTE_COST;
                break;
            case "녹차" :
                price = GREENTEA_COST;
            case "유자차" :
                price = YUJUTEA_COST;
                break;
        }
        return price;
    }
    public String getSalesVolume() {
        return "\n매출액 : " + this.salesVolume;
    }

    public void printOutResult(String str) {
        System.out.println(str);
    }
}

class Buyer{
    private int money;
    private String inventory;

    public Buyer(int money) {
        this.money = money;
    }
    public String get_Product(int money, String product)
    {
        this.money -= money;
        this.inventory = product;
        return "소지금 : " + money + "\t보유 물품 : " + inventory;
    }

    public int get_Money() {
        return money;
    }
}

class Barista {
    CoffeeShop coffeeShop;
    private int coffee;
    private int milk;
    private int greentea;
    private int yujutea;

    Barista(int coffee, int milk, int greentea, int yujutea)
    {
        super();
        this.coffee = coffee;
        this.milk = milk;
        this.greentea = greentea;
        this.yujutea = yujutea;
    }

    boolean Make_Tea(String product)
    {
        if(product.equals("아메리카노") && coffee >= 1)
            return true;
        else if(product.equals("라떼") && coffee >= 1)
            return true;
        else if(product.equals("녹차") && greentea >= 1)
            return true;
        else if(product.equals("유자차") && yujutea >= 1)
            return true;
        else
            return false;
    }
}

class Casher {

    String buy(int money, String name) {
        if(money >= 4000 && name.equals("아메리카노"))
        {
            return "아메리카노";
        }
        else if(money >= 4500) {
            if(name.equals("라떼"))
            {
                return "라떼";
            }
            else if(name.equals("녹차"))
            {
                return "녹차";
            }
            else
            {
                return "미등록상품";
            }
        }
        else if((money >= 5000)) {
            if(name.equals("유자차"))
            {
                return "유자차";
            }
            else
                return "미등록상품";
        }
        else
            return null;
    }
}

public class Ex_2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Buyer b = new Buyer(10000);
        CoffeeShop coffeeShop = new CoffeeShop(new Barista(10, 10, 10, 10), new Casher());

        String want_drink = "아메리카노";
        coffeeShop.orderToCasher(b, want_drink);
    }
}
