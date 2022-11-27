import java.util.ArrayList;

// Class will handle authentication
// i,e checking passwords
// checking if account exists
public class Authenticator 
{

    // Check if the password to an account is correct;
    public static boolean passwordIsCorrect(Account account, String password)
    {
        if (account.getPassword().equals(password))
        {
            return true;
        }
        return false;
    }

    // Gets an account from the list of accounts using account number as an identifier
    public static Account geAccount(ArrayList<Account> listOfAccounts, String accountNumber)
    {
        for (Account accounts : listOfAccounts)
        {
            if (accounts.getAccountNumber().equals(accountNumber)) 
            {
                return accounts;
            }
        }
        return null;
    }
}
