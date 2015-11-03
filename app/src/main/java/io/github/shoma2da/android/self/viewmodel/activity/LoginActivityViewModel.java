package io.github.shoma2da.android.self.viewmodel.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.view.activity.LoginActivity;
import io.github.shoma2da.android.self.view.fragment.SelectLoginFragment;
import io.github.shoma2da.android.self.view.fragment.SignupFragment;
import io.github.shoma2da.android.self.viewmodel.fragment.SelectLoginFragmentViewModel;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class LoginActivityViewModel implements SelectLoginFragmentViewModel.OnSelectSignupListener {

    private LoginActivity mActivity;

    public LoginActivityViewModel(LoginActivity activity) {
        mActivity = activity;
        setupSelectLoginFragment();
    }

    public void setupSelectLoginFragment() {
        FragmentManager fragmentManager = mActivity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_fragment, new SelectLoginFragment());
        transaction.commit();
    }

    @Override
    public void onSelectSignup() {
        FragmentManager fragmentManager = mActivity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_fragment, new SignupFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onBackPressed() {
        FragmentManager fm = mActivity.getFragmentManager();
        fm.popBackStack();
    }

}
