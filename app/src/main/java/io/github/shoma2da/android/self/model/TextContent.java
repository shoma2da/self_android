package io.github.shoma2da.android.self.model;

import android.support.annotation.VisibleForTesting;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import rx.Observable;
import timber.log.Timber;

import static io.github.shoma2da.android.self.model.TextContentList.*;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class TextContent {

    private User mUser;
    private String mContent;

    @VisibleForTesting
    TextContent() {}

    public TextContent(User user, String content) {
        mUser = user;
        mContent = content;
    }

    public Observable<Boolean> save() {
        return Observable.create(observer -> {
            ParseObject parseObject = new ParseObject(CLASS_NAME);
            parseObject.put(COLUMN_TEXT, mContent);
            parseObject.put(COLUMN_USER, mUser.toParseUser());
            parseObject.saveInBackground(e -> {
                if (e != null) {
                    observer.onError(e);
                    return;
                }
                observer.onNext(true);
                observer.onCompleted();
            });
        });
    }

    public String get() {
        return mContent;
    }

}
