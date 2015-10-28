package vn.asiantech.intership.myapplication.ui.league;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.League;
import vn.asiantech.intership.myapplication.ui.FootballTeam.FootballTeamActivity_;

/**
 * Created by igianhtran on 20/10/2015.
 */
public class LeagueRecyclerAdapter extends
        RecyclerView.Adapter<LeagueRecyclerAdapter.LeagueRecyclerHolder> {
    List<League> mLeagues = new ArrayList<>();
    LeagueActivity mLeagueActivity;

    public interface OnCallFootballTeamActivityListener {
        void onCall(League league);
    }

    OnCallFootballTeamActivityListener mOnCallFootballTeamActivityListener;

    public LeagueRecyclerAdapter(List<League> listData, LeagueActivity leagueActivity) {
        this.mLeagues = listData;
        this.mLeagueActivity = leagueActivity;
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
        holder.circleImageViewLeagueLogo.setImageResource(R.drawable.img_league);
        holder.tvLeagueName.setText(mLeagues.get(position).getName());
        holder.edtLeagueName.setVisibility(View.INVISIBLE);
        mOnCallFootballTeamActivityListener = mLeagueActivity;

    }

    public class LeagueRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView circleImageViewLeagueLogo;
        EditText edtLeagueName;
        ImageView imgViewEditLeague;
        ImageView imgViewSubmitEditLeague;
        ImageView imgViewCancelEditLeague;
        TextView tvLeagueName;

        public LeagueRecyclerHolder(View itemView) {
            super(itemView);
            circleImageViewLeagueLogo =
                    (CircleImageView) itemView.findViewById(R.id.circleImgViewLeagueLogoItem);
            edtLeagueName = (EditText) itemView.findViewById(R.id.edtLeagueName);
            tvLeagueName = (TextView) itemView.findViewById(R.id.tvLeagueName);
            imgViewSubmitEditLeague = (ImageView) itemView.findViewById(R.id.imgViewSubmitEditLeague);
            imgViewSubmitEditLeague.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgViewSubmitEditLeague.startAnimation(AnimationUtils.loadAnimation(mLeagueActivity,
                            R.anim.abc_popup_enter));
                    if (!edtLeagueName.getText().toString().equals("")) {
                        mLeagueActivity.editLeague(mLeagues.get(getPosition()),
                                edtLeagueName.getText().toString(),
                                "img_league");
                        enableEdit();

                    } else {
                        edtLeagueName.setError(mLeagueActivity.getString(R.string.error_field_not_be_empty1));
                    }

                }
            });
            imgViewCancelEditLeague = (ImageView) itemView.findViewById(R.id.imgViewCancelEditLeague);
            imgViewCancelEditLeague.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgViewCancelEditLeague.startAnimation(AnimationUtils.loadAnimation(mLeagueActivity,
                            R.anim.abc_popup_enter));
                    enableEdit();
                    updateList(mLeagues);
                }
            });
            imgViewEditLeague = (ImageView) itemView.findViewById(R.id.imgViewEditLeague);
            imgViewEditLeague.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgViewEditLeague.startAnimation(AnimationUtils.loadAnimation(mLeagueActivity,
                            R.anim.abc_popup_enter));
                    disableEdit();
                }
            });
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    /**
                     *  Using method remove of list to replace method update list in data
                     */
                    League league = mLeagues.get(getPosition());
                    showDialogConfirmDelete(league);

                    return false;
                }
            });

        }

        public void showDialogConfirmDelete(final League league) {
            final Dialog dialog = new Dialog(mLeagueActivity);
            dialog.setContentView(R.layout.dialog_confirm_delete);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.setTitle("are you sure to delete?");

            Button btnSubmitDelete = (Button) dialog.findViewById(R.id.btnSubmitDelete);
            Button btnCancelDelete = (Button) dialog.findViewById(R.id.btnCancelDelete);

            btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLeagueActivity.deleteLeague(league);
                    dialog.dismiss();
                }
            });
            btnCancelDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }

        @Override
        public void onClick(View v) {
            mOnCallFootballTeamActivityListener.onCall(mLeagues.get(getPosition()));
        }

        public void enableEdit() {
            imgViewSubmitEditLeague.setVisibility(View.INVISIBLE);
            imgViewCancelEditLeague.setVisibility(View.INVISIBLE);
            imgViewEditLeague.setVisibility(View.VISIBLE);
            tvLeagueName.setVisibility(View.VISIBLE);
        }

        public void disableEdit() {
            imgViewSubmitEditLeague.setVisibility(View.VISIBLE);
            imgViewCancelEditLeague.setVisibility(View.VISIBLE);
            imgViewEditLeague.setVisibility(View.INVISIBLE);
            edtLeagueName.setFocusable(true);
            tvLeagueName.setVisibility(View.INVISIBLE);
            edtLeagueName.setVisibility(View.VISIBLE);
            edtLeagueName.setText(tvLeagueName.getText().toString());
        }


    }


}
