package accounts;

public class CreditAccount extends Account {
    private double creditLimit;

    public CreditAccount(String accountNumber, Customer owner, double creditLimit) {
        super(accountNumber, owner);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        
        // Проверка с учетом лимита (баланс может быть отрицательным)
        // Пример: баланс 0, лимит 1000. Можем снять 1000. Будет -1000.
        // -1000 - 100 < -1000 (false)
        if (this.balance - amount >= -creditLimit) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String getAccountType() {
        return "Credit (Limit: " + creditLimit + ")";
    }
}