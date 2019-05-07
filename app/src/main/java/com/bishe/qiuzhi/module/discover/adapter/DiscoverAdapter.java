package com.bishe.qiuzhi.module.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.module.discover.model.DiscoverModel;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {
    private Context mContext;
    private List<DiscoverModel> discoverModels;

    public interface OnItemCLickListener {
        void onClick(int position, DiscoverModel positionBean);
    }

    private OnItemCLickListener onItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener lickListener) {
        this.onItemCLickListener = lickListener;
    }

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
        final DiscoverModel discoverModel = discoverModels.get(position);
        discoverViewHolder.tvTitle.setText(discoverModel.getTitle());
        discoverViewHolder.tvSource.setText("来自: " + discoverModel.getSource());
        if (onItemCLickListener != null) {
            discoverViewHolder.itemView.setOnClickListener(v -> onItemCLickListener.onClick(position, discoverModel));
        }
    }


    @Override
    public int getItemCount() {
        if (discoverModels != null)
            return discoverModels.size();
        return 0;
    }

    public void setData(List<DiscoverModel> discoverModels) {
        this.discoverModels = discoverModels;
        notifyDataSetChanged();
    }

    class DiscoverViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource;

        public DiscoverViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSource = itemView.findViewById(R.id.tv_source);
        }
    }
}
