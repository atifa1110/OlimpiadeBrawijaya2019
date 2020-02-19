package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.User
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.guest.GuestMainActivity
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.main.MainActivity
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.PreferenceHelper
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.PreferenceHelper.set
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.PreferenceHelper.get
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cv_logo_login.bringToFront()

        val prefs = PreferenceHelper.defaultPrefs(this)
        if(prefs["logged_in", false]!!){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        loginBtn_login.setOnClickListener{
            login()
        }

        loginGuest_login.setOnClickListener {
            val intent = Intent(this@LoginActivity, GuestMainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    private fun login() {

        val nim = nim_et_login.text.toString().trim()
        val password = password_et_login.text.toString().trim()

        if(nim.isBlank() && password.isBlank()){
            Toast.makeText(this@LoginActivity, "Tolong Masukkan nim dan password", Toast.LENGTH_SHORT).show()
        }else if(nim.isBlank()){
            Toast.makeText(this@LoginActivity, "Tolong Masukkan nim", Toast.LENGTH_SHORT).show()
        }else if(password.isBlank()){
            Toast.makeText(this@LoginActivity, "Tolong Masukkan password", Toast.LENGTH_SHORT).show()
        }else{
            showProgressDialog()
            val user = User.UserLogin(nim, password)
            RestClient.loginService.login(nim,password)
                .enqueue(object : retrofit2.Callback<User.LoginResponse> {
                    override fun onFailure(call: Call<User.LoginResponse>?, t: Throwable?) {
                        alertDialog.dismiss()
                        Toast.makeText(this@LoginActivity, "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<User.LoginResponse>?, response: Response<User.LoginResponse>?) {
                        alertDialog.dismiss()
                        if (response?.body()?.status.equals("false")) {
                            Toast.makeText(this@LoginActivity, "Password atau Username salah", Toast.LENGTH_SHORT).show()
                        }else{
                            onLoginSucces(response?.body())
                        }
                    }
                })
        }

    }


    private fun onLoginSucces(body: User.LoginResponse?) {
        val prefs = PreferenceHelper.defaultPrefs(this)

        prefs["logged_in"] = true
        prefs["nama_lengkap"] = body?.nama
        prefs["nim"] = body?.nim
        prefs["fak"] = body?.fak
        prefs["foto"] = body?.foto
        //prefs["enroll"] = body?.enroll

//        if(body?.enroll!!){
//            navigateToEnrollActivity()
//        }else{
            navigateToMainActivity()
        //}

    }

//    private fun navigateToEnrollActivity() {
//        val intent = Intent(this@LoginActivity, EnrollKeyActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun showProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.getLayoutInflater()
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
