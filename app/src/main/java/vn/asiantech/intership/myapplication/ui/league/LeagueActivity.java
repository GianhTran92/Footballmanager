package vn.asiantech.intership.myapplication.ui.league;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.intership.myapplication.R;

/**
 * created by igianhtran on 20/10/2015
 */

@EActivity(R.layout.activity_league)
public class LeagueActivity extends AppCompatActivity {

    @ViewById(R.id.recyclerViewLeague)
    RecyclerView mRecyclerViewLeague;

    @Click(R.id.imgViewAddLeague)
    void addNewLeague() {
    }

    @AfterViews
    void afterView() {
    }

}
