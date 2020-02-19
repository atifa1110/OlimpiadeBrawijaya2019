package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita;


import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * A simple [Fragment] subclass.
 *
 */
public class BeritaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "news";
    public ObservableList<News.NewsData> mNewsList;
    private SwipeRefreshLayout mBeritaSwipeToRefresh;
    private RecyclerView mRecyclerView;


    public BeritaFragment() {
    }

    public static BeritaFragment newInstance(String param1, String param2) {
        return new BeritaFragment();
    }

    // MARK : - Activity Lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsList = new ObservableArrayList<>();

        mRecyclerView =  view.findViewById(R.id.berita_list);
        mBeritaSwipeToRefresh = view.findViewById(R.id.berita_swipeToRefresh);
        mBeritaSwipeToRefresh.setOnRefreshListener(this);

        fetchData();

    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        mBeritaSwipeToRefresh.setRefreshing(true);
        Log.d(TAG, "fetchData: ");
        RestClient.beritaService.getAllNews().enqueue(new Callback<News.NewsResponse>() {
            @Override
            public void onResponse(Call<News.NewsResponse> call, Response<News.NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<News.NewsData> responseList = response.body().getData();
                    if (responseList != null) {
                        mNewsList.clear();
                        mNewsList.addAll(responseList);
                        setupRecyclerView(mRecyclerView);
                    } else {
                        Toast.makeText(getContext(), "Tidak ada data.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Terjadi kesalahan network. Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News.NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan network. Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
            }
        });
        mBeritaSwipeToRefresh.setRefreshing(false);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(
                new BeritaRecyclerViewAdapter(
                        mNewsList,
                        getContext()
                ));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
