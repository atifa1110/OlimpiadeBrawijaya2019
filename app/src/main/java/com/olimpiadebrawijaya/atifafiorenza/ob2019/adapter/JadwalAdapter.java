package com.olimpiadebrawijaya.atifafiorenza.ob2019.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.ActualJadwal;
import java.util.ArrayList;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.MyViewHolder> {

    private ArrayList<ActualJadwal> mJadwalList;
    private Context mContext;
    onAdapterClickListener onAdapterclicklistener;

    public interface onAdapterClickListener {
        public void onFavoriteClicked(View view, int position);
    }

    public JadwalAdapter(ArrayList<ActualJadwal> jadwalList, Context context, onAdapterClickListener onAdapterClickListener) {
        mJadwalList = jadwalList;
        mContext = context;
        this.onAdapterclicklistener = onAdapterClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_jadwal, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        ActualJadwal jadwal = mJadwalList.get(position);

        holder.namaJadwal.setText(jadwal.getNamaJadwal());
        //holder.iconCabor.setImageResource(jadwal.getCaborRes());
        holder.caborName.setText(jadwal.getCaborName());
        holder.caborKeterangan.setText(jadwal.getCaborKet());
        holder.caborDate.setText(jadwal.getCaborDate());
        holder.caborLocation.setText(jadwal.getCaborPlace());
        if(jadwal.isVersus()){
            holder.caborVersus.setVisibility(View.VISIBLE);
            holder.team1Res.setImageResource(jadwal.getTeam1Res());
            holder.team1Name.setText(jadwal.getTeam1Name());
            holder.team1Score.setText(jadwal.getTeam1Score());
            holder.team2Res.setImageResource(jadwal.getTeam2Res());
            holder.team2Name.setText(jadwal.getTeam2Name());
            holder.team2Score.setText(jadwal.getTeam2Score());
        }else{
            holder.caborVersus.setVisibility(View.GONE);
        }

        if(jadwal.isFavorit()){
            holder.iconFavorit.setImageResource(R.mipmap.ic_action_favorit);
        }else{
            holder.iconFavorit.setImageResource(R.mipmap.ic_favorit);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean loggedIn = prefs.getBoolean("logged_in", false);

        if(!loggedIn){
            holder.iconFavorit.setVisibility(View.GONE);
        }

        holder.iconFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onAdapterclicklistener != null) {
                    onAdapterclicklistener.onFavoriteClicked(view, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mJadwalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namaJadwal;
        ImageView iconFavorit;
        ImageView iconCabor;
        TextView caborName;
        TextView caborKeterangan;
        TextView caborDate;
        TextView caborLocation;
        RelativeLayout caborVersus;
        ImageView team1Res;
        TextView team1Name;
        TextView team1Score;
        ImageView team2Res;
        TextView team2Name;
        TextView team2Score;

        public MyViewHolder(@NonNull View view) {
            super(view);
            namaJadwal = view.findViewById(R.id.nama_jadwal);
            iconFavorit = view.findViewById(R.id.icon_favorit);
            iconCabor = view.findViewById(R.id.cabor_icon);
            caborName = view.findViewById(R.id.cabor_name);
            caborDate = view.findViewById(R.id.cabor_date);
            caborKeterangan = view.findViewById(R.id.cabor_keterangan);
            caborLocation = view.findViewById(R.id.cabor_place);
            caborVersus = (RelativeLayout) view.findViewById(R.id.cabor_versus);
            team1Res = (ImageView) view.findViewById(R.id.cardScore_team1Res);
            team1Name = (TextView) view.findViewById(R.id.cardScore_team1Nama);
            team1Score = (TextView) view.findViewById(R.id.cardScore_team1Score);
            team2Res = (ImageView) view.findViewById(R.id.cardScore_team2Res);
            team2Name = (TextView) view.findViewById(R.id.cardScore_team2Nama);
            team2Score = (TextView) view.findViewById(R.id.cardScore_team2Score);
        }
    }
}
