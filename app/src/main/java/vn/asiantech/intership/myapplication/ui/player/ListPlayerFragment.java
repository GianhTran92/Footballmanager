package vn.asiantech.intership.myapplication.ui.player;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.dialog.AddNewPlayerDialog;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */

@EFragment(R.layout.fragment_list_player)
public class ListPlayerFragment extends BaseFragment implements LoadLayerInterface,
        PlayerRecyclerAdapter.OnCallPlayerDetail,
        AddNewPlayerDialog.OnSendPlayerListener {
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
    void addPlayer() {
        showDialogAddNewPlayer(getActivity());
    }

    @AfterViews
    void afterView() {
        mFLoatingBtnAddPlayer.attachToRecyclerView(mRecyclerViewPlayer);
        PlayerActivity playerActivity = (PlayerActivity) getActivity();
        mFootballTeamId = playerActivity.getFootballTeamId();
        test.setText(mFootballTeamId + "");
        LoadAsyncTask();
    }

    public void LoadAsyncTask() {
        LoadListDataPlayerByFootballTeamId loadListDataPlayerByFootballTeamId
                = new LoadListDataPlayerByFootballTeamId(mFootballTeamId);
        loadListDataPlayerByFootballTeamId.delegate = this;
        loadListDataPlayerByFootballTeamId.execute();
    }

    public void setAdapter(List<Player> players) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewPlayer.setLayoutManager(mLayoutManager);
        mPlayerRecyclerAdapter = new PlayerRecyclerAdapter(players, mContext, listPlayerFragment);
        mRecyclerViewPlayer.setAdapter(mPlayerRecyclerAdapter);
    }

    /**
     * method implement LoadLayerInterface
     *
     * @param players object is result from LoadListDataPlayerByFootballTeamId AsyncTask classs
     */
    @Override
    public void processFinish(List<Player> players) {
        setAdapter(players);
    }

    /**
     * method implement PlayerRecyclerAdapter.OnCallPlayerDetail interface
     *
     * @param playerId id of player is result when click Detail button on item of recycler view
     */
    @Override
    public void processSuccess(long playerId) {
        Bundle bundle = new Bundle();
        bundle.putLong(Common.KEY_PLAYER_ID, playerId);
        PlayerDetailFragment_ playerDetailFragment_ = new PlayerDetailFragment_();
        playerDetailFragment_.setArguments(bundle);
        this.replaceFragment(R.id.frameContain,
                playerDetailFragment_,
                "PlayerDetailFragment_",
                null);
    }

    /**
     * method implement AddNewPlayerDialog.OnSendPlayerListener interface
     *
     * @param player object is result from dialog when click add on item of recycler view
     */
    @Override
    public void sendSuccess(Player player) {
        SavePlayerInFreezoneToTeam savePlayerInFreezoneToTeam = new SavePlayerInFreezoneToTeam(player, mFootballTeamId);
        savePlayerInFreezoneToTeam.execute();
        LoadAsyncTask();
    }

    /**
     * Using AsyncTask to add a player in freeZone to team
     */
    public class SavePlayerInFreezoneToTeam extends AsyncTask<Void, Void, Void> {
        Player mPlayer;
        long mTeamId;

        public SavePlayerInFreezoneToTeam(Player player, long id) {
            this.mPlayer = player;
            this.mTeamId = id;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mPlayer.setTeamId(mTeamId);
            mPlayer.save();
            return null;
        }
    }

    /**
     * Using AsyncTask to load data
     */

    public class LoadListDataPlayerByFootballTeamId extends AsyncTask<Void, Void, List<Player>> {
        long mFootballTeamId;
        LoadLayerInterface delegate = null;

        public LoadListDataPlayerByFootballTeamId(long id) {
            this.mFootballTeamId = id;
        }

        @Override
        protected List<Player> doInBackground(Void... params) {
            List<Player> players = Player.find(Player.class,
                    "teamId=?",
                    String.valueOf(mFootballTeamId));
            return players;
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            super.onPostExecute(players);
            delegate.processFinish(players);
        }
    }

    /**
     * method to show dialog add new player with event save new player when click submit
     *
     * @param context content to show dialog
     */
    public void showDialogAddNewPlayer(Context context) {
        final AddNewPlayerDialog dialog = new AddNewPlayerDialog(context);
        dialog.setDelegate(this);
        Button mBtnSubmitNewPlayer = (Button) dialog.findViewById(R.id.btnSubmitNewPlayer);
        Button mBtnCancelNewPlayer = (Button) dialog.findViewById(R.id.btnCancelNewPlayer);
        dialog.setSpinerAdapter();
        mBtnCancelNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBtnSubmitNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialog.getName().equals("")) {
                    saveNewPlayer(mFootballTeamId, dialog.getName(), dialog.getWeight(), dialog.getHeight(), dialog.getBirthday(), dialog.getNumber(), dialog.getPosition(), dialog.getCountry(), "img_messi");
                    dialog.dismiss();
                    LoadAsyncTask();
                } else
                    dialog.setEror();
            }
        });
        dialog.show();
    }

    /**
     * Using SyncTask to save new player
     */
    public class NewPlayer extends AsyncTask<Void, Void, Void> {
        String mName;
        String mBirthday;
        String mCountry;
        String mAvatar;

        Float mWeight;
        Float mHeight;

        int mNumber;

        Position.POSITISON mPositison;

        long mTeamId;

        public NewPlayer(long teamId,
                         String name,
                         Float weight,
                         Float height,
                         String bird,
                         int number,
                         Position.POSITISON positison,
                         String country,
                         String avatar) {
            this.mName = name;
            this.mTeamId = teamId;
            this.mWeight = weight;
            this.mHeight = height;
            this.mBirthday = bird;
            this.mNumber = number;
            this.mPositison = positison;
            this.mCountry = country;
            this.mAvatar = avatar;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Player player = new Player(mTeamId,
                    mName,
                    mWeight,
                    mHeight,
                    mBirthday,
                    mNumber,
                    mPositison,
                    mCountry,
                    mAvatar);
            player.save();
            return null;
        }
    }

    public void saveNewPlayer(long teamId,
                              String name,
                              Float weight,
                              Float height,
                              String bird,
                              int number,
                              Position.POSITISON positison,
                              String country,
                              String avatar) {
        NewPlayer newPlayer = new NewPlayer(teamId,
                name,
                weight,
                height,
                bird,
                number,
                positison,
                country,
                avatar);
        newPlayer.execute();
    }
}
