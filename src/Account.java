import java.util.Random;

public class Account 
{
    private String fName;
    private String lName;
    private String password;
    private String bankAccountNumber;
    private String accountType;
    private double balance;
    private double interestRate;

    public Account(String fName, String lName, String password, String accountType)
    {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = 0.0;
        setAccountNumber();

        if (accountType.equals("Savings"))
        {
            this.interestRate = 0.03;
        }
        else
        {
            this.interestRate = 0.0;
        }
        this.accountType = accountType;
    }

    public Account(String bankAccountNumber, String fName, String lName, double balance, String password, String accountType, double interestRate)
    {
        this.bankAccountNumber = bankAccountNumber;
        this.fName = fName;
        this.lName = lName;
        this.balance = balance;
        this.password = password;
        this.accountType = accountType;
        this.interestRate = interestRate;
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

    public String getAccountType()
    {
        return accountType;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String newPassword)
    {
        this.password = newPassword;
    }

    public double getBalance()
    {
        return balance;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void deposit(double depostAmt)
    {
        balance += depostAmt;
    }


    public void withdraw(double withdrawAmt)
    {
        if (withdrawAmt > balance)
        {
            System.out.println("Can't withdraw more than balance");
        }
        else 
        {
            balance -= withdrawAmt;
        }
    }

    public void waitOneDay()
    {
        balance *= (1 + interestRate);
        interestRate = Math.random();
    }

    public String toString()
    {
        return (String.format(
                "Account type: %s"
                + "\nFirst Name: %s"
                + "\nLast Name: %s"
                + "\nBalance: $%,.2f"
                + "\n\nCurrent Interest Rate: %.0f%%", accountType, fName, lName, balance, interestRate * 100
        ));
    }

}
