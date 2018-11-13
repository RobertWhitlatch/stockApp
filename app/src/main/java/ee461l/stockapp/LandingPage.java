package ee461l.stockapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LandingPage extends AppCompatActivity {

    private Handler LandingWait = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        LandingWait.postDelayed(new Runnable() {

            @Override
            public void run() {

                //The following code will execute after the 5 seconds.

                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish(); // marks Landing Page complete, thus non-navigable...
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 1000); //changed to 1000 for debugging speed //5000);  // LandingWait.postDelay()
    } // onCreate()

    @Override
    public void onDestroy() {
        super.onDestroy();
        LandingWait.removeCallbacksAndMessages(null); // makes this page completely unreachable
    }

}
