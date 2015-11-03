package io.github.shoma2da.android.self.viewmodel.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.fragment.LoginFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class LoginFragmentViewModel {

    private LoginFragment mFragment;
    private ProgressDialog mDialog;

    public LoginFragmentViewModel(LoginFragment fragment) {
        mFragment = fragment;
    }

    public void onCreateView(View view) {
        final EditText nameTextView = (EditText)view.findViewById(R.id.edittext_name);
        final EditText passwordTextView = (EditText)view.findViewById(R.id.edittext_password);

        view.findViewById(R.id.button_login).setOnClickListener(v -> {
            String name = nameTextView.getText().toString();
            String password = passwordTextView.getText().toString();

            User.login(name, password)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(() -> showProgressDialog())
                    .subscribe(
                            user -> {
                                dissmissProgressDialog();
                                mFragment.getActivity().finish();
                            },
                            error -> {
                                dissmissProgressDialog();
                                Toast.makeText(mFragment.getActivity(), "ログインに失敗しました", Toast.LENGTH_SHORT).show();
                                Timber.d(error, "ログインに失敗");
                            },
                            () -> dissmissProgressDialog()
                    );
        });
    }

    void showProgressDialog() {
        mDialog = new ProgressDialog(mFragment.getActivity());
        mDialog.setMessage("ログインしています");
        mDialog.show();
    }

    void dissmissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
