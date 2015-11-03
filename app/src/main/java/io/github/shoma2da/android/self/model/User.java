package io.github.shoma2da.android.self.model;

import com.eccyan.optional.Optional;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import rx.Observable;
import rx.Subscriber;

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

    public static User getForce() {
        return get().toBlocking().first();
    }

    public static Observable<User> signup(String name, String email, String password) {
        return Observable.create(observer -> {
            ParseUser parseUser = new ParseUser();
            parseUser.setUsername(name);
            parseUser.setEmail(email);
            parseUser.setPassword(password);

            parseUser.signUpInBackground(e -> {
                if (e != null) {
                    observer.onError(e);
                    return;
                }
                observer.onNext(new User(parseUser));
                observer.onCompleted();
            });
        });
    }

    public static Observable<User> login(String name, String password) {
        return Observable.create(observer -> {
            ParseUser.logInInBackground(name, password, (parseUser, e) -> {
                if (e != null) {
                    observer.onError(e);
                    return;
                }
                observer.onNext(new User(parseUser));
                observer.onCompleted();
            });
        });
    }

    public static Observable<User> loginAsAnonymous() {
        return Observable.create(observer -> {
            ParseAnonymousUtils.logIn((parseUser, e) -> {
                if (e != null) {
                    observer.onError(e);
                    return;
                }
                observer.onNext(new User(parseUser));
                observer.onCompleted();
            });
        });
    }

    private final ParseUser mParseUser;

    User(ParseUser parseUser) {
        mParseUser = parseUser;
    }

    public ParseUser toParseUser() {
        return mParseUser;
    }

}
