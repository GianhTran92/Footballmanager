package vn.asiantech.intership.myapplication.common;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import vn.asiantech.intership.myapplication.R;

/**
 * Created by igianhtran on 21/10/2015.
 * edited by gianhtran on 22/10/2015
 */
public class BaseFragment extends Fragment {
    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();

    }

    public void replaceFragment(@IdRes int containerViewId,
                                @NonNull Fragment fragment,
                                @NonNull String fragmentTag,
                                @Nullable String backStackStateName) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out,R.anim.abc_slide_in_top,R.anim.abc_slide_out_top)
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
//        .setCustomAnimations(R.anim.card_flip_right_in, R.anim.card_flip_right_out,
//                R.anim.card_flip_left_in, R.anim.card_flip_left_out)
    }
}