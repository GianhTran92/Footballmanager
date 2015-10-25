package vn.asiantech.intership.myapplication.ui.player;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Coach;

/**
 * A simple {@link Fragment} subclass.
 * <p/>
 * create by gianhtran on 2015/10/22
 */
@EFragment(R.layout.fragment_edit_coach)
public class EditCoachFragment extends BaseFragment {
    @ViewById(R.id.edtEditCoachName)
    EditText mEdtEditCoachName;

    @ViewById(R.id.edtEditCoachBirthday)
    EditText mEdtEditCoachBirthday;

    @Click(R.id.imgViewCancelEditCoach)
    void doCancel() {
        replaceFragment(R.id.rlContentCoachInfor,
                CoachFragment_.builder().build()
                , "CoachFragment", null);

    }

    @Click(R.id.imgViewSubmitEditCoach)
    void doSubmit() {

    }

    @AfterViews
    void afterView() {


    }


}
