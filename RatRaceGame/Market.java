public class Market extends Spaces 
{
    private MarketCard card;

    public Market(MarketCard card) 
    {
        this.card = card;
    }

    @Override
    public void action(Player player) 
    {
        card.action(player);
    }
}