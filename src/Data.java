import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


// Class handles any data handling
// I,e saving and loading 
public class Data 
{
    /**
     * @param listOfAccounts List of accounts to save to a text file
     */
    // Gets attributes from each account object and writes it to txt file
    public static void save(ArrayList<Account> listOfAccounts)
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Data.txt"));
            
            for (Account accounts : listOfAccounts)
            {
                String accountNum = accounts.getAccountNumber();
                String fName = accounts.getFirstName();
                String lName = accounts.getLastName();
                double balance = accounts.getBalance();
                String password = accounts.getPassword();
                String accountType = accounts.getAccountType();
                Double interestRate = accounts.getInterestRate();

                writer.write(accountNum + " | " + fName + " | " + lName + " | " + balance + " | " + password + " | " + accountType + " | " + interestRate + "\n");
            }
            writer.close();

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * @param listOfAccounts List where accounts will load into
     * @return List of accounts with all accounts loaded
     */
    // Reads attributes from text file and creates new object
    // This is then stored in an arraylist and is returned
    public static ArrayList<Account> load(ArrayList<Account> listOfAccounts)
    {
        try 
        {
            String object;
            BufferedReader reader = new BufferedReader(new FileReader("src\\Data.txt"));

            while ((object = reader.readLine()) != null)
            {
                String[] attributes = object.split(" \\| ");
                String accountNum = attributes[0];
                String firstName = attributes[1];
                String lastName = attributes[2];
                double balance = Double.parseDouble(attributes[3]);
                String password = attributes[4];
                String accountType = attributes[5];
                double interestRate = Double.parseDouble(attributes[6]);
                
                Account account = new Account(accountNum, firstName, lastName, balance, password, accountType, interestRate);
                listOfAccounts.add(account);

            }
            reader.close();
            return listOfAccounts;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Account getAccount(ArrayList<Account> listOfAccounts, String accountNumber)
    {
        for (Account account : listOfAccounts)
        {
            if (account.getAccountNumber().equals(accountNumber))
            {
                return account;
            }
        }
        return null;
    }
    
}
