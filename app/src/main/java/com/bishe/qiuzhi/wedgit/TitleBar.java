package com.bishe.qiuzhi.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;

public class TitleBar extends ConstraintLayout {
    private String title;
    private LayoutInflater mInflater;
    private View view;
    private TextView tvTitle;
    private ImageView ivBack;


    public void setOnBackClickListener(OnBackClickListener listener) {
        if (listener != null) {
            ivBack.setOnClickListener(v -> {
                listener.onClick();
            });
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }


    public interface OnBackClickListener {
        void onClick();
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TitleBar);
        if (typedArray != null) {
            title = typedArray.getString(R.styleable.TitleBar_title);
        }
        initView();
    }

    private void initView() {
        if (view == null) {
            mInflater = LayoutInflater.from(getContext());
            view = mInflater.inflate(R.layout.titlebar, this);
            tvTitle = view.findViewById(R.id.tv_title);
            ivBack = view.findViewById(R.id.iv_back);
            tvTitle.setText(title);
        }
    }
}
