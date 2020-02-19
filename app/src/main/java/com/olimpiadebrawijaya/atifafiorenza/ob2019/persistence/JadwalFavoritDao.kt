package com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence


import android.arch.persistence.room.*
import io.reactivex.Flowable


@Dao
interface JadwalFavoritDao {
    @get:Query("SELECT * FROM jadwal_favorit")
    val allJadwalFavorit : Flowable<List<JadwalFavorit>>

    @Insert
    fun insertJadwalFavorit(vararg jadwal: JadwalFavorit)

    @Update
    fun updateJadwalFavorit(vararg jadwal: JadwalFavorit)

    @Delete
    fun deleteJadwalFavorit(jadwal: JadwalFavorit)

    @Query("DELETE FROM jadwal_favorit WHERE jadwal_id=:jadwalId")
    fun deleteJadwalFavoritById(jadwalId : Int)
}