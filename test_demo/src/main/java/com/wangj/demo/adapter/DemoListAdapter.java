package com.wangj.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.demo.R;
import com.wangj.demo.model.DemoVO;

import java.util.List;

public class DemoListAdapter extends RecyclerView.Adapter<DemoListAdapter.DemoItemHolder> {

    private List<DemoVO> list;

    public DemoListAdapter(@NonNull List<DemoVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DemoItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DemoItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_demo, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DemoItemHolder demoItemHolder, int i) {
        final DemoVO itemVo = list.get(i);
        demoItemHolder.tvName.setText(itemVo.getName());
        demoItemHolder.tvPath.setText(itemVo.getPath());

        demoItemHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(itemVo.getPath()).navigation();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DemoItemHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView tvName;
        TextView tvPath;

        public DemoItemHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            tvName = itemView.findViewById(R.id.tvName);
            tvPath = itemView.findViewById(R.id.tvPath);
        }
    }
}
