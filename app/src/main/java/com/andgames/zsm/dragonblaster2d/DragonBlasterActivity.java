package com.andgames.zsm.dragonblaster2d;

/**
 * Stefan Misik - AbiduSolutions 15.5.2015
 * Main Activity:
 *  -> used when starting the application;
 *   > an entry point of the app;
 */

/* Imported libraries:  */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class DragonBlasterActivity extends Activity {

    /* Called when activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBEngine.display = ((WindowManager)
                getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        super.onCreate(savedInstanceState);

        // show entry splash screen
        setContentView(R.layout.splashscreen);

        // start a game thread with a delay
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                // launch MainMenu activity
                Intent mainMenu =
                        new Intent(DragonBlasterActivity.this, DBMainMenu.class);
                DragonBlasterActivity.this.startActivity(mainMenu);
                // stop the entry screen activity to prevent launching multiple game threads
                DragonBlasterActivity.this.finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        }, DBEngine.GAME_THREAD_DELAY);
    }
}
