package vn.asiantech.intership.myapplication.ui.player;


import android.app.Dialog;
import android.os.AsyncTask;
import android.app.Fragment;
import android.view.View;;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.model.Position;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_player_detail)
public class PlayerDetailFragment extends BaseFragment implements LoadPlayerByIdInterface {
    String mPlayerCountry;

    boolean isEdit;

    long mPlayerId;

    Player mPlayer;

    Position.POSITISON mPlayerPosition;

    @ViewById(R.id.tvInformationText)
    TextView mTvInformationText;

    @ViewById(R.id.spnPlayerCountry)
    Spinner mSpnPlayerCountry;

    @ViewById(R.id.spnPlayerPosition)
    Spinner mSpnPlayerPosition;

    @ViewById(R.id.tvPlayerNameDetail)
    EditText mTvPlayerNameDetail;

    @ViewById(R.id.tvPlayerBirthday)
    EditText mTvPlayerBirthday;

    @ViewById(R.id.tvPlayerCountry)
    EditText mTvPlayerCountry;

    @ViewById(R.id.tvPlayerWeight)
    EditText mTvPlayerWeight;

    @ViewById(R.id.tvPlayerHeight)
    EditText mTvPlayerHeight;

    @ViewById(R.id.tvPlayerPostionDetail)
    EditText mTvPlayerPositionDetail;

    @ViewById(R.id.tvPlayerNumber)
    EditText mTvPlayerNumber;

    @ViewById(R.id.imgViewBackFromPlayerDetail)
    ImageView mImgViewBackFromPlayerDetail;

    @ViewById(R.id.imgViewCheckoutPlayer)
    ImageView mImgViewCheckoutPlayer;

    @ViewById(R.id.imgViewEditPlayer)
    ImageView mImgViewEditPlayer;

    @ViewById(R.id.imgViewDeletePlayer)
    ImageView mImgViewDeletePlayer;

    @ViewById(R.id.imgViewSubmitEditPlayer)
    ImageView mImgViewSubmitEditPlayer;

