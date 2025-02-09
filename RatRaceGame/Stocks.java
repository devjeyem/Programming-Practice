import java.security.SecureRandom;

public class Stocks //extends Property
{
    private int shares;
    private String sname;
    private double svalue;
    private double smonthlyIncome;

    public Stocks(int shares, String name, double value, double monthlyIncome) 
    {
        /*super(name, value, monthlyIncome)*/
        this.shares = shares;
        this.sname = name;
        this.svalue = value * shares;
        this.smonthlyIncome = monthlyIncome * shares; 
    }

    public int getShares()
    {
        return shares;
    }

    //@Override
    public String getName() 
    {
        return sname;
    }

    //@Override
    public double getValue() 
    {
        return svalue;
    }

    //@Override
    public void setValue(double value)
    {
        this.svalue = value;
    }

    //@Override
    public double getMonthlyIncome() 
    {
        return smonthlyIncome;
    }
}
