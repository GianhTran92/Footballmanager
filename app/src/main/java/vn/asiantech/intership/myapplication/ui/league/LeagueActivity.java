package vn.asiantech.intership.myapplication.ui.league;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.model.League;

/**
 * created by igianhtran on 20/10/2015
 */

@EActivity(R.layout.activity_league)
public class LeagueActivity extends AppCompatActivity {
    LeagueRecyclerAdapter mLeagueRecyclerAdapter;
    List<League> mLeagues = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext = this;
    Boolean mIs1Line = true;

    @ViewById(R.id.recyclerViewLeague)
    RecyclerView mRecyclerViewLeague;

    @ViewById(R.id.imgViewSet1Line)
    ImageView mImgViewSet1Line;

    @Click(R.id.imgViewSet1Line)
    void setLinearLayout() {
        mImgViewSet1Line.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        setAdapter(mLayoutManager);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        mImgViewSet3Line.setVisibility(View.VISIBLE);
    }

    @ViewById(R.id.imgViewSet3Line)
    ImageView mImgViewSet3Line;

    @Click(R.id.imgViewSet3Line)
    void setGridLayout() {
        mImgViewSet3Line.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        mLayoutManager = new GridLayoutManager(LeagueActivity.this, 2, LinearLayoutManager.VERTICAL, true);
        setAdapter(mLayoutManager);
        mImgViewSet1Line.setVisibility(View.VISIBLE);
        mImgViewSet3Line.setVisibility(View.INVISIBLE);
        mIs1Line = false;
    }

    @ViewById(R.id.imgViewAddLeague)
    ImageView mImgViewAddLeague;

    @Click(R.id.imgViewAddLeague)
    void addNewLeague() {
        mImgViewAddLeague.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter));
        showDialogAddNewLeague();
    }

    @AfterViews
    void afterView() {
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mImgViewSet1Line.setVisibility(View.INVISIBLE);
        setAdapter(mLayoutManager);
    }

    public List<League> createDemoData() {
        List<League> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            League league = new League("premier league " + i, null);
            listData.add(league);
        }

        return listData;
    }

    public void setAdapter(RecyclerView.LayoutManager layoutManager) {
        mLeagues = createDemoData();
        mRecyclerViewLeague.setLayoutManager(layoutManager);
        mLeagueRecyclerAdapter = new LeagueRecyclerAdapter(mLeagues, mContext);
        mRecyclerViewLeague.setAdapter(mLeagueRecyclerAdapter);
    }

    public void showDialogAddNewLeague() {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_new_league);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        final EditText edtLeagueName = (EditText) dialog.findViewById(R.id.edtLeagueName);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmitAddLeague);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtLeagueName.getText().toString().equals("")) {
                    League league = new League(edtLeagueName.getText().toString(), null);
                    mLeagues.add(league);
                    mLeagueRecyclerAdapter.updateList(mLeagues);
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

}
