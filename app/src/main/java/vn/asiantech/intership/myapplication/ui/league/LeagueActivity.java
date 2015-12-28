package vn.asiantech.intership.myapplication.ui.league;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.Common;
import vn.asiantech.intership.myapplication.model.Coach;
import vn.asiantech.intership.myapplication.model.FootballTeam;
import vn.asiantech.intership.myapplication.model.League;
import vn.asiantech.intership.myapplication.model.Player;
import vn.asiantech.intership.myapplication.ui.FootballTeam.FootballTeamActivity_;

/**
 * created by igianhtran on 20/10/2015
 */

@EActivity(R.layout.activity_league)
public class LeagueActivity extends AppCompatActivity implements
        LeagueRecyclerAdapter.OnCallFootballTeamActivityListener,GetListNumberofTeamInterface {
    LeagueRecyclerAdapter mLeagueRecyclerAdapter;

    Boolean isLoadListNumberTeamSuccess = false;

    List<String> mListNumberTeam = new ArrayList<>();

    RecyclerView.LayoutManager mLayoutManager;

    @ViewById(R.id.tvTest)
    TextView mTvTest;

    @ViewById(R.id.recyclerViewLeague)
    RecyclerView mRecyclerViewLeague;

    @ViewById(R.id.imgViewSet3Line)
    ImageView mImgViewSet3Line;

    @ViewById(R.id.imgViewSet1Line)
    ImageView mImgViewSet1Line;

    @ViewById(R.id.fLoatingBtnAddLeague)
    FloatingActionButton mFLoatingBtnAddLeague;

    @Click(R.id.imgViewSet1Line)
    void setLinearLayout() {
        mImgViewSet1Line.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.abc_popup_enter));
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        mImgViewSet3Line.setVisibility(View.VISIBLE);
        loadDataByLayout(mLayoutManager);
    }

    @Click(R.id.imgViewSet3Line)
    void setGridLayout() {
        mImgViewSet3Line.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        mLayoutManager = new GridLayoutManager(LeagueActivity.this,
                2,
                LinearLayoutManager.VERTICAL,
                false);
        mImgViewSet1Line.setVisibility(View.VISIBLE);
        mImgViewSet3Line.setVisibility(View.INVISIBLE);
        loadDataByLayout(mLayoutManager);
    }

    @Click(R.id.fLoatingBtnAddLeague)
    void addNewLeague() {
        showDialogAddNewLeague();
    }

    @AfterViews
    void afterView() {
        getListNumberTeam();
        mFLoatingBtnAddLeague.attachToRecyclerView(mRecyclerViewLeague);
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        loadDataByLayout(mLayoutManager);

        mTvTest.setText(String.valueOf(isLoadListNumberTeamSuccess));
    }

    /**
     * method load list league by LinearLayout or GridLayout
     *
     * @param layoutManager LinearLayout or GridLayout
     */
    public void loadDataByLayout(RecyclerView.LayoutManager layoutManager) {
        LoadLeagueData mLoadLeagueData = new LoadLeagueData(this, layoutManager);
        mLoadLeagueData.execute();
    }

    public Context getContext() {
        return this;
    }

    public void updateData() {
        UpdateLeagueData mUpdateLeagueData = new UpdateLeagueData(mLeagueRecyclerAdapter);
        mUpdateLeagueData.execute();
    }

    public void setAdapter(List<League> mLeagues,
                           LeagueActivity leagueActivity,
                           RecyclerView.LayoutManager layoutManager) {
        mRecyclerViewLeague.setLayoutManager(layoutManager);
        mLeagueRecyclerAdapter = new LeagueRecyclerAdapter(mLeagues, leagueActivity, mListNumberTeam);
        mRecyclerViewLeague.setAdapter(mLeagueRecyclerAdapter);
    }

    /**
     * method show dialog to add new league
     */
    public void showDialogAddNewLeague() {

        final Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_new_league);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        View v = dialog.getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        final EditText edtLeagueName = (EditText) dialog.findViewById(R.id.edtLeagueName);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmitAddLeague);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtLeagueName.getText().toString().equals("")) {
                    League league = new League(edtLeagueName.getText().toString(), null);
                    league.save();
                    updateData();
                    dialog.dismiss();
                } else {
                    edtLeagueName.setError(getString(R.string.error_field_not_be_empty));
                }

            }
        });

        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelAddLeague);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    @Override
    public void onCall(League league) {
        FootballTeamActivity_.intent(this)
                .extra(Common.KEY_LEAGUE_ID, league.getId())
                .start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getListNumberTeam();
        updateData();
    }

    /**
     * method get image from gala
     *
     * @param requestCode code request
     * @param resultCode  sode result
     * @param data uri
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
//                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                //TODO
            } catch (IOException e) {
                //TODO
            }
        }
    }

    @Override
    public void getSuccess(List<String> list) {
        isLoadListNumberTeamSuccess = true;
    }

    /**
     * load list league from database using asynctask
     * param List<League>
     */
    public class LoadLeagueData extends AsyncTask<Void, Void, List<League>> {
        LeagueActivity leagueActivity;
        RecyclerView.LayoutManager layoutManager;

        public LoadLeagueData(LeagueActivity leagueActivity,
                              RecyclerView.LayoutManager layoutManager) {
            this.leagueActivity = leagueActivity;
            this.layoutManager = layoutManager;
        }

        @Override
        protected List<League> doInBackground(Void... params) {
            List<League> leagues = League.listAll(League.class);
            return leagues;
        }

        @Override
        protected void onPostExecute(List<League> leagues) {
            super.onPostExecute(leagues);
            leagueActivity.setAdapter(leagues, leagueActivity, layoutManager);

        }
    }

    /**
     * update list data using asynctask
     */
    public class UpdateLeagueData extends AsyncTask<Void, Void, List<League>> {
        LeagueRecyclerAdapter recyclerAdapter;

        public UpdateLeagueData(LeagueRecyclerAdapter recyclerAdapter) {
            this.recyclerAdapter = recyclerAdapter;
        }

        @Override
        protected List<League> doInBackground(Void... params) {
            List<League> leagues = League.listAll(League.class);
            return leagues;
        }

        @Override
        protected void onPostExecute(List<League> leagues) {
            recyclerAdapter.updateList(leagues);
        }
    }

    public void deleteLeague(League league) {
        DeleteLeague deleteLeague = new DeleteLeague(league);
        deleteLeague.execute();
        updateData();
    }

    public void editLeague(League league, String name, String logo) {
        EditLeague editLeague = new EditLeague(league, name, logo);
        editLeague.execute();
        updateData();
    }

    /**
     * Using AsyncTask to edit league
     */
    public class EditLeague extends AsyncTask<Void, Void, Void> {
        League mLeague;
        String mName;
        String mLogo;

        public EditLeague(League league, String name, String logo) {
            this.mLeague = league;
            this.mName = name;
            this.mLogo = logo;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mLeague.setName(mName);
            mLeague.setLogo(mLogo);
            mLeague.save();
            return null;
        }
    }

    /**
     * Using AsyncTask to delete league
     */
    public class DeleteLeague extends AsyncTask<Void, Void, Void> {
        League mLeague;

        public DeleteLeague(League league) {
            this.mLeague = league;
        }

        @Override
        protected Void doInBackground(Void... params) {

            List<FootballTeam> footballTeams = FootballTeam.find(FootballTeam.class, "leagueId=?", String.valueOf(mLeague.getId()));
            for (FootballTeam footballTeam : footballTeams) {
                List<Player> players = Player.find(Player.class, "teamId=?", String.valueOf(footballTeam.getId()));
                for (Player player : players) {
                    player.setTeamId(-1);
                    player.save();
                }
                List<Coach> coaches = Coach.find(Coach.class, "teamId=?", String.valueOf(footballTeam.getId()));
                for (Coach coach : coaches) {
                    coach.setTeamId(-1);
                    coach.save();
                }
                footballTeam.setLeagueId(-1);
                footballTeam.save();
            }
            mLeague.delete();
            return null;
        }
    }

    public void LoadImage() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, Common.RESULT_LOAD_IMAGE);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * Using AsyncTask to loaf Team number of league
     */
    public class LoadTeamNumber extends AsyncTask<Void,Void,List<String>> {
        List<String> mList = new ArrayList<>();

        GetListNumberofTeamInterface delegate;

        public LoadTeamNumber() {
            super();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            List<League> leagues = League.listAll(League.class);
            for (League league : leagues) {
                List<FootballTeam> footballTeams = FootballTeam.find(FootballTeam.class,
                        "leagueId=?",String.valueOf(league.getId()));
                mList.add(league.getName());
                mList.add(String.valueOf(footballTeams.size()));
            }
            return mList;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            delegate.getSuccess(strings);
        }
    }

    public void getListNumberTeam(){
        LoadTeamNumber loadTeamNumber = new LoadTeamNumber();
        loadTeamNumber.delegate = this;
        loadTeamNumber.execute();
    }
}
