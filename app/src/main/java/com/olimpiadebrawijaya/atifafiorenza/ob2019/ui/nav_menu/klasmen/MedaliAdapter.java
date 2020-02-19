package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.klasmen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Klasemen;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.detail_klasemen.DetailKlasemenActivity;

import java.util.List;

public class MedaliAdapter extends RecyclerView.Adapter<MedaliAdapter.MyViewHolder> {

    private List<Klasemen.Medali> klasmenList;
    private Context mContext;

    public MedaliAdapter(List<Klasemen.Medali> klasmenList, Context context) {
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

        final Klasemen.Medali object = klasmenList.get(i);

        int emas = Integer.parseInt(object.getPEROLEHAN_EMAS());
        int perak = Integer.parseInt(object.getPEROLEHAN_PERAK());
        int perunggu = Integer.parseInt(object.getPEROLEHAN_PERUNGGU());
        int total = emas + perak + perunggu;

        holder.fakultasName.setText(object.getNAMA_FAKULTAS());
        holder.medaliGold.setText(object.getPEROLEHAN_EMAS());
        holder.medaliSilver.setText(object.getPEROLEHAN_PERAK());
        holder.medaliBronze.setText(object.getPEROLEHAN_PERUNGGU());

        holder.totalMedali.setText(String.valueOf(total));

        holder.itemRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetailKlasemen(object.getNAMA_FAKULTAS(), object.getID_FAKULTAS());
            }
        });

    }

    private void navigateToDetailKlasemen(String fakultas, String id_fakultas) {
        Intent intent = new Intent(mContext, DetailKlasemenActivity.class);
        intent.putExtra("fakultas", fakultas);
        intent.putExtra("id_fakultas", id_fakultas);
        mContext.startActivity(intent);
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
            itemRow = itemView.findViewById(R.id.item_klasemen);
            fakultasName = itemView.findViewById(R.id.itemKlasmen_nama);
            medaliGold = itemView.findViewById(R.id.itemKlasmen_gold);
            medaliSilver = itemView.findViewById(R.id.itemKlasmen_silver);
            medaliBronze = itemView.findViewById(R.id.itemKlasmen_bronze);
            totalMedali = itemView.findViewById(R.id.itemKlasmen_total);
        }

    }

}
