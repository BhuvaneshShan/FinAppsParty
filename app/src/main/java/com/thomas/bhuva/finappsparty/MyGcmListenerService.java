package com.thomas.bhuva.finappsparty;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MyGcmListenerService extends GcmListenerService{

    private static final String TAG = "MyGcmListenerService";

    public TextToSpeech t1;
    public String Amount;
    public MediaPlayer mediaPlayer;
    private Context gcmContext;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        gcmContext = this.getApplicationContext();
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        try {
            JSONObject obj = new JSONObject(message);

            Transaction.newTransaction(obj);
            RegDbConn regdbconn = new RegDbConn(gcmContext);
            if ( regdbconn.insertTransaction(Transaction.vendorName, Transaction.vendorId, Integer.parseInt(Transaction.amount), Transaction.time, Transaction.longitute, Transaction.latitude)) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        t1 = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Log.i(TAG, "tts on init listener!");
                t1.setLanguage(Locale.UK);

                t1.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
                    @Override
                    public void onUtteranceCompleted(String utteranceId) {
                        Log.i(TAG, "On utterance completed " + utteranceId); //utteranceId == "SOME MESSAGE"
                        try {

                            if(Transaction.mode == Transaction.VOLUMEPRESS) {

                                Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(gcmContext, RingtoneManager.TYPE_RINGTONE);
                                mediaPlayer = MediaPlayer.create(gcmContext, defaultRintoneUri);
                                mediaPlayer.setLooping(true);
                                mediaPlayer.setVolume(0, 0);
                                mediaPlayer.start();
                                Log.i("i", "Media player started!");

                                Timer timer = new Timer();
                                timer.schedule(new MusicTimerTask(), 15000);
                                stopSelf();

                            }else if(Transaction.mode == Transaction.GPS){
                                //get and check gps
                                float curlati = 232.32f;
                                float curlongi = 232.232f;
                                if(Transaction.latitude == curlati && Transaction.longitute == curlongi){
                                    //process trans
                                }else{
                                    //trans not processed
                                }
                                t1.speak("GPS coordinates match! Transaction processing and is successful.", TextToSpeech.QUEUE_FLUSH, null);
                                //Transaction.processTransaction();
                                Transaction.processTransactionGPS();
                                stopSelf();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


                HashMap<String, String> myHashAlarm = new HashMap<String, String>();
                myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");
                String toSpeak = "The bill amount is "+Transaction.amount+" Please confirm the payment";
                Log.i(TAG, "Calling t1 speak");
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
            }
        });

        sendNotification(message);

        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
    }

    public class MusicTimerTask extends TimerTask {
        @Override
        public void run() {
            if(mediaPlayer!=null) {
                if(Transaction.finishedTransaction==false){
                    t1.speak("The payment was not processed!",TextToSpeech.QUEUE_FLUSH,null );
                }
                Transaction.clearTransactionDetails();
                mediaPlayer.stop();
                mediaPlayer.release();
                Log.i(TAG,"Media player stopped!");
            }
            this.cancel();
        }

    }

    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_plus_signin_btn_icon_light)
                .setContentTitle("Bill Payment")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
