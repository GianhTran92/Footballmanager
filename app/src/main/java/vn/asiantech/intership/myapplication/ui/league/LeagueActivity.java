package vn.asiantech.intership.myapplication.ui.league;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


import java.util.List;


import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.League;
import vn.asiantech.intership.myapplication.ui.FootballTeam.FootballTeamActivity_;

/**
 * created by igianhtran on 20/10/2015
 */

@EActivity(R.layout.activity_league)
public class LeagueActivity extends AppCompatActivity implements LeagueRecyclerAdapter.OnCallFootballTeamActivityListener {
    LeagueRecyclerAdapter mLeagueRecyclerAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @ViewById(R.id.recyclerViewLeague)
    RecyclerView mRecyclerViewLeague;

    @ViewById(R.id.imgViewSet1Line)
    ImageView mImgViewSet1Line;

    @Click(R.id.imgViewSet1Line)
    void setLinearLayout() {
        mImgViewSet1Line.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        mImgViewSet3Line.setVisibility(View.VISIBLE);
        loadDataByLayout(mLayoutManager);
    }

    @ViewById(R.id.imgViewSet3Line)
    ImageView mImgViewSet3Line;

    @Click(R.id.imgViewSet3Line)
    void setGridLayout() {
        mImgViewSet3Line.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        mLayoutManager = new GridLayoutManager(LeagueActivity.this, 2, LinearLayoutManager.VERTICAL,false);
        mImgViewSet1Line.setVisibility(View.VISIBLE);
        mImgViewSet3Line.setVisibility(View.INVISIBLE);
        loadDataByLayout(mLayoutManager);
    }

    @ViewById(R.id.fLoatingBtnAddLeague)
    FloatingActionButton mFLoatingBtnAddLeague;

    @Click(R.id.fLoatingBtnAddLeague)
    void addNewLeague() {
        showDialogAddNewLeague();
    }

    @AfterViews
    void afterView() {
        mFLoatingBtnAddLeague.attachToRecyclerView(mRecyclerViewLeague);
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        loadDataByLayout(mLayoutManager);
    }

    public void loadDataByLayout(RecyclerView.LayoutManager layoutManager) {
        LoadLeagueData mLoadLeagueData = new LoadLeagueData(this, layoutManager);
        mLoadLeagueData.execute();
    }

    public Context getContext() {
        return this;
    }

    public void updateData() {
        UpdateLeagueData mUpdateLeagueData = new UpdateLeagueData(mLeagueRecyclerAdapter);
        mUpdateLeagueData.execute();
    }

    public void setAdapter(List<League> mLeagues, LeagueActivity leagueActivity, RecyclerView.LayoutManager layoutManager) {
        mRecyclerViewLeague.setLayoutManager(layoutManager);
        mLeagueRecyclerAdapter = new LeagueRecyclerAdapter(mLeagues, leagueActivity);
        mRecyclerViewLeague.setAdapter(mLeagueRecyclerAdapter);
    }

    /**
     * method show dialog to add new league
     */

    public void showDialogAddNewLeague() {

        final Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_new_league);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        final EditText edtLeagueName = (EditText) dialog.findViewById(R.id.edtLeagueName);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmitAddLeague);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtLeagueName.getText().toString().equals("")) {
                    League league = new League(edtLeagueName.getText().toString(), null);
                    league.save();
                    updateData();
                    dialog.dismiss();
                } else {
                    edtLeagueName.setError(getString(R.string.error_field_not_be_empty));
                }

            }
        });

        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelAddLeague);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    @Override
    public void onCall(League league) {
        FootballTeamActivity_.intent(this)
                .extra(Common.KEY_LEAGUE_ID, league.getId())
                .start();
    }

    /**
     * load list league from database using asynctask
     * param List<League>
     */
    public class LoadLeagueData extends AsyncTask<Void, Void, List<League>> {
        LeagueActivity leagueActivity;
        RecyclerView.LayoutManager layoutManager;

        public LoadLeagueData(LeagueActivity leagueActivity, RecyclerView.LayoutManager layoutManager) {
            this.leagueActivity = leagueActivity;
            this.layoutManager = layoutManager;
        }

        @Override
        protected List<League> doInBackground(Void... params) {
            List<League> leagues = League.listAll(League.class);
            return leagues;
        }

        @Override
        protected void onPostExecute(List<League> leagues) {
            super.onPostExecute(leagues);
            leagueActivity.setAdapter(leagues, leagueActivity, layoutManager);

        }
    }

    /**
     * update list data using asynctask
     */
    public class UpdateLeagueData extends AsyncTask<Void, Void, List<League>> {
        LeagueRecyclerAdapter recyclerAdapter;

        public UpdateLeagueData(LeagueRecyclerAdapter recyclerAdapter) {
            this.recyclerAdapter = recyclerAdapter;
        }

        @Override
        protected List<League> doInBackground(Void... params) {
            List<League> leagues = League.listAll(League.class);
            return leagues;
        }

        @Override
        protected void onPostExecute(List<League> leagues) {
            recyclerAdapter.updateList(leagues);
        }
    }

    public void deleteLeague(League league) {
        league.delete();
        updateData();
    }

    public void editLeague(League league, String name, String logo) {
        league.setName(name);
        league.setLogo(logo);
        league.save();
        updateData();
    }

}
