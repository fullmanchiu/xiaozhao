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
import com.bishe.qiuzhi.model.JobBean;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private Context mContext;
    private List<JobBean> jobBeans;

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
        final JobBean jobBean = jobBeans.get(position);
        jobViewHolder.tvTitle.setText(jobBean.getTitle());
        jobViewHolder.tvSalary.setText(jobBean.getSalary());
        jobViewHolder.tvCompanyName.setText(jobBean.getCompanyName());
        jobViewHolder.tvDate.setText(jobBean.getDate());
        jobViewHolder.tvLocation.setText(jobBean.getLocation());
        jobViewHolder.tvNum.setText("招" + jobBean.getNum() + "人");
    }


    @Override
    public int getItemCount() {
        return jobBeans.size();
    }

    public void setData(List<JobBean> jobBeans) {
        this.jobBeans = jobBeans;
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
