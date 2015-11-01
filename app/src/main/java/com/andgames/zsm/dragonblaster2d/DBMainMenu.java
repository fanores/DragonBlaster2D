package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 15.5.2015
 * Main Menu (DragonBlaster2D):
 *  -> activity for displayin game's main menu;
 */

/* Imported libraries:  */
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DBMainMenu extends Activity {

    /* Called when activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // show the main menu layout
        setContentView(R.layout.activity_main_menu);

        // start background music playing
        DBEngine.musicThread = new Thread() {
            public void run() {
                Intent bgMusic =
                    new Intent(getApplicationContext(), DBMusic.class);
                startService(bgMusic);
                DBEngine.context = getApplicationContext();
            }
        };
        DBEngine.musicThread.start();

        // reference to game engine
        final DBEngine dbEngine = new DBEngine();

        // set buttons' properties
        ImageButton btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        ImageButton btnExit = (ImageButton)findViewById(R.id.btnExit);
        ImageButton btnStgs = (ImageButton)findViewById(R.id.btnStgs);

        btnPlay.getBackground().setAlpha(DBEngine.MENU_BUTTON_ALPHA);
        btnPlay.setHapticFeedbackEnabled(DBEngine.HAPTIC_BUTTON_FEEDBACK);
        btnExit.getBackground().setAlpha(DBEngine.MENU_BUTTON_ALPHA);
        btnExit.setHapticFeedbackEnabled(DBEngine.HAPTIC_BUTTON_FEEDBACK);
        btnStgs.getBackground().setAlpha(DBEngine.MENU_BUTTON_ALPHA);
        btnStgs.setHapticFeedbackEnabled(DBEngine.HAPTIC_BUTTON_FEEDBACK);

        // button PLAY onClickListener
        // launch the game
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // Start the game
                Intent game = new Intent(getApplicationContext(), DBGame.class);
                DBMainMenu.this.startActivity(game);
            }
        });

        // button EXIT onClickListener
        // kill the game and free its resources
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean clean = false;
                clean = dbEngine.onExit(view);
                if (clean) {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                }
            }
        });

        // button SETTINGS onClickListener
        btnStgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: decide what settings to use
                // maybe muting the music
            }
        });


    }
}
