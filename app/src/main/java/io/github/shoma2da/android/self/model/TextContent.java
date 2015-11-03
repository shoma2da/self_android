package io.github.shoma2da.android.self.model;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class TextContent {

    public static final String CLASS_NAME = "TextContent";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_USER = "user";

    public static Observable<TextContent> find(User user, int count) {
        return Observable.create(subscriber -> {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS_NAME);
            Timber.d(user.getId());
            query.whereEqualTo(COLUMN_USER, user.toParseUser())
                    .setLimit(count)
                    .findInBackground((objects, e) -> {
                        if (e != null) {
                            subscriber.onError(e);
                            return;
                        }
                        for (ParseObject parseObject : objects) {
                            subscriber.onNext(new TextContent(user, parseObject.getString(COLUMN_TEXT)));
                        }
                        subscriber.onCompleted();
                    });
        });
    }

    private User mUser;
    private String mContent;

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
