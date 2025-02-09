import java.util.ArrayList;
import java.util.List;

public class Employee 
{
    private String id;
    private String firstName;
    private String lastName;
    private double salary;
    private String username;
    private String password;
    private List<Double> salaryHistory;
    private List<TimeOffRequest> timeOffRequests;

    public Employee(String id, String firstName, String lastName, double salary, String username, String password) 
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.salaryHistory = new ArrayList<>();
        this.timeOffRequests = new ArrayList<>();
        salaryHistory.add(salary);
    }

    public String getID() 
    {
        return id;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public double getSalary() 
    {
        return salary;
    }

    public double getAnnualSalary() 
    {
        return salary * 12;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public List<Double> getSalaryHistory() 
    {
        return salaryHistory;
    }

    public List<TimeOffRequest> getTimeOffRequests() 
    {
        return timeOffRequests;
    }

    public void setPassword(String newPassword) 
    {
        this.password = newPassword;
    }

    public void updateSalary(double newSalary) 
    {
        this.salary = newSalary;
        salaryHistory.add(newSalary);
    }

    public void increaseSalaryByPercentage(double percentage) 
    {
        double increase = salary * (percentage / 100);
        this.salary += increase;
        salaryHistory.add(this.salary);
    }

    public void decreaseSalaryByPercentage(double percentage) 
    {
        double decrease = salary * (percentage / 100);
        this.salary -= decrease;
        salaryHistory.add(this.salary);
    }

    public void requestTimeOff(String startDate, String endDate, String reason) 
    {
        TimeOffRequest request = new TimeOffRequest(startDate, endDate, reason);
        timeOffRequests.add(request);
        //TestEmployee2.saveTimeOffRequest(request, id);
        System.out.println("Time off requested from " + startDate + " to " + endDate + " for: " + reason);
    }
}