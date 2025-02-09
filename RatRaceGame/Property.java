public class Property 
{
    private String name;
    private double value;
    private double monthlyIncome;

    public Property(String name, double value, double monthlyIncome) 
    {
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

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getMonthlyIncome() 
    {
        return monthlyIncome;
    }
}
