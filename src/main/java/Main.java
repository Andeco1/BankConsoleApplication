import accounts.Account;
import accounts.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Banking System Simulator.");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create customer");
            System.out.println("2. Open debit account");
            System.out.println("3. Open credit account");
            System.out.println("4. Deposit money");
            System.out.println("5. Withdraw money");
            System.out.println("6. Transfer money");
            System.out.println("7. Show customer accounts");
            System.out.println("8. Show all Transactions");
            System.out.println("9. Bank report");
            System.out.println("10. Exit");
            System.out.print("> ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter full name: ");
                    String name = scanner.nextLine();
                    Customer c = bank.createCustomer(name);
                    System.out.println("Customer created: " + c);
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    int idDeb = Integer.parseInt(scanner.nextLine());
                    Customer ownerDeb = bank.findCustomerById(idDeb);
                    if (ownerDeb != null) {
                        Account a = bank.openDebitAccount(ownerDeb);
                        System.out.println("Opened: " + a);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    int idCred = Integer.parseInt(scanner.nextLine());
                    Customer ownerCred = bank.findCustomerById(idCred);
                    if (ownerCred != null) {
                        System.out.print("Enter credit limit: ");
                        double limit = Double.parseDouble(scanner.nextLine());
                        Account a = bank.openCreditAccount(ownerCred, limit);
                        System.out.println("Opened: " + a);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 4:
                    System.out.print("Account number: ");
                    String accDep = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amtDep = Double.parseDouble(scanner.nextLine());
                    if (bank.deposit(accDep, amtDep)) {
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;
                case 5:
                    System.out.print("Account Number: ");
                    String accWd = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amtWd = Double.parseDouble(scanner.nextLine());
                    if (bank.withdraw(accWd, amtWd)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Withdrawal failed.");
                    }
                    break;
                case 6:
                    System.out.print("From account number: ");
                    String from = scanner.nextLine();
                    System.out.print("To account number: ");
                    String to = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amtTr = Double.parseDouble(scanner.nextLine());
                    if (bank.transfer(from, to, amtTr)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Transfer failed.");
                    }
                    break;
                case 7:
                    System.out.print("Enter customer ID: ");
                    int idShow = Integer.parseInt(scanner.nextLine());
                    bank.printCustomerAccounts(idShow);
                    break;
                case 8:
                    bank.printTransactions();
                    break;
                case 9:
                    bank.printReport();
                    break;
                case 10:
                    System.out.println("Exiting system");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown option.");
            }
        }
        scanner.close();
    }
}