import java.util.Scanner;
import java.util.ArrayList;

public class ATM 
{
    final private static Scanner input = new Scanner(System.in);
    final private static boolean onScreen = true;
    private static ArrayList<Account> listOfAccounts = new ArrayList<>();


    // Opening screen for the ATM machine
    public static void openingScreen()
    {
        listOfAccounts = Data.load(listOfAccounts);
        while (onScreen)
        {
            System.out.printf("%30s", "::ATM Machine::");
            System.out.print(
                "\n1. Start"
                + "\n9. Exit"
                + "\n>>> "
            );
            
            String option = input.nextLine();

            if (option.equals("1"))
            {
                displayAccounts();
            }
            else if (option.equals("9"))
            {
                System.exit(0);
            }
            else 
            {
                System.out.println("Not a valid choice\n");    
            }
        }
    }

    // Displays the accounts to the user
    // Allows user to login or create a new account
    private static void displayAccounts()
    {
        while (onScreen)
        {
            System.out.printf("Account Type" + "%20s%15s%15s\n", "Account Number", "First Name", "Last Name");
            
            printAccounts();
            
            System.out.print(
                "\n1. Log in"
                + "\n2. Create Account"
                + "\n9. Go back to start"
                + "\n>>> "
            );

            String option = input.nextLine();

            if (option.equals("1"))
            {
                promptLogin();
            }
            else if (option.equals("2"))
            {
                createAccount();
                Data.save(listOfAccounts);
            }
            else if(option.equals("9"))
            {
                return;
            }
            else 
            {
                System.out.println("Not a valid choice\n");    
            }
        }
    }


    // Lets the user to log into an account
    // Have to to check if the user exists in the first place
    // Have to check if the password and user input mat
    private static void promptLogin()
    {
        System.out.print("Enter Account Number: ");
        String accountNum = input.nextLine().strip();

        Account account = Data.getAccount(listOfAccounts, accountNum);

        if (account != null)
        {
            System.out.printf("Enter password for account %s: ", accountNum);
            String password = input.nextLine().strip();

            if (account.getPassword().equals(password))
            {
                promptAccountAction(account);
            }
            else
            {
                System.out.println("Password Incorrect\n");
            }
        }
        else
        {
            System.out.println("Account doesn't exist\n");
        }
    }


    // Gets input from user to create an account
    private static void createAccount()
    {
        String accountType = promptAccountType();

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        Account account = new Account(firstName, lastName, password, accountType);
        listOfAccounts.add(account);
    }

    
    // Prints available accounts
    private static void printAccounts()
    {
        for (Account accounts : listOfAccounts)
        {
            System.out.printf(accounts.getAccountType() + "%20s%15s%15s\n", accounts.getAccountNumber(), accounts.getFirstName(), accounts.getLastName());
        }
    }


    // Once logged in, allows user to deposit or withdraw money
    private static void promptAccountAction(Account account) 
    {
       while (onScreen)
       {
            Data.save(listOfAccounts);
            System.out.println("\n" + account);
            System.out.print(
                "\n1. Deposit"
                + "\n2. Withdraw"
                + "\n3. Change Password"
                + "\n4. Wait 1 day"
                + "\n9. Logout"
                + "\n>>> "
            );

            String option = input.nextLine();
            
            if (option.equals("1"))
            {
                System.out.print("Enter amount to deposit: ");
                double depositAmt = input.nextDouble();
                input.nextLine();
                account.deposit(depositAmt);
            }
            else if (option.equals("2"))
            {
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmt = input.nextDouble();
                input.nextLine();
                account.withdraw(withdrawAmt);
            }
            else if (option.equals("3"))
            {
                System.out.print("Enter current password: ");
                String currentPassword = input.nextLine();

                if (account.getPassword().equals(currentPassword))
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();

                    account.setPassword(newPassword);
                }
                else
                {
                    System.out.println("Password Incorrect");
                }
            }
            else if (option.equals("4"))
            {
                account.waitOneDay();
            }
            else if (option.equals("9"))
            {
                return;
            }
            else 
            {
                System.out.println("Not a valid choice\n");
            }
       }
    }

    private static String promptAccountType()
    {
        while (onScreen)
        {
            System.out.print(
                    "What type of account do you want to create?"
                    + "\n1. Saving"
                    + "\n2. Checking"
                    + "\n>>> "
            );
            String accountType = input.nextLine();

            if (accountType.equals("1"))
            {
                return "Savings";
            }
            else if (accountType.equals("2"))
            {
                return "Checkings";
            }
            else
            {
                System.out.println("Not a valid choice");
            }
        }
    }

}
