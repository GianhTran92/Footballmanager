package vn.asiantech.intership.myapplication.ui.player;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * A simple {@link Fragment} subclass.
 */

@EFragment(R.layout.fragment_list_player)
public class ListPlayerFragment extends BaseFragment {
    List<Player> mPlayers = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    PlayerRecyclerAdapter mPlayerRecyclerAdapter;
    Context mContext = getActivity();
    @ViewById(R.id.recyclerViewPlayer)
    RecyclerView mRecyclerViewPlayer;

    @AfterViews
    void afterView(){
        setAdapter();
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
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewPlayer.setLayoutManager(mLayoutManager);
        mPlayerRecyclerAdapter = new PlayerRecyclerAdapter(mPlayers, mContext);
        mRecyclerViewPlayer.setAdapter(mPlayerRecyclerAdapter);
    }
}
