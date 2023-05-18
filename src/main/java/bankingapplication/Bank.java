package bankingapplication;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }

    public void listAccount() {
        Connection con = BankConnection.connect();
        try {
            Statement sta = con.createStatement();
            String sql = "select * from account";
            ResultSet result = sta.executeQuery(sql);

            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2) + " "
                        + result.getString(3));
            }
            System.out.println();

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Insert Acc
    public void openAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID, accName, accBalance)" + "values(?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Delete
    public void closeAccount(int number) {
        Connection con = BankConnection.connect();
        String sql = "Delete from account where accID = ?"; // ลบเฉพาะ account ที่เราต้องการ
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update
    public void depositMoney(Account account, double amount) {
        account.deposit(amount); //เอาบัญชี (account) นี้มาฝากเงินเข้า (amount)
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "Update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "Update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int number) {
        Connection con = BankConnection.connect();
        Account account = null;
        String sql = "select * from account where accID = '" + number + "'";
        Statement sta;

        try {
            String accountName = "";
            double balance = 0;
            sta = con.createStatement();
            ResultSet result = sta.executeQuery(sql);

            while (result.next()) {
                accountName = result.getString(2);
                balance = result.getDouble(3);
            }

            account = new Account(number, accountName, balance);

        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
}
