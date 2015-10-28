package vn.asiantech.intership.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;
import vn.asiantech.intership.myapplication.ui.player.ListPlayerFragment;
import vn.asiantech.intership.myapplication.ui.player.LoadLayerInterface;

/**
 * Created by igianhtran on 27/10/2015.
 */
public class AddNewPlayerDialog extends Dialog implements LoadLayerInterface,PlayerFreeRecyclerViewAdapter.OnChoicePlayerListener{
    Boolean isChoice;

    Button mBtnAddLayerByList;
    Button mBtnAddNewPlayer;

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

    ImageView mImgViewCloseDialog;

    Context context;


    String mPlayerCountry;

    Position.POSITISON mPlayerPosition;

    public interface OnSendPlayerListener{
        void sendSuccess(Player player);
    }

    OnSendPlayerListener delegate;

    public AddNewPlayerDialog(Context context) {
        super(context);
        this.context = context;

        setContentView(R.layout.dialog_new_player);
        mBtnAddLayerByList = (Button) findViewById(R.id.btnAddLayerByList);
        mBtnAddNewPlayer = (Button) findViewById(R.id.btnAddNewPlayer);

        mCircleImgViewNewPlayerAvatar = (CircleImageView) findViewById(R.id.circleImgViewNewPlayerAvatar);
        mEdtNewPlayerName = (EditText) findViewById(R.id.edtNewPlayerName);
        mEdtNewPlayerBirthday = (EditText) findViewById(R.id.edtNewPlayerBirthday);
        mEdtNewPlayerHeight = (EditText) findViewById(R.id.edtNewPlayerHeight);
        mEdtNewPlayerWeight = (EditText) findViewById(R.id.edtNewPlayerWeight);
        mEdtNewPlayerNumber = (EditText) findViewById(R.id.edtNewPlayerNumber);
        mSpnNewPlayerCountry = (Spinner) findViewById(R.id.spnNewPlayerCountry);
        mSpnNewPlayerPosition = (Spinner) findViewById(R.id.spnNewPlayerPosition);
        mRecyclerViewPlayerFree = (RecyclerView) findViewById(R.id.recyclerViewPlayerFree);
        mRlContentNewPlayer = (RelativeLayout) findViewById(R.id.rlContentNewPlayer);
        mImgViewCloseDialog = (ImageView) findViewById(R.id.imgViewCloseDialog);

        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        mImgViewCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mBtnAddLayerByList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice = true;
                mRlContentNewPlayer.setVisibility(View.GONE);
                mBtnAddLayerByList.setVisibility(View.GONE);
                mRecyclerViewPlayerFree.setVisibility(View.VISIBLE);
                mBtnAddNewPlayer.setVisibility(View.VISIBLE);
                setAdapter();

            }
        });

        mBtnAddNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice = false;
                mRlContentNewPlayer.setVisibility(View.VISIBLE);
                mBtnAddLayerByList.setVisibility(View.VISIBLE);
                mRecyclerViewPlayerFree.setVisibility(View.GONE);
                mBtnAddNewPlayer.setVisibility(View.GONE);
            }
        });
    }

    public void setSpinerAdapter() {
        ArrayAdapter<String> spinerCountryAdapter = new ArrayAdapter<>
                (
                        context,
                        android.R.layout.simple_spinner_item,
                        Common.ARR
                );
        spinerCountryAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnNewPlayerCountry.setAdapter(spinerCountryAdapter);
        mSpnNewPlayerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPlayerCountry = Common.ARR[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPlayerCountry = Common.ARR[0];
            }
        });

        ArrayAdapter<Position.POSITISON> spinerPositionAdapter = new ArrayAdapter<Position.POSITISON>(
                context,
                android.R.layout.simple_spinner_item,
                Position.POSITISON.values()
        );
        spinerPositionAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnNewPlayerPosition.setAdapter(spinerPositionAdapter);
        mPlayerPosition = ((Position.POSITISON) mSpnNewPlayerPosition.getSelectedItem());
    }

    public String getName() {
        return mEdtNewPlayerName.getText().toString();
    }

    public String getBirthday() {
        return mEdtNewPlayerBirthday.getText().toString();
    }

    public float getHeight() {
        return Float.valueOf(mEdtNewPlayerHeight.getText().toString());
    }

    public float getWeight() {
        return Float.valueOf(mEdtNewPlayerWeight.getText().toString());
    }

    public int getNumber() {
        return Integer.valueOf(mEdtNewPlayerNumber.getText().toString());
    }

    public String getCountry() {
        return mPlayerCountry;
    }

    public Position.POSITISON getPosition() {
        return mPlayerPosition;
    }

    public Boolean getIsChoice() {
        return isChoice;
    }

    public void setEror() {
        mEdtNewPlayerName.setError("not null");
    }

    public void setAdapter(){
        LoadPlayerFree loadPlayerFree = new LoadPlayerFree();
        loadPlayerFree.delegate = this;
        loadPlayerFree.execute();
    }

    @Override
    public void processFinish(List<Player> players) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        mRecyclerViewPlayerFree.setLayoutManager(mLayoutManager);
        PlayerFreeRecyclerViewAdapter playerFreeRecyclerViewAdapter = new PlayerFreeRecyclerViewAdapter(players);
        playerFreeRecyclerViewAdapter.delegate = this;
        mRecyclerViewPlayerFree.setAdapter(playerFreeRecyclerViewAdapter);
    }

    @Override
    public void getPlayerSuccess(Player player) {
        delegate.sendSuccess(player);
        dismiss();
    }

    public void setDelegate(ListPlayerFragment fragment) {
        this.delegate = fragment;
    }

    /**
     * Using AsyncTask to load list Player in free
     */
    public class LoadPlayerFree extends AsyncTask<Void, Void, List<Player>> {
        LoadLayerInterface delegate = null;

        @Override
        protected List<Player> doInBackground(Void... params) {
            List<Player> players = Player.find(Player.class, "teamId=?", "-1");
            return players;
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            super.onPostExecute(players);
            delegate.processFinish(players);
        }
    }

}
