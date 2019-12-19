package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "170311U.db";
    public static final String TABLE_NAME_1 = "accounts";
    public static final String col_1_1 = "Account_no";
    public static final String col_1_2 = "bank";
    public static final String col_1_3 = "Account_holder";
    public static final String col_1_4 = "initial_balance";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME_1 + " ( Account_no integer primary key,bank text,Account_holder text,initial_balance decimal(6,2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME_1);
        onCreate(db);
    }
}
