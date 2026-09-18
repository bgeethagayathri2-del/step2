class MessWallet {

    // Private variable
    private double balance;

    // Constructor
    public MessWallet(double balance) {

        if (balance < 0) {
            System.out.println("Warning: Negative opening balance. Starting with 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    // Method to add money
    public void topUp(double amount) {

        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than 0");
        } else {
            balance = balance + amount;
            System.out.println("Balance after top-up: " + balance);
        }
    }

    // Method to deduct money
    public void deduct(double amount) {

        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else if (amount <= 0) {
            System.out.println("Deduct rejected: amount must be greater than 0");
        } else {
            balance = balance - amount;
            System.out.println("Balance after deduction: " + balance);
        }
    }

    // Getter
    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        wallet.deduct(1000);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}