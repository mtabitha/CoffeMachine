import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.start();

    }

    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;
    private Scanner scan = new Scanner(System.in);

    enum CoffeeType {
        ESPRESSO(250, 0, 16,  4),
        LATTE(350, 75, 20,  7),
        CAPPUCCINO(200, 100, 12,  6);

        public int water;
        public int milk;
        public int coffee;
        public int money;

        CoffeeType(int water, int milk, int coffee, int money) {
            this.water = water;
            this.milk = milk;
            this.coffee = coffee;
            this.money = money;
        }
    }

    public void start() {
        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");
            String action = scan.nextLine();
            if (action.equals("exit"))
                break;
            switch (action) {
                case ("buy"):
                    buyAction();
                    break;
                case ("fill"):
                    fillAction();
                    break;
                case ("take"):
                    takeAction();
                    break;
                case ("remaining"):
                    remainingAction();
                    break;
            }
        }
    }

    private void takeAction() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void fillAction() {
        System.out.print("\nWrite how many ml of water you want to add:\n> ");
        water += validStr();
        System.out.print("Write how many ml of milk you want to add:\n> ");
        milk += validStr();
        System.out.print("Write how many grams of coffee beans you want to add:\n> ");
        coffee += validStr();
        System.out.print("Write how many disposable cups of coffee you want to add:\n> ");
        cups += validStr();
    }

    private int validStr() {
        String str = scan.nextLine();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9')
                return 0;
        }
        return Integer.parseInt(str);
    }


    private void buyAction() {
        System.out.print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n> ");
        int choice = validStr();
        CoffeeType type = null;
        switch (choice) {
            case (1):
                type = CoffeeType.ESPRESSO;
                break;
            case (2):
                type = CoffeeType.LATTE;
                break;
            case (3):
                type = CoffeeType.CAPPUCCINO;
                break;
        }
        if(type != null && enoughResources(type)) {
            System.out.println("I have enough resources, making you a coffee!\n");
            createCoffee(type);
        }
    }

    private boolean enoughResources(CoffeeType type) {
        String resource = "";
        if (water < type.water)
            resource = "water";
        else if (milk < type.milk)
            resource = "milk";
        else if (coffee < type.coffee)
            resource = "coffee";
        else if (cups == 0)
            resource = "cups";
        if (!resource.isEmpty()) {
            System.out.println("Sorry, not enough " + resource + "!\n");
            return false;
        }
        return true;
    }

    private void createCoffee(CoffeeType type) {
        water -= type.water;
        milk -= type.milk;
        coffee -= type.coffee;
        money += type.money;
        cups--;
    }

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        coffee = 120;
        cups = 9;
        money = 550;
    }

    private void remainingAction() {
        System.out.println("\nThe coffee machine has:\n"
                + water + " ml of water\n"
                + milk + " ml of milk\n"
                + coffee + " g of coffee beans\n"
                + cups + " disposable cups\n"
                + "$" + money + " of money\n");
    }

}