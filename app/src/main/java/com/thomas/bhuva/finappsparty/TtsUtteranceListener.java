package com.thomas.bhuva.finappsparty;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ThomasGeorge on 3/12/2016.
 */
public class TtsUtteranceListener extends UtteranceProgressListener {
    public  static MediaPlayer mediaPlayer;
    private Context mContext;

    public TtsUtteranceListener(Context context){
        mContext = context;
    }
    @Override
    public void onDone(String utteranceId) {
        Log.d("TtsUtteranceListener", "utterance Done: " + utteranceId);

        try {
            Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_RINGTONE);
            mediaPlayer = MediaPlayer.create(mContext, defaultRintoneUri);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(0, 0);
            mediaPlayer.start();
            Log.i("i", "Media player started!");

            Timer timer = new Timer();
            timer.schedule(new MusicTimerTask(), 15000);
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public void onStart(String utteranceId) {
        Log.d("TtsUtteranceListener", "utterance Start: " + utteranceId);
    }

    @Override
    public void onError(String utteranceId) {
        Log.d("TtsUtteranceListener", "utterance Error: " + utteranceId);
    }


    public class MusicTimerTask extends TimerTask {
        @Override
        public void run() {
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                Log.i("i","Media player stopped!");
            }
            this.cancel();
        }
    }
}