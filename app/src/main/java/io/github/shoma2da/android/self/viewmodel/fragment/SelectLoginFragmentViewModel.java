package io.github.shoma2da.android.self.viewmodel.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.fragment.SelectLoginFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class SelectLoginFragmentViewModel {

    private SelectLoginFragment mFragment;
    private ProgressDialog mDialog;

    public SelectLoginFragmentViewModel(SelectLoginFragment fragment) {
        mFragment = fragment;
    }

    public void onCreateView(View view) {
        view.findViewById(R.id.button_signup).setOnClickListener(button -> {
            Activity activity = mFragment.getActivity();
            if (activity != null || activity instanceof OnSelectLoginTypeListener) {
                ((OnSelectLoginTypeListener)activity).onSelectSignup();
            }
        });

        view.findViewById(R.id.button_login).setOnClickListener(button -> {
            Activity activity = mFragment.getActivity();
            if (activity != null || activity instanceof OnSelectLoginTypeListener) {
                ((OnSelectLoginTypeListener)activity).onSelectLogin();
            }
        });

        view.findViewById(R.id.button_login_as_guest).setOnClickListener(v -> {
            User.loginAsAnonymous()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .isEmpty()
                    .doOnSubscribe(() -> showProgressDialog())
                    .subscribe(
                            isEmpty -> {
                                if (isEmpty) {
                                    onNotPrepareUser();
                                } else {
                                    mFragment.getActivity().finish();
                                }
                            },
                            e -> onNotPrepareUser(e),
                            () -> dissmissProgressDialog()
                    );
        });
    }

    void onNotPrepareUser() {
        onNotPrepareUser(null);
    }

    void onNotPrepareUser(@Nullable Throwable throwable) {
        if (throwable != null) {
            throwable.printStackTrace();
        }
        Toast.makeText(mFragment.getActivity(), "失敗しました", Toast.LENGTH_SHORT).show();
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

    public interface OnSelectLoginTypeListener {
        void onSelectSignup();
        void onSelectLogin();
    }

}
