package com.bishe.qiuzhi.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;

public class NiceToolBar extends Toolbar {
    private Drawable icon;
    private View view;
    private LayoutInflater mInflater;
    private TextView tvTitle;
    private ImageView imageView;
    private String title;

    public NiceToolBar(Context context) {
        this(context, null);
    }

    public NiceToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NiceToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.NiceTollBar);
        if (typedArray != null) {
            title = typedArray.getString(R.styleable.NiceTollBar_title);
            icon = typedArray.getDrawable(R.styleable.NiceTollBar_rightIcon);
        }
        initView();
    }

    private void initView() {
        if (view == null) {
            mInflater = LayoutInflater.from(getContext());
            view = mInflater.inflate(R.layout.toolbar, null);
            tvTitle = view.findViewById(R.id.tv_title);
            imageView = view.findViewById(R.id.iv_icon);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            //如果没有这行代码，title不会居中显示
            tvTitle.setText(title);
            imageView.setImageDrawable(icon);
            addView(view, layoutParams);
        }
    }
}
