package vn.asiantech.intership.myapplication.ui.FootballTeam;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.model.League;

@EActivity(R.layout.activity_football_team)
public class FootballTeamActivity extends AppCompatActivity {
    FootballTeamRecyclerAdapter mFootballTeamRecyclerAdapter;
    League mLeague;
    RecyclerView.LayoutManager mLayoutManager;
    long mLeagueId;

    @ViewById(R.id.recyclerViewFootballTeam)
    RecyclerView mRecyclerViewFootballTeam;
    @ViewById(R.id.fLoatingBtnAddFootballTeam)
    FloatingActionButton mFLoatingBtnAddFootballTeam;

    @Click(R.id.fLoatingBtnAddFootballTeam)
    void addNewFootballTeam() {
        showDialogAddNewFootballTeam();
    }

    @ViewById(R.id.rlFootballTeamTop)
    RelativeLayout mRlFootballTeamTop;

    @ViewById(R.id.imgViewBackFromFootballTeam)
    ImageView mImgViewBackFromFootballTeam;

    @Click(R.id.imgViewBackFromFootballTeam)
    void doBack() {
        mImgViewBackFromFootballTeam.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        this.finish();
    }

    @AfterViews
    void afterView() {
        loadData();
        mFLoatingBtnAddFootballTeam.attachToRecyclerView(mRecyclerViewFootballTeam);
        mLayoutManager = new LinearLayoutManager(FootballTeamActivity.this);
        mRecyclerViewFootballTeam.setLayoutManager(mLayoutManager);
        reSizeHeader();
        getDataFromLeagueActivity();
    }

    /**
     * this method to get data from leagueActivity and show it on UI
     */
    public void getDataFromLeagueActivity() {
        Intent intent = getIntent();
        mLeagueId = intent.getLongExtra(Common.KEY_LEAGUE_ID, 0l);
        LoadLeagueById loadLeagueById = new LoadLeagueById(mLeagueId,this);
        loadLeagueById.execute();
    }

    public void setAdapter(List<FootballTeam> footballTeams) {
        mFootballTeamRecyclerAdapter = new FootballTeamRecyclerAdapter(footballTeams, this);
        mRecyclerViewFootballTeam.setAdapter(mFootballTeamRecyclerAdapter);
    }


    public void reSizeHeader() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlFootballTeamTop.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels / 4 * 2;
        mRlFootballTeamTop.setLayoutParams(params);
    }

    /**
     * Using AsyncTask to load data of League from LeagueActivity by Id and show on UI
     */
    public class LoadLeagueById extends AsyncTask<Void, Void, League> {
        long mLeagueId;
        FootballTeamActivity mFootballTeamActivity;

        public LoadLeagueById(long leagueId,FootballTeamActivity footballTeamActivity) {
            this.mLeagueId = leagueId;
            this.mFootballTeamActivity = footballTeamActivity;
        }

        @Override
        protected League doInBackground(Void... params) {
            return League.findById(League.class,mLeagueId);
        }

        @Override
        protected void onPostExecute(League league) {
            super.onPostExecute(league);
            TextView mTvLeagueName = (TextView) mFootballTeamActivity.findViewById(R.id.tvLeagueNameTeam);
            mTvLeagueName.setText(league.getName());
        }
    }

    /**
     * using AsyncTask to load data and set to adapter
     */
    public class loadData extends AsyncTask<Void, Void, List<FootballTeam>> {
        long leagueId;
        FootballTeamActivity footballTeamActivity;


        public loadData(long leagueId, FootballTeamActivity footballTeamActivity) {
            this.leagueId = leagueId;
            this.footballTeamActivity = footballTeamActivity;
        }

        @Override
        protected List<FootballTeam> doInBackground(Void... params) {
            List<FootballTeam> footballTeams = FootballTeam.find(FootballTeam.class, "leagueId = ?", String.valueOf(mLeagueId));
            return footballTeams;
        }

        @Override
        protected void onPostExecute(List<FootballTeam> footballTeams) {
            super.onPostExecute(footballTeams);
            footballTeamActivity.setAdapter(footballTeams);
        }
    }

    /**
     * Using AsyncTask to reload date and update list
     */
    public class updateData extends AsyncTask<Void, Void, List<FootballTeam>> {
        long mLeagueId;
        FootballTeamRecyclerAdapter footballTeamRecyclerAdapter;

        public updateData(long leagueId, FootballTeamRecyclerAdapter footballTeamRecyclerAdapter) {
            this.mLeagueId = leagueId;
            this.footballTeamRecyclerAdapter = footballTeamRecyclerAdapter;
        }

        @Override
        protected List<FootballTeam> doInBackground(Void... params) {
            List<FootballTeam> footballTeams = FootballTeam.find(FootballTeam.class, "leagueId=?", String.valueOf(mLeagueId));
//            List<FootballTeam> footballTeams = FootballTeam.listAll(FootballTeam.class);
            return footballTeams;
        }

        @Override
        protected void onPostExecute(List<FootballTeam> footballTeams) {
            super.onPostExecute(footballTeams);
            footballTeamRecyclerAdapter.updateList(footballTeams);
        }
    }

    /**
     * All method to manager data
     */
    public void loadData() {
        loadData loadData = new loadData(mLeagueId, this);
        loadData.execute();
    }

    public void updateData() {
        updateData updateData = new updateData(mLeagueId, mFootballTeamRecyclerAdapter);
        updateData.execute();
    }

    public void deleteFootballTeam(FootballTeam footballTeam) {
        footballTeam.delete();
        updateData();
    }

    public void editFootballTeam(FootballTeam footballTeam, String name, String description, String logo) {
        footballTeam.setName(name);
        footballTeam.setLogo(logo);
        footballTeam.setDescripstion(description);
        footballTeam.save();
        updateData();
    }

    /**
     * method show dialog to add new FootballTeam
     */
    public void showDialogAddNewFootballTeam() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_new_football_team);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final EditText edtAddFootballTeamName = (EditText) dialog.findViewById(R.id.edtAddFootballTeamName);
        final EditText edtAddDescription = (EditText) dialog.findViewById(R.id.edtAddDescription);
        Button btnSubmitAddFootballTeam = (Button) dialog.findViewById(R.id.btnSubmitAddFootballTeam);
        Button btnCancelAddFootballTeam = (Button) dialog.findViewById(R.id.btnCancelAddFootballTeam);

        btnSubmitAddFootballTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtAddFootballTeamName.getText().toString().equals("")) {
                    FootballTeam footballTeam = new FootballTeam(edtAddFootballTeamName.getText().toString(), mLeagueId, edtAddDescription.getText().toString(), "img_mu");
                    footballTeam.save();
                    updateData();
                    dialog.dismiss();

                } else {
                    edtAddFootballTeamName.setError(getString(R.string.error_field_not_be_empty));
                }
            }
        });

        btnCancelAddFootballTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
