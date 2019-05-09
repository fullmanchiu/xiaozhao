package com.bishe.qiuzhi.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

import com.bishe.qiuzhi.R;

/**
 * 自定义View BottomView
 */
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

    /**
     * 设置fav按钮状态
     *
     * @param status
     */
    public void setFavStatus(boolean status) {
        isFav = status;
        ivFav.setSelected(status);
    }

    public boolean getFavStatus() {
        return isFav;
    }

    /**
     * 设置apply按钮状态
     *
     * @param status
     */
    public void setApplyStatus(boolean status) {
        isApply = status;
        textView.setText(status ? R.string.resumeApplied : R.string.resumeApply);
    }

    public boolean getApplyStatus() {
        return isApply;
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
        //获取自定义view的相关属性
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BottomView);
        if (typedArray != null) {
            text = typedArray.getString(R.styleable.BottomView_text);
        }
        initView();
        initListener();
    }

    /**
     * 初始化view
     */
    private void initView() {
        if (view == null) {
            mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.bottom_view_layout, this);
            textView = view.findViewById(R.id.tv_text);
            ivShare = view.findViewById(R.id.iv_share);
            ivFav = view.findViewById(R.id.iv_fav);
        }
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        ivShare.setOnClickListener(v -> onShareButtonClickListener.onClick());
        ivFav.setOnClickListener(v -> onFavButtonClickListener.onClick());
        textView.setOnClickListener(v -> onTextButtonClickListener.onClick());
    }
}
