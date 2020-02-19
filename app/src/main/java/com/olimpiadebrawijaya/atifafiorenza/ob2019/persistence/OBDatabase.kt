package com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log

@Database(entities = arrayOf(JadwalFavorit::class),
    version = 1)
abstract class OBDatabase : RoomDatabase(){

    abstract fun jadwalFavoritDao() : JadwalFavoritDao

    companion object {
        private const val DATABASE_NAME = "db_orsim"
        private var mInstance: OBDatabase? = null

        fun getInstance(context: Context): OBDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context, OBDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }

    }

}