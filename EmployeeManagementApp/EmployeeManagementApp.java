import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class EmployeeManagementApp 
{
    private static List<Employee> employees = new ArrayList<>();
    private static List<Employer> employers = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        loadEmployeeRecords();
        loadEmployerRecords();
        loadTimeOffRequests();

        int loginType;
        do 
        {
            System.out.println("Enter login type: [1]employer [2]employee ");
            System.out.print(">> ");
            loginType = input.nextInt();
            input.nextLine(); 
        } while (loginType != 1 && loginType != 2);

        if (loginType == 1) 
        {
            Employer employer = loginEmployer(input);
            if (employer != null) 
            {
                handleEmployerLogin(employer, input);
            } 
            else 
            {
                System.out.println("Invalid employer login credentials.");
            }
        } 
        else 
        {
            Employee employee = loginEmployee(input);
            if (employee != null) 
            {
                handleEmployeeLogin(employee, input);
            } 
            else 
            {
                System.out.println("Invalid employee login credentials.");
            }
        }

        input.close();

        saveEmployeeRecords();
        saveEmployerRecords();
    }

    private static Employer loginEmployer(Scanner input) 
    {
        System.out.print("Enter employer username: ");
        String username = input.nextLine();
        System.out.print("Enter employer password: ");
        String password = input.nextLine();

        for (Employer employer : employers) 
        {
            if (employer.getUsername().equals(username) && employer.getPassword().equals(password)) 
            {
                return employer;
            }
        }

        return null;
    }

    private static Employee loginEmployee(Scanner input) 
    {
        System.out.print("Enter employee username: ");
        String username = input.nextLine();
        System.out.print("Enter employee password: ");
        String password = input.nextLine();

        for (Employee employee : employees) 
        {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) 
            {
                return employee;
            }
        }

        return null;
    }

    private static void handleEmployerLogin(Employer employer, Scanner input) 
    {
        int choice;
        do 
        {
            System.out.print("========================================");
            System.out.println("\nEmployer Menu:");
            System.out.println("1. View all employee details");
            System.out.println("2. Add new employee");
            System.out.println("3. Update employee salary");
            System.out.println("4. Increase employee salary by percentage");
            System.out.println("5. Decrease employee salary by percentage");
            System.out.println("6. Approve time off requests");
            System.out.println("7. Save changes");
            System.out.println("8. Logout");
            System.out.print(">> ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) 
            {
                case 1:
                    viewAllEmployees();
                    break;
                case 2:
                    addNewEmployee(input);
                    break;
                case 3:
                    updateEmployeeSalary(input);
                    break;
                case 4:
                    increaseEmployeeSalaryByPercentage(input);
                    break;
                case 5:
                    decreaseEmployeeSalaryByPercentage(input);
                    break;
                case 6:
                    approveTimeOffRequests(input);
                    break;
                case 7:
                    saveEmployeeRecords();
                    break;
                case 8:
                    System.out.println("Exiting employer mode.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);
    }

    private static void handleEmployeeLogin(Employee employee, Scanner input) 
    {
        int choice;
        do 
        {
            System.out.print("========================================");
            System.out.println("\nEmployee Menu:");
            System.out.println("1. View employee details");
            System.out.println("2. Change password");
            System.out.println("3. View salary history");
            System.out.println("4. Request time off");
            System.out.println("5. View time off requests");
            System.out.println("6. Save changes");
            System.out.println("7. Logout");
            System.out.print(">> ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) 
            {
                case 1:
                    viewEmployeeDetails(employee);
                    break;
                case 2:
                    changeEmployeePassword(employee, input);
                    break;
                case 3:
                    viewSalaryHistory(employee);
                    break;
                case 4:
                    requestTimeOff(employee, input);
                    break;
                case 5:
                    viewTimeOffRequests(employee);
                    break;
                case 6:
                    saveEmployeeRecords();
                    break;
                case 7:
                    System.out.println("Logged Out Successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    private static void changeEmployeePassword(Employee employee, Scanner input) 
    {
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();
        employee.setPassword(newPassword);
        System.out.println("Password changed successfully.");
    }

    private static void viewEmployeeDetails(Employee employee) 
    {
        System.out.println("--------------------------------------------------------");
        System.out.println("Employee Details:");
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.printf("ID: %s%n", employee.getID());
        System.out.printf("Salary: %.2f%n", employee.getSalary());
        System.out.printf("Annual Salary: %.2f%n", employee.getAnnualSalary());
        System.out.println("--------------------------------------------------------");
    }

    private static void viewSalaryHistory(Employee employee) 
    {
        System.out.println("Salary History:");
        for (int i = 0; i < employee.getSalaryHistory().size(); i++) 
        {
            System.out.println("Salary " + (i + 1) + ": " + employee.getSalaryHistory().get(i));
        }
    }

    private static void requestTimeOff(Employee employee, Scanner input) 
    {
        System.out.print("Enter start date: ");
        String startDate = input.nextLine();
        System.out.print("Enter end date: ");
        String endDate = input.nextLine();
        System.out.print("Enter reason: ");
        String reason = input.nextLine();
        employee.requestTimeOff(startDate, endDate, reason);
        System.out.println("Time off request submitted successfully.");
    }

    private static void viewAllEmployees() 
    {
        System.out.println("========================================");
        System.out.println("EMPLOYEE RECORD");
        if (employees.isEmpty()) 
        {
            System.out.println("No employees found.");
        } 
        else 
        {
            final int ID_WIDTH = 10;
            final int FIRST_NAME_WIDTH = 15;
            final int LAST_NAME_WIDTH = 15;
            final int SALARY_WIDTH = 10;
            final int ANNUAL_SALARY_WIDTH = 15;

            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("| %-" + ID_WIDTH + "s | %-" + FIRST_NAME_WIDTH + "s | %-" + LAST_NAME_WIDTH + "s | %" + SALARY_WIDTH + "s | %" + ANNUAL_SALARY_WIDTH + "s |\n",
                    "ID", "First Name", "Last Name", "Salary", "Annual Salary");
            System.out.println("---------------------------------------------------------------------------------");
            for (Employee employee : employees) 
            {
                System.out.printf("| %-" + ID_WIDTH + "s | %-" + FIRST_NAME_WIDTH + "s | %-" + LAST_NAME_WIDTH + "s | %" + SALARY_WIDTH + ".2f | %" + ANNUAL_SALARY_WIDTH + ".2f |\n",
                        employee.getID(), employee.getFirstName(), employee.getLastName(),
                        employee.getSalary(), employee.getAnnualSalary());
            }
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

    private static void addNewEmployee(Scanner input) 
    {
        System.out.print("Enter employee ID: ");
        String id = input.nextLine();
        System.out.print("Enter employee first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter employee last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = input.nextDouble();
        input.nextLine(); // Consume the newline character
        System.out.print("Enter employee username: ");
        String username = input.nextLine();
        System.out.print("Enter employee password: ");
        String password = input.nextLine();
        Employee employee = new Employee(id, firstName, lastName, salary, username, password);
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    private static void updateEmployeeSalary(Scanner input) 
    {
        System.out.print("Enter employee ID: ");
        String id = input.nextLine();
        System.out.print("Enter new salary: ");
        double newSalary = input.nextDouble();
        input.nextLine();

        for (Employee employee : employees) 
        {
            if (employee.getID().equals(id)) 
            {
                employee.updateSalary(newSalary);
                System.out.println("Salary updated successfully.");
                return;
            }
        }
        System.out.println("Employee ID not found.");
    }

    private static void increaseEmployeeSalaryByPercentage(Scanner input) 
    {
        System.out.print("Enter employee ID: ");
        String id = input.nextLine();
        System.out.print("Enter percentage increase: ");
        double percentage = input.nextDouble();
        input.nextLine(); // Consume the newline character

        for (Employee employee : employees) 
        {
            if (employee.getID().equals(id)) 
            {
                employee.increaseSalaryByPercentage(percentage);
                System.out.println("Salary increased successfully.");
                return;
            }
        }
        System.out.println("Employee ID not found.");
    }

    private static void decreaseEmployeeSalaryByPercentage(Scanner input) 
    {
        System.out.print("Enter employee ID: ");
        String id = input.nextLine();
        System.out.print("Enter percentage decrease: ");
        double percentage = input.nextDouble();
        input.nextLine(); // Consume the newline character

        for (Employee employee : employees) 
        {
            if (employee.getID().equals(id)) 
            {
                employee.decreaseSalaryByPercentage(percentage);
                System.out.println("Salary decreased successfully.");
                return;
            }
        }
        System.out.println("Employee ID not found.");
    }

    private static void viewTimeOffRequests(Employee employee) 
    {
        System.out.println("Time Off Requests:");
        for (TimeOffRequest request : employee.getTimeOffRequests()) 
        {
            System.out.println("From: " + request.getStartDate() +
                    " To: " + request.getEndDate() +
                    " Reason: " + request.getReason() +
                    " Approved: " + (request.isApproved() ? "Yes" : "No"));
        }
    }

    private static void approveTimeOffRequests(Scanner input) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("time_off_requests.txt"))) 
        {
            String line;
            List<String> updatedRequests = new ArrayList<>();
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 5) 
                {
                    String employeeId = parts[0];
                    String startDate = parts[1];
                    String endDate = parts[2];
                    String reason = parts[3];
                    boolean approved = Boolean.parseBoolean(parts[4]);

                    System.out.println("Time off request for Employee ID: " + employeeId);
                    System.out.println("From: " + startDate + " To: " + endDate + " Reason: " + reason + " Approved: " + (approved ? "Yes" : "No"));
                    System.out.print("Approve this request? (yes/no): ");
                    String response = input.nextLine();
                    if (response.equalsIgnoreCase("yes")) 
                    {
                        approved = true; // Update the approval flag
                        for (Employee employee : employees) 
                        {
                            if (employee.getID().equals(employeeId)) 
                            {
                                for (TimeOffRequest request : employee.getTimeOffRequests()) 
                                {
                                    // Find the matching request and approve it
                                    if (request.getStartDate().equals(startDate) && request.getEndDate().equals(endDate) && request.getReason().equals(reason)) 
                                    {
                                        request.approve(); // Set the request as approved
                                        break;
                                    }
                                }
                            }
                        }
                        System.out.println("Time off request approved.");
                    }
                    updatedRequests.add(employeeId + "," + startDate + "," + endDate + "," + reason + "," + approved);
                }
            }

            // Now write the updated content back to the file.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("time_off_requests.txt"))) 
            {
                for (String request : updatedRequests) 
                {
                    writer.write(request);
                    writer.newLine();
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading time off requests: " + e.getMessage());
        }
    }

    private static void loadEmployeeRecords() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("employee_records.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 6) 
                {
                    String id = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    double salary = Double.parseDouble(parts[3]);
                    String username = parts[4];
                    String password = parts[5];
                    Employee employee = new Employee(id, firstName, lastName, salary, username, password);
                    employees.add(employee);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading employee records: " + e.getMessage());
        }
    }

    private static void loadEmployerRecords() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("employer_records.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 2) 
                {
                    String username = parts[0];
                    String password = parts[1];
                    Employer employer = new Employer(username, password);
                    employers.add(employer);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading employer records: " + e.getMessage());
        }
    }

    private static void loadTimeOffRequests() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("time_off_requests.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 5) 
                {
                    String employeeId = parts[0];
                    String startDate = parts[1];
                    String endDate = parts[2];
                    String reason = parts[3];
                    boolean approved = Boolean.parseBoolean(parts[4]);

                    // Find the employee that matches the ID and add the TimeOffRequest
                    for (Employee employee : employees) 
                    {
                        if (employee.getID().equals(employeeId)) 
                        {
                            TimeOffRequest request = new TimeOffRequest(startDate, endDate, reason);
                            if (approved) 
                            {
                                request.approve();
                            }
                            employee.getTimeOffRequests().add(request);
                        }
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading time off requests: " + e.getMessage());
        }
    }

    private static void saveEmployeeRecords() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_records.txt"))) 
        {
            for (Employee employee : employees) 
            {
                writer.write(employee.getID() + "," + employee.getFirstName() + "," + employee.getLastName() + "," +
                        employee.getSalary() + "," + employee.getUsername() + "," + employee.getPassword());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving employee records: " + e.getMessage());
        }
    }

    private static void saveEmployerRecords() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employer_records.txt"))) 
        {
            for (Employer employer : employers) 
            {
                writer.write(employer.getUsername() + "," + employer.getPassword());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving employer records: " + e.getMessage());
        }
    }

    public static void saveTimeOffRequest(TimeOffRequest request, String employeeId) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("time_off_requests.txt", true))) 
        {
            writer.write(employeeId + "," + request.getStartDate() + "," + request.getEndDate() + "," + request.getReason() + "," + request.isApproved());
            writer.newLine();
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving time off request: " + e.getMessage());
        }
    }
}