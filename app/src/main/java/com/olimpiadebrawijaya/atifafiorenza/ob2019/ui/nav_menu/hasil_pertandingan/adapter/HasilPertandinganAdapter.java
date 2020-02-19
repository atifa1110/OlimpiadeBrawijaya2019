package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.HasilPertandingan;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.DetailHasilActivity;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.FakultasConverter;

import java.util.ArrayList;

public class HasilPertandinganAdapter extends RecyclerView.Adapter<HasilPertandinganAdapter.MyViewHolder> {

    private ArrayList<HasilPertandingan.HasilData> mHasilList;
    private Context mContext;

    public HasilPertandinganAdapter(ArrayList<HasilPertandingan.HasilData> hasilList, Context context) {
        mHasilList = hasilList;
        mContext = context;
    }

    @NonNull
    @Override
    public HasilPertandinganAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hasil_pertandingan, parent, false);
        return new HasilPertandinganAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilPertandinganAdapter.MyViewHolder holder, final int position) {
        final HasilPertandingan.HasilData hasil = mHasilList.get(position);

        holder.namaJadwal.setText(hasil.getNAMA_JADWAL());

        int fakultas1Res = FakultasConverter.getFakultasDrawable(hasil.getNAMA_FAKULTAS());
        holder.team1Res.setImageResource(fakultas1Res);
        holder.team1Name.setText(hasil.getNAMA_FAKULTAS());
        holder.team1Score.setText(hasil.getSKOR());

        int fakultas2Res = FakultasConverter.getFakultasDrawable(hasil.getNAMA_FAKULTAS2());
        holder.team2Res.setImageResource(fakultas2Res);
        holder.team2Name.setText(hasil.getNAMA_FAKULTAS2());
        holder.team2Score.setText(hasil.getSKOR2());

        holder.lombaVersus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasil.getJENIS_PERTANDINGAN().equalsIgnoreCase("Versus")){
                    Intent intent = new Intent(mContext, DetailHasilActivity.class);
                    intent.putExtra("detailHasil", hasil.getHASIL());
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHasilList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView lombaVersus;
        TextView namaJadwal;
        ImageView team1Res;
        TextView team1Name;
        TextView team1Score;
        ImageView team2Res;
        TextView team2Name;
        TextView team2Score;

        public MyViewHolder(View view) {
            super(view);
            lombaVersus = view.findViewById(R.id.lomba_versus);
            namaJadwal = view.findViewById(R.id.nama_jadwal_tv);
            team1Res = view.findViewById(R.id.cardScore_team1Res);
            team1Name = view.findViewById(R.id.cardScore_team1Nama);
            team1Score = view.findViewById(R.id.cardScore_team1Score);
            team2Res = view.findViewById(R.id.cardScore_team2Res);
            team2Name = view.findViewById(R.id.cardScore_team2Nama);
            team2Score = view.findViewById(R.id.cardScore_team2Score);
        }
    }

}

