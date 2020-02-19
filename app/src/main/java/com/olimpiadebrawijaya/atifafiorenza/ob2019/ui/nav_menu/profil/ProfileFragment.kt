package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.profil


import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.LoginActivity
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.PreferenceHelper.set
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

    lateinit var prefs : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = PreferenceManager.getDefaultSharedPreferences(context)

        namaTv.text = prefs.getString("nama_lengkap", "")
        fakultasTv.text = prefs.getString("fak", "")
        val nim = prefs.getString("nim", "")

        nimTv.text = nim

        val photo = prefs.getString("foto", "")

        Picasso.with(context)
            .load(photo)
            .placeholder(R.color.material_grey_300)
            .into(profileImage)

        keluarBtn.setOnClickListener{
            showdialog()
        }

    }

    private fun showdialog() {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setMessage("Apakah Anda yakin untuk keluar dari aplikasi?")
            .setPositiveButton("Keluar") { _, _ ->
                prefs["logged_in"] = false
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        }
        alertDialog.show()

    }
}
