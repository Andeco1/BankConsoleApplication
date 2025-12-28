package accounts;

public abstract class Account {
    private String accountNumber;
    protected double balance;
    private Customer owner;

    public Account(String accountNumber, Customer owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }
    public abstract boolean withdraw(double amount);

    public boolean transfer(Account to, double amount) {
        if (to == null || amount <= 0) return false;

        if (this.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }
    
    // Абстрактный метод, чтобы понимать тип счета при выводе
    public abstract String getAccountType();

    @Override
    public String toString() {
        return String.format("Acc: %s | Owner: %s | Balance: %.2f | Type: %s", 
                accountNumber, owner.getFullName(), balance, getAccountType());
    }
}