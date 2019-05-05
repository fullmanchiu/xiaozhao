package com.bishe.qiuzhi.module.fav.adapter;

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
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.utils.DateUtil;

import java.util.List;

public class FavPositionAdapter extends RecyclerView.Adapter<FavPositionAdapter.PositionViewHolder> {
    private Context mContext;
    private List<PositionBean> positionBeans;

    public interface OnItemCLickListener {
        void onClick(int position, PositionBean positionBean);
    }

    private OnItemCLickListener onItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener lickListener) {
        this.onItemCLickListener = lickListener;
    }

    public FavPositionAdapter(Context ctx) {
        mContext = ctx;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fav_position, parent, false);
        PositionViewHolder viewHolder = new PositionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder positionViewHolder, int position) {
        final PositionBean positionBean = positionBeans.get(position);
        positionViewHolder.tvTitle.setText(positionBean.getTitle());
        positionViewHolder.tvSalary.setText(positionBean.getSalary_min() + "~" + positionBean.getSalary_max());
        positionViewHolder.tvDate.setText(DateUtil.convertTimeToFormat(App.getInstance(), positionBean.getPublish_time()));
        if (onItemCLickListener != null) {
            positionViewHolder.itemView.setOnClickListener(v -> onItemCLickListener.onClick(position, positionBean));
        }
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

    class PositionViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSalary, tvCompanyName, tvDate, tvLocation, tvNum;
        ImageView ivLogo;

        public PositionViewHolder(@NonNull View itemView) {
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
