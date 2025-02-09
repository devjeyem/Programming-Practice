public class Payday extends Spaces
{
    @Override
    public void action(Player player) 
    {
        System.out.printf("You landed on Payday! \nAdded your Salary of %.2f to money! \nAdded your passive income of %.2f to money! \nDeducted from money your Monthly expenses of %.2f. \n", player.getSalary(), player.getFinancialStatement().getPassiveIncome(), player.getFinancialStatement().getExpenses());
        player.getFinancialStatement().incAssets(player.getSalary());
        player.getFinancialStatement().incAssets(player.getFinancialStatement().getPassiveIncome());
        player.getFinancialStatement().decAssets(player.getFinancialStatement().getExpenses());
        player.getFinancialStatement().displayStatement();
    }
}