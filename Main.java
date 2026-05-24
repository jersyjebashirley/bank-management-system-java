import java.util.Scanner;

abstract class Account {
    long Accnum;
    String name;
    String branch_name;
    String phnum;
    String IFSC;
    double bal;

    Account(long Accnum, String name, String branch_name, String phnum, String IFSC, double bal) {
        this.Accnum = Accnum;
        this.name = name;
        this.branch_name = branch_name;
        this.phnum = phnum;
        this.IFSC = IFSC;
        this.bal = bal;
    }

    abstract void deposit(double amt);
    abstract void withdraw(double amt);
    abstract void transfer(double amt, String IFSC, String branch_name);
}

class Bank extends Account {

    Bank(long Accnum, String name, String branch_name, String phnum, String IFSC, double bal) {
        super(Accnum, name, branch_name, phnum, IFSC, bal);
    }

    @Override
    public void deposit(double amt) {
        if (amt > 0) {
            bal += amt;
            System.out.println("Deposited: " + amt);
            System.out.println("Balance: " + bal);
        } else {
            System.out.println("Invalid amount");
        }
    }

    @Override
    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("Invalid amount");
        } else if (amt <= bal) {
            bal -= amt;
            System.out.println("Withdrawn: " + amt);
            System.out.println("Balance: " + bal);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    @Override
    public void transfer(double amt, String IFSC, String branch_name) {
        if (amt <= 0) {
            System.out.println("Invalid amount");
        } else if (amt <= bal) {
            bal -= amt;
            System.out.println("Transferred Successfully");
            System.out.println("Transferred Amount: " + amt);
            System.out.println("To Branch: " + branch_name);
            System.out.println("To IFSC: " + IFSC);
            System.out.println("Remaining Balance: " + bal);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void showDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + Accnum);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch_name);
        System.out.println("Phone: " + phnum);
        System.out.println("IFSC: " + IFSC);
        System.out.println("Balance: " + bal);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Bank b1 = new Bank(
                33442,
                "Ishu",
                "Chennai",
                "9876543210",
                "ISDRT001",
                5000.67
        );

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Show Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    b1.deposit(dep);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double with = sc.nextDouble();
                    b1.withdraw(with);
                    break;

                case 3:
                    System.out.print("Enter transfer amount: ");
                    double transAmt = sc.nextDouble();

                    System.out.print("Enter receiver IFSC: ");
                    String IFSC = sc.next();

                    sc.nextLine(); // clear buffer
                    System.out.print("Enter receiver branch name: ");
                    String branch_name = sc.nextLine();

                    b1.transfer(transAmt, IFSC, branch_name);
                    break;

                case 4:
                    b1.showDetails();
                    break;

                case 5:
                    System.out.println("Thank you! Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
