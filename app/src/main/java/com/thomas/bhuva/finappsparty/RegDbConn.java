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
        Log.e("DB OP", "DATABASE CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + RegDb.regDb1.TABLE_NAME +
                        "(_id integer primary key," + RegDb.regDb1.NAME + " text," + RegDb.regDb1.PHONE_NUMBER +
                        " text," + RegDb.regDb1.CARD_NUMBER + " integer," + RegDb.regDb1.EXPIRY_DATE + " text," + RegDb.regDb1.CVV + " integer," + RegDb.regDb1.BILLING_ADDR +
                        " text," + RegDb.regDb1.CITY + " text," + RegDb.regDb1.STATE + " text," + RegDb.regDb1.PIN + " integer)");

        db.execSQL("CREATE TABLE IF NOT EXISTS transaction1 (_id integer primary key, vendor_name text, vendor_id text, amount integer, time text, longitude text, latitude text)");
        Log.d("DB OP", "onCREATE ()");
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
    public boolean insertTransaction  (String vendorname, String vendorid, int amount, String time,float longitude, float latitude)
    {
        myDataBase = this.getWritableDatabase();
        String longi=Float.toString(longitude);
        String lat=Float.toString(latitude);
        ContentValues contentValues = new ContentValues();
        contentValues.put("vendor_name", vendorname);
        contentValues.put("vendor_id", vendorid);
        contentValues.put("amount", amount);
        contentValues.put("time",time);
        contentValues.put("longitude", longi);
        contentValues.put("latitude", lat);

        myDataBase.insert("transaction1", null, contentValues);
        Log.e("DB OP", "data entry transaction");
        myDataBase.close();
        return true;
    }

}
