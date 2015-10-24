package vn.asiantech.intership.myapplication.ui.FootballTeam;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.model.League;

@EActivity(R.layout.activity_football_team)
public class FootballTeamActivity extends AppCompatActivity {
    FootballTeamRecyclerAdapter mFootballTeamRecyclerAdapter;
    List<FootballTeam> mFootballTeams;
    League mLeague;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext = this;
    long mLeagueId;
    @ViewById(R.id.tvLeagueNameTeam)
    TextView mTvLeagueName;
    @ViewById(R.id.recyclerViewFootballTeam)
    RecyclerView mRecyclerViewFootballTeam;
    @ViewById(R.id.imgViewAddFootballTeam)
    ImageView mImgViewAddFootballTeam;

    @Click(R.id.imgViewAddFootballTeam)
    void addNewFootballTeam() {
        mImgViewAddFootballTeam.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        showDialogAddNewLeague();
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
        reSizeHeader();
        getDataFromLeagueActivity();
        mTvLeagueName.setText(mLeague.getName());
        setAdapter();

    }

    public void getDataFromLeagueActivity() {
        Intent intent = getIntent();
        mLeagueId = intent.getLongExtra(Common.KEY_LEAGUE_ID,0l);
        mLeague = League.findById(League.class, mLeagueId);
    }

    public List<FootballTeam> createDemoData() {
        List<FootballTeam> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FootballTeam footballTeam = new FootballTeam("manchester united " + i, 1l, "description " + i, null);
            listData.add(footballTeam);
        }

        return listData;
    }

    public void setAdapter() {
        mFootballTeams = createDemoData();
        mLayoutManager = new LinearLayoutManager(FootballTeamActivity.this);
        mRecyclerViewFootballTeam.setLayoutManager(mLayoutManager);
        mFootballTeamRecyclerAdapter = new FootballTeamRecyclerAdapter(mFootballTeams, mContext);
        mRecyclerViewFootballTeam.setAdapter(mFootballTeamRecyclerAdapter);
    }

    public void showDialogAddNewLeague() {
        final Dialog dialog = new Dialog(mContext);
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
                    FootballTeam footballTeam = new FootballTeam(edtAddFootballTeamName.getText().toString(), 1l, edtAddDescription.getText().toString(), null);
                    mFootballTeams.add(footballTeam);
                    mFootballTeamRecyclerAdapter.updateList(mFootballTeams);
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

    public void reSizeHeader() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlFootballTeamTop.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels / 4 * 2;
        mRlFootballTeamTop.setLayoutParams(params);
    }
}
