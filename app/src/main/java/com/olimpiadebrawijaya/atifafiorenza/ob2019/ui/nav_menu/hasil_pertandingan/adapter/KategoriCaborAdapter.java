package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.HasilPertandingan;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.HasilPertandinganActivity;

import java.util.List;

public class KategoriCaborAdapter extends RecyclerView.Adapter<KategoriCaborAdapter.MyViewHolder>{

    private List<HasilPertandingan.KategoriData> kategoriList;
    private Context context;

    public KategoriCaborAdapter(Context context, List<HasilPertandingan.KategoriData> kategoriList) {
        this.context = context;
        this.kategoriList = kategoriList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_kategori_cabor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HasilPertandingan.KategoriData kategori = kategoriList.get(position);

        holder.namaKategoriTv.setText(kategori.getKATEGORI_CABOR());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HasilPertandinganActivity.class);
                intent.putExtra("caborName", kategori.getNAMA_CABOR());
                intent.putExtra("kategoriCabor", kategori.getKATEGORI_CABOR());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView namaKategoriTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            namaKategoriTv =  itemView.findViewById(R.id.nama_kategori);
        }

    }

}

