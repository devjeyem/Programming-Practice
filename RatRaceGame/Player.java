public class Player 
{
    private String name;
    private double salary;
    private Job job;
    private int playerNumber;
    private FinancialStatement financialStatement;

    public Player(String name, int playerNumber) 
    {
        this.name = name;
        this.playerNumber = playerNumber;
        this.job = Job.getJob();
        this.salary = Job.getSalary(this.job);
        this.financialStatement = new FinancialStatement(salary);
    }

    public String getName() 
    {
        return name;
    }

    public Job getJob() 
    {
        return job;
    }

    public double getSalary()
    {
        return salary;
    }

    public FinancialStatement getFinancialStatement() 
    {
        return financialStatement;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber)
    {
        this.playerNumber = playerNumber;
    }

    public void displayProfile() 
    {
        System.out.printf("\n");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Player: " + playerNumber);
        System.out.printf("-------------------------------------------------------------------------\n");
        System.out.println("Character Profile");
        System.out.println("Name: " + name);
        System.out.println("Job: " + job);
        System.out.printf("Salary: %.2f\n", salary);
        System.out.println("-------------------------------------------------------------------------");
        financialStatement.displayStatement();
    }

}
