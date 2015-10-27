package vn.asiantech.intership.myapplication.ui.player;


import android.os.AsyncTask;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_coach)
public class CoachFragment extends BaseFragment implements LoadCoachAsyncResponse {

    public interface OnSendCoachData {
        void processFinish(Coach coach);
    }

    OnSendCoachData delegate;

    @ViewById(R.id.circleImgViewCoachAvatar)
    CircleImageView mCircleImgViewCoachAvatar;
    @ViewById(R.id.tvCoachName)
    TextView mTvCoachName;
    Coach mCoach;

    @Click(R.id.imgViewCoachDetail)
    void showDetailCoach() {
        delegate.processFinish(mCoach);
        replaceFragment(R.id.rlContentCoachInfor, CoachDetailFragment_.builder().build(), "CoachDetailFragment", null);
    }

    @AfterViews
    void afterView() {
        delegate = (PlayerActivity) getActivity();
        PlayerActivity playerActivity = (PlayerActivity) getActivity();
        LoadDataByFootBallTeamId loadDataByFootBallTeamId = new LoadDataByFootBallTeamId(playerActivity.getFootballTeamId());
        loadDataByFootBallTeamId.delegate = this;
        loadDataByFootBallTeamId.execute();
    }

    @Override
    public void processFinish(Coach output) {
        mCoach = output;
        mTvCoachName.setText(mCoach.getName());
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
            delegate.processFinish(coaches.get(0));
        }
    }

    public void sendDataUsingBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Common.KEY_COACH_NAME, mCoach.getName());
        bundle.putString(Common.KEY_COACH_BIRTHDAY, mCoach.getBirthday());
        bundle.putLong(Common.KEY_COACH_TEAMID, mCoach.getTeamId());
        bundle.putString(Common.KEY_COACH_AVATAR, mCoach.getAvatar());
        bundle.putString(Common.KEY_COACH_COUNTRY, mCoach.getCountry());
        CoachDetailFragment_.builder().build().setArguments(bundle);
    }

}
