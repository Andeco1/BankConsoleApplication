import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type;
    private double amount;
    private String fromAccountNumber;
    private String toAccountNumber;
    private LocalDateTime timestamp;
    private boolean success;
    private String message;

    public Transaction(TransactionType type, double amount, String from, String to, boolean success, String message) {
        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = from;
        this.toAccountNumber = to;
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String status = success ? "OK" : "FAILED";
        return String.format("[%s] %s | Type: %s | Amount: %.2f | From: %s | To: %s | Msg: %s",
                timestamp.format(formatter), status, type, amount,
                (fromAccountNumber == null ? "-" : fromAccountNumber),
                (toAccountNumber == null ? "-" : toAccountNumber),
                message);
    }
    
    // Геттер нужен для отчетов
    public boolean isSuccess() {
        return success;
    }
}