public class Doodad extends Spaces 
{
    private DoodadCard card;

    public Doodad(DoodadCard card) 
    {
        this.card = card;
    }

    @Override
    public void action(Player player) 
    {
        card.action(player);
    }
}