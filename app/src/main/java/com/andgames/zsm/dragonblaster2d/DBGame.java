package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 24.5.2015
 * Game (DragonBlaster2D):
 *  -> class for controling the game environment and its play;
 */

/* Imported libraries:  */
import android.app.Activity;
import android.os.Bundle;

public class DBGame extends Activity {
    /* --- GAME ATTRIBUTES --- */
    private  DBGameView gameView;

    /* Called when activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new DBGameView(this);
        setContentView(gameView);

    }

    /* Called when activity is pause. Activity is not interacting with user. */

    @Override
    protected void onPause() {
        super.onPause();
        gameView.onPause();
    }

    /* Called on resuming activity. Activity is about to be used by user. */

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

}
