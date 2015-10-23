package vn.asiantech.intership.myapplication.ui.player;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_coach_detail)
public class CoachDetailFragment extends BaseFragment {

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

    }

}
