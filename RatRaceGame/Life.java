public class Life extends Spaces 
{
    private LifeCard card;

    public Life(LifeCard card) 
    {
        this.card = card;
    }

    @Override
    public void action(Player player) 
    {
        card.action(player);
    }
}