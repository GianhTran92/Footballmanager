package vn.asiantech.intership.myapplication.dialog.NewCoach;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.dialog.dialog.CoachInFreeZone.CoachInFreeZoneListDialog;

/**
 * Created by igianhtran on 28/10/2015.
 */
public class NewCoachDialog extends Dialog {
    Button mBtnChoiceCoach;

    Context mContext;

    String mCoachCountry;

    EditText mEdtNewCoachName;
    EditText mEdtNewCoachBirthday;

    Spinner mSpnNewCoachCountry;

    public NewCoachDialog(Context context) {
        super(context);
        this.mContext = context;
        setContentView(R.layout.dialog_new_coach);
        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        mBtnChoiceCoach = (Button) findViewById(R.id.btnChoiceCoach);

        mEdtNewCoachName = (EditText) findViewById(R.id.edtNewCoachName);
        mEdtNewCoachBirthday = (EditText) findViewById(R.id.edtNewCoachBirthday);

        mSpnNewCoachCountry = (Spinner) findViewById(R.id.spnNewCoachCountry);
        setSpinerAdapter();

        mBtnChoiceCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoachInFreeZoneListDialog coachInFreeZoneListDialog = new CoachInFreeZoneListDialog(mContext);
                coachInFreeZoneListDialog.show();
            }
        });
    }

    public void setSpinerAdapter() {
        ArrayAdapter<String> spinerCountryAdapter = new ArrayAdapter<>
                (
                        mContext,
                        android.R.layout.simple_spinner_item,
                        Common.ARR
                );
        spinerCountryAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnNewCoachCountry.setAdapter(spinerCountryAdapter);
        mSpnNewCoachCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public String getName() {
        return mEdtNewCoachName.getText().toString();
    }

    public String getBirth() {
        return mEdtNewCoachBirthday.getText().toString();
    }

    public String getCountry() {
        return mCoachCountry;
    }

    public void setError() {
        mEdtNewCoachName.setError("Not null");
    }
}
