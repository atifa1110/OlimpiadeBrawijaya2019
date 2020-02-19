package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json

object Klasemen {
    data class KlasemenResponse(var status: String = "", var message: String = "",
                                var data: List<Medali>? = null)

    data class KlasemenResponse2(var status: String = "", var message: String = "",
                                 var data: List<MedaliDetail>? = null)

    data class Medali(var ID_FAKULTAS: String = "", var NAMA_FAKULTAS: String = "",
                      var PEROLEHAN_EMAS: String = "", var PEROLEHAN_PERAK: String = "",
                      var PEROLEHAN_PERUNGGU: String = "")

    data class MedaliDetail(var ID_FAKULTAS: String = "", var NAMA_FAKULTAS: String = "",
                            var NAMA_CABOR: String = "", var PEROLEHAN_EMAS: String = "",
                            var PEROLEHAN_PERAK: String = "", var PEROLEHAN_PERUNGGU: String = "")
}