package vn.asiantech.intership.myapplication.ui.player;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
 * <p/>
 * create by gianhtran on 2015/10/22
 */
@EFragment(R.layout.fragment_edit_coach)
public class EditCoachFragment extends BaseFragment {
    Coach mCoach;

    String mCoachCountry;

    @ViewById(R.id.spnCoachCountry)
    Spinner mSpnCoachCountry;

    @ViewById(R.id.edtEditCoachName)
    EditText mEdtEditCoachName;

    @ViewById(R.id.edtEditCoachBirthday)
    EditText mEdtEditCoachBirthday;

    @Click(R.id.imgViewCancelEditCoach)
    void doCancel() {
        replaceFragment(R.id.rlContentCoachInfor,
                CoachDetailFragment_.builder().build()
                , "CoachFragment_", null);
    }

    @Click(R.id.imgViewSubmitEditCoach)
    void doSubmit() {
        if (!mEdtEditCoachName.getText().equals("")) {
            SaveCoach saveCoach = new SaveCoach(mCoach,
                    mEdtEditCoachName.getText().toString(),
                    mEdtEditCoachBirthday.getText().toString(),
                    mCoachCountry);
            saveCoach.execute();
            replaceFragment(R.id.rlContentCoachInfor,
                    CoachDetailFragment_.builder().build()
                    , "CoachDetailFragment_", null);
        } else {
            //TODO using @String
            mEdtEditCoachName.setError("erer");
        }
    }

    @AfterViews
    void afterView() {
        mCoach = getCoach();
        setSpinerAdapter();
        mEdtEditCoachName.setText(mCoach.getName());
        mEdtEditCoachBirthday.setText(mCoach.getBirthday());
    }

    /**
     * get Coach from Activity content using method get
     * @return coach object
     */
    public Coach getCoach() {
        PlayerActivity playerActivity = (PlayerActivity) getActivity();
        return playerActivity.getCoach();
    }

    /**
     * set adapter for spiner country and position
     */
    public void setSpinerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (
                        getActivity(),
                        android.R.layout.simple_spinner_item,
                        Common.ARR
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnCoachCountry.setAdapter(adapter);
        mSpnCoachCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCoachCountry = Common.ARR[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCoachCountry = Common.ARR[0];
            }
        });
    }

    /**
     * Using AsyncTask to save Coach
     */
    public class SaveCoach extends AsyncTask<Void, Void, Void> {
        Coach mCoach;

        String mName;

        String mBirth;

        String mCountry;

        public SaveCoach(Coach coach, String name, String birth, String country) {
            this.mCoach = coach;
            this.mName = name;
            this.mBirth = birth;
            this.mCountry = country;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mCoach.setName(mName);
            mCoach.setBirthday(mBirth);
            mCoach.setCountry(mCountry);
            mCoach.save();
            return null;
        }
    }


}
