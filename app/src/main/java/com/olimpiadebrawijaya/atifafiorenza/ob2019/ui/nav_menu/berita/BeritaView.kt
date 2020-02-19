package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita

interface BeritaView {
    abstract fun showToastMessage(message: String)

    abstract fun toBeritaDetailActivity()

    abstract fun showProgressDialog()

    abstract fun hideProgressDialog()
}