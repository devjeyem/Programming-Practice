import java.util.Scanner;
import java.security.SecureRandom;

public class OpportunityCard 
{
    private String description;
    private String name;
    private double value;
    private double monthlyIncome;

    public OpportunityCard(String description, String name, double value, double monthlyIncome) 
    {
        this.description = description;
        this.name = name;
        this.value = value;
        this.monthlyIncome = monthlyIncome;
    }

    public String getName() 
    {
        return name;
    }

    public double getValue() 
    {
        return value;
    }

    public double getMonthlyIncome() 
    {
        return monthlyIncome;
    }

    private int getRandomNumber(int min, int max) 
    {
        SecureRandom sr = new SecureRandom();
        return sr.nextInt(max - min + 1) + min;
    }

    public void action(Player player) 
    {
        System.out.println(description);
        System.out.printf("Do you want to invest %.2f to earn %.2f/month in passive income? [y]es / [Any key]no\n", getValue(), getMonthlyIncome());
        Scanner scanner = new Scanner(System.in);
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("y") && getName().contains("Property")) 
        {
            if(getName().contains("Property"))
            {
                if(getValue() > player.getFinancialStatement().getAssets())
                {
                    System.out.println("Insufficient funds.");
                    System.out.println("No investment made.");
                }
                else 
                {
                    player.getFinancialStatement().decAssets(getValue());
                    Property property = new Property(getName(), getValue(), getMonthlyIncome());
                    player.getFinancialStatement().addProperty(property);
                    System.out.println("Investment made. Updated financial statement:");
                    player.getFinancialStatement().displayStatement();
                }  
            }
            else
            {
                if(getValue() > player.getFinancialStatement().getAssets())
                {
                    System.out.println("Insufficient funds.");
                    System.out.println("No investment made.");
                }
                else 
                {
                    player.getFinancialStatement().decAssets(getValue());
                    Stocks stock = new Stocks(getRandomNumber(0,50), getName(), getValue(), getMonthlyIncome());
                    player.getFinancialStatement().addStocks(stock);
                    System.out.println("Investment made. Updated financial statement:");
                    player.getFinancialStatement().displayStatement();
                } 
            }
            
        }
        else 
        {
            System.out.println("No investment made.");
        }
    }
}
