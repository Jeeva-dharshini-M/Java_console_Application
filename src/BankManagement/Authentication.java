package BankManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Authentication {
    DBconnection dbconnection = new DBconnection();
    CustomerID customer_id = new CustomerID();
    Scanner scanner = new Scanner(System.in);
    static int customerid = 10101001;
    final String temppassword = "Aspire@123";

    public int balance;

    UserFunctions userfunction;

    public void CreateAccount() {
        try {
            dbconnection.getConnect();
            System.out.print("Name :");
            String name = scanner.next();

            System.out.print("Age :");
            int age = scanner.nextInt();

            System.out.print("Mobile Number :");
            String number = scanner.next();

            System.out.print("Location :");
            String location = scanner.next();

            System.out.print("Date of Birth :");
            String DOB = scanner.next();

            System.out.print("Initial deposit amount:");
            int balance = scanner.nextInt();

            customerid = customer_id.generateCustomerID();
            Statement statement = DBconnection.connection.createStatement();
            String sql = "insert into userdetails values(" + customerid + ",'" + temppassword + "')";
            int result = statement.executeUpdate(sql);

            String details = "insert into personaldetails values('" + name + "'," + age + ",'" + number + "','" + location + "','" + DOB + "'," + customerid + ",'" + temppassword + "'," + balance + ")";
            int status = statement.executeUpdate(details);

            if (result == 1 && status == 1) {
                
                System.out.println("Account Created Successfully");
                System.out.println("Customer ID : " + customerid + "\nPassword : " + temppassword);
            } else {
                System.out.println("Account creation failed. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }
    
    public void Login() {
        try {
            dbconnection.getConnect();
            System.out.println("Enter username :");
            String username = scanner.next();
            System.out.println("Enter password :");
            String password = scanner.next();

            Statement statement = DBconnection.connection.createStatement();
            String sqlQuery = "select * from userdetails where username='" + username + "' and password='" + password + "'";

            ResultSet result = statement.executeQuery(sqlQuery);
            int flag = result.next() ? 1 : 0;

            if (password.equals(temppassword) || flag == 1) {
                if (password.equals(temppassword) && flag == 1) {
                    System.out.println("Reset password :");
                    System.out.println("New password :");
                    String newpassword = scanner.next();

                    String sql = "UPDATE userdetails SET password = '" + newpassword + "' WHERE username = '" + username + "'";
                    int status = statement.executeUpdate(sql);
                    if (status == 1) {
                        System.out.println("Password updated successfully");
                    } else {
                        System.out.println("Updation failed");
                    }
                } else if (flag == 1) {
                    System.out.println("Login successfully");
                    
                    userfunction = new UserFunctions(customerid, balance);
                    AvailableOptions();
                }
            } else {
                System.out.println("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    public void CreateLogin() {
    	
    	//userfunction.display();
    	
        try {
            System.out.println("Create Account/Login to Account");
            System.out.println("1. Create Account");
            System.out.println("2. Login Account");
            int choose = scanner.nextInt();

            switch (choose) {
                case 1:                    
                    CreateAccount();                    
                    Login();        
              
                    break;

                case 2:
                    
                    Login();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error in CreateLogin: " + e.getMessage());
        }
    }
    
    public void AvailableOptions() {
        try {
        	userfunction = new UserFunctions(customerid, balance);
            while (true) {
                System.out.println("\n--- Bank Management System ---");
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Loan options");
                System.out.println("4. View Profile");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Deposit Amount :");
                        int deposit = scanner.nextInt();
                        userfunction.deposit(deposit);
                        break;

                    case 2:
                        System.out.println("Withdraw Amount :");
                        int withdraw = scanner.nextInt();
                        userfunction.Withdraw(withdraw);
                        break;

                    case 3:
                        userfunction.loanOptions();
                        break;

                    case 4:
                        viewProfile();
                        break;

                    case 5:
                        userfunction.displayInfo();
                        return;

                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in AvailableOptions: " + e.getMessage());
        }
    }

    public void viewProfile() {
        try {
            dbconnection.getConnect();

            Statement statement = DBconnection.connection.createStatement();
            System.out.print("CustomerID :");
            String CustomerId = scanner.next();

            String sqlQuery = "select * from personaldetails where customerid='" + CustomerId + "'";

            ResultSet result = statement.executeQuery(sqlQuery);
            if (result.next()) {
                String Name = result.getString(1);
                int age = result.getInt(2);
                String mobilenumber = result.getString(3);
                String location = result.getString(4);
                String Dob = result.getString(5);
                System.out.println("Name :" + Name + "\n" + "Age :" + age + "\n" +
                        "Mobile number :" + mobilenumber + "\n" + "Location :" + location + "\n" +
                        "Date of Birth :" + Dob + "\n");
            } else {
                System.out.println("Invalid CustomerID");
            }
        } catch (SQLException e) {
        	
            System.out.println("Error fetching profile: " + e.getMessage());
        }
    }
}
