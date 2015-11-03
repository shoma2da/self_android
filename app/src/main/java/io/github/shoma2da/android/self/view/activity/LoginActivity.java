package io.github.shoma2da.android.self.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.viewmodel.activity.LoginActivityViewModel;
import io.github.shoma2da.android.self.viewmodel.fragment.SelectLoginFragmentViewModel;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class LoginActivity extends Activity implements SelectLoginFragmentViewModel.OnSelectLoginTypeListener {

    private LoginActivityViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewModel = new LoginActivityViewModel(this);
    }

    @Override
    public void onSelectSignup() {
        mViewModel.onSelectSignup();
    }

    @Override
    public void onSelectLogin() {
        mViewModel.onSelectLogin();
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

}
