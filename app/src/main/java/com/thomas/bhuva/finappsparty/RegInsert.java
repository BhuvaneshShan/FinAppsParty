package com.thomas.bhuva.finappsparty;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegInsert extends Activity{

    public int DEFAULTCARD;


    Context context;

    RegDbConn regdbconn;
    SQLiteDatabase db;

    EditText name,phoneno,cardno,expdate,cvv,billingaddr,city,state,pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        name = (EditText)findViewById(R.id.custName);
        phoneno = (EditText)findViewById(R.id.phoneNumber);
        cardno = (EditText)findViewById(R.id.cardNo);
        expdate = (EditText)findViewById(R.id.expDate);
        cvv = (EditText)findViewById(R.id.cVV);
        billingaddr = (EditText)findViewById(R.id.billAddr);
        city = (EditText)findViewById(R.id.city);
        state = (EditText)findViewById(R.id.state);
        pin = (EditText)findViewById(R.id.pinNo);

        DBAdapter(this);



    }
    public void DBAdapter(Context ctx){//This might go up in the constructor
        this.context=ctx;
        regdbconn=new RegDbConn(context);
    }
    public void addReg(View view) {
        if (regdbconn.insertRegister(name.getText().toString(), phoneno.getText().toString(), Integer.parseInt(cardno.getText().toString()), expdate.getText().toString(), Integer.parseInt(cvv.getText().toString()), billingaddr.getText().toString(), city.getText().toString(), state.getText().toString(), Integer.parseInt(pin.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
            DEFAULTCARD = Integer.parseInt(cardno.getText().toString());
        }
    }
    public void display(View view) {
        String dbquery = "SELECT " + "_id," + RegDb.regDb1.NAME + ',' + RegDb.regDb1.PIN + " FROM " + RegDb.regDb1.TABLE_NAME + " WHERE " + RegDb.regDb1.CARD_NUMBER + "=" + DEFAULTCARD ;
        try {

            db = regdbconn.getReadableDatabase();
            Cursor c = db.rawQuery(dbquery, null);
            if (c.moveToFirst()) {
                do {
                    Log.e("ENtry", c.getString(2));
                } while (c.moveToNext());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
