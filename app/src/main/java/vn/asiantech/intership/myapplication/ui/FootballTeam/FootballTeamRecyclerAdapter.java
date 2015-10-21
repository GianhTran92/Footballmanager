package vn.asiantech.intership.myapplication.ui.FootballTeam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.ui.player.PlayerActivity_;

/**
 * Created by igianhtran on 20/10/2015.
 */
public class FootballTeamRecyclerAdapter extends RecyclerView.Adapter<FootballTeamRecyclerAdapter.FootballTeamRecyclerHolder> {
    List<FootballTeam> mFootballTeams = new ArrayList<>();
    Context mContext;

    public FootballTeamRecyclerAdapter(List<FootballTeam> listData, Context context) {
        this.mContext = context;
        this.mFootballTeams = listData;
    }

    public void updateList(List<FootballTeam> listData) {
        this.mFootballTeams = listData;
        notifyDataSetChanged();
    }

    @Override
    public FootballTeamRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_football_team_recycler, parent, false);
        return new FootballTeamRecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FootballTeamRecyclerHolder holder, int position) {
        holder.tvFootballTeamName.setText(mFootballTeams.get(position).getName());
        holder.tvDescriptionFootballTeam.setText(mFootballTeams.get(position).getDescripstion());
        holder.edtFootballTeamName.setVisibility(View.INVISIBLE);
        holder.edtDescriptionFootballTeam.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mFootballTeams.size();
    }

    public class FootballTeamRecyclerHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImgViewFootballTeamLogo;
        TextView tvFootballTeamName;
        TextView tvDescriptionFootballTeam;
        ImageView imgViewEditFootballTeam;
        ImageView imgViewCancelEditFootballTeam;
        ImageView imgViewSubmitEditFootballTeam;
        EditText edtDescriptionFootballTeam;
        EditText edtFootballTeamName;

        public FootballTeamRecyclerHolder(View itemView) {
            super(itemView);
            circleImgViewFootballTeamLogo = (CircleImageView) itemView.findViewById(R.id.circleImgViewFootballTeamLogo);
            tvFootballTeamName = (TextView) itemView.findViewById(R.id.tvFootballTeamName);
            tvDescriptionFootballTeam = (TextView) itemView.findViewById(R.id.tvDescriptionFootballTeam);
            imgViewEditFootballTeam = (ImageView) itemView.findViewById(R.id.imgViewEditFootballTeam);
            edtDescriptionFootballTeam = (EditText) itemView.findViewById(R.id.edtDescriptionFootballTeam);
            edtFootballTeamName = (EditText) itemView.findViewById(R.id.edtFootballTeamName);
            imgViewEditFootballTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvFootballTeamName.setVisibility(View.INVISIBLE);
                    tvDescriptionFootballTeam.setVisibility(View.INVISIBLE);
                    edtFootballTeamName.setVisibility(View.VISIBLE);
                    edtDescriptionFootballTeam.setVisibility(View.VISIBLE);
                    imgViewSubmitEditFootballTeam.setVisibility(View.VISIBLE);
                    imgViewCancelEditFootballTeam.setVisibility(View.VISIBLE);
                    imgViewEditFootballTeam.setVisibility(View.INVISIBLE);
                }
            });
            imgViewSubmitEditFootballTeam = (ImageView) itemView.findViewById(R.id.imgViewSubmitEditFootballTeam);
            imgViewSubmitEditFootballTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!edtFootballTeamName.getText().toString().equals("")) {
                        mFootballTeams.get(getPosition()).setName(edtFootballTeamName.getText().toString());
                        mFootballTeams.get(getPosition()).setDescripstion(edtDescriptionFootballTeam.getText().toString());
                        updateList(mFootballTeams);

                    } else {
                        edtFootballTeamName.setError(mContext.getString(R.string.error_field_not_be_empty1));
                    }

                }
            });
            imgViewCancelEditFootballTeam = (ImageView) itemView.findViewById(R.id.imgViewCancelEditFootballTeam);
            imgViewCancelEditFootballTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateList(mFootballTeams);

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mFootballTeams.remove(getPosition());
                    updateList(mFootballTeams);
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO used interface to move another activity
                    PlayerActivity_.intent(mContext)
                            .extra(Common.KEY_FOOTBALL_TEAM_NAME,mFootballTeams.get(getPosition()).getName())
                            .start();
                }
            });
        }

    }

}
