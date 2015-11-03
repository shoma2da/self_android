package io.github.shoma2da.android.self.viewmodel.fragment;

import android.app.Activity;
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

    private Activity mActivity;
    private SelectLoginFragment mFragment;

    public SelectLoginFragmentViewModel(Activity activity, SelectLoginFragment fragment) {
        mActivity = activity;
        mFragment = fragment;
    }

    public void onCreateView(View view) {
        view.findViewById(R.id.button_login_as_guest).setOnClickListener(v -> {
            User.loginAsAnonymous()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .isEmpty()
                    .subscribe(
                            isEmpty -> {
                                if (isEmpty) {
                                    onNotPrepareUser();
                                } else {
                                    mActivity.finish();
                                }
                            },
                            e -> onNotPrepareUser(e),
                            () -> {}
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
        Toast.makeText(mActivity, "失敗しました", Toast.LENGTH_SHORT).show();
    }
}
