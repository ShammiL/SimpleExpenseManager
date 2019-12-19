package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DatabaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class persistentAccountDAO implements AccountDAO {

    DatabaseHelper db;

    persistentAccountDAO(Context context)
    {
        db = new DatabaseHelper(context);
    }

    @Override
    public List<String> getAccountNumbersList() {
        Cursor res= db.getAccountNumbers();
        int count = res.getCount();
        ArrayList<String> arr = new ArrayList<>();
        if (count ==0)
            return arr;
        else
            while (res.moveToNext())
                arr.add(res.getString(0));

            return arr;
    }

    @Override
    public List<Account> getAccountsList() {
        ArrayList<Account> arr = (ArrayList<Account>) db.getAllAccountData();
        return arr;

    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account acc= db.getAccount(accountNo);
        return acc;

    }

    @Override
    public void addAccount(Account account) {
        db.insertAccount(account.getAccountNo(),account.getBankName(),account.getAccountHolderName(),account.getBalance());
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        db.deleteAccount(accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account acc= db.getAccount(accountNo);
        double balance = acc.getBalance();
        if(String.valueOf(expenseType)=="EXPENSE")
        {
            if(balance - amount>=0)
            {
                 balance = balance-amount;
            }
            db.updateAccount(accountNo,acc.getBankName(),acc.getAccountHolderName(),balance);
        }
        else if(String.valueOf(expenseType)=="EXPENSE")
        {
            db.updateAccount(accountNo,acc.getBankName(),acc.getAccountHolderName(),balance+amount);
        }
        else{
            System.out.print("error");
        }


    }
}
