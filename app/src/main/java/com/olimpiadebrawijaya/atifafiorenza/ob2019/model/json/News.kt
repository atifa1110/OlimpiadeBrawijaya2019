package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json

object News{
    data class NewsResponse(var status : String, var message : String, var data : List<NewsData>)
    data class NewsData(var ID_BERITA : String, var JUDUL_BERITA : String,
                        var WAKTU_BERITA : String, var ISI_BERITA : String,
                        var FOTO_BERITA : String, var LIKE_POST : String)
}