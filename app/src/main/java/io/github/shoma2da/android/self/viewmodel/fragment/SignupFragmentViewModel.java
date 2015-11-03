package io.github.shoma2da.android.self.viewmodel.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.fragment.SignupFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class SignupFragmentViewModel {

    private SignupFragment mFragment;
    private ProgressDialog mDialog;

    public SignupFragmentViewModel(SignupFragment fragment) {
        mFragment = fragment;
    }

    public void onCreateView(View view) {
        final EditText nameTextView = (EditText)view.findViewById(R.id.edittext_name);
        final EditText emailTextView = (EditText)view.findViewById(R.id.edittext_email);
        final EditText passwordTextView = (EditText)view.findViewById(R.id.edittext_password);

        view.findViewById(R.id.button_signup).setOnClickListener(v -> {
            String name = nameTextView.getText().toString();
            String email = emailTextView.getText().toString();
            String password = passwordTextView.getText().toString();

            User.signup(name, email, password)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(() -> showProgressDialog())
                    .subscribe(
                            next -> {
                            },
                            error -> {
                                dissmissProgressDialog();
                                Toast.makeText(mFragment.getActivity(), "失敗しました。少し時間を置いてから再度登録してください", Toast.LENGTH_LONG).show();
                            },
                            () -> {
                                dissmissProgressDialog();
                                mFragment.getActivity().finish();
                            }
                    );
        });
    }

    void showProgressDialog() {
        mDialog = new ProgressDialog(mFragment.getActivity());
        mDialog.setMessage("新規登録しています");
        mDialog.show();
    }

    void dissmissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
