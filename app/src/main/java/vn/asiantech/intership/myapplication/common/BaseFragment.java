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
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_right,
                        R.anim.slide_in_left,
                        R.anim.slide_out_left)
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }
}