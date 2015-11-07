package io.github.shoma2da.android.self.view.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import io.github.shoma2da.android.self.R;

/**
 * Created by shoma2da on 2015/11/07.
 */
public class MainContentsRecyclerView extends FrameLayout {

    private RecyclerView mChildRecyclerView;

    public MainContentsRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mChildRecyclerView = (RecyclerView)LayoutInflater.from(context).inflate(R.layout.recycler_view_main_contents, null);
        mChildRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        addView(mChildRecyclerView);
    }

    public RecyclerView toRecyclerView() {
        return mChildRecyclerView;
    }

}
