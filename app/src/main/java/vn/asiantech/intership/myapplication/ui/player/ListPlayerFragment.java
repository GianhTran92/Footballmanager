package vn.asiantech.intership.myapplication.ui.player;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */

@EFragment(R.layout.fragment_list_player)
public class ListPlayerFragment extends BaseFragment implements LoadLayerInterface{
    RecyclerView.LayoutManager mLayoutManager;
    PlayerRecyclerAdapter mPlayerRecyclerAdapter;
    Context mContext = getActivity();
    ListPlayerFragment listPlayerFragment = this;
    long mFootballTeamId;

    @ViewById(R.id.recyclerViewPlayer)
    RecyclerView mRecyclerViewPlayer;

    @ViewById(R.id.fLoatingBtnAddPlayer)
    FloatingActionButton mFLoatingBtnAddPlayer;

    @ViewById(R.id.test)
    TextView test;

    @Click(R.id.fLoatingBtnAddPlayer)
    void addPlayer(){

    }

    @AfterViews
    void afterView() {
        mFLoatingBtnAddPlayer.attachToRecyclerView(mRecyclerViewPlayer);
        PlayerActivity playerActivity = (PlayerActivity) getActivity();
        mFootballTeamId = playerActivity.getFootballTeamId();
        test.setText(mFootballTeamId + "");

        LoadListDataPlayerByFootballTeamId loadListDataPlayerByFootballTeamId = new LoadListDataPlayerByFootballTeamId(mFootballTeamId);
        loadListDataPlayerByFootballTeamId.delegate = this;
        loadListDataPlayerByFootballTeamId.execute();

    }

    public void setAdapter(List<Player> players) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewPlayer.setLayoutManager(mLayoutManager);
        mPlayerRecyclerAdapter = new PlayerRecyclerAdapter(players, mContext,listPlayerFragment);
        mRecyclerViewPlayer.setAdapter(mPlayerRecyclerAdapter);
    }

    @Override
    public void processFinish(List<Player> players) {
        setAdapter(players);
    }

    /**
     * Using AsyncTask to load data
     */

    public class LoadListDataPlayerByFootballTeamId extends AsyncTask<Void,Void,List<Player>> {
        long mFootballTeamId;
        LoadLayerInterface delegate = null;
        public LoadListDataPlayerByFootballTeamId(long id) {
            this.mFootballTeamId = id;
        }

        @Override
        protected List<Player> doInBackground(Void... params) {
            List<Player> players = Player.find(Player.class,"teamId=?",String.valueOf(mFootballTeamId));
            return players;
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            super.onPostExecute(players);
            delegate.processFinish(players);
        }
    }
}
