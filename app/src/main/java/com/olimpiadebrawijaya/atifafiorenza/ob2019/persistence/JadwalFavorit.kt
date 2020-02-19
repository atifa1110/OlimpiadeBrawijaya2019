package com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "jadwal_favorit")
data class JadwalFavorit (
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorit_id")
    var favorit_id: Int = 0,

    @ColumnInfo(name = "jadwal_id")
    var jadwalId : Int = 0,

    @ColumnInfo(name = "jadwal_name")
    var jadwalName : String = "",

    @ColumnInfo(name = "cabor_res")
    var caborRes : Int = 0,

    @ColumnInfo(name = "cabor_name")
    var caborName : String = "",

    @ColumnInfo(name = "cabor_ket")
    var caborKet : String = "",

    @ColumnInfo(name = "cabor_date")
    var caborDate : String = "",

    @ColumnInfo(name = "cabor_place")
    var caborPlace : String = "",

    @ColumnInfo(name = "team1_res")
    var team1Res : Int? = 0,

    @ColumnInfo(name = "team1_name")
    var team1Name : String? = "",

    @ColumnInfo(name = "team1_score")
    var team1Score : Int? = 0,

    @ColumnInfo(name = "team2_res")
    var team2Res : Int? = 0,

    @ColumnInfo(name = "team2_name")
    var team2Name : String? = "",

    @ColumnInfo(name = "team2_score")
    var team2Score : Int? = 0,

    @ColumnInfo(name = "versus")
    var versus : Int = 0,

    @ColumnInfo(name = "favorited")
    var favorited : Int = 0

)