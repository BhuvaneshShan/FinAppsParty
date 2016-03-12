package com.thomas.bhuva.finappsparty;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

/**
 * Created by bhuva on 3/12/2016.
 */
public class SettingsContentObserver extends ContentObserver{

    int previousVolume;
    Context context;
    private static String TAG = "SettingsContentObserver";
    int STREAM = AudioManager.STREAM_MUSIC;
    public SettingsContentObserver(Context c, Handler handler) {
        super(handler);
        context=c;

        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //previousVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        previousVolume = audio.getStreamVolume(STREAM);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.i(TAG,"TRansaction successful!");
        /*
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(STREAM);

        int delta=previousVolume-currentVolume;

        if(delta>0)
        {
            Log.d(TAG,"Decreased");
            previousVolume=currentVolume;
        }
        else if(delta<0)
        {
            Log.d(TAG, "Increased");
            previousVolume=currentVolume;
        }*/
    }
}
