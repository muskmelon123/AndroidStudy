package com.example.lvhang.androidstudyproject.SearchView;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lvhang.androidstudyproject.R;

/**
 * Created by lv.hang on 2016/5/31.
 */
public class SearchView extends LinearLayout {
    private ImageView imageView;

    public SearchView(Context context) {
        super(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_view_action_bar, this, true);
        imageView = (ImageView) findViewById(R.id.search_view_image);
    }
}
