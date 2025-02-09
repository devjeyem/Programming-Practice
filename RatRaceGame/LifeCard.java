public class LifeCard 
{
    private String description;
    private double  monthlyExpense;

    public LifeCard(String description, double monthlyExpense) 
    {
        this.description = description;
        this.monthlyExpense = monthlyExpense;
    }

    public void action(Player player) 
    {
        System.out.println(description);
        System.out.println(monthlyExpense);
        player.getFinancialStatement().incExpenses(monthlyExpense);
        System.out.println("Life event taken. Updated financial statement:");
        player.getFinancialStatement().displayStatement();
        
    }
}
