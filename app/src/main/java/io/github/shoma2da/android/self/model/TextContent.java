package io.github.shoma2da.android.self.model;

import com.parse.ParseObject;

import rx.Observable;

/**
 * Created by shoma2da on 2015/11/03.
 */
public class TextContent {

    private User mUser;
    private String mContent;

    public TextContent(User user, String content) {
        mUser = user;
        mContent = content;
    }

    public Observable<Boolean> save() {
        return Observable.create(observer -> {
            ParseObject parseObject = new ParseObject("TextContent");
            parseObject.put("text", mContent);
            parseObject.put("user", mUser.toParseUser());
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

}
