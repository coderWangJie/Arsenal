package com.wangj.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangj.core.entity.BaseVO;
import com.wangj.demo.R;
import com.wangj.demo.TabLayoutRecyclerView3Activity;

import java.util.List;

public class TablayoutRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int TYPE_SECTION = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private int FOOTER_COUNT;

    private List<BaseVO> list;

    private int spanCount;
    private int lastSectionChildCount;
    private int containerHeight;
    private int sectionLayoutHeight;
    private int itemLayoutHeight;


    public void setContainerHeight(int containerHeight) {
        this.containerHeight = containerHeight;
    }

    public void setLastSectionChildCount(int lastSectionChildCount) {
        this.lastSectionChildCount = lastSectionChildCount;
    }

    public TablayoutRecyclerViewAdapter(final List<BaseVO> list, final GridLayoutManager gridLayoutManager, boolean hasFooter) {
        this.list = list;
        FOOTER_COUNT = hasFooter ? 1 : 0;
        spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                // 此判断中要小心加Footer后引起的数组越界，所以要先加 i < list.size()
                if (i < list.size() && list.get(i) instanceof TabLayoutRecyclerView3Activity.Item) {
                    return 1;
                } else {
                    return gridLayoutManager.getSpanCount();
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_SECTION) {
            return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_tablayout_recyclerview_section, viewGroup, false));
        } else if (i == TYPE_ITEM) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_tablayout_recyclerview_item, viewGroup, false));
        } else {
            return new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_tablayout_recyclerview_section, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemViewHolder, int i) {
        if (itemViewHolder instanceof SectionViewHolder) {
            ((SectionViewHolder) itemViewHolder).textView.setText(((TabLayoutRecyclerView3Activity.Section)list.get(i)).getSecName());

            // 记录菜单组标题的高度（同类ViewHolder高度一致此方法有效，如果同类ViewHolder高度不一致，则需要改变记录规则）
            if (sectionLayoutHeight == 0) {
                sectionLayoutHeight = itemViewHolder.itemView.getHeight();
            }
        } else if (itemViewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) itemViewHolder).textView.setText(((TabLayoutRecyclerView3Activity.Item)list.get(i)).getName());

            // 记录菜单项的高度（同类ViewHolder高度一致此方法有效，如果同类ViewHolder高度不一致，则需要改变记录规则）
            if (itemLayoutHeight == 0) {
                itemLayoutHeight = itemViewHolder.itemView.getHeight();
            }
        } else if (itemViewHolder instanceof FooterViewHolder) {
            int height = itemLayoutHeight * ((int) Math.ceil(lastSectionChildCount / (double) spanCount)) + sectionLayoutHeight;
            itemViewHolder.itemView.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, containerHeight - height));
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + FOOTER_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        } else {
            if (list.get(position) instanceof TabLayoutRecyclerView3Activity.Section) {
                return TYPE_SECTION;
            } else if (list.get(position) instanceof TabLayoutRecyclerView3Activity.Item) {
                return TYPE_ITEM;
            } else {
                return -1;
            }
        }
    }

    /**
     *  菜单组标题ViewHolder
     */
    static class SectionViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }

    }

    /**
     * 菜单项ViewHolder
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }

    }

    /**
     * “脚”，用于填充页面，使得最后一个菜单组可以顶到页面头部
     */
    static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
