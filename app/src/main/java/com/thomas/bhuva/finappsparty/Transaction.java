package com.thomas.bhuva.finappsparty;

import android.util.Log;

import org.json.JSONObject;

import java.sql.Time;

/**
 * Created by bhuva on 3/12/2016.
 */
public class Transaction {

    public static int GPS = 1;
    public static int VOLUMEPRESS = 0;

    public static int mode = 0; // 0 = Volume press ; 1= gps

    public static String amount = "";
    public static String vendorName = "";
    public static String vendorId = "";
    public static String time = "";
    public static float longitute = 0;
    public static float latitude = 0;

    private static String TAG = "TransactionOBj";

    private static Transaction ourInstance = new Transaction();

    public static Transaction getInstance() {
        return ourInstance;
    }

    private Transaction() {
    }
    public static void newTransaction(JSONObject message){
        try {
            amount = message.getString("Amount");
            Log.i(TAG, "Amnt:" + amount);
            vendorName = message.getString("VendorName");
            vendorId = message.getString("VendorId");
            time = message.getString("Time");
            longitute = Float.parseFloat(message.getString("Longitude"));
            latitude = Float.parseFloat(message.getString("Latitude"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void clearTransactionDetails(){
         amount = "";
         vendorName = "";
         String vendorId = "";
         String time = "";
         float longitute = 0;
         float latitude = 0;
    }
    public static boolean transactionPending(){
        if(amount!="")
            return true;
        else
            return false;
    }

    public static void processTransaction(){
        Log.i(TAG, "TRansaction successful!");

    }
}
