package io.github.shoma2da.android.self.viewmodel.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.TextContent;
import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.util.Keyboard;
import io.github.shoma2da.android.self.view.activity.LoginActivity;
import io.github.shoma2da.android.self.view.activity.MainActivity;
import io.github.shoma2da.android.self.view.view.MainContentsRecyclerView;
import rx.Observable;
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

                                Keyboard.hidden(mMainActivity);
                            }
                    );
        });

    }

    public void showContents() {
        User.get().subscribe(user -> {
            TextContent.find(user, 100).toList().subscribe(textContents -> {

                //setup RecyclerView
                MainContentsRecyclerView view = (MainContentsRecyclerView) mMainActivity.findViewById(R.id.list_content);
                RecyclerView recyclerView = view.toRecyclerView();
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
//                    .subscribe(
//                            next -> Timber.d("next is " + next.get()),
//                            error -> {
//                                Timber.d("error!!");
//                                error.printStackTrace();
//                            },
//                            () -> Timber.d("onComplete!")
//                    );
        });
//        //FIXME 同期的にテキスト一覧を読み込んでる
//        List<TextContent> textContents = TextContent.find(User.getForce(), 100).toList().toBlocking().first();
//
    }
}
