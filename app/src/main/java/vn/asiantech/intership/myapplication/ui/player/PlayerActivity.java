package vn.asiantech.intership.myapplication.ui.player;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Coach;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * created by gianhtran on 21/10/2015
 */
@EActivity(R.layout.activity_player)
public class PlayerActivity extends AppCompatActivity {

    String mFootballTeamName;


    @ViewById(R.id.tvDescriptionPlayer)
    TextView mTvDescriptionPlayer;
    @ViewById(R.id.circleImgViewFootballTeamLogoPlayer)
    CircleImageView mCircleImgViewFootballTeamLogoPlayer;
    @ViewById(R.id.tvFootballTeamNamePlayer)
    TextView mTvFootballTeamNamePlayer;

    @ViewById(R.id.imgViewAddPlayer)
    ImageView mImgViewAddPlayer;

    @Click(R.id.imgViewBackFromFootballTeam)
    void doBack() {
        this.finish();
    }


    @AfterViews
    void afterView() {
        getDataFromLeagueActivity();
        mTvFootballTeamNamePlayer.setText(mFootballTeamName);
        mTvDescriptionPlayer.setText("this is demo description");
        addFragment(R.id.frameContain, ListPlayerFragment_.builder().build(), "ListPlayerFragment");
        addFragment(R.id.rlContentCoachInfor, CoachFragment_.builder().build(), "CoachFragment");

    }


    public void getDataFromLeagueActivity() {
        Intent intent = getIntent();
        mFootballTeamName = intent.getStringExtra(Common.KEY_FOOTBALL_TEAM_NAME);
    }

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();

    }

}
