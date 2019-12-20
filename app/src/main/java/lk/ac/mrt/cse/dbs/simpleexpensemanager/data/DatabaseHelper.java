package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "170311U";
    public static final String TABLE_NAME_1 = "accounts";
    public static final String TABLE_NAME_2 = "transactions";

    public static final String col_1_1 = "Account_no";
    public static final String col_1_2 = "bank";
    public static final String col_1_3 = "Account_holder";
    public static final String col_1_4 = "initial_balance";
    public static final String col_2_1 = "date";
    public static final String col_2_2 = "Account_no";
    public static final String col_2_3 = "expense_type";
    public static final String col_2_4 = "amount";



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME_1 + " (Account_no Text(50) primary key,bank text(50),Account_holder text(50),initial_balance real)");
        db.execSQL("create table "+TABLE_NAME_2 + " (date date,Account_no text(50),expense_type text(50),amount real, foreign key (Account_no) references accounts(Account_no))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME_1);
        onCreate(db);
    }

    public boolean insertAccount(String Account_no,String bank, String Account_holder,double initial_balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ctVal = new ContentValues();
        ctVal.put(col_1_1,Account_no);
        ctVal.put(col_1_2,bank);
        ctVal.put(col_1_3,Account_holder);
        ctVal.put(col_1_4,initial_balance);
        long result = db.insert(TABLE_NAME_1,null,ctVal);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean insertTransaction(String date,String Account_no, String expense_type,double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ctVal = new ContentValues();
        ctVal.put(col_1_1,date);
        ctVal.put(col_1_2,Account_no);
        ctVal.put(col_1_3,expense_type);
        ctVal.put(col_1_4,amount);
        long result = db.insert(TABLE_NAME_1,null,ctVal);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Account getAccount(String acc_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_1 + " where Account_no = ?",new String[]{acc_no});
        int count = res.getCount();
        Account acc = null;
        if (count ==0)
            return acc;
        else
            while (res.moveToNext()) {
                String Account_no = res.getString(0);
                String bank = res.getString(1);
                String Account_holder = res.getString(2);
                double initial_balance = res.getDouble(3);


                acc= new Account(Account_no, bank, Account_holder, initial_balance);
            }

        return acc;
    }

    public List<Account> getAllAccountData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_1,null);
        int count = res.getCount();
        ArrayList<Account> arr2 = new ArrayList<>();
        if (count ==0)
            return arr2;
        else
            while (res.moveToNext()) {
                String Account_no = res.getString(0);
                String bank = res.getString(1);
                String Account_holder = res.getString(2);
                double initial_balance = res.getDouble(3);


                arr2.add(new Account(Account_no, bank, Account_holder, initial_balance));
            }

        return arr2;
    }

    public Cursor getAccountNumbers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Account_no from "+TABLE_NAME_1,null);
        return res;
    }

    public List<Transaction> getAllTransactionData() {
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat format = new SimpleDateFormat("m-d-yyyy", Locale.ENGLISH);
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2,null);
        int count = res.getCount();
        ArrayList<Transaction> arr2 = new ArrayList<>();
        if (count ==0)
            return arr2;
        else
            while (res.moveToNext()) {
                String Account_no = res.getString(1);
                Date date = new Date();
                ExpenseType expense_type = ExpenseType.valueOf(res.getString(2));
                try {
                    date =  format.parse(res.getString(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                double amount = res.getDouble(3);

//Date a = date1.parse(date);
                arr2.add(new Transaction(date, Account_no, expense_type, amount));
            }

        return arr2;
    }

    public List<Transaction> getPaginatedTransactions(int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        DateFormat format = new SimpleDateFormat("m-d-yyyy", Locale.ENGLISH);
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2 + " limit "+ limit,null);
        int count = res.getCount();
        ArrayList<Transaction> arr2 = new ArrayList<>();
        if (count ==0)
            return arr2;
        else
            while (res.moveToNext()) {
                String Account_no = res.getString(1);
                Date date = new Date();
                ExpenseType expense_type = ExpenseType.valueOf(res.getString(2));
                try {
                    date =  format.parse(res.getString(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                double amount = res.getDouble(3);

//Date a = date1.parse(date);
                arr2.add(new Transaction(date, Account_no, expense_type, amount));
            }

        return arr2;
    }

    public boolean updateAccount(String Account_no,String bank,String Account_holder,double initial_balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1_1,Account_no);
        contentValues.put(col_1_2,bank);
        contentValues.put(col_1_3,Account_holder);
        contentValues.put(col_1_4,initial_balance);
        db.update(TABLE_NAME_1, contentValues, "Account_no = ?",new String[] { Account_no });
        return true;
    }

    public Integer deleteAccount (String AccountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_1, "Account_no = ?",new String[] {AccountNo});
    }
}
