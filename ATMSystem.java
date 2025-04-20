import java.util.Scanner;

public class ATMSystem {
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                System.out.println("Initial balance can't be negative. Setting to 0.");
                this.balance = 0;
            } else {
                this.balance = initialBalance;
            }
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("❌ Deposit amount must be greater than 0.");
            } else {
                balance += amount;
                System.out.printf("✅ Deposited: $%.2f\n", amount);
            }
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("❌ Withdrawal amount must be greater than 0.");
            } else if (amount > balance) {
                System.out.println("❌ Insufficient balance.");
            } else {
                balance -= amount;
                System.out.printf("✅ Withdrawn: $%.2f\n", amount);
            }
        }
    }
    static class ATM {
        private BankAccount account;
        private Scanner scanner;

        public ATM(BankAccount account) {
            this.account = account;
            this.scanner = new Scanner(System.in);
        }

        public void start() {
            System.out.println("=== Welcome to the ATM Machine ===");

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        checkBalance();
                        break;
                    case "2":
                        deposit();
                        break;
                    case "3":
                        withdraw();
                        break;
                    case "4":
                        System.out.println("👋 Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
            }
        }

        private void checkBalance() {
            System.out.printf("💰 Your current balance is: $%.2f\n", account.getBalance());
        }

        private void deposit() {
            System.out.print("Enter amount to deposit: $");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                account.deposit(amount);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount entered.");
            }
        }

        private void withdraw() {
            System.out.print("Enter amount to withdraw: $");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                account.withdraw(amount);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount entered.");
            }
        }
    }
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
