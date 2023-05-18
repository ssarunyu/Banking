package bankingapplication;

import java.util.Random;
import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("KASIKORN");
        int option = 0, number;
        String name;
        double balance, amount;
        Account account;

        while (option != 6) {
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            option = scan.nextInt();
            scan.nextLine();
            System.out.println();

            // Class Bank
            switch (option) {
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    number = generateAccountNumber();
                    System.out.print("Enter Account Name: ");
                    name = scan.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    account = new Account(number, name, balance);
                    bank.openAccount(account);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    bank.closeAccount(number);
                    System.out.println("Account is Deleted");
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: "); // ให้ใส่จำนวนเงินที่จะฝาก
                    amount = scan.nextDouble();
                    bank.depositMoney(account, amount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: "); // ให้ใส่จำนวนเงินที่จะถอน
                    amount = scan.nextDouble();
                    bank.withdrawMoney(account, amount);
            }
        }
    }

    // Random accID numbers
    public static int generateAccountNumber() {
        // Random 6 digit
        Random random = new Random();
        int accNumber = 100000 + random.nextInt(900000);
        return accNumber;
    }
}
