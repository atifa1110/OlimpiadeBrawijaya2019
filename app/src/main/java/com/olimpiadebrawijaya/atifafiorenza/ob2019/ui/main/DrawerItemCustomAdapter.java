package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.DataModel;

import java.util.List;

public class DrawerItemCustomAdapter extends RecyclerView.Adapter<DrawerItemCustomAdapter.MyViewHolder>{
    Context mContext;
    List<DataModel> data;
    private ClickListener clicklistener = null;

    public DrawerItemCustomAdapter(Context mContext, List<DataModel> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nav_menu, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        DataModel navItem = data.get(i);
        holder.icon.setImageResource(navItem.icon);
        holder.name.setText(navItem.name);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView icon;
        public TextView name;
        public FrameLayout container;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            container = view.findViewById(R.id.container_navMenu);
            icon = view.findViewById(R.id.navItem_icon);
            name = view.findViewById(R.id.navItem_title);

        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }
    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }
}
