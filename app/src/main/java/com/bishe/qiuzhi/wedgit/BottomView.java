package com.bishe.qiuzhi.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.qiuzhi.R;

public class BottomView extends LinearLayout {
    private Context mContext;
    private String text;
    private ImageView ivShare, ivFav;
    private TextView textView;
    private View view;

    public BottomView(Context context) {
        this(context, null);
    }

    public BottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BottomView);
        if (typedArray != null) {
            text = typedArray.getString(R.styleable.BottomView_text);
        }
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.bottom_view_layout, null);
        textView = view.findViewById(R.id.tv_text);
        ivShare = view.findViewById(R.id.iv_share);
        ivFav = view.findViewById(R.id.iv_fav);
        addView(view);
    }

}
