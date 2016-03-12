package com.thomas.bhuva.finappsparty;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RegDbConn extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.thomas.bhuva.finappsparty/databases/";

    private static String DB_NAME = "regdb";

    private static final int DATABASE_VERSION = 1;

    public SQLiteDatabase myDataBase=null;

    public RegDbConn(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);
        Log.e("DB OP","DATABASE CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + RegDb.regDb1.TABLE_NAME +
                        "(_id integer primary key," + RegDb.regDb1.NAME + " text," + RegDb.regDb1.PHONE_NUMBER +
                        " text," + RegDb.regDb1.CARD_NUMBER + " integer," + RegDb.regDb1.EXPIRY_DATE + " text," + RegDb.regDb1.CVV + " integer," + RegDb.regDb1.BILLING_ADDR +
                        " text," + RegDb.regDb1.CITY + " text," + RegDb.regDb1.STATE + " text," + RegDb.regDb1.PIN + " integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertRegister  (String name, String phone, int cardno, String expdate,int cvv, String billaddr, String city, String state, int pin)
    {
        myDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RegDb.regDb1.NAME, name);
        contentValues.put(RegDb.regDb1.PHONE_NUMBER, phone);
        contentValues.put(RegDb.regDb1.CARD_NUMBER, cardno);
        contentValues.put(RegDb.regDb1.EXPIRY_DATE, expdate);
        contentValues.put(RegDb.regDb1.CVV, cvv);
        contentValues.put(RegDb.regDb1.BILLING_ADDR, billaddr);
        contentValues.put(RegDb.regDb1.CITY, city);
        contentValues.put(RegDb.regDb1.STATE, state);
        contentValues.put(RegDb.regDb1.PIN, pin);
        myDataBase.insert(RegDb.regDb1.TABLE_NAME, null, contentValues);
        Log.e("DB OP", "data entry");
        myDataBase.close();
        return true;
    }

}
