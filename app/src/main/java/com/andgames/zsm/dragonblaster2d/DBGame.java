package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 24.5.2015
 * Game (DragonBlaster2D):
 *  -> class for controling the game environment and its play;
 */

/* Imported libraries:  */
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;

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

    /* Called when user interacts (touches) the screen. */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Point size = new Point();
        float x = event.getX();
        float y = event.getY();

        DBEngine.display.getSize(size);
        int height = size.y / 4;
        int playableArea = size.y - height;

        if (y > playableArea) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (x < size.x / 2) {
                        DBEngine.dragonFlightAction = DBEngine.DRAGON_MOVE_UP_1;
                    } else {
                        DBEngine.dragonFlightAction = DBEngine.DRAGON_MOVE_DOWN_4;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    DBEngine.dragonFlightAction = DBEngine.DRAGON_RELEASE_3;
                    break;
            }
        }

        //return super.onTouchEvent(event);
        return false;
    }

    /* Called on resuming activity. Activity is about to be used by user. */

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

}
