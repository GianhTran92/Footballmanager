package vn.asiantech.intership.myapplication.dialog.NewPlayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.Player;

/**
 * Created by igianhtran on 27/10/2015.
 */
public class PlayerFreeRecyclerViewAdapter extends
        RecyclerView.Adapter<PlayerFreeRecyclerViewAdapter.PlayerFreeRecyclerViewHolder> {

    public interface OnChoicePlayerListener{
        void getPlayerSuccess(Player player);
    }

    OnChoicePlayerListener delegate;

    List<Player> mPlayers;

    public PlayerFreeRecyclerViewAdapter(List<Player> listData) {
        this.mPlayers = listData;
    }

    @Override
    public PlayerFreeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_player_recycler, parent, false);
        return new PlayerFreeRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerFreeRecyclerViewHolder holder, int i) {
        holder.tvPlayerName.setText(mPlayers.get(i).getName().toString());
        //TODO get old from birthday
        holder.tvPlayerOld.setText(String.valueOf(mPlayers.get(i).getNumber()));
        holder.tvPlayerPosition.setText(mPlayers.get(i).getPositison().name().toString());
        holder.imgViewPlayerDetail.setImageResource(R.drawable.ic_add_circle_black_18dp);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayerFreeRecyclerViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImgViewAvatar;
        TextView tvPlayerName;
        TextView tvPlayerOld;
        ImageView imgViewPlayerDetail;
        TextView tvPlayerPosition;
        public PlayerFreeRecyclerViewHolder (View itemView){
            super(itemView);
            circleImgViewAvatar = (CircleImageView) itemView.findViewById(R.id.circleImgViewAvatar);
            tvPlayerName = (TextView) itemView.findViewById(R.id.tvPlayerName);
            tvPlayerOld = (TextView) itemView.findViewById(R.id.tvPlayerOld);
            tvPlayerPosition = (TextView) itemView.findViewById(R.id.tvPlayerPosition);
            imgViewPlayerDetail = (ImageView) itemView.findViewById(R.id.imgViewPlayerDetail);
            imgViewPlayerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delegate.getPlayerSuccess(mPlayers.get(getPosition()));
                }
            });
        }
    }
}
