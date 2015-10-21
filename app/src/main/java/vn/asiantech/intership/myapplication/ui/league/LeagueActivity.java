package vn.asiantech.intership.myapplication.ui.league;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @ViewById(R.id.recyclerViewLeague)
    RecyclerView mRecyclerViewLeague;

    @Click(R.id.imgViewAddLeague)
    void addNewLeague() {
        showDialogAddNewLeague();
    }

    @AfterViews
    void afterView() {
        setAdapter();
    }

    public List<League> createDemoData() {
        List<League> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            League league = new League("premier league " + i, null);
            listData.add(league);
        }

        return listData;
    }

    public void setAdapter() {
        mLeagues = createDemoData();
        mLayoutManager = new LinearLayoutManager(LeagueActivity.this);
        mRecyclerViewLeague.setLayoutManager(mLayoutManager);
        mLeagueRecyclerAdapter = new LeagueRecyclerAdapter(mLeagues, mContext);
        mRecyclerViewLeague.setAdapter(mLeagueRecyclerAdapter);
    }

    public void showDialogAddNewLeague() {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_new_league);

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
