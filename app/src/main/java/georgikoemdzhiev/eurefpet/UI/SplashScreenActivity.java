package georgikoemdzhiev.eurefpet.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import georgikoemdzhiev.eurefpet.R;

public class SplashScreenActivity extends AppCompatActivity {
    /**
     * Duration of wait before transferring the user to the UrlActivity
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /**
     * Method that is called when the activity is created
     *
     * @param savedInstanceState bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

/* New Handler to start the UrlActivity
         * and close this Splash-Screen after the above amount of seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the UrlActivity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                // Finish this activity to delete it from the activity hierarchy
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
