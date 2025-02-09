public class Opportunity extends Spaces 
{
    private OpportunityCard card;

    public Opportunity(OpportunityCard card) 
    {
        this.card = card;
    }

    @Override
    public void action(Player player) 
    {
        card.action(player);
    }
}
