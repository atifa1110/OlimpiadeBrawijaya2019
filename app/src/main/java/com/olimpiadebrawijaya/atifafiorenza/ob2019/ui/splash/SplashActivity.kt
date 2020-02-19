package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var animation : Animation
    private lateinit var mDelayHandler : Handler
    private val SPLASH_DELAY : Long = 3000;

    internal val mRunnable : Runnable = Runnable {
        if(!isFinishing){
            navigateToLoginActivitiy()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        //animation = AnimationUtils.loadAnimation(this@SplashActivity,R.anim.blinking_animation)
        //imageSplash_splash.startAnimation(animation)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable,SPLASH_DELAY)
    }

    private fun navigateToLoginActivitiy() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onResume() {
        super.onResume()

        val result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this@SplashActivity)
        if (result == ConnectionResult.SUCCESS) {

        } else if (result == ConnectionResult.SERVICE_MISSING || result == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
            result == ConnectionResult.SERVICE_DISABLED) {
            GoogleApiAvailability.getInstance().getErrorDialog(this@SplashActivity, result, 99).show()
        }
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
