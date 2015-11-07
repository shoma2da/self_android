package io.github.shoma2da.android.self.model;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by shoma2da on 2015/11/07.
 */
public class TextContentList implements Iterable<TextContent> {

    public static final String CLASS_NAME = "TextContent";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_USER = "user";

    public static Observable<TextContentList> get(User user, int count) {
        return Observable.create(subscriber -> {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASS_NAME);
            query.whereEqualTo(COLUMN_USER, user.toParseUser())
                    .setLimit(count)
                    .findInBackground((objects, e) -> {
                        if (e != null) {
                            subscriber.onError(e);
                            return;
                        }

                        TextContentList textContentList = new TextContentList();
                        for (ParseObject parseObject : objects) {
                            textContentList.add(new TextContent(user, parseObject.getString(COLUMN_TEXT)));
                        }

                        subscriber.onNext(textContentList);
                        subscriber.onCompleted();
                    });
        });
    }

    private ArrayList<TextContent> mList = new ArrayList<>();

    public boolean add(TextContent textContent) {
        return mList.add(textContent);
    }

    @Override
    public Iterator<TextContent> iterator() {
        return mList.iterator();
    }

    public TextContent get(int index) {
        return mList.get(index);
    }

    public int size() {
        return mList.size();
    }
}
