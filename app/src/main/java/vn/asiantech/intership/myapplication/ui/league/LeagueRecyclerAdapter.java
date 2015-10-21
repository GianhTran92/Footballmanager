package vn.asiantech.intership.myapplication.ui.league;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.League;

/**
 * Created by igianhtran on 20/10/2015.
 */
public class LeagueRecyclerAdapter extends RecyclerView.Adapter<LeagueRecyclerAdapter.LeagueRecyclerHolder> {
    List<League> mLeagues = new ArrayList<>();
    Context context;

    public LeagueRecyclerAdapter(List<League> listData, Context context) {
        this.mLeagues = listData;
        this.context = context;
    }

    public void updateList(List<League> listData) {
        this.mLeagues = listData;
        notifyDataSetChanged();
    }

    @Override
    public LeagueRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_league_recycler, parent, false);
        return new LeagueRecyclerHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mLeagues.size();
    }

    @Override
    public void onBindViewHolder(LeagueRecyclerHolder holder, int position) {
        // TODO upload image for logo
        holder.circleImageViewLeagueLogo.setImageResource(R.drawable.img_mu_logo);
        holder.tvLeagueName.setText(mLeagues.get(position).getName());
    }

    public class LeagueRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView circleImageViewLeagueLogo;
        TextView tvLeagueName;
        ImageView imgViewLeagueDetail;

        public LeagueRecyclerHolder(View itemView) {
            super(itemView);
            circleImageViewLeagueLogo = (CircleImageView) itemView.findViewById(R.id.circleImgViewLeagueLogoItem);
            tvLeagueName = (TextView) itemView.findViewById(R.id.tvLeagueName);
            imgViewLeagueDetail = (ImageView) itemView.findViewById(R.id.imgViewLeagueDetail);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLeagues.remove(getPosition());
                    updateList(mLeagues);
                    return false;
                }
            });

        }

        @Override
        public void onClick(View v) {
            // TODO used interface to move another activity

        }
    }
}
