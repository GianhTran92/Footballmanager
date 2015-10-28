package vn.asiantech.intership.myapplication.dialog.dialog.CoachInFreeZone;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * Created by igianhtran on 28/10/2015.
 */
public class CoachInFreeZoneListDialog extends Dialog implements LoadListCoachInFreeZoneInterface {
    RecyclerView mRecyclerViewCoachInFreeZone;

    Context mContext;

    public CoachInFreeZoneListDialog(Context context){
        super(context);
        this.mContext = context;
        setContentView(R.layout.dialog_list_coach_in_free_zone);
        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mRecyclerViewCoachInFreeZone = (RecyclerView) findViewById(R.id.recyclerViewCoachInFreeZone);
        setRecyclerAdapter();
    }

    @Override
    public void loadSuccess(List<Coach> coaches) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewCoachInFreeZone.setLayoutManager(mLayoutManager);
        RecyclerViewCoachInFreeZoneAdapter recyclerViewCoachInFreeZoneAdapter = new RecyclerViewCoachInFreeZoneAdapter(coaches);
        mRecyclerViewCoachInFreeZone.setAdapter(recyclerViewCoachInFreeZoneAdapter);
    }

    /**
     * Using AsyncTask to load data of list coach in free zone
     */
    public class LoadListCoachInFreeZone extends AsyncTask<Void,Void,List<Coach>> {
        LoadListCoachInFreeZoneInterface delegate = null;
        @Override
        protected List<Coach> doInBackground(Void... params) {
            List<Coach> coaches = Coach.find(Coach.class,"teamId=",String.valueOf(-1l));
            return coaches;
        }

        @Override
        protected void onPostExecute(List<Coach> coaches) {
            super.onPostExecute(coaches);
            delegate.loadSuccess(coaches);
        }
    }

    public void setRecyclerAdapter() {
        LoadListCoachInFreeZone loadListCoachInFreeZone = new LoadListCoachInFreeZone();
        loadListCoachInFreeZone.delegate = this;
        loadListCoachInFreeZone.execute();
    }
}
