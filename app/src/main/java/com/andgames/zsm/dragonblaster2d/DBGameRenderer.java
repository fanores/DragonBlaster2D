package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 24.5.2015
 * Game Renderer (DragonBlaster2D):
 *  -> controll mechanism for all the graphics in a game;
 */

/* Imported libraries:  */
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DBGameRenderer implements GLSurfaceView.Renderer {

    /* Attributes of the class. */
    private DBBackground gameBackg = new DBBackground();
    private DBBackground cloudsBackg = new DBBackground();
    private DBGoodDragon dragon1 = new DBGoodDragon();
    private int goodDragonFrames = 0;
    private float textureX = 0.0f;
    private float textureY = 0.0f;
    private float textureZ = 0.0f;
    private int textureIncrement = DBEngine.TEXTURE_INCREMENT_UP;
    private long loopStart = 0;
    private long loopEnd = 0;
    private long loopRunTime = 0;
    private float scrollClouds;

    /* Called when the Surface is created.
    *  All the textures used in a game are loaded here. */

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // activate OpenGL library
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // test the depth of all objects on the scene
        gl.glClearDepthf(1.0f);
        // set the transparency of objects added
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);

        gameBackg.loadTexture(gl, DBEngine.BACKGROUND_LAYER_ONE, DBEngine.context);
        cloudsBackg.loadTexture(gl, DBEngine.BACKGROUND_LAYER_TWO, DBEngine.context);
        dragon1.loadTexture(gl, DBEngine.PLAYER_DRAGON, DBEngine.context);
    }

    /* Called when drawing a frame on a Surface. */

    @Override
    public void onDrawFrame(GL10 gl) {
        loopStart = System.currentTimeMillis();
        try {
            if (loopRunTime < DBEngine.GAME_THREAD_FPS_SLEEP) {
                Thread.sleep(DBEngine.GAME_THREAD_FPS_SLEEP - loopRunTime);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        drawGameBackground(gl);
        drawCloudsBackground(gl);

        moveDragon1(gl);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);

        loopEnd = System.currentTimeMillis();
        loopRunTime = ((loopEnd - loopStart));
    }

    /* Called when a first background is being drawn. */
    private void drawGameBackground (GL10 gl) {
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();

        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gameBackg.draw(gl);
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    /* Called when a second background is being drawn. */
    private void drawCloudsBackground (GL10 gl) {
        //TODO implement drawing the second background
        if (scrollClouds == Float.MAX_VALUE) {
            scrollClouds = 0f;
        }

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(1f, .25f, 1f);
        gl.glTranslatef(0f, 2.9f, 0f);

        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(scrollClouds, 0.0f, 0.0f);
        cloudsBackg.draw(gl);
        gl.glPopMatrix();
        scrollClouds -= DBEngine.SCROLL_CLOUDS; // cloud move from R -> L
        gl.glLoadIdentity();
    }

    /* Called when Surface changes (dimensions change, or at the begining). */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // prepare a display field
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        // set the cutting edges of the scene
        gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
    }

    /* Used when moving a dragon figure. */
    private void moveDragon1(GL10 gl) {
        switch (DBEngine.dragonFlightAction) {
            case DBEngine.DRAGON_MOVE_UP_1:
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glPushMatrix();
                gl.glScalef(0.25f, 0.25f, 1f);

                if (DBEngine.dragonMoveY > 0) {
                    DBEngine.dragonMoveY -= DBEngine.DRAGON_MOVE_SPEED;
                    gl.glTranslatef(0f, DBEngine.dragonMoveY, 0f);
                    gl.glMatrixMode(GL10.GL_TEXTURE);
                    gl.glLoadIdentity();
                    gl.glTranslatef(0.5f, 0.0f, 0.0f);
                    goodDragonFrames += 1;
                } else {
                    gl.glTranslatef(0.0f, DBEngine.dragonMoveY, 0.0f);
                    gl.glMatrixMode(GL10.GL_TEXTURE);
                    gl.glLoadIdentity();
                    gl.glTranslatef(0.0f, 0.25f, 0f);
                }

                dragon1.draw(gl);
                gl.glPopMatrix();
                gl.glLoadIdentity();
                break;
            case DBEngine.DRAGON_MOVE_DOWN_4:
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glPushMatrix();
                gl.glScalef(0.25f, 0.25f, 1f);

                if (DBEngine.dragonMoveY < 3) {
                    DBEngine.dragonMoveY += DBEngine.DRAGON_MOVE_SPEED;
                    gl.glTranslatef(0f, DBEngine.dragonMoveY, 0f);
                    gl.glMatrixMode(GL10.GL_TEXTURE);
                    gl.glLoadIdentity();
                    gl.glTranslatef(0.5f, 0.25f, 0.0f);
                    goodDragonFrames += 1;
                } else {
                    gl.glTranslatef(0.0f, DBEngine.dragonMoveY, 0.0f);
                    gl.glMatrixMode(GL10.GL_TEXTURE);
                    gl.glLoadIdentity();
                    gl.glTranslatef(0.0f, 0.25f, 0f);
                }

                dragon1.draw(gl);
                gl.glPopMatrix();
                gl.glLoadIdentity();
                break;
            case DBEngine.DRAGON_RELEASE_3:
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glPushMatrix();
                gl.glScalef(.25f, .25f, 1f);
                gl.glTranslatef(0f, DBEngine.dragonMoveY, 0f);
                gl.glMatrixMode(GL10.GL_TEXTURE);
                gl.glLoadIdentity();
                //gl.glTranslatef(0.0f, 0.0f, 0.0f);
                setGoodDragonTextureCoordinates(gl, textureX, textureY, textureZ, goodDragonFrames);
                gl.glTranslatef(textureX, textureY, textureZ);
                dragon1.draw(gl);
                gl.glPopMatrix();
                gl.glLoadIdentity();
                goodDragonFrames += 1;
                break;
            default:
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glPushMatrix();
                gl.glScalef(.25f, .25f, 1f);
                gl.glTranslatef(0f, DBEngine.dragonMoveY, 0f);
                gl.glMatrixMode(GL10.GL_TEXTURE);
                gl.glLoadIdentity();
                //gl.glTranslatef(0.0f, 0.0f, 0.0f);
                setGoodDragonTextureCoordinates(gl, textureX, textureY, textureZ, goodDragonFrames);
                gl.glTranslatef(textureX, textureY, textureZ);
                dragon1.draw(gl);
                gl.glPopMatrix();
                gl.glLoadIdentity();
                goodDragonFrames += 1;
                break;
        }

    }

    /* Used when moving a dragon figure. */
    private void setGoodDragonTextureCoordinates (GL10 gl, float x, float y, float z, int dragonFrames ) {
        // continue only if a dragonFrame change is required!!!
        if ( dragonFrames >= DBEngine.DRAGON_FRAMES_BETWEEN_ANI) {
            /* Determine the INCREMENT ACTION. */
            if ( y == 0.0f ) {
                textureIncrement = DBEngine.TEXTURE_INCREMENT_UP;
            } else if ( y == 0.75f) {
                textureIncrement = DBEngine.TEXTURE_INCREMENT_DOWN;
            }

            /* Set Y-coordinate based on INCREMENT ACTION. */
            switch (textureIncrement) {
                case DBEngine.TEXTURE_INCREMENT_UP:
                    y += 0.25f;
                    break;
                case DBEngine.TEXTURE_INCREMENT_DOWN:
                    y -= 0.25f;
                    break;
                default:
                    y += 0.25f;
                    break;
            }

            goodDragonFrames = 0; // initialize frame countdown variable
        }

        /* Set X, Y, Z coordinates. */
        textureX = x; // set new texture X coordinate
        textureY = y; // set new texture Y coordinate
        textureZ = z; // set new texture Z coordinate
    }
}
