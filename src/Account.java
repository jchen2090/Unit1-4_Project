import java.util.Random;

public class Account 
{
    private String fName;
    private String lName;
    private String password;
    private String bankAccountNumber;
    private double balance;

    public Account(String fName, String lName, String password)
    {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = 0.0;
        setAccountNumber();
    }

    public Account(String bankAccountNumber, String fName, String lName, double balance, String password)
    {
        this.bankAccountNumber = bankAccountNumber;
        this.fName = fName;
        this.lName = lName;
        this.balance = balance;
        this.password = password;
    }

    private void setAccountNumber()
    {
        Random rand = new Random();
        this.bankAccountNumber = String.format("%8d", rand.nextInt(999999999));
    }
        
    public String getFirstName()
    {
        return fName;
    }

    public String getLastName()
    {
        return lName;
    }

    public String getAccountNumber()
    {
        return bankAccountNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double depostAmt)
    {
        balance += depostAmt;
    }


    public void withdraw(double withdrawAmt)
    {
        if (withdrawAmt > balance)
        {
            System.out.println("Can't withdraw more than balance!");
        }
        else 
        {
            balance -= withdrawAmt;
        }
    }

    public String toString()
    {
        return (
            "Account: " + bankAccountNumber
            + "\nFirst Name: " + fName
            + "\nLast Name: " + lName
            + "\nBalance: " + balance
        );
    }

}
