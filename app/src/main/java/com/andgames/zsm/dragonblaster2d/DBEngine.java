package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 15.5.2015
 * Game Engine (DragonBlaster2D):
 *  -> class for storing the attributes and method of a game engine;
 */

/* Imported libraries:  */
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.View;

public class DBEngine {

    /* --- GAME ENGINE CONSTANTS --- */
    public static final int GAME_THREAD_DELAY = 3000;
    public static final int MENU_BUTTON_ALPHA = 0;
    public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
    public static final int SPASH_SCREEN_MUSIC = R.raw.menu_music;
    public static final int R_VOLUME = 100;
    public static final int L_VOLUME = 100;
    public static final boolean LOOP_BACKGROUND_MUSIC = true;
    public static final int BACKGROUND_LAYER_ONE = R.drawable.game_backg;
    public static final int BACKGROUND_LAYER_TWO = R.drawable.clouds_backg;
    public static final int PLAYER_DRAGON = R.drawable.dragon_goodsprite;
    public static final int DRAGON_MOVE_UP_1 = 1;
    public static final int DRAGON_RELEASE_3 = 3;
    public static final int DRAGON_MOVE_DOWN_4 = 4;
    public static final int DRAGON_FRAMES_BETWEEN_ANI = 9;
    public static final float DRAGON_MOVE_SPEED = .1f;
    public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
    public static float SCROLL_CLOUDS = .001f;

    public static Context context; // stores thread with music activity
    public static Thread musicThread;
    public static Display display;
    public static int dragonFlightAction = 0;
    public static float dragonMoveY = 1.75f;

    /* --- GAME ENGINE METHODS --- */
    public boolean onExit(View view) {
        // clean up all game resources on exiting the game
        try {
            Intent bgMusic = new Intent(context, DBMusic.class);
            context.stopService(bgMusic);
            musicThread.interrupt();
            // musicThread.stop(); /* deprecated method */
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

}
