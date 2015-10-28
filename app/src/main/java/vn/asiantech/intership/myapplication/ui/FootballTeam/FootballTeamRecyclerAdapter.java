package vn.asiantech.intership.myapplication.ui.FootballTeam;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.ui.player.PlayerActivity_;

/**
 * Created by igianhtran on 20/10/2015.
 * Edited by gianhtran on 23/10/2015
 */
public class FootballTeamRecyclerAdapter extends
        RecyclerView.Adapter<FootballTeamRecyclerAdapter.FootballTeamRecyclerHolder> {
    List<FootballTeam> mFootballTeams = new ArrayList<>();

    FootballTeamActivity mFootballTeamActivity;

    OnCallPlayerActivity mOnCallPlayerActivity;

    public interface OnCallPlayerActivity {
        void onCall(FootballTeam footballTeam);
    }

    public FootballTeamRecyclerAdapter(List<FootballTeam> listData,
                                       FootballTeamActivity footballTeamActivity) {
        this.mFootballTeamActivity = footballTeamActivity;
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
        mOnCallPlayerActivity = mFootballTeamActivity;
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

        EditText edtDescriptionFootballTeam;
        EditText edtFootballTeamName;

        public FootballTeamRecyclerHolder(View itemView) {
            super(itemView);
            circleImgViewFootballTeamLogo =
                    (CircleImageView) itemView.findViewById(R.id.circleImgViewFootballTeamLogo);
            tvFootballTeamName = (TextView) itemView.findViewById(R.id.tvFootballTeamName);
            tvDescriptionFootballTeam =
                    (TextView) itemView.findViewById(R.id.tvDescriptionFootballTeam);
            imgViewEditFootballTeam = (ImageView) itemView.findViewById(R.id.imgViewEditFootballTeam);
            edtDescriptionFootballTeam = (EditText) itemView.findViewById(R.id.edtDescriptionFootballTeam);
            edtFootballTeamName = (EditText) itemView.findViewById(R.id.edtFootballTeamName);
            imgViewEditFootballTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgViewEditFootballTeam.startAnimation(AnimationUtils.loadAnimation(mFootballTeamActivity,
                            R.anim.rotate_anim));
                    showDialogEditFootballTeam();

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDialogConfirmDelete(mFootballTeams.get(getPosition()));
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnCallPlayerActivity.onCall(mFootballTeams.get(getPosition()));
                }
            });
        }

        public void showDialogEditFootballTeam() {
            final Dialog dialog = new Dialog(mFootballTeamActivity);
            dialog.setContentView(R.layout.dialog_new_football_team);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            final EditText edtAddFootballTeamName =
                    (EditText) dialog.findViewById(R.id.edtAddFootballTeamName);
            final EditText edtAddDescription =
                    (EditText) dialog.findViewById(R.id.edtAddDescription);
            Button btnCancelAddFootballTeam =
                    (Button) dialog.findViewById(R.id.btnCancelAddFootballTeam);
            Button btnSubmitAddFootballTeam =
                    (Button) dialog.findViewById(R.id.btnSubmitAddFootballTeam);

            edtAddFootballTeamName.setText(tvFootballTeamName.getText());
            edtAddDescription.setText(tvDescriptionFootballTeam.getText());

            btnSubmitAddFootballTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!edtAddFootballTeamName.getText().toString().equals("")) {
                        mFootballTeamActivity.editFootballTeam(mFootballTeams.get(getPosition()),
                                edtAddFootballTeamName.getText().toString(),
                                edtAddDescription.getText().toString(),
                                "img_mu");
                        dialog.dismiss();
                    } else {
                        edtAddFootballTeamName.setError(mFootballTeamActivity.getString(R.string.error_field_not_be_empty));
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

        public void showDialogConfirmDelete(final FootballTeam footballTeam) {
            final Dialog dialog = new Dialog(mFootballTeamActivity);
            dialog.setContentView(R.layout.dialog_confirm_delete);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.setTitle("are you sure to delete?");

            Button btnSubmitDelete = (Button) dialog.findViewById(R.id.btnSubmitDelete);
            Button btnCancelDelete = (Button) dialog.findViewById(R.id.btnCancelDelete);

            btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFootballTeamActivity.deleteFootballTeam(footballTeam);
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
    }
}
