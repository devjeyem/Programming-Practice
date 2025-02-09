import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

public class FinancialStatement 
{
    private double expenses;
    private double assets;
    private double liabilities;
    private double passiveIncome;
    private List<Property> properties;
    private List<Stocks> stocks;

    public FinancialStatement(double salary) 
    {
        this.expenses = getRandomNumber(500, 2000);
        this.assets = salary;
        this.liabilities = 0.0;
        this.passiveIncome = 0.0;
        this.properties = new ArrayList<>();
        this.stocks = new ArrayList<>();
    }

    private double getRandomNumber(double min, double max) 
    {
        SecureRandom sr = new SecureRandom();
        return sr.nextDouble(max - min + 1.0) + min;
    }

    public void incExpenses(double amount) 
    {
        this.expenses += amount;
    }

    public void decExpenses(double amount) 
    {
        this.expenses -= amount;
    }

    public void incAssets(double amount) 
    {
        this.assets += amount;
    }

    public void decAssets(double amount) 
    {
        this.assets -= amount;
    }

    public void incLiabilities(double amount) 
    {
        this.liabilities += amount;
    }

    public void decLiabilities(double amount) 
    {
        this.liabilities -= amount;
    }

    public void incPassiveIncome(double amount) 
    {
        this.passiveIncome += amount;
    }

    public void decPassiveIncome(double amount) 
    {
        this.passiveIncome -= amount;
    }

    public void addProperty(Property property) 
    {
        properties.add(property);
        incAssets(properties.get(properties.size()).getValue());
        incPassiveIncome(properties.get(properties.size()).getMonthlyIncome());
    }

    public void remProperty(int index) 
    {
        incAssets(properties.get(properties.size()).getValue());
        decPassiveIncome(properties.get(properties.size()).getMonthlyIncome());
        properties.remove(index);
    }

    public void addStocks(Stocks stock) 
    {
        stocks.add(stock);
        incAssets(stocks.get(stocks.size()).getValue());
        incPassiveIncome(stocks.get(stocks.size()).getMonthlyIncome());
    }

    public void remStocks(int index) 
    {
        incAssets(stocks.get(index).getValue());
        decPassiveIncome(stocks.get(index).getMonthlyIncome());
        stocks.remove(index);
    }

    public List<Property> getProperties() 
    {
        return properties;
    }

    public List<Stocks> getStocks() 
    {
        return stocks;
    }

    public double getPassiveIncome() 
    {
        return passiveIncome;
    }

    public double getExpenses() 
    {
        return expenses;
    }

    public double getAssets() 
    {
        return assets;
    }

    public double getLiabilities() 
    {
        return liabilities;
    }

    public void displayStatement() 
    {
        System.out.printf("-------------------------------------------------------------------------\n");
        System.out.println("Financial Statement");
        System.out.printf("Monthly Expenses: %.2f\n", expenses);
        System.out.println("Assets: ");
        System.out.printf("\tMoney: %.2f\n", assets);
        System.out.println("\tProperties: ");
        for (Property property : properties) 
        {
            System.out.printf("\t\t%s: \n\t\tValue = %.2f, \n\t\tMonthly Income = %.2f", property.getName(), property.getValue(), property.getMonthlyIncome());
            //System.out.println("\t\t" + property.getName() + ": Value = " + property.getValue() + ", Monthly Income = " + property.getMonthlyIncome());
        }
        System.out.println("\tStocks: ");
        for (Stocks stock : stocks) 
        {
            System.out.printf("\t\t%s: \n\t\tQuantity = %d, \n\t\tValue per stock = %.2f, \n\t\tMonthly Income = %.2f", stock.getName(), stock.getShares(), stock.getValue(), stock.getMonthlyIncome());
        }
        System.out.printf("Liabilities: %.2f\n", liabilities);
        System.out.printf("Monthly Passive Income: %.2f\n", passiveIncome);
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("\n");
    }   
}
