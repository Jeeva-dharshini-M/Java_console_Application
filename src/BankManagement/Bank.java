package BankManagement;

public interface Bank {
	
	default public void display()
	{
		System.out.println("Welcome");
	}
    public void deposit(int deposit);
    public void Withdraw(int withdraw);
    
    public void loanOptions();
    
    default public void displayInfo()
	{
		System.out.println("Thank you! visit again ");
	}
    
}
