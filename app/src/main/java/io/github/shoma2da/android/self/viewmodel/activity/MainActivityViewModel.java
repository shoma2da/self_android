package io.github.shoma2da.android.self.viewmodel.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.TextContent;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.activity.LoginActivity;
import io.github.shoma2da.android.self.view.activity.MainActivity;
import timber.log.Timber;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class MainActivityViewModel {

    private MainActivity mMainActivity;

    public MainActivityViewModel(MainActivity activity) {
        mMainActivity = activity;
    }

    public void confirmLogin() {
        //ログインできていなければログイン画面を表示する
        User.get().isEmpty()
                .filter(b -> b)
                .subscribe(it -> {
                    Intent intent = new Intent(mMainActivity, LoginActivity.class);
                    mMainActivity.startActivity(intent);
                });

        //Debug
        Timber.d("user is " + ParseUser.getCurrentUser());
    }

    public void setup() {
        EditText contentTextView = (EditText)mMainActivity.findViewById(R.id.edittext_content);
        mMainActivity.findViewById(R.id.button_send).setOnClickListener(button -> {
            String content = contentTextView.getText().toString();
            TextContent textContent = new TextContent(User.getForce(), content);
            textContent.save()
                    .doOnSubscribe(() -> {
                        contentTextView.setEnabled(false);
                        button.setEnabled(false);
                    })
                    .subscribe(
                            result -> Toast.makeText(mMainActivity, "OK", Toast.LENGTH_SHORT).show(),
                            error -> Timber.d(error, "テキストの送信に失敗しました"),
                            () -> {
                                contentTextView.setEnabled(true);
                                button.setEnabled(true);
                                contentTextView.setText("");

                                // Check if no view has focus:
                                View view = mMainActivity.getCurrentFocus();
                                if (view != null) {
                                    InputMethodManager imm = (InputMethodManager)mMainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                }
                            }
                    );
        });
    }
}
