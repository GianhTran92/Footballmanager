package vn.asiantech.intership.myapplication.ui.player;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
    List<Player> mPlayers = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    PlayerRecyclerAdapter mPlayerRecyclerAdapter;
    String mFootballTeamName;
    Context mContext = this;
    @ViewById(R.id.circleImgViewCoachAvatar)
    CircleImageView mCircleImgViewCoachAvatar;
    @ViewById(R.id.tvCoachName)
    TextView mTvCoachName;
    @ViewById(R.id.tvDescriptionPlayer)
    TextView mTvDescriptionPlayer;
    @ViewById(R.id.circleImgViewFootballTeamLogoPlayer)
    CircleImageView mCircleImgViewFootballTeamLogoPlayer;
    @ViewById(R.id.tvFootballTeamNamePlayer)
    TextView mTvFootballTeamNamePlayer;
    @ViewById(R.id.recyclerViewPlayer)
    RecyclerView mRecyclerViewPlayer;
    @ViewById(R.id.imgViewAddPlayer)
    ImageView mImgViewAddPlayer;

    @Click(R.id.imgViewBackFromFootballTeam)
    void doBack() {
        this.finish();
    }
    @Click(R.id.imgViewCoachDetail)
    void showDetailCoach(){

    }

    @AfterViews
    void afterView() {
        getDataFromLeagueActivity();
        mTvFootballTeamNamePlayer.setText(mFootballTeamName);
        mTvDescriptionPlayer.setText("this is demo description");
        setAdapter();
        getCoach();

    }

    public List<Player> createDemoData() {
        List<Player> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Player player = new Player(1l, "Leonal Messi " + i, 100f, 100f, "00/00/000", 24, Position.POSITISON.CentreBack, "USA", null);
            listData.add(player);
        }

        return listData;
    }

    public void setAdapter() {
        mPlayers = createDemoData();
        mLayoutManager = new LinearLayoutManager(PlayerActivity.this);
        mRecyclerViewPlayer.setLayoutManager(mLayoutManager);
        mPlayerRecyclerAdapter = new PlayerRecyclerAdapter(mPlayers, mContext);
        mRecyclerViewPlayer.setAdapter(mPlayerRecyclerAdapter);
    }

    public void getDataFromLeagueActivity() {
        Intent intent = getIntent();
        mFootballTeamName = intent.getStringExtra(Common.KEY_FOOTBALL_TEAM_NAME);
    }
    public void getCoach(){
        Coach coach = new Coach("Alex Ferguson","10/10/2010",1l,"USB",null);
        mTvCoachName.setText(coach.getName());
    }
}
