package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.berita_detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.Const;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Komentar;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.berita_detail.adapter.KomentarAdapter;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BeritaDetailActivity extends AppCompatActivity implements BeritaDetailView, EditText.OnTouchListener {

    private TextView mTvHeadlineBeritaDetail;
    private TextView mTvDetailBeritaIsiBerita;
    private TextView mTvDetailBeritaTanggal;
    private ImageView mIvDetailBerita;
    private TextView mTvTidakAdaKomentar;
    private EditText mEtKomentar;
    private TextView mTvKirim;
    private RecyclerView mRecyclerView;
    private BeritaDetailPresenter mBeritaDetailPresenter;

    private ActionBar mActionBar;
    private SimpleDateFormat df;
    private KomentarAdapter mAdapter;
    private ObservableList<Komentar.KomentarItem> komentarList;

    String idBeritaExtras;
    String judulBerita;
    String isiBerita;
    String gambarBerita;
    String tanggalBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // init views
        mTvHeadlineBeritaDetail = findViewById(R.id.tv_headline_berita_detail);
        mTvDetailBeritaIsiBerita = findViewById(R.id.detailBerita_isiBerita);
        mTvDetailBeritaTanggal = findViewById(R.id.detailBerita_tanggal);
        mIvDetailBerita = findViewById(R.id.iv_headline_berita_detail);
        mTvTidakAdaKomentar = findViewById(R.id.tv_tidak_ada_komentar);
        mEtKomentar = findViewById(R.id.et_komentar);
        mTvKirim = findViewById(R.id.tv_kirim_komentar);
        mRecyclerView = findViewById(R.id.recycler_view);

        // init presenter
        mBeritaDetailPresenter = new BeritaDetailPresenterImpl(this);
        mBeritaDetailPresenter.attemptGetDataFromPreviousActivity();

        // init dateFormatter
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean loggedIn = prefs.getBoolean("logged_in", false);

        if(!loggedIn){
            LinearLayout komentarContainer = findViewById(R.id.komentar_container);
            komentarContainer.setVisibility(View.GONE);
        }

        komentarList = new ObservableArrayList<>();

        mEtKomentar.setOnTouchListener(this);
        mEtKomentar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    mTvKirim.setEnabled(false);
                    mTvKirim.setTextColor(ContextCompat.getColor(
                        BeritaDetailActivity.this, R.color.material_grey_400));
                } else {
                    mTvKirim.setEnabled(true);
                    mTvKirim.setTextColor(ContextCompat.getColor(
                        BeritaDetailActivity.this, R.color.light_blue));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mTvKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKomentar();
            }
        });


    }

    private void sendKomentar() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nama = prefs.getString("nama_lengkap", "");
        String komentar = mEtKomentar.getText().toString().trim();
        mEtKomentar.setText("");

        RestClient.komentarService.setKomentarBerita(
            idBeritaExtras, nama, komentar)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Komentar.SendKomentarResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Komentar.SendKomentarResponse sendKomentarResponse) {
                    Toast.makeText(BeritaDetailActivity.this, "Komentar berhasil dikirim", Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtKomentar.getWindowToken(), 0);
                    mEtKomentar.setFocusable(false);
                    mEtKomentar.setFocusableInTouchMode(true);
                    fetchData(idBeritaExtras);
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(BeritaDetailActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {

                }
            });

    }

    private void fetchData(String idBeritaExtras) {
        RestClient.komentarService.getKomentarBerita(idBeritaExtras)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Komentar.KomentarResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Komentar.KomentarResponse komentarResponse) {

                    String message = komentarResponse.getMessage();
                    List<Komentar.KomentarItem> responseList = komentarResponse.getData();
                    if (responseList != null) {
                        komentarList.clear();
                        komentarList.addAll(responseList);
                        mTvTidakAdaKomentar.setVisibility(View.GONE);
                        setupRecyclerView(mRecyclerView);
                    } else {
                        mTvTidakAdaKomentar.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(
            new KomentarAdapter(
                    komentarList,
            this
        ));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateUI() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idBeritaExtras = extras.getString(Const.KEY.BERITA.BERITA_ID);
            judulBerita = extras.getString(Const.KEY.BERITA.BERITA_JUDUL);
            isiBerita = extras.getString(Const.KEY.BERITA.BERITA_ISI);
            gambarBerita = extras.getString(Const.KEY.BERITA.BERITA_GAMBAR);
            tanggalBerita = extras.getString(Const.KEY.BERITA.BERITA_TANGGAL);
            fetchData(idBeritaExtras);
            setBeritaView();
        }
    }

    private void setBeritaView() {
        if (mActionBar != null) {
            mActionBar.setTitle(judulBerita);
        }

        // update komponen ui
        mTvHeadlineBeritaDetail.setText(judulBerita);
        mTvDetailBeritaTanggal.setText(tanggalBerita);
        mTvDetailBeritaIsiBerita.setText(isiBerita);
        Picasso.with(getApplicationContext())
            .load(gambarBerita)
            .resize(800, 800)
            .into(mIvDetailBerita);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.et_komentar) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                view.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            }
        }
        return false;
    }
}

