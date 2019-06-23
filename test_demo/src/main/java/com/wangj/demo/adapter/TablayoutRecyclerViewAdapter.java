package com.wangj.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wangj.core.entity.BaseVO;

import java.util.List;

public class TablayoutRecyclerViewAdapter extends RecyclerView.Adapter<TablayoutRecyclerViewAdapter.ItemViewHolder> {

    private List<BaseVO> list;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     *
     */
    static class SectionViewHolder extends RecyclerView.ViewHolder {

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    /**
     *
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    /**
     *
     */
    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
