package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.guest;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.DataModel;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.LoginActivity;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.main.DrawerItemCustomAdapter;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.about_us.AboutUsActivity;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.beranda.BerandaFragment;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita.BeritaFragment;

import java.util.ArrayList;
import java.util.List;


public class GuestMainActivity extends AppCompatActivity implements DrawerItemCustomAdapter.ClickListener,
        BeritaFragment.OnFragmentInteractionListener{

    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    List<DataModel> drawerItem;

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

        drawerItem.add(new DataModel(R.mipmap.ic_action_login, R.string.navDrawerLogIn));
        drawerItem.add(new DataModel(R.mipmap.ic_action_info, R.string.navDrawerAbout));

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, drawerItem);
        adapter.setClickListener(this);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDrawerList.setAdapter(adapter);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void itemClicked(View view, int position) {

        switch (position) {
            case 0:
                navigateToLoginActivity();
                break;
            case 1 :
                navigateToAboutUsActivity();
                break;

        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(GuestMainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void navigateToAboutUsActivity() {
        Intent intent = new Intent(GuestMainActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }


    public void updateFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_frame, fragment, "FRAGMENT");
        transaction.commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
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
