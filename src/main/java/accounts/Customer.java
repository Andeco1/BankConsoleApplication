package accounts;

public class Customer {
    private static int idCounter = 1;
    
    private int id;
    private String fullName;

    public Customer(String fullName) {
        this.id = idCounter++;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName;
    }
}