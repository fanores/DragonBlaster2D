package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 24.5.2015
 * Game View (DragonBlaster2D):
 *  -> class for displaying graphics in a game;
 */

/* Imported libraries:  */
import android.content.Context;
import android.opengl.GLSurfaceView;

public class DBGameView extends GLSurfaceView {

    /* Attributes of the class. */
    private DBGameRenderer renderer;

    /* Constructor of the class.*/

    public DBGameView(Context context) {
        super(context);
        renderer = new DBGameRenderer();
        this.setRenderer(renderer);

    }
}
