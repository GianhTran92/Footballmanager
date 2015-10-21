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

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_coach)
public class CoachFragment extends BaseFragment {
    @ViewById(R.id.circleImgViewCoachAvatar)
    CircleImageView mCircleImgViewCoachAvatar;
    @ViewById(R.id.tvCoachName)
    TextView mTvCoachName;

    @Click(R.id.imgViewCoachDetail)
    void showDetailCoach() {
        replaceFragment(R.id.rlContentCoachInfor,CoachDetailFragment_.builder().build(),"CoachDetailFragment",null);
    }

    @AfterViews
    void afterView() {
        getCoach();
    }
    public void getCoach(){
        Coach coach = new Coach("Alex Ferguson","10/10/2010",1l,"USB",null);
        mTvCoachName.setText(coach.getName());
    }


}
