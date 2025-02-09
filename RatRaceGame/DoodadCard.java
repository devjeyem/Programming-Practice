public class DoodadCard 
{
    private String description;
    private double cost;

    public DoodadCard(String description, double cost) 
    {
        this.description = description;
        this.cost = cost;
    }

    public void action(Player player) 
    {
        System.out.println(description);
        System.out.println(cost);
        if(cost < player.getFinancialStatement().getAssets())
        {
            player.getFinancialStatement().decAssets(cost);
            System.out.println("Expense made. Updated financial statement:");
            player.getFinancialStatement().displayStatement();
        }
        else
        {
            if(player.getFinancialStatement().getAssets() != 0)
            {
                //double over = cost - player.getFinancialStatement().getAssets();
                player.getFinancialStatement().incLiabilities(cost - player.getFinancialStatement().getAssets());
                System.out.println("Expense made. Updated financial statement:");
                player.getFinancialStatement().displayStatement();
            }
            else
            {
                player.getFinancialStatement().incLiabilities(cost - player.getFinancialStatement().getAssets());
                System.out.println("Expense made. Updated financial statement:");
                player.getFinancialStatement().displayStatement();
            }
            

        }
        
        
    }
}
