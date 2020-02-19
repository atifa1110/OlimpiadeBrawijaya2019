package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.berita_detail.adapter;

import android.content.Context;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Komentar;

public class KomentarAdapter extends RecyclerView.Adapter<KomentarAdapter.MyViewHolder> {

    ObservableList<Komentar.KomentarItem> mKomentarList;
    Context mContext;

    public KomentarAdapter(ObservableList<Komentar.KomentarItem> komentarList, Context context) {
        mKomentarList = komentarList;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_komentar, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Komentar.KomentarItem komentar = mKomentarList.get(position);
        holder.namaPengguna.setText(komentar.getNAMA_PENGGUNA());
        holder.komentarPengguna.setText(komentar.getKOMENTAR_PENGGUNA());
    }

    @Override
    public int getItemCount() {
        return mKomentarList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namaPengguna;
        TextView komentarPengguna;

        public MyViewHolder(View view) {
            super(view);
            namaPengguna = view.findViewById(R.id.nama_pengguna);
            komentarPengguna = view.findViewById(R.id.komentar_pengguna);
        }
    }

}
