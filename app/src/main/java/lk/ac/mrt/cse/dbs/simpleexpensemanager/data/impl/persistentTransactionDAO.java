package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DatabaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class persistentTransactionDAO implements TransactionDAO {

    DatabaseHelper db;
    public persistentTransactionDAO(Context context){
        this.db = new DatabaseHelper(context);
    }
    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        db.insertTransaction(String.valueOf(date),accountNo,String.valueOf(expenseType),amount);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        List<Transaction> arr = db.getAllTransactionData();
        return arr;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        List<Transaction> arr2 =  db.getPaginatedTransactions(limit);
        return arr2;
    }
}
