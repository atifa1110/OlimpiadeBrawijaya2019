package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view

data class ActualJadwal(var idJadwal : Int = 0, var caborRes : Int? = 0,
                        var namaJadwal : String = "", var caborName : String = "",
                        var caborKet : String = "", var caborDate : String = "",
                        var caborPlace : String = "", var team1Res : Int  = 0,
                        var team1Name : String  = "", var team1Score : String  = "",
                        var team2Res : Int  = 0, var team2Name : String = "",
                        var team2Score : String = "", var isVersus : Boolean = false,
                        var isFavorit : Boolean = true)
