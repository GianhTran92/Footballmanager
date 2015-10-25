package vn.asiantech.intership.myapplication.ui.player;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * A simple {@link Fragment} subclass.
 *
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_coach_detail)
public class CoachDetailFragment extends BaseFragment {
    Coach mCoach;

    @ViewById(R.id.tvCoachNameDetail)
    TextView mTvCoachNameDetail;

    @ViewById(R.id.tvCoachBirthday)
    TextView mTvCoachBirthday;

    @ViewById(R.id.tvCoachCountry)
    TextView mTvCoachCountry;

    @Click(R.id.imgViewBackFromCoachDetailFragment)
    void doBack() {
        replaceFragment(R.id.rlContentCoachInfor, CoachFragment_.builder().build(), "CoachFragment", null);
    }

    @Click(R.id.imgViewEditCoach)
    void doEdit(){
        replaceFragment(R.id.rlContentCoachInfor, EditCoachFragment_.builder().build(), "EditCoachFragment", null);

    }

    @AfterViews
    void afterView() {
//        mCoach = new Coach(getArguments()
//                .getString(Common.KEY_COACH_NAME),
//                getArguments().getString(Common.KEY_COACH_BIRTHDAY),
//                getArguments().getLong(Common.KEY_FOOTBALL_TEAM_ID),
//                getArguments().getString(Common.KEY_COACH_COUNTRY),
//                getArguments().getString(Common.KEY_COACH_AVATAR));
//        mTvCoachNameDetail.setText("haha");
//        mTvCoachBirthday.setText(mCoach.getBirthday());
//        mTvCoachCountry.setText(mCoach.getCountry());
    }

}
