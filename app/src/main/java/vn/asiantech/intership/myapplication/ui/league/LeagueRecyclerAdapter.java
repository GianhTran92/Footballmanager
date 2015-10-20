package vn.asiantech.intership.myapplication.ui.league;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.League;

/**
 * Created by igianhtran on 20/10/2015.
 */
public class LeagueRecyclerAdapter extends RecyclerView.Adapter<LeagueRecyclerAdapter.LeagueRecyclerHolder> {
    List<League> mLeagues = new ArrayList<>();
    public LeagueRecyclerAdapter (List<League> listData) {
        this.mLeagues = listData;
    }

    @Override
    public LeagueRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.activity_league,parent,false);
        return new LeagueRecyclerHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mLeagues.size();
    }

    @Override
    public void onBindViewHolder(LeagueRecyclerHolder holder, int position) {

    }

    public class LeagueRecyclerHolder extends RecyclerView.ViewHolder {
        public LeagueRecyclerHolder(View itemView){
            super(itemView);
        }
    }
}
