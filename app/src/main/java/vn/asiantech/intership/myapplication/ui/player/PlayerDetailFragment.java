package vn.asiantech.intership.myapplication.ui.player;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import vn.asiantech.intership.myapplication.R;
import vn.asiantech.intership.myapplication.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * created by gianhtran on 2015/10/21
 */
@EFragment(R.layout.fragment_player_detail)
public class PlayerDetailFragment extends BaseFragment {

    @ViewById(R.id.tvPlayerNameDetail)
    TextView mTvPlayerNameDetail;

    @ViewById(R.id.tvPlayerBirthday)
    TextView mTvPlayerBirthday;

    @ViewById(R.id.tvPlayerCountry)
    TextView mTvPlayerCountry;

    @ViewById(R.id.tvPlayerWeight)
    TextView mTvPlayerWeight;

    @ViewById(R.id.tvPlayerHeight)
    TextView mTvPlayerHeight;

    @ViewById(R.id.tvPlayerPostionDetail)
    TextView mTvPlayerPositionDetail;

    @ViewById(R.id.tvPlayerNumber)
    TextView mTvPlayerNumber;
    @ViewById(R.id.imgViewBackFromPlayerDetail)
    ImageView mImgViewBackFromPlayerDetail;

    @Click(R.id.imgViewBackFromPlayerDetail)
    void doBack() {
        mImgViewBackFromPlayerDetail.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_popup_enter));
        this.replaceFragment(R.id.frameContain, ListPlayerFragment_.builder().build(), "ListPlayerFragment_", null);

    }

    @Click(R.id.imgViewEditPlayer)
    void doEditPlayer() {

    }

    @Click(R.id.imgViewDeletePlayer)
    void doDeletePlayer() {

    }

    @AfterViews
    void afterView() {
    }


}
