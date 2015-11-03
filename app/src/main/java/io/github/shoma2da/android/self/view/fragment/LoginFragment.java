package io.github.shoma2da.android.self.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.viewmodel.fragment.LoginFragmentViewModel;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class LoginFragment extends Fragment {

    private LoginFragmentViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new LoginFragmentViewModel(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mViewModel.onCreateView(view);

        return view;
    }
}
