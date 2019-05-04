package com.bishe.qiuzhi.module.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {
    private Context mContext;
    private List<PositionBean> positionBeans;

    public DiscoverAdapter(Context ctx) {
        mContext = ctx;
    }

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover, parent, false);
        DiscoverViewHolder viewHolder = new DiscoverViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder discoverViewHolder, int position) {
        final PositionBean positionBean = positionBeans.get(position);
        //Glide.with(mContext).load(Constants.DOMAIN + positionBean.getCompany().getImage()).into(jobViewHolder.ivLogo);
        //Glide.with(App.getInstance()).load(positionBean.getCompany().getImage()).into(jobViewHolder.ivLogo);
//        if (onItemCLickListener != null) {
//            jobViewHolder.itemView.setOnClickListener(v -> onItemCLickListener.onClick(position, positionBean));
//        }
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

    class DiscoverViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSalary, tvCompanyName, tvDate, tvLocation, tvNum;
        ImageView ivLogo;

        public DiscoverViewHolder(@NonNull View itemView) {
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
