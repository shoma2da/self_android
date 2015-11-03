package io.github.shoma2da.android.self.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.viewmodel.fragment.SignupFragmentViewModel;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class SignupFragment extends Fragment {

    private SignupFragmentViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SignupFragmentViewModel(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mViewModel.onCreateView(view);

        return view;
    }

}