    @Click(R.id.imgViewBackFromPlayerDetail)
    void doBack() {
        mImgViewBackFromPlayerDetail.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.abc_popup_enter));
        if (isEdit) {
            showDialogCofirmBack();
        } else {
            this.replaceFragment(R.id.frameContain,
                    ListPlayerFragment_.builder().build(),
                    "ListPlayerFragment_",
                    null);
        }
    }

    @Click(R.id.imgViewEditPlayer)
    void doEditPlayer() {
        mImgViewEditPlayer.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.abc_popup_enter));
        enableEditPlayer();
        setAdapter();
    }

    @Click(R.id.imgViewDeletePlayer)
    void doDeletePlayer() {
        mImgViewDeletePlayer.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.abc_popup_enter));
        showDialogCofirmDeletePlayer();
    }

    @Click(R.id.imgViewCheckoutPlayer)
    void doCheckoutPlayer() {
        mImgViewCheckoutPlayer.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.abc_popup_enter));
        showDialogConfirmDismissalPlayer();
    }

    @Click(R.id.imgViewSubmitEditPlayer)
    void doEdit() {
        mImgViewSubmitEditPlayer.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.abc_popup_enter));
        if (!mTvPlayerNameDetail.getText().toString().equals("")) {
            editPlayer(mPlayer,
                    mTvPlayerNameDetail.getText().toString(),
                    mTvPlayerBirthday.getText().toString(),
                    mPlayerCountry,
                    Float.valueOf(mTvPlayerWeight.getText().toString()),
                    Float.valueOf(mTvPlayerHeight.getText().toString()),
                    Integer.valueOf(mTvPlayerNumber.getText().toString()),
                    mPlayerPosition);
            this.replaceFragment(R.id.frameContain,
                    ListPlayerFragment_.builder().build(),
                    "ListPlayerFragment_",
                    null);
        } else {
            mTvPlayerNameDetail.setError(getActivity().getString(R.string.error_field_not_be_empty));
        }
    }

    @AfterViews
    void afterView() {
        disableEditPlayer();
        mPlayerId = getPlayerId();
        mTvPlayerNameDetail.setText(mPlayerId + " ");
        loadPlayerById(mPlayerId);
    }

    public long getPlayerId() {
        return this.getArguments().getLong(Common.KEY_PLAYER_ID);
    }

    public void loadPlayerById(long id) {
        LoadPlayerById loadPlayerById = new LoadPlayerById(id);
        loadPlayerById.delegate = this;
        loadPlayerById.execute();
    }

    /**
     * method to enable edit player mode
     */
    public void enableEditPlayer() {
        mTvInformationText.setText("EDIT INFORMATION");

        isEdit = true;

        mTvPlayerNameDetail.setEnabled(true);
        mTvPlayerBirthday.setEnabled(true);
        mTvPlayerWeight.setEnabled(true);
        mTvPlayerHeight.setEnabled(true);
        mTvPlayerNumber.setEnabled(true);
        mTvPlayerCountry.setEnabled(true);
        mTvPlayerPositionDetail.setEnabled(true);

        mTvPlayerCountry.setVisibility(View.GONE);
        mTvPlayerPositionDetail.setVisibility(View.GONE);
        mSpnPlayerCountry.setVisibility(View.VISIBLE);
        mSpnPlayerPosition.setVisibility(View.VISIBLE);
        mImgViewCheckoutPlayer.setVisibility(View.GONE);
        mImgViewDeletePlayer.setVisibility(View.GONE);
        mImgViewEditPlayer.setVisibility(View.GONE);
        mImgViewSubmitEditPlayer.setVisibility(View.VISIBLE);
    }

    /**
     * method to disable edit player mode
     */
    public void disableEditPlayer() {
        mTvInformationText.setText("INFORMATION");
        isEdit = false;

        mTvPlayerNameDetail.setEnabled(false);
        mTvPlayerBirthday.setEnabled(false);
        mTvPlayerWeight.setEnabled(false);
        mTvPlayerHeight.setEnabled(false);
        mTvPlayerNumber.setEnabled(false);
        mTvPlayerCountry.setEnabled(false);
        mTvPlayerPositionDetail.setEnabled(false);

        mTvPlayerCountry.setVisibility(View.VISIBLE);
        mTvPlayerPositionDetail.setVisibility(View.VISIBLE);
        mSpnPlayerCountry.setVisibility(View.GONE);
        mSpnPlayerPosition.setVisibility(View.GONE);
        mImgViewCheckoutPlayer.setVisibility(View.VISIBLE);
        mImgViewDeletePlayer.setVisibility(View.VISIBLE);
        mImgViewEditPlayer.setVisibility(View.VISIBLE);
        mImgViewSubmitEditPlayer.setVisibility(View.GONE);
    }

    /**
     * method set adapter for country spinner and position spinner
     */
    public void setAdapter() {
        ArrayAdapter<String> spinerCountryAdapter = new ArrayAdapter<>
                (
                        getActivity(),
                        android.R.layout.simple_spinner_item,
                        Common.ARR
                );
        spinerCountryAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnPlayerCountry.setAdapter(spinerCountryAdapter);
        mSpnPlayerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                getActivity(),
                android.R.layout.simple_spinner_item,
                Position.POSITISON.values()
        );
        spinerPositionAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpnPlayerPosition.setAdapter(spinerPositionAdapter);
        mPlayerPosition = ((Position.POSITISON) mSpnPlayerPosition.getSelectedItem());

    }

    public void editPlayer(Player player,
                           String name,
                           String birth,
                           String country,
                           Float weight,
                           Float height,
                           int number,
                           Position.POSITISON positison) {
        EditPlayer editPlayer = new EditPlayer(player,
                name,
                birth,
                country,
                weight,
                height,
                number,
                positison);
        editPlayer.execute();
    }

    /**
     * method implement LoadPlayerById interface
     * @param player object result from asyncTask LoadPlayerById
     */
    @Override
    public void processSuccess(Player player) {
        mTvPlayerNameDetail.setText(player.getName());
        mTvPlayerNameDetail.setText(player.getName());
        mTvPlayerBirthday.setText(player.getBird());
        mTvPlayerCountry.setText(player.getCountry());
        mTvPlayerHeight.setText(player.getHeight().toString());
        mTvPlayerWeight.setText(player.getWeight().toString());
        mTvPlayerNumber.setText(String.valueOf(player.getNumber()));
        mTvPlayerPositionDetail.setText(player.getPositison().toString());
        mPlayer = new Player(player);
    }

    /**
     * Using AsyncTask to edit Player
     */
    public class EditPlayer extends AsyncTask<Void, Void, Void> {
        Player mPlayer;
        String mName;
        String mBirthday;
        String mCountry;
        Float mHeight;
        Float mWeight;
        int mNumber;
        Position.POSITISON mPosition;

        public EditPlayer(Player player,
                          String name,
                          String birth,
                          String country,
                          Float weight,
                          Float height,
                          int number,
                          Position.POSITISON positison) {
            this.mPlayer = player;
            this.mName = name;
            this.mBirthday = birth;
            this.mCountry = country;
            this.mHeight = height;
            this.mWeight = weight;
            this.mNumber = number;
            this.mPosition = positison;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mPlayer.setName(mName);
            mPlayer.setBird(mBirthday);
            mPlayer.setCountry(mCountry);
            mPlayer.setWeight(mWeight);
            mPlayer.setHeight(mHeight);
            mPlayer.setPositison(mPosition);
            mPlayer.setNumber(mNumber);
            mPlayer.save();
            return null;
        }
    }

    /**
     * Using AsyncTask to load player by Id
     */
    public class LoadPlayerById extends AsyncTask<Void, Void, Player> {
        LoadPlayerByIdInterface delegate;
        long mPlayerId;

        public LoadPlayerById(long id) {
            this.mPlayerId = id;
        }

        @Override
        protected Player doInBackground(Void... params) {
            Player mPlayer = Player.findById(Player.class, mPlayerId);
            return mPlayer;
        }

        @Override
        protected void onPostExecute(Player player) {
            super.onPostExecute(player);
            delegate.processSuccess(player);
        }
    }

    public void showDialogCofirmBack() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_confirm_delete);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setTitle("are you sure to exit ?");

        Button btnSubmitDelete = (Button) dialog.findViewById(R.id.btnSubmitDelete);
        Button btnCancelDelete = (Button) dialog.findViewById(R.id.btnCancelDelete);
        btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.id.frameContain,
                        ListPlayerFragment_.builder().build(),
                        "ListPlayerFragment_",
                        null);
                dialog.dismiss();
            }
        });
        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    /**
     * Using AsyncTask to delete player
     */
    public class DeletePlayer extends AsyncTask<Void, Void, Void> {
        Player mPlayer;

        public DeletePlayer(Player player) {
            this.mPlayer = player;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mPlayer.delete();
            return null;
        }
    }

    /**
     * Using AsyncTask to move player to free zone
     */
    public class DismissalPlayer extends AsyncTask<Void, Void, Void> {
        Player mPlayer;

        public DismissalPlayer(Player player) {
            this.mPlayer = player;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mPlayer.setTeamId(-1l);
            mPlayer.save();
            return null;
        }
    }

    public void deletePlayer(Player player) {
        DeletePlayer deletePlayer = new DeletePlayer(player);
        deletePlayer.execute();
    }

    public void dismissalPlayer(Player player) {
        DismissalPlayer dismissalPlayer = new DismissalPlayer(player);
        dismissalPlayer.execute();
    }

    public void showDialogCofirmDeletePlayer() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_confirm_delete);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setTitle("are you sure to delete this player ?");

        Button btnSubmitDelete = (Button) dialog.findViewById(R.id.btnSubmitDelete);
        Button btnCancelDelete = (Button) dialog.findViewById(R.id.btnCancelDelete);
        btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlayer(mPlayer);
                replaceFragment(R.id.frameContain,
                        ListPlayerFragment_.builder().build(),
                        "ListPlayerFragment_",
                        null);
                dialog.dismiss();
            }
        });
        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public void showDialogConfirmDismissalPlayer() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_confirm_delete);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setTitle("are you sure to dismissal this player ?");

        Button btnSubmitDelete = (Button) dialog.findViewById(R.id.btnSubmitDelete);
        Button btnCancelDelete = (Button) dialog.findViewById(R.id.btnCancelDelete);
        btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissalPlayer(mPlayer);
                replaceFragment(R.id.frameContain,
                        ListPlayerFragment_.builder().build(),
                        "ListPlayerFragment_",
                        null);
                dialog.dismiss();
            }
        });
        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
