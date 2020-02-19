package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json

object Jadwal {
    data class JadwalResponse(var status : Boolean, var message : String, var data : List<JadwalData>)
    data class JadwalData(var ID_JADWAL : String, var NAMA_JADWAL : String,
                          var NAMA_FAKULTAS : String, var NAMA_CABOR : String,
                          var KATEGORI_CABOR : String, var JENIS_PERTANDINGAN : String,
                          var WAKTU : String, var VENUE : String,
                          var SKOR : String, var NAMA_FAKULTAS2 : String,
                          var SKOR2 : String, var NAMA_FAKULTAS3 : String,
                          var SKOR3 : String, var NAMA_FAKULTAS4 : String,
                          var SKOR4 : String, var NAMA_FAKULTAS5 : String,
                          var SKOR5 : String, var NAMA_FAKULTAS6 : String,
                          var SKOR6 : String, var NAMA_FAKULTAS7 : String,
                          var SKOR7 : String, var NAMA_FAKULTAS8 : String,
                          var SKOR8 : String, var NAMA_FAKULTAS9 : String,
                          var SKOR9 : String, var NAMA_FAKULTAS10 : String,
                          var SKOR10 : String, var NAMA_FAKULTAS11 : String,
                          var SKOR11 : String, var NAMA_FAKULTAS12 : String,
                          var SKOR12 : String, var NAMA_FAKULTAS13 : String,
                          var SKOR13 : String, var NAMA_FAKULTAS14 : String,
                          var SKOR14 : String, var NAMA_FAKULTAS15 : String,
                          var SKOR15 : String, var NAMA_FAKULTAS16 : String,
                          var SKOR16 : String, var NAMA_FAKULTAS17 : String,
                          var SKOR17 : String)
}