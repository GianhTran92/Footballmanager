package vn.asiantech.intership.myapplication.common;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.orm.SugarApp;
import com.orm.SugarContext;

import java.util.Locale;

import vn.asiantech.intership.myapplication.R;

public class FootballManager extends SugarApp {
    private static FootballManager instance = null;


    @Override
    public void onCreate() {
        super.onCreate();
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale("ja");
        res.updateConfiguration(conf, dm);
        SugarContext.init(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public FootballManager getInstance() {
        if (instance == null) {
            instance = new FootballManager();
        }
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}