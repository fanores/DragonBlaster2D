package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 15.5.2015
 * Music Service (DragonBlaster2D):
 *  -> class for maintaining sounds in the game;
 */

/* Imported libraries:  */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class DBMusic extends Service{

    public static boolean isRunning = false;
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setMusicOptions(this, DBEngine.LOOP_BACKGROUND_MUSIC,
                DBEngine.R_VOLUME, DBEngine.L_VOLUME, DBEngine.SPASH_SCREEN_MUSIC);
    }

    public void setMusicOptions(Context context, boolean isLooped,
                                int rVolume, int lVolume, int soundFile) {
        player = MediaPlayer.create(context, soundFile);
        player.setLooping(isLooped);
        player.setVolume(lVolume, rVolume);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player.start();
            isRunning = true;
        }
        catch (Exception e) {
            isRunning = false;
            player.stop();
        }

        return 1;
    }

    public void onStart(Intent intent, int startId) {

    }

    public void onStop() {
        isRunning = false;
    }

    public IBinder onUnBind(Intent intent) {
        //TODO Auto-generated method stub
        return null;
    }

    public void onPause() {

    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {
        player.stop();
    }

}
