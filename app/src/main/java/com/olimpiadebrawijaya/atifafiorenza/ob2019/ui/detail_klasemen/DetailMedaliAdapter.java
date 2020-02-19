package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.detail_klasemen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Klasemen;

import java.util.List;

public class DetailMedaliAdapter extends RecyclerView.Adapter<DetailMedaliAdapter.MyViewHolder>{

    private List<Klasemen.MedaliDetail> klasmenList;
    private Context mContext;

    public DetailMedaliAdapter(List<Klasemen.MedaliDetail> klasmenList, Context context) {
        this.klasmenList = klasmenList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_klasmen, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        final Klasemen.MedaliDetail object = klasmenList.get(i);

        int emas = Integer.parseInt(object.getPEROLEHAN_EMAS());
        int perak = Integer.parseInt(object.getPEROLEHAN_PERAK());
        int perunggu = Integer.parseInt(object.getPEROLEHAN_PERUNGGU());
        int total = emas + perak + perunggu;

        holder.fakultasName.setText(object.getNAMA_CABOR());
        holder.medaliGold.setText(object.getPEROLEHAN_EMAS());
        holder.medaliSilver.setText(object.getPEROLEHAN_PERAK());
        holder.medaliBronze.setText(object.getPEROLEHAN_PERUNGGU());

        holder.totalMedali.setText(String.valueOf(total));
    }


    @Override
    public int getItemCount() {
        return klasmenList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemRow;
        private TextView fakultasName;
        private TextView medaliGold;
        private TextView medaliSilver;
        private TextView medaliBronze;
        private TextView totalMedali;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemRow = (LinearLayout) itemView.findViewById(R.id.item_klasemen);
            fakultasName = (TextView) itemView.findViewById(R.id.itemKlasmen_nama);
            medaliGold = (TextView) itemView.findViewById(R.id.itemKlasmen_gold);
            medaliSilver = (TextView) itemView.findViewById(R.id.itemKlasmen_silver);
            medaliBronze = (TextView) itemView.findViewById(R.id.itemKlasmen_bronze);
            totalMedali = (TextView) itemView.findViewById(R.id.itemKlasmen_total);
        }

    }
}
