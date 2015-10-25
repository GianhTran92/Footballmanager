package vn.asiantech.intership.myapplication.ui.player;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.FootballTeam;

/**
 * created by gianhtran on 21/10/2015
 */
@EActivity(R.layout.activity_player)
public class PlayerActivity extends AppCompatActivity {

    String mFootballTeamName;
    long mFootballTeamId;

    @ViewById(R.id.circleImgViewFootballTeamLogoPlayer)
    CircleImageView mCircleImgViewFootballTeamLogoPlayer;

    @ViewById(R.id.imgViewBackFromPlayer)
    ImageView mImgViewBackFromPlayer;

    @Click(R.id.imgViewBackFromPlayer)
    void doBack() {
        mImgViewBackFromPlayer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        this.finish();
    }


    @AfterViews
    void afterView() {
        getDataFromLeagueActivity();
        Bundle bundle = new Bundle();
        bundle.putLong(Common.KEY_FOOTBALL_TEAM_ID, mFootballTeamId);
        ListPlayerFragment listPlayerFragment = new ListPlayerFragment();
        listPlayerFragment.setArguments(bundle);

        // send data truc tiep toi using method getFootballTeamId
        addFragment(R.id.frameContain, ListPlayerFragment_.builder().build(), "ListPlayerFragment");
        // dend data using bundle
        addFragment(R.id.rlContentCoachInfor, CoachFragment_.builder().build(), "CoachFragment");

    }

    /**
     * Fragment can access this menthod to get footballteam Id
     * @return football team id
     */
    public long getFootballTeamId(){
        return mFootballTeamId;
    }
    public PlayerActivity getActivity(){
        return this;
    }


    public void getDataFromLeagueActivity() {
        Intent intent = getIntent();
        mFootballTeamId = intent.getLongExtra(Common.KEY_FOOTBALL_TEAM_ID, 0l);
        LoadFootballTeamById loadFootballTeamById = new LoadFootballTeamById(mFootballTeamId,this);
        loadFootballTeamById.execute();
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

    /**
     * Using AsyncTask to load FootballTeam by ID and show on UI
     */
    public class LoadFootballTeamById extends AsyncTask<Void, Void, FootballTeam> {
        long mFootballTeamId;
        PlayerActivity mPlayerActivity;

        public LoadFootballTeamById(long id, PlayerActivity playerActivity) {
            this.mFootballTeamId = id;
            this.mPlayerActivity = playerActivity;
        }

        @Override
        protected FootballTeam doInBackground(Void... params) {
            return FootballTeam.findById(FootballTeam.class, mFootballTeamId);
        }

        @Override
        protected void onPostExecute(FootballTeam footballTeam) {
            super.onPostExecute(footballTeam);
            TextView mTvFootballTeamNamePlayer = (TextView) mPlayerActivity.findViewById(R.id.tvFootballTeamNamePlayer);
            TextView mTvDescriptionPlayer = (TextView) mPlayerActivity.findViewById(R.id.tvDescriptionPlayer);
            mTvFootballTeamNamePlayer.setText(footballTeam.getName());
            mTvDescriptionPlayer.setText(footballTeam.getDescripstion());
        }
    }

}
