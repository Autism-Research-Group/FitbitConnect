package com.example.fitbitconnect.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(UserContract.SQL_CREATE_USER_TABLE)
        db.execSQL(HeartRateContract.SQL_CREATE_HEARTRATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // For now we will delete the db and start over but in future this must be changed
        // TODO: Store data for upgrade
        db.execSQL(UserContract.SQL_DELETE_USER_TABLE)
        db.execSQL(HeartRateContract.SQL_DELETE_HEARTRATE_TABLE)
        onCreate(db)
    }


    // Deletes the tables and then rebuilds them
    fun rebuildDB(db: SQLiteDatabase) {
        db.execSQL(UserContract.SQL_DELETE_USER_TABLE)
        db.execSQL(HeartRateContract.SQL_DELETE_HEARTRATE_TABLE)
        onCreate(db)
    }


    companion object {
        const val DATABASE_NAME = "Fitbit.db"
        const val DATABASE_VERSION = 1 // NOTE: If scheme is changed the version must be incremented
    }
}