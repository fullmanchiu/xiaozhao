package com.bishe.qiuzhi.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;

public class BottomView extends ConstraintLayout {
    private Context mContext;
    private String text;
    private ImageView ivShare, ivFav;
    private TextView textView;
    private View view;
    private boolean isFav, isApply;
    private LayoutInflater mInflater;
    private OnShareButtonClickListener onShareButtonClickListener;
    private OnFavButtonClickListener onFavButtonClickListener;
    private OnTextButtonClickListener onTextButtonClickListener;

    public void setOnShareButtonClickListener(OnShareButtonClickListener onShareButtonClickListener) {
        this.onShareButtonClickListener = onShareButtonClickListener;
    }

    public void setOnFavButtonClickListener(OnFavButtonClickListener onFavButtonClickListener) {
        this.onFavButtonClickListener = onFavButtonClickListener;
    }

    public void setOnTextButtonClickListener(OnTextButtonClickListener onTextButtonClickListener) {
        this.onTextButtonClickListener = onTextButtonClickListener;
    }

    public void setFavStatus(boolean status) {
        isFav = status;
        ivFav.setSelected(status);
    }

    public boolean getFavStatus() {
        return isFav;
    }

    public interface OnShareButtonClickListener {
        void onClick();
    }

    public interface OnFavButtonClickListener {
        void onClick();
    }

    public interface OnTextButtonClickListener {
        void onClick();
    }

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
        initListener();
    }

    private void initView() {
        if (view == null) {
            mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.bottom_view_layout, this);
            textView = view.findViewById(R.id.tv_text);
            ivShare = view.findViewById(R.id.iv_share);
            ivFav = view.findViewById(R.id.iv_fav);
        }
    }

    private void initListener() {
        ivShare.setOnClickListener(v -> onShareButtonClickListener.onClick());
        ivFav.setOnClickListener(v -> onFavButtonClickListener.onClick());
        textView.setOnClickListener(v -> onTextButtonClickListener.onClick());
    }
}
