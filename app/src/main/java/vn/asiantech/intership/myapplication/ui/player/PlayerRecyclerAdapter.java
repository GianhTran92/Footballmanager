package vn.asiantech.intership.myapplication.ui.player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.Player;

/**
 * Created by igianhtran on 21/10/2015.
 * Edited by gianhtran on 22/10/2015
 */
public class PlayerRecyclerAdapter extends
        RecyclerView.Adapter<PlayerRecyclerAdapter.PlayerRecyclerHolder> {
    public interface OnCallPlayerDetail {
        void processSuccess(long playerId);
    }

    OnCallPlayerDetail delegate;

    List<Player> mPlayers = new ArrayList<>();

    Context mContext;

    ListPlayerFragment mListPlayerFragment;

    public PlayerRecyclerAdapter(List<Player> listData,
                                 Context context,
                                 ListPlayerFragment listPlayerFragment) {
        this.mPlayers = listData;
        this.mContext = context;
        this.mListPlayerFragment = listPlayerFragment;
    }

    public void updateList(List<Player> listData) {
        this.mPlayers = listData;
        notifyDataSetChanged();
    }

    @Override
    public PlayerRecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_player_recycler, viewGroup, false);
        return new PlayerRecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerRecyclerHolder holder, int i) {
        holder.tvPlayerName.setText(mPlayers.get(i).getName().toString());
        holder.tvPlayerOld.setText(String.valueOf(mPlayers.get(i).getNumber()));
        holder.tvPlayerPosition.setText(mPlayers.get(i).getPositison().name().toString());
        delegate = mListPlayerFragment;
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayerRecyclerHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImgViewAvatar;
        TextView tvPlayerName;
        TextView tvPlayerOld;
        ImageView imgViewPlayerDetail;
        TextView tvPlayerPosition;

        public PlayerRecyclerHolder(View itemView) {
            super(itemView);
            circleImgViewAvatar = (CircleImageView) itemView.findViewById(R.id.circleImgViewAvatar);
            tvPlayerName = (TextView) itemView.findViewById(R.id.tvPlayerName);
            tvPlayerOld = (TextView) itemView.findViewById(R.id.tvPlayerOld);
            tvPlayerPosition = (TextView) itemView.findViewById(R.id.tvPlayerPosition);
            imgViewPlayerDetail = (ImageView) itemView.findViewById(R.id.imgViewPlayerDetail);
            imgViewPlayerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgViewPlayerDetail.startAnimation(AnimationUtils.loadAnimation(mListPlayerFragment.getActivity(),
                            R.anim.abc_popup_enter));
                    delegate.processSuccess(mPlayers.get(getPosition()).getId());
                }
            });

        }
    }
}
