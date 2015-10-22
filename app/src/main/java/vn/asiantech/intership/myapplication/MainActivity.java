package vn.asiantech.intership.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import vn.asiantech.intership.myapplication.ui.league.LeagueActivity_;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    Context mContext = this;

    @AfterViews
    void afterView() {
        loadSplashScreen();
    }
    private void loadSplashScreen() {
        Thread splashTimer = new Thread() {

            @Override
            public void run() {
                try {
                    int timer = 0;
                    while (timer < 3000) {
                        sleep(100);
                        timer += 100;
                    }
                    LeagueActivity_.intent(mContext)
                            .start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashTimer.start();
    }

}