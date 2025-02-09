import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarketCard 
{
    private String name;
    private String description;
    private double effect; // Use double to handle percentage changes

    public MarketCard(String name, String description, double effect) 
    {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public void action(Player player) 
    {
        System.out.println(description);
        System.out.println("Do you want to proceed with the market action? [y]es / [Any key]no");
        Scanner scanner = new Scanner(System.in);
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("y")) 
        {
            int ctr = 1;
            List<Property> properties = player.getFinancialStatement().getProperties();
            List<Stocks> stocks = player.getFinancialStatement().getStocks();

            if(properties.size() == 0 && stocks.size() == 0)
            {
                System.out.println("No properties nor stocks.");
                System.out.println("No investment made.");
            }
            else if(name.equalsIgnoreCase("AddP"))
            {
                System.out.println("Which property do you want to sell?");
                for (Property property : properties) 
                {
                    System.out.printf("["+ ctr +"] " +property.getName());
                    ctr++;
                }
                int index = scanner.nextInt();
                double newValue = (properties.get(index-1).getValue() * effect);
                properties.get(index-1).setValue(newValue);
                player.getFinancialStatement().remProperty(index-1);
                System.out.println("Market action taken. Updated financial statement:");
                player.getFinancialStatement().displayStatement();
            }
            else if(name.equalsIgnoreCase("LessP"))
            {
                System.out.println("Which property do you want to sell?");
                for (Property property : properties) 
                {
                    System.out.printf("["+ ctr +"] " +property.getName());
                    ctr++;
                }
                int index = scanner.nextInt();
                double newValue = (properties.get(index-1).getValue()) - (properties.get(index-1).getValue() * effect);
                properties.get(index-1).setValue(newValue);
                player.getFinancialStatement().remProperty(index-1);
                System.out.println("Market action taken. Updated financial statement:");
                player.getFinancialStatement().displayStatement();
            }
            else if(name.equalsIgnoreCase("AddS"))
            {
                System.out.println("Do you want to sell your stocks? [y]es / [Any key]no");
                String choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("y"))
                {
                    System.out.println("Which stocks do you want to sell?");
                    for (Stocks stock : stocks) 
                    {
                        System.out.printf("["+ ctr +"] " +stock.getName());
                        ctr++;
                    }
                    int index = scanner.nextInt();
                    double newValue = (stocks.get(index-1).getValue() * effect);
                    stocks.get(index-1).setValue(newValue);
                    player.getFinancialStatement().remStocks(index-1);
                    System.out.println("Market action taken. Updated financial statement:");
                    player.getFinancialStatement().displayStatement();
                }
                else
                {
                    System.out.println("Choose which stocks the card effects.");
                    for (Stocks stock : stocks) 
                    {
                        System.out.printf("["+ ctr +"] " +stock.getName());
                        ctr++;
                    }
                    int index = scanner.nextInt();
                    double newValue = (stocks.get(index-1).getValue() * effect);
                    stocks.get(index-1).setValue(newValue);
                    player.getFinancialStatement().remStocks(index-1);
                    System.out.println("Market action taken. Updated financial statement:");
                    player.getFinancialStatement().displayStatement();
                }
                
            }
            else if(name.equalsIgnoreCase("LessS"))
            {
                System.out.println("Which stocks do you want to sell?");
                for (Stocks stock : stocks) 
                {
                    System.out.printf("["+ ctr +"] " +stock.getName());
                    ctr++;
                }
                int index = scanner.nextInt();
                double newValue = (stocks.get(index-1).getValue()) - (stocks.get(index-1).getValue() * effect);
                stocks.get(index-1).setValue(newValue);
                player.getFinancialStatement().remStocks(index-1);
                System.out.println("Market action taken. Updated financial statement:");
                player.getFinancialStatement().displayStatement();
            }
        } 
        else 
        {
            System.out.println("No market action taken.");
        }
    }
}
