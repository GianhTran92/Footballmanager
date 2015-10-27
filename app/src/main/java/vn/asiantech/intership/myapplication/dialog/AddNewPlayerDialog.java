package vn.asiantech.intership.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;

/**
 * Created by igianhtran on 27/10/2015.
 */
public class AddNewPlayerDialog extends Dialog{
    Button mBtnAddLayerByList;
    Button mBtnAddNewPlayer;
    Button mBtnSubmitNewPlayer;
    Button mBtnCancelNewPlayer;
    CircleImageView mCircleImgViewNewPlayerAvatar;
    EditText mEdtNewPlayerName;
    EditText mEdtNewPlayerBirthday;
    EditText mEdtNewPlayerHeight;
    EditText mEdtNewPlayerWeight;
    EditText mEdtNewPlayerNumber;
    Spinner mSpnNewPlayerCountry;
    Spinner mSpnNewPlayerPosition;
    RecyclerView mRecyclerViewPlayerFree;
    RelativeLayout mRlContentNewPlayer;

    public AddNewPlayerDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_new_player);
        mBtnAddLayerByList = (Button) findViewById(R.id.btnAddLayerByList);
        mBtnAddNewPlayer = (Button) findViewById(R.id.btnAddNewPlayer);
        mBtnSubmitNewPlayer = (Button) findViewById(R.id.btnSubmitNewPlayer);
        mBtnCancelNewPlayer = (Button) findViewById(R.id.btnCancelNewPlayer);
        mCircleImgViewNewPlayerAvatar = (CircleImageView) findViewById(R.id.circleImgViewNewPlayerAvatar);
        mEdtNewPlayerName = (EditText) findViewById(R.id.edtNewPlayerName);
        mEdtNewPlayerBirthday = (EditText) findViewById(R.id.edtNewPlayerBirthday);
        mEdtNewPlayerHeight = (EditText) findViewById(R.id.edtNewPlayerHeight);
        mEdtNewPlayerWeight = (EditText) findViewById(R.id.edtNewPlayerWeight);
        mEdtNewPlayerNumber = (EditText) findViewById(R.id.edtNewPlayerNumber);
        mSpnNewPlayerCountry = (Spinner) findViewById(R.id.spnNewPlayerCountry);
        mSpnNewPlayerPosition= (Spinner) findViewById(R.id.spnNewPlayerPosition);
        mRecyclerViewPlayerFree = (RecyclerView) findViewById(R.id.recyclerViewPlayerFree);
        mRlContentNewPlayer = (RelativeLayout) findViewById(R.id.rlContentNewPlayer);

        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

    }

}
