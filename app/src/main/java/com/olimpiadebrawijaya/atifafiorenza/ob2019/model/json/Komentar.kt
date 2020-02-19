package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json

object Komentar{
    data class KomentarResponse(var status : String, var message : String, var data : List<KomentarItem>)
    data class KomentarItem(var ID_KOMENTAR : String, var ID_BERITA : String,
                            var NAMA_PENGGUNA : String, var KOMENTAR_PENGGUNA : String)
    data class SendKomentarResponse(var status : String, var message : String, var data : KomentarResponse)
}