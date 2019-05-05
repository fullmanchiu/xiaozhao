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
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.utils.DateUtil;

import java.util.List;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ApplyViewHolder> {
    private Context mContext;
    private List<ApplyModel> applyModels;

    public interface OnItemCLickListener {
        void onClick(int position, ApplyModel applyModel);
    }

    private OnItemCLickListener onItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener lickListener) {
        this.onItemCLickListener = lickListener;
    }

    public ApplyAdapter(Context ctx) {
        mContext = ctx;
    }

    @NonNull
    @Override
    public ApplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fav_position, parent, false);
        ApplyViewHolder viewHolder = new ApplyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyViewHolder applyViewHolder, int position) {
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

    public void setData(List<ApplyModel> applyModels) {
        this.applyModels = applyModels;
        notifyDataSetChanged();
    }

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
