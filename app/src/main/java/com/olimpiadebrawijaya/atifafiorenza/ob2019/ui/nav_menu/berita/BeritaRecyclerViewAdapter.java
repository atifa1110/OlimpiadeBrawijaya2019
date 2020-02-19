package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.Const;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.News;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.berita_detail.BeritaDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BeritaRecyclerViewAdapter extends RecyclerView.Adapter<BeritaRecyclerViewAdapter.ViewHolder> {

    private List<com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.News.NewsData> mNewsList;
    private Context mContext;


    public BeritaRecyclerViewAdapter(List<com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.News.NewsData> newsList, Context context) {
        this.mNewsList = newsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public BeritaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BeritaRecyclerViewAdapter.ViewHolder holder, final int position) {

        final News.NewsData news = mNewsList.get(position);
        holder.mTvHeadlineBerita.setText(news.getJUDUL_BERITA());
        holder.mTvIsiBerita.setText(news.getISI_BERITA());
        Picasso.with(mContext)
                .load(news.getFOTO_BERITA())
                .resize(800, 800)
                .into(holder.iv_item_news);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BeritaDetailActivity.class);
                intent.putExtra(Const.KEY.BERITA.BERITA_ID, news.getID_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_JUDUL, news.getJUDUL_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_ISI, news.getISI_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_GAMBAR, news.getFOTO_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_TANGGAL, news.getWAKTU_BERITA());
                context.startActivity(intent);
            }
        });

        holder.mBtnBacaSelengkapnyaBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BeritaDetailActivity.class);
                intent.putExtra(Const.KEY.BERITA.BERITA_ID, news.getID_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_JUDUL, news.getJUDUL_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_ISI, news.getISI_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_GAMBAR, news.getFOTO_BERITA());
                intent.putExtra(Const.KEY.BERITA.BERITA_TANGGAL, news.getWAKTU_BERITA());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("news", "getItemCount: " + mNewsList.size());
        return mNewsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvHeadlineBerita;
        public final TextView mTvIsiBerita;
        public final Button mBtnBacaSelengkapnyaBerita;
        public final ImageView iv_item_news;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvHeadlineBerita = (TextView) view.findViewById(R.id.tv_headline_berita);
            mTvIsiBerita = (TextView) view.findViewById(R.id.tv_isi_berita);
            mBtnBacaSelengkapnyaBerita = (Button) view.findViewById(R.id.btn_baca_selengkapnya_berita);
            iv_item_news = (ImageView) view.findViewById(R.id.iv_item_news);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvIsiBerita.getText() + "'";
        }
    }
}