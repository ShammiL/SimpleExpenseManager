package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.Context;
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
        SQLiteDatabase db = this.getWritableDatabase();
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
}
