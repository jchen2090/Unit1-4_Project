import java.util.Scanner;

public class ATM {

    final static Scanner input = new Scanner(System.in);

    public static void openingScreen()
    {
        do {
            System.out.printf("%30s\n", "ATM Machine");
            System.out.print(
                    "1. Accounts\n"
                    + "9. Exit\n"
                    + ">>> "
            );
            String option = input.nextLine();

            if (option.equals("1")) {
                displayAccounts();
            } else if (option.equals("9")) {
                System.exit(0);
            } else {
                System.out.println("Not a valid option");
            }
        } while(!input.equals("9"));
    }

    private static void displayAccounts()
    {
        System.out.println("Display accounts");
        System.out.println(
                "1. Login"
                + "2. Create Account"
        );

        if (input.equals("1"))
        {
            promptLogin();
        }
        else if (input.equals("2"))
        {
            createAccount();
        }
    }

    private static void promptLogin()
    {
        System.out.println("Prompt login");
    }

    private static void createAccount()
    {
        System.out.println("Create account");
    }

}
