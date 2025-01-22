package BankManagement;

import java.sql.Statement;
import java.util.Scanner;

public class UserFunctions implements Bank {
    Scanner scanner = new Scanner(System.in);

    private int name;
    int Totalamount;

    public UserFunctions(int name, int Totalamount) {
        super();
        this.name = name;
        this.Totalamount = Totalamount;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getTotalamount() {
        return Totalamount;
    }

    public void setTotalamount(int Totalamount) {
        this.Totalamount = Totalamount;
    }

    @Override
    public void deposit(int deposit) {
        try {
            Totalamount += deposit;

            Statement statement = DBconnection.connection.createStatement();
            String sql = "UPDATE personaldetails SET balance =" + Totalamount + " WHERE CustomerID = " + name;

            int result = statement.executeUpdate(sql);

            if (result == 1) {
                System.out.println("Available Balance is : " + Totalamount);
                System.out.println("Deposit Successful");
            } else {
                System.out.println("Deposit failed");
            }
        } catch (Exception e) {
            System.out.println("Error during deposit: " + e.getMessage());
        }
    }

    @Override
    public void Withdraw(int withdraw) {
        try {
            if (Totalamount > withdraw) {
                Totalamount -= withdraw;

                Statement statement = DBconnection.connection.createStatement();
                String sql = "UPDATE personaldetails SET balance =" + Totalamount + " WHERE CustomerID = " + name;

                int result = statement.executeUpdate(sql);
                if (result == 1) {
                    System.out.println("Remaining Balance is : " + Totalamount);
                    System.out.println("Withdraw Successful");
                } else {
                    System.out.println("Withdraw failed");
                }
            } else {
                System.out.println("Insufficient Balance");
            }
        } catch (Exception e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
    }

    @Override
    public void loanOptions() {
        try {
            System.out.println("Income : ");
            int income = scanner.nextInt();
            if (income > 200000) {
                System.out.println("Eligible for Loan");
            } else {
                System.out.println("Not eligible for loan");
            }
        } catch (Exception e) {
            System.out.println("Error during loan options: " + e.getMessage());
        }
    }
}
