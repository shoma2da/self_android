package io.github.shoma2da.android.self.viewmodel.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.TextContent;
import io.github.shoma2da.android.self.model.TextContentList;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.util.Keyboard;
import io.github.shoma2da.android.self.view.activity.LoginActivity;
import io.github.shoma2da.android.self.view.activity.MainActivity;
import io.github.shoma2da.android.self.view.view.MainContentsRecyclerView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class MainActivityViewModel {

    private MainActivity mMainActivity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

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

    public void onCreate() {
        EditText contentTextView = (EditText)mMainActivity.findViewById(R.id.edittext_content);
        mMainActivity.findViewById(R.id.button_send).setOnClickListener(button -> {
            String content = contentTextView.getText().toString();
            TextContent textContent = new TextContent(content);
            textContent.belongTo(User.getForce())
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

                                Keyboard.hidden(mMainActivity);
                                showContents(); //TODO ほんとはListに追加したのをobserveする方が良さそう
                            }
                    );
        });

    }

    public void showContents() {
        User user = User.get().toBlocking().first();
        if (user == null) {
            return;
        }

        MainContentsRecyclerView view = (MainContentsRecyclerView)mMainActivity.findViewById(R.id.list_content);
        RecyclerView recyclerView = view.toRecyclerView();
        Subscription subscribe = TextContentList.get(user, 100).subscribe(textContents -> {
            recyclerView.setAdapter(new RecyclerView.Adapter() {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                    return new RecyclerView.ViewHolder(view) {
                    };
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                    ((TextView) holder.itemView).setText(textContents.get(position).get());
                }

                @Override
                public int getItemCount() {
                    return textContents.size();
                }
            });
            recyclerView.scrollToPosition(textContents.size() - 1);
        });
        mSubscriptions.add(subscribe);
    }

    public void onPause() {
        mSubscriptions.unsubscribe();
    }
}
