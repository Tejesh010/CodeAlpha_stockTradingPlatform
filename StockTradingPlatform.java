import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class StockTradingPlatform {

    static ArrayList<Stock> market = new ArrayList<>();
    static ArrayList<Stock> portfolio = new ArrayList<>();
    static double balance = 10000; // starting money

    public static void main(String[] args) {

        // Available stocks
        try (Scanner sc = new Scanner(System.in)) {
            // Available stocks
            market.add(new Stock("Apple", 150));
            market.add(new Stock("Tesla", 200));
            market.add(new Stock("Amazon", 180));
            
            int choice;
            
            do {
                System.out.println("\n===== Stock Trading Platform =====");
                System.out.println("Balance: $" + balance);
                System.out.println("1. View Market Stocks");
                System.out.println("2. Buy Stock");
                System.out.println("3. Sell Stock");
                System.out.println("4. View Portfolio");
                System.out.println("5. Exit");
                
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                
                switch (choice) {
                    
                    case 1 -> {
                        System.out.println("\nAvailable Stocks:");
                        for (int i = 0; i < market.size(); i++) {
                            System.out.println((i + 1) + ". " + market.get(i).name + " - $" + market.get(i).price);
                        }
                    }
                        
                    case 2 -> {
                        System.out.print("Enter stock number to buy: ");
                        int buy = sc.nextInt() - 1;
                        
                        if (buy >= 0 && buy < market.size()) {
                            Stock s = market.get(buy);
                            
                            if (balance >= s.price) {
                                portfolio.add(s);
                                balance -= s.price;
                                System.out.println("Bought " + s.name);
                            } else {
                                System.out.println("Not enough balance.");
                            }
                        }
                    }
                        
                    case 3 -> {
                        if (portfolio.isEmpty()) {
                            System.out.println("No stocks to sell.");
                            break;
                        }
                        
                        System.out.println("Your Portfolio:");
                        for (int i = 0; i < portfolio.size(); i++) {
                            System.out.println((i + 1) + ". " + portfolio.get(i).name);
                        }
                        
                        System.out.print("Enter stock number to sell: ");
                        int sell = sc.nextInt() - 1;
                        
                        if (sell >= 0 && sell < portfolio.size()) {
                            Stock s = portfolio.remove(sell);
                            balance += s.price;
                            System.out.println("Sold " + s.name);
                        }
                    }
                        
                    case 4 -> {
                        System.out.println("\nYour Portfolio:");
                        if (portfolio.isEmpty()) {
                            System.out.println("No stocks owned.");
                        } else {
                            for (Stock s : portfolio) {
                                System.out.println(s.name + " - $" + s.price);
                            }
                        }
                    }
                        
                    case 5 -> System.out.println("Exiting program.");
                        
                    default -> System.out.println("Invalid choice.");
                }
                
            } while (choice != 5);
        }
    }
}