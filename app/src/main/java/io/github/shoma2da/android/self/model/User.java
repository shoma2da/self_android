package io.github.shoma2da.android.self.model;

import com.eccyan.optional.Optional;
import com.parse.ParseUser;

import rx.Observable;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class User {

    public static Observable<User> get() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return Observable.just(new User(currentUser));
        }
        return Observable.empty();
    }

    private final ParseUser mParseUser;

    User(ParseUser parseUser) {
        mParseUser = parseUser;
    }

}
