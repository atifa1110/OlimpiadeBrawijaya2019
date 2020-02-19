package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita

import android.app.Activity

interface BeritaInteractor {

    interface OnFetchBeritaFinishedListener {

        fun onError()

        fun onSuccess()

    }

    fun fetchData(listener: OnFetchBeritaFinishedListener, activity: Activity)

}