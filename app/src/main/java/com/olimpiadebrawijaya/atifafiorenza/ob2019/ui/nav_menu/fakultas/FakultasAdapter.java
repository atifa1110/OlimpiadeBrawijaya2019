package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.Fakultas;

import java.util.List;

/**
 * Created by amira on 7/5/2017.
 */

public class FakultasAdapter extends RecyclerView.Adapter<FakultasAdapter.MyViewHolder> {

    List<Fakultas> fakultasList;
    Context context;
    private ClickListener clicklistener = null;

    public FakultasAdapter(Context context, List<Fakultas> fakultasList) {
        this.context = context;
        this.fakultasList = fakultasList;
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_fakultas_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Fakultas fakultas = fakultasList.get(i);
        holder.fakultasIcon.setImageResource(fakultas.getFakultasIconRes());
        holder.fakultasName.setText(fakultas.getFakultasNameRes());
    }

    @Override
    public int getItemCount() {
        return fakultasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout container;
        ImageView fakultasIcon;
        TextView fakultasName;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            container = itemView.findViewById(R.id.container);
            fakultasIcon =  itemView.findViewById(R.id.fakultas_icon);
            fakultasName =  itemView.findViewById(R.id.fakultas_name);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }

}