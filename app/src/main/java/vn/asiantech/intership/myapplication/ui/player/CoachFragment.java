package vn.asiantech.intership.myapplication.ui.player;


import android.os.AsyncTask;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.dialog.NewCoach.NewCoachDialog;
import vn.asiantech.intership.myapplication.dialog.dialog.CoachInFreeZone.CoachInFreeZoneListDialog;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_coach)
public class CoachFragment extends BaseFragment implements LoadCoachAsyncResponse {
    Coach mCoach;

    PlayerActivity mPlayerActivity;

    public interface OnSendCoachData {
        void processFinish(Coach coach);
    }

    OnSendCoachData delegate;

    Boolean mIsHaveCoach = true;

    @ViewById(R.id.imgViewChangeCoach)
    ImageView mImgViewChangeCoach;

    @ViewById(R.id.imgViewNewCoach)
    ImageView mImgViewNewCoach;

    @ViewById(R.id.imgViewCoachDetail)
    ImageView mImgViewCoachDetail;

    @ViewById(R.id.circleImgViewCoachAvatar)
    CircleImageView mCircleImgViewCoachAvatar;

    @ViewById(R.id.tvCoachName)
    TextView mTvCoachName;

    @Click(R.id.imgViewChangeCoach)
    void showChoiceCoachDialog() {
        final CoachInFreeZoneListDialog coachInFreeZoneListDialog = new CoachInFreeZoneListDialog(getActivity());
        coachInFreeZoneListDialog.show();
    }

    @Click(R.id.imgViewNewCoach)
    void showNewCoachDialog() {
        final NewCoachDialog newCoachDialog = new NewCoachDialog(getActivity());
        newCoachDialog.show();
        Button mBtnSubmitAddCoach = (Button) newCoachDialog.findViewById(R.id.btnSubmitAddCoach);
        Button mBtnCancelAddCoach = (Button) newCoachDialog.findViewById(R.id.btnCancelAddCoach);

        mBtnCancelAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCoachDialog.dismiss();
            }
        });

        mBtnSubmitAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newCoachDialog.getName().equals("")) {
                    SaveCoach saveCoach = new SaveCoach(newCoachDialog.getName(),
                            newCoachDialog.getBirth(),
                            newCoachDialog.getCountry(),
                            mPlayerActivity.getFootballTeamId());
                    saveCoach.execute();
                    newCoachDialog.dismiss();
                    loadCoachToUI();
                } else {
                    newCoachDialog.setError();
                }
            }
        });

    }

    @Click(R.id.imgViewCoachDetail)
    void showDetailCoach() {
        delegate.processFinish(mCoach);
        replaceFragment(R.id.rlContentCoachInfor, CoachDetailFragment_.builder().build(), "CoachDetailFragment", null);
    }

    @AfterViews
    void afterView() {
        delegate = (PlayerActivity) getActivity();
        loadCoachToUI();
    }

    public void loadCoachToUI(){
        mPlayerActivity = (PlayerActivity) getActivity();
        LoadDataByFootBallTeamId loadDataByFootBallTeamId = new LoadDataByFootBallTeamId(mPlayerActivity.getFootballTeamId());
        loadDataByFootBallTeamId.delegate = this;
        loadDataByFootBallTeamId.execute();
    }

    @Override
    public void processFinish(Coach output) {
        mCoach = output;
        if (mCoach.getName().toString().equals("no")) {
            mIsHaveCoach = false;
            mTvCoachName.setText("this team have no coach yet");
            mImgViewCoachDetail.setVisibility(View.GONE);
            mCircleImgViewCoachAvatar.setVisibility(View.GONE);
            mImgViewChangeCoach.setVisibility(View.GONE);
            mImgViewNewCoach.setVisibility(View.VISIBLE);

        } else {
            mIsHaveCoach = true;
            mTvCoachName.setText(mCoach.getName());
            mImgViewCoachDetail.setVisibility(View.VISIBLE);
            mCircleImgViewCoachAvatar.setVisibility(View.VISIBLE);
            mImgViewChangeCoach.setVisibility(View.VISIBLE);
            mImgViewNewCoach.setVisibility(View.GONE);
        }

    }

    /**
     * Using AsyncTask to load data
     */
    public class LoadDataByFootBallTeamId extends AsyncTask<Void, Void, List<Coach>> {
        long mFootballTeamId;
        public LoadCoachAsyncResponse delegate = null;


        public LoadDataByFootBallTeamId(long id) {
            this.mFootballTeamId = id;
        }

        @Override
        protected List<Coach> doInBackground(Void... params) {
            List<Coach> coaches = Coach.find(Coach.class, "teamId=?", String.valueOf(mFootballTeamId));
            return coaches;
        }

        @Override
        protected void onPostExecute(List<Coach> coaches) {
            super.onPostExecute(coaches);
            if (coaches.size() == 0) {
                delegate.processFinish(new Coach("no", null, 0l, null, null));
            } else {
                delegate.processFinish(coaches.get(0));
            }

        }
    }

    /**
     * Using AsyncTask to save new Coach
     */
    public class SaveCoach extends AsyncTask<Void, Void, Void> {
        String mName;
        String mBirth;
        String mCountry;
        long mTeamId;

        public SaveCoach(String name, String birth, String country, long id) {
            this.mName = name;
            this.mBirth = birth;
            this.mCountry = country;
            this.mTeamId = id;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Coach coach = new Coach(mName, mBirth, mTeamId, mCountry, "img_coach");
            coach.save();
            return null;
        }
    }
}
