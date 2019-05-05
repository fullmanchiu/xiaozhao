package com.bishe.qiuzhi.module.seminar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.adapter.PositionAdapter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.utils.DateUtil;
import com.bumptech.glide.Glide;

import java.util.IllegalFormatCodePointException;
import java.util.List;

public class SeminarAdapter extends RecyclerView.Adapter<SeminarAdapter.SeminarViewHolder> {
    private Context mContext;
    private List<SeminarBean> seminarBeans;

    public interface OnItemCLickListener {
        void onClick(int position, SeminarBean positionBean);
    }

    private OnItemCLickListener onItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener lickListener) {
        this.onItemCLickListener = lickListener;
    }

    public SeminarAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public SeminarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_seminar, parent, false);
        SeminarViewHolder viewHolder = new SeminarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeminarViewHolder seminarViewHolder, int position) {
        final SeminarBean seminarBean = seminarBeans.get(position);
        seminarViewHolder.tvTitle.setText(seminarBean.getName());
        String date = DateUtil.simpleFormat("MM-dd    hh:mm", seminarBean.getStart_time()) + "-" + DateUtil.simpleFormat("hh:mm", seminarBean.getEnd_time());
        seminarViewHolder.tvDate.setText(date);
        seminarViewHolder.tvLocation.setText(seminarBean.getSchool().getName() + " · " + seminarBean.getAddress());
        Glide.with(mContext).load(Constants.DOMAIN + seminarBean.getSchool().getImage()).into(seminarViewHolder.ivLogo);
        if (onItemCLickListener != null) {
            seminarViewHolder.itemView.setOnClickListener(v -> onItemCLickListener.onClick(position, seminarBean));
        }
    }

    @Override
    public int getItemCount() {
        if (seminarBeans != null)
            return seminarBeans.size();
        return 0;
    }

    public void setData(List<SeminarBean> arraySeminarBeanFromData) {
        this.seminarBeans = arraySeminarBeanFromData;
        notifyDataSetChanged();
    }

    class SeminarViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvLocation;
        ImageView ivLogo;

        public SeminarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvLocation = itemView.findViewById(R.id.tv_location);
            ivLogo = itemView.findViewById(R.id.iv_logo);
        }
    }
}
