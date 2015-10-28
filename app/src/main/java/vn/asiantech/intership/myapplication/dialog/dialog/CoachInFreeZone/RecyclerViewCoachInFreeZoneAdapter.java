package vn.asiantech.intership.myapplication.dialog.dialog.CoachInFreeZone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * Created by igianhtran on 28/10/2015.
 */
public class RecyclerViewCoachInFreeZoneAdapter extends RecyclerView.Adapter<RecyclerViewCoachInFreeZoneAdapter.RecyclerViewCoachInFreeZoneViewHolder> {
    List<Coach> mCoaches;

    public RecyclerViewCoachInFreeZoneAdapter(List<Coach> coaches){
        this.mCoaches = coaches;
    }

    @Override
    public RecyclerViewCoachInFreeZoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_player_recycler, parent, false);
        return new RecyclerViewCoachInFreeZoneViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mCoaches.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewCoachInFreeZoneViewHolder holder, int position) {
        holder.mTvCoachInFreeZoneName.setText(mCoaches.get(position).getName());
        holder.mTvCoachInFreeZoneBirthday.setText(mCoaches.get(position).getBirthday());
        holder.mTvCoachInFreeZoneCountry.setText(mCoaches.get(position).getCountry());

    }

    public class RecyclerViewCoachInFreeZoneViewHolder extends RecyclerView.ViewHolder {
        TextView mTvCoachInFreeZoneName;
        TextView mTvCoachInFreeZoneBirthday;
        TextView mTvCoachInFreeZoneCountry;
        ImageView mImgViewAddCoach;
        public RecyclerViewCoachInFreeZoneViewHolder(View itemView) {
            super(itemView);
            mTvCoachInFreeZoneName = (TextView) itemView.findViewById(R.id.tvCoachInFreeZoneName);
            mTvCoachInFreeZoneCountry = (TextView) itemView.findViewById(R.id.tvCoachInFreeZoneName);
            mTvCoachInFreeZoneBirthday = (TextView) itemView.findViewById(R.id.tvCoachInFreeZoneName);

            mImgViewAddCoach = (ImageView) itemView.findViewById(R.id.imgViewAddCoach);

            mImgViewAddCoach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
