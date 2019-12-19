package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "170311U.db";
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
        db.execSQL("create table "+TABLE_NAME_1 + " (Account_no Text primary key,bank text,Account_holder text,initial_balance real)");
        db.execSQL("create table "+TABLE_NAME_2 + " ( date text,Account_no text,expense_type text,amount real, foreign key (Account_no) references accounts(Account_no))");
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

    public Cursor getAllAccountData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_1,null);
        return res;
    }

    public Cursor getAllTransactionData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2,null);
        return res;
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

    public Integer deleteAccount (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_1, "ID = ?",new String[] {id});
    }
}
