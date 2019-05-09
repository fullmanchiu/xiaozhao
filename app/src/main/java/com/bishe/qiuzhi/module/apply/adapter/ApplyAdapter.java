package com.bishe.qiuzhi.module.apply.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.apply.model.ApplyModel;
import com.bishe.qiuzhi.utils.DateUtil;

import java.util.List;

/**
 * 投递页面中RecyclerView对应的适配器
 */
public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ApplyViewHolder> {
    private Context mContext;
    private List<ApplyModel> applyModels; //存放数据的List

    /**
     * recycler view item 对应的点击事件接口
     */
    public interface OnItemCLickListener {
        void onClick(int position, ApplyModel applyModel);
    }

    private OnItemCLickListener onItemCLickListener;

    /**
     * 设置点击事件
     *
     * @param lickListener
     */
    public void setOnItemCLickListener(OnItemCLickListener lickListener) {
        this.onItemCLickListener = lickListener;
    }

    public ApplyAdapter(Context ctx) {
        mContext = ctx;
    }

    @NonNull
    @Override
    public ApplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item 布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fav_position, parent, false);
        ApplyViewHolder viewHolder = new ApplyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyViewHolder applyViewHolder, int position) {
        //加载数据到布局上，注册点击事件
        final ApplyModel applyModel = applyModels.get(position);
        applyViewHolder.tvTitle.setText(applyModel.getTitle());
        applyViewHolder.tvSalary.setText(applyModel.getSalary_min() + "~" + applyModel.getSalary_max());
        applyViewHolder.tvDate.setText(DateUtil.convertTimeToFormat(App.getInstance(), applyModel.getPublish_time()));
        if (onItemCLickListener != null) {
            applyViewHolder.itemView.setOnClickListener(v -> onItemCLickListener.onClick(position, applyModel));
        }
    }


    @Override
    public int getItemCount() {
        if (applyModels != null)
            return applyModels.size();
        return 0;
    }

    /**
     * 更新数据
     *
     * @param applyModels
     */
    public void setData(List<ApplyModel> applyModels) {
        this.applyModels = applyModels;
        notifyDataSetChanged();
    }

    /**
     * view holder 初始化一些控件
     */
    class ApplyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSalary, tvCompanyName, tvDate, tvLocation, tvNum;
        ImageView ivLogo;

        public ApplyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSalary = itemView.findViewById(R.id.tv_salary);
            tvCompanyName = itemView.findViewById(R.id.tv_companyName);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvNum = itemView.findViewById(R.id.tv_num);
            ivLogo = itemView.findViewById(R.id.iv_logo);
        }
    }
}
