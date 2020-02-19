package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.about_us;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.widget.ImageView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.squareup.picasso.Picasso;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView mIvMaskotContainer;
    private ImageView mIvSponsor;
    private ImageView mIvMediaPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mIvMaskotContainer = (ImageView) findViewById(R.id.iv_maskot_container_about_us);
        mIvSponsor = (ImageView) findViewById(R.id.iv_sponsor1);
        //mIvMediaPartner = (ImageView) findViewById(R.id.iv_media_partner_about_us);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        String mDrawableName = "maskot";
        int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        Picasso.with(getApplicationContext())
                .load(resId)
                .resize(400, 450)
                .into(mIvMaskotContainer);

//
//        String mDrawableMediaPartnerName = "mediapartner_baru";
//        int mediaPartnerResId = getResources().getIdentifier(mDrawableMediaPartnerName, "drawable", getPackageName());
//        Picasso.with(getApplicationContext())
//                .load(mediaPartnerResId)
//                .resize(width, height / 2)
//                .into(mIvMediaPartner);
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


}
