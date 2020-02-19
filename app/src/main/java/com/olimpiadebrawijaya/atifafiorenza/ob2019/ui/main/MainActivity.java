package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.main;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.DataModel;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.about_us.AboutUsActivity;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.beranda.BerandaFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita.BeritaFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas.FakultasLainFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas.FakultasResultFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas.FakultasSayaFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.favorit.FavoritFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.CaborFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.jadwal.JadwalFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.profil.ProfileFragment;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DrawerItemCustomAdapter.ClickListener,
        BeritaFragment.OnFragmentInteractionListener{

    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    List<DataModel> drawerItem;

//    private SharedPreferences prefs;
//    private Context context;
//    private TextView nama,fakultas;
//    private CircleImageView picture;


    public ObservableList<String> mJadwalstrList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList =  findViewById(R.id.left_drawer);

        setupToolbar();

        if (getSupportActionBar() != null) actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.navDrawerBeranda);
        updateFragment(new BerandaFragment());

        drawerItem = new ArrayList<>();

        drawerItem.add(new DataModel(R.mipmap.ic_home, R.string.navDrawerBeranda));
        drawerItem.add(new DataModel(R.mipmap.ic_action_jadwal, R.string.navDrawerJadwal));
        drawerItem.add(new DataModel(R.mipmap.ic_action_fakultas_saya, R.string.navDrawerFakSaya));
        drawerItem.add(new DataModel(R.mipmap.ic_action_fakultas_lain, R.string.navDrawerFakLain));
        drawerItem.add(new DataModel(R.mipmap.ic_jumlah_medali_silver, R.string.navDrawerHasil));
        drawerItem.add(new DataModel(R.mipmap.ic_action_favorit, R.string.navDrawerFavorit));
        drawerItem.add(new DataModel(R.mipmap.ic_action_profile, R.string.navDrawerProfil));
        drawerItem.add(new DataModel(R.mipmap.ic_action_info, R.string.navDrawerAbout));


        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, drawerItem);
        adapter.setClickListener(this);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDrawerList.setAdapter(adapter);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        mJadwalstrList = new ObservableArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void itemClicked(View view, int position) {

        switch (position) {
            case 0:
                actionBar.setTitle(R.string.navDrawerBeranda);
                updateFragment(new BerandaFragment());
                break;
            case 1:
                actionBar.setTitle(R.string.navDrawerJadwal);
                updateFragment(new JadwalFragment());
                break;
            case 2:
                actionBar.setTitle(R.string.navDrawerFakSaya);
                updateFragment(new FakultasSayaFragment());
                break;
            case 3:
                actionBar.setTitle(R.string.navDrawerFakLain);
                updateFragment(new FakultasLainFragment());
                break;
            case 4:
                actionBar.setTitle(R.string.navDrawerHasil);
                updateFragment(CaborFragment.newInstance());
                break;
            case 5:
                actionBar.setTitle(R.string.navDrawerFavorit);
                updateFragment(new FavoritFragment());
                break;
            case 6:
                actionBar.setTitle("Profile");
                updateFragment(new ProfileFragment());
                break;
            case 7 :
                navigateToAboutUsActivity();
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }


    private void navigateToAboutUsActivity() {
        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }


    public void updateFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_frame, fragment, "FRAGMENT");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null && fragment instanceof FakultasResultFragment) {
            actionBar.setTitle(R.string.navDrawerFakLain);
            updateFragment(new FakultasLainFragment());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

