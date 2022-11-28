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
            System.out.printf("Account Number" + "%15s%15s\n", "First Name", "Last Name");
            
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
                System.out.println("Not a valid choice!\n");    
            }
        }
    }


    // Lets the user to log into an account
    // Have to to check if the user exists in the first place
    // Have to check if the password and user input mat
    private static void promptLogin()
    {
        System.out.print("Enter Account Number: ");
        String accountNum = input.nextLine();

        int indexOfAccount = Data.getAccount(listOfAccounts, accountNum);
        System.out.println(indexOfAccount);

        if (indexOfAccount != -1)
        {
            Account account = listOfAccounts.get(indexOfAccount);
            System.out.printf("Enter password for account %s: ", accountNum);
            String password = input.nextLine();

            if (account.getPassword().equals(password))
            {
                promptAccountAction(account);
            }
            else
            {
                System.out.println("Password Incorrect!\n");
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
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        Account account = new Account(firstName, lastName, password);
        listOfAccounts.add(account);
    }

    
    // Prints available accounts
    private static void printAccounts()
    {
        for (Account accounts : listOfAccounts)
        {
            System.out.printf(accounts.getAccountNumber() + "%20s%15s\n", accounts.getFirstName(), accounts.getLastName());
        }
    }


    // Once logged in, allows user to deposit or withdraw money
    private static void promptAccountAction(Account account) 
    {
       while (onScreen)
       {
            System.out.println("\n" + account);
            System.out.print(
                "\n1. Deposit"
                + "\n2. Withdraw"
                + "\n3. Change Password"
                + "\n9. Logout"
                + "\n>>> "
            );

            String option = input.next();
            
            if (option.equals("1"))
            {
                System.out.print("Enter amount to deposit: ");
                double depositAmt = input.nextDouble();
                account.deposit(depositAmt);
                Data.save(listOfAccounts);
            }
            else if (option.equals("2"))
            {
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmt = input.nextDouble();
                account.withdraw(withdrawAmt);
                Data.save(listOfAccounts);
            }
            else if (option.equals("3"))
            {
                System.out.print("Enter current password: ");
                String currentPassword = input.next();

                if (account.getPassword().equals(currentPassword))
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.next();

                    account.setPassword(newPassword);
                }
                else
                {
                    System.out.println("Password Incorrect");
                }
            }
            else if (option.equals("9"))
            {
                return;
            }
            else 
            {
                System.out.println("Not a valid choice!\n");
            }
       }
    }

}
