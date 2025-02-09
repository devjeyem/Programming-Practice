import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.SecureRandom;

public class Game 
{
    private Player[] players;
    private Scanner scanner;
    private List<Spaces> board;

    public Game() 
    {
        scanner = new Scanner(System.in);
        board = new ArrayList<>();
        setupBoard();
    }

    public void start() 
    {
        System.out.printf("---------------------------\n");
        System.out.println("+Rat Race Game+");
        System.out.println("1. Start game");
        System.out.println("2. Exit");
        System.out.printf("---------------------------\n");
        int choice = scanner.nextInt();
        if (choice == 1) 
        {
            setupPlayers();
            playGame();
        } 
        else 
        {
            System.out.println("Exiting game. Thank you for playing ^_^");
        }
    }

    private void setupPlayers() 
    {
        System.out.println("How many players will be playing?");
        int numPlayers = scanner.nextInt();
        players = new Player[numPlayers];
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numPlayers; i++) 
        {
            System.out.println("Enter name for Player " + (i + 1) + ":");
            String name = scanner.nextLine();
            players[i] = new Player(name, i + 1);
        }

        for (Player player : players) 
        {
            player.displayProfile();
        }
    }

    private void removePlayer(int playerNumber) 
    {
        if (players.length <= 1) 
        {
            System.out.println("Cannot remove player. Only one player remaining.");
            return;
        }

        Player[] newPlayers = new Player[players.length - 1];
        int index = 0;

        for (int i = 0; i < players.length; i++) 
        {
            if (players[i].getPlayerNumber() != playerNumber) 
            {
                newPlayers[index++] = players[i];
            }
        }

        players = newPlayers;

        // Update player numbers
        for (int i = 0; i < players.length; i++) 
        {
            players[i].setPlayerNumber(i + 1);
        }

        System.out.println("Player " + playerNumber + " lost and has been removed.");
    }


    private void setupBoard() 
    {
        // Add spaces to the board
        board.add(new Payday());
        board.add(new Blank());
        board.add(new Opportunity(new OpportunityCard("Rental Property Investment: Invest 80,000 to earn 1,000/month in passive income.", "2-Bedroom Rental Property", 8000, 800)));
        board.add(new Blank());
        board.add(new Doodad(new DoodadCard("Vacation: Pay 3,000 for a vacation.", 3000.0)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("AddP","Booming Real Estate Market: Sell any property you own at double its value.", 2.0)));
        board.add(new Blank());
        board.add(new Life(new LifeCard("New Baby: Increase your monthly expenses by 500.", 500.0)));
        board.add(new Blank());
        board.add(new Opportunity(new OpportunityCard("A leading technology company specializing in software development, cloud services, and AI. It's a blue-chip stock with strong growth potential and steady earnings.", "Tech Giant Inc. Stocks", 250, 600)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("AddS","The market has been kind to stable, long-standing companies. A popular blue-chip stock sees a significant rise. Any one of your stocks increases 150% of its current value.", 1.5)));


        board.add(new Blank());
        board.add(new Payday());
        board.add(new Opportunity(new OpportunityCard("P Duplex Rental: A small duplex is available for purchase. It's a great rental property that could bring in consistent cash flow.", "Duplex Rental", 5000, 500)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("LessP","Real Estate Market Correction: Sell any one of your properties at 20% less than its current value.", 0.2)));
        board.add(new Blank());
        board.add(new Doodad(new DoodadCard("New Electronics: You've decided to splurge on the latest tech gadgets(a new laptop, smartphone, and smartwatch).", 2000.0)));
        board.add(new Blank());
        board.add(new Life(new LifeCard("Education: You've decided to go back to school to further your career. Tuition, books, and supplies will add to your monthly expenses.", 600.0)));
        board.add(new Blank());
        board.add(new Opportunity(new OpportunityCard("A rapidly growing company focused on renewable energy, including solar and wind power. This stock is volatile but promises high growth as the world transitions to clean energy.", "Green Energy Solutions Stocks", 150, 500)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("LessS","The tech sector faces a major downturn as several companies report disappointing earnings. Sell any stocks you own at 50% of their current value.", 0.5)));

        board.add(new Blank());
        board.add(new Payday());
        board.add(new Opportunity(new OpportunityCard("Single-Family Home: A single-family home in a growing neighborhood is up for sale. It's a solid investment with potential for appreciation and steady rental income.", "Single-Family Home", 12000, 1200)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("AddP","Real Estate Market Correction: Sell any one of your properties at 20% less than its current value.", 0.2)));
        board.add(new Blank());
        board.add(new Doodad(new DoodadCard("New Car Purchase: You decide to buy a new car to replace your old one, complete with all the latest features.", 2500.0)));
        board.add(new Blank());
        board.add(new Life(new LifeCard("Relocation: you've decided to move to a new city for work or personal reasons. The cost of moving and adjusting to a new living situation is significant.", 800.0)));
        board.add(new Blank());
        board.add(new Opportunity(new OpportunityCard("A fast-growing company in the electric vehicle market, specializing in electric cars and autonomous driving technology. The stock can be volatile, but its future is promising.", "Electric Vehicles Corp. Stocks", 220, 650)));
        board.add(new Blank());
        board.add(new Market(new MarketCard("AddS","A company you've invested in announces a stock split, increasing the number of shares you own. Double the number of shares you own in the chosen stock. The value of each share remains the same, but your total assets in that stock increase.", 2.0)));

        board.add(new Blank());
        // Add more spaces as needed
    }

    private void playGame() 
    {
        boolean gameRunning = true;
        while (gameRunning) 
        {
            for (Player player : players) 
            {
                if(player.getFinancialStatement().getLiabilities() == 0.0)
                {
                    System.out.printf("\n");
                    System.out.println(player.getName() + ", press Enter to roll the dice.");
                    scanner.nextLine();
                    int roll = rollDice();
                    System.out.println("Roll the Dice 2 times: Rolled a " + roll + ".");
                    movePlayer(player, roll);

                    //player.displayProfile();

                    if (checkWinCondition(player)) 
                    {
                        System.out.println(player.getName() + " has escaped the Rat Race! Congratulations!");
                        gameRunning = false;
                        break;
                    }

                    if (checkLoseCondition(player)) 
                    {
                        removePlayer(player.getPlayerNumber());
                    }
                }
                else 
                {
                    System.out.printf("\n");
                    System.out.println("Do you just want to roll the dice ? \nOr \nRoll the the dice and Pay your loan ? \n[r]oll / [any key]pay");
                    String dec = scanner.nextLine();
                    if(dec.equalsIgnoreCase("r"))
                    {
                        System.out.println(player.getName() + ", press Enter to roll the dice.");
                        scanner.nextLine();
                        int roll = rollDice();
                        System.out.println("Roll the Dice 2 times: The sum of the 2 dice is " + roll + ".");
                        movePlayer(player, roll);

                        if (checkWinCondition(player)) 
                        {
                            System.out.println(player.getName() + " has escaped the Rat Race! Congratulations!");
                            gameRunning = false;
                            break;
                        }

                        if (checkLoseCondition(player)) 
                        {
                            removePlayer(player.getPlayerNumber());
                        }
                    }
                    else
                    {
                        System.out.println("How much will you pay?");
                        double pay = scanner.nextDouble(); 
                        if(player.getFinancialStatement().getAssets() == 0 || player.getFinancialStatement().getAssets() < pay)
                        {
                            System.out.println("Insufficient funds.");
                            System.out.println("No payment made.");
                        }
                        else
                        {
                            player.getFinancialStatement().decLiabilities(pay);
                            player.getFinancialStatement().decAssets(pay);
                            System.out.println("Payment made.Updated financial statement:");
                            player.getFinancialStatement().displayStatement();
                        }
                        System.out.println(player.getName() + ", press Enter to roll the dice.");
                        scanner.nextLine();
                        int roll = rollDice();
                        System.out.println("Roll the Dice 2 times: The sum of the 2 dice is " + roll + ".");
                        movePlayer(player, roll);

                        if (checkWinCondition(player)) 
                        {
                            System.out.println(player.getName() + " has escaped the Rat Race! Congratulations!");
                            gameRunning = false;
                            break;
                        }

                        if (checkLoseCondition(player)) 
                        {
                            removePlayer(player.getPlayerNumber());
                        }
                    }
                }
            }
        }
    }

    private int rollDice() 
    {
        SecureRandom sr = new SecureRandom();
        return sr.nextInt(6) + 1 + sr.nextInt(6) + 1; // Roll two dice
    }

    private void movePlayer(Player player, int roll) 
    {
        int position = roll % board.size();
        //System.out.println("Move Token: Move your token " + roll + " spaces forward and land on a " + board.get(position).getClass().getSimpleName() + " space.");
        System.out.println("Landed on " + board.get(position).getClass().getSimpleName() + " space.");
        System.out.printf("Draw card: ");
        board.get(position).action(player);
    }

    private boolean checkWinCondition(Player player) 
    {
        return player.getFinancialStatement().getPassiveIncome() > (player.getFinancialStatement().getExpenses() + player.getFinancialStatement().getLiabilities());
    }

    private boolean checkLoseCondition(Player player) 
    {
        return (player.getFinancialStatement().getExpenses() + player.getFinancialStatement().getLiabilities()) > player.getFinancialStatement().getAssets();
    }
}
