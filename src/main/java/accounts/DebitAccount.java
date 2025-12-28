package accounts;

public class DebitAccount extends Account {

    public DebitAccount(String accountNumber, Customer owner) {
        super(accountNumber, owner);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        // Нельзя уходить в минус
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String getAccountType() {
        return "Debit";
    }
}