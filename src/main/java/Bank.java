import accounts.Account;
import accounts.CreditAccount;
import accounts.Customer;
import accounts.DebitAccount;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    
    private int accountCounter = 0;

    public Customer createCustomer(String fullName) {
        Customer c = new Customer(fullName);
        customers.add(c);
        return c;
    }

    public Account openDebitAccount(Customer owner) {
        String accNum = "D-" + (accountCounter++);
        DebitAccount acc = new DebitAccount(accNum, owner);
        accounts.add(acc);
        return acc;
    }

    public Account openCreditAccount(Customer owner, double creditLimit) {
        String accNum = "C-" + (accountCounter++);
        CreditAccount acc = new CreditAccount(accNum, owner, creditLimit);
        accounts.add(acc);
        return acc;
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }


    public boolean deposit(String accountNumber, double amount) {
        Account acc = findAccount(accountNumber);
        boolean success = false;
        String msg = "OK";

        if (acc == null) {
            msg = "accounts.Account not found";
        } else {
            success = acc.deposit(amount);
            if (!success) msg = "Invalid amount";
        }

        // Запись в историю
        transactions.add(new Transaction(
                TransactionType.DEPOSIT, amount, null, accountNumber, success, msg
        ));
        return success;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account acc = findAccount(accountNumber);
        boolean success = false;
        String msg = "OK";

        if (acc == null) {
            msg = "accounts.Account not found";
        } else {
            success = acc.withdraw(amount);
            if (!success) msg = "Insufficient funds or limit exceeded";
        }

        transactions.add(new Transaction(
                TransactionType.WITHDRAW, amount, accountNumber, null, success, msg
        ));
        return success;
    }

    public boolean transfer(String fromAccNum, String toAccNum, double amount) {
        Account from = findAccount(fromAccNum);
        Account to = findAccount(toAccNum);
        boolean success = false;
        String msg = "OK";

        if (from == null || to == null) {
            msg = "One of the accounts not found";
        } else if (fromAccNum.equals(toAccNum)) {
             msg = "Cannot transfer to self";
        } else {
            success = from.transfer(to, amount);
            if (!success) msg = "Transfer failed (low balance or limit)";
        }

        transactions.add(new Transaction(
                TransactionType.TRANSFER, amount, fromAccNum, toAccNum, success, msg
        ));
        return success;
    }


    public void printCustomerAccounts(int customerId) {
        System.out.println("--- Accounts for accounts.Customer ID: " + customerId + " ---");
        boolean found = false;
        for (Account acc : accounts) {
            if (acc.getOwner().getId() == customerId) {
                System.out.println(acc);
                found = true;
            }
        }
        if (!found) System.out.println("No accounts found.");
    }

    public void printTransactions() {
        System.out.println("--- Transaction History ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void printReport() {
        int debitCount = 0;
        int creditCount = 0;
        double totalDebitBalance = 0;
        double totalCreditBalance = 0;

        for (Account acc : accounts) {
            if (acc instanceof DebitAccount) {
                debitCount++;
                totalDebitBalance += acc.getBalance();
            } else if (acc instanceof CreditAccount) {
                creditCount++;
                totalCreditBalance += acc.getBalance();
            }
        }

        long successOps = 0;
        long failOps = 0;
        for (Transaction t : transactions) {
            if (t.isSuccess()) successOps++;
            else failOps++;
        }

        System.out.println("====== BANK REPORT ======");
        System.out.println("Debit Accounts: " + debitCount + " | Total Balance: " + totalDebitBalance);
        System.out.println("Credit Accounts: " + creditCount + " | Total Balance: " + totalCreditBalance);
        System.out.println("Transactions: Success=" + successOps + ", Failed=" + failOps);
        System.out.println("=========================");
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}