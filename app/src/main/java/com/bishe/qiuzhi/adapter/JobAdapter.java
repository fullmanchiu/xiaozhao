package com.bishe.qiuzhi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.model.PositionBean;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private Context mContext;
    private List<PositionBean> positionBeans;

    public JobAdapter(Context ctx) {
        mContext = ctx;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_job, parent, false);
        JobViewHolder viewHolder = new JobViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder jobViewHolder, int position) {
        final PositionBean positionBean = positionBeans.get(position);
        jobViewHolder.tvTitle.setText(positionBean.getTitle());
        jobViewHolder.tvSalary.setText(positionBean.getSalaryMin() + "~" + positionBean.getSalaryMax());
        jobViewHolder.tvCompanyName.setText(positionBean.getCompanyName());
        jobViewHolder.tvDate.setText(positionBean.getPublishTime());
        jobViewHolder.tvLocation.setText(positionBean.getLocation());
        jobViewHolder.tvNum.setText("招" + positionBean.getNum() + "人");
    }


    @Override
    public int getItemCount() {
        if (positionBeans != null)
            return positionBeans.size();
        return 0;
    }

    public void setData(List<PositionBean> positionBeans) {
        this.positionBeans = positionBeans;
        notifyDataSetChanged();
    }

    class JobViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSalary, tvCompanyName, tvDate, tvLocation, tvNum;
        ImageView ivLogo;

        public JobViewHolder(@NonNull View itemView) {
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
