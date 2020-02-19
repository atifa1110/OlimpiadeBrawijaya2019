package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.detail_klasemen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Klasemen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class DetailKlasemenActivity extends AppCompatActivity {

    private String fakultas;
    private String idFakultas;

    public RecyclerView mRvKlasmen;
    private ArrayList<Klasemen.MedaliDetail> mKlasmenList;
    private DetailMedaliAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_klasemen);

        if (getIntent().getExtras() != null) {
            fakultas = getIntent().getExtras().getString("fakultas");
            idFakultas = getIntent().getExtras().getString("id_fakultas");
        }

        getSupportActionBar().setTitle(fakultas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRvKlasmen = findViewById(R.id.klasmen_recyclerView);

        mKlasmenList = new ArrayList<>();

        setupRecyclerView(mRvKlasmen);

        fetchData();

    }


    private void fetchData() {
        RestClient.medaliService.getListMedalibyFakultas(idFakultas).enqueue(new Callback<Klasemen.KlasemenResponse2>() {
            @Override
            public void onResponse(Call<Klasemen.KlasemenResponse2> call, Response<Klasemen.KlasemenResponse2> response) {
                List<Klasemen.MedaliDetail> responseList = response.body().getData();
                if(responseList != null){
                    mKlasmenList.clear();
                    mKlasmenList.addAll(responseList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Klasemen.KlasemenResponse2> call, Throwable t) {
                Toast.makeText(DetailKlasemenActivity.this,
                        "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        mAdapter = new DetailMedaliAdapter(
                mKlasmenList,
                this
        );

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
