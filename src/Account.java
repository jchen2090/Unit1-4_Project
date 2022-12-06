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

    /**
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param password Password that will be used to log in to the account
     * @param accountType Checking or saving account
     */
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

    /**
     * @param bankAccountNumber An account's unique number
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param balance Balance in the account
     * @param password Password that will be used to log in to the account
     * @param accountType Checking or saving account
     * @param interestRate Interest rate of the account
     */
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

    /**
     * @return Account's first name
     */
    public String getFirstName()
    {
        return fName;
    }

    /**
     * @return Account's last name
     */
    public String getLastName()
    {
        return lName;
    }

    /**
     * @return Account number
     */
    public String getAccountNumber()
    {
        return bankAccountNumber;
    }

    /**
     * @return Account type
     */
    public String getAccountType()
    {
        return accountType;
    }

    /**
     * @return Account password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param newPassword The new password that will replace the old one
     */
    public void setPassword(String newPassword)
    {
        this.password = newPassword;
    }

    /**
     * @return Account balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * @return Account interest rate
     */
    public double getInterestRate()
    {
        return interestRate;
    }

    /**
     * @param depositAmt Amount to deposit into account
     */
    public void deposit(double depositAmt)
    {
        balance += depositAmt;
    }


    /**
     * @param withdrawAmt Amount to withdraw from account
     */
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

    /**
     * Wait one day, balance will increase if account is checking, will be unchanged if it is saving
     */
    public void waitOneDay()
    {
        if (accountType.equals("Savings") || interestRate != 0)
        {
            balance *= (1 + interestRate);
            interestRate = Math.random();
        }
        else
        {
            System.out.println("You waited one day but nothing happened...");
        }

    }

    /**
     * @return Formatted account information
     */
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
