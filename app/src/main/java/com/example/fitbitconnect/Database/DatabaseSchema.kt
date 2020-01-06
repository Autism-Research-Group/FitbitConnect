package com.example.fitbitconnect.Database

import android.provider.BaseColumns


object UserContract {
    object UserEntry: BaseColumns {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME_USERNAME = "username"
        const val COLUMN_USERID = "user_id"
    }

    const val SQL_CREATE_USER_TABLE =
        "CREATE TABLE ${UserEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${UserEntry.COLUMN_NAME_USERNAME} TEXT NOT NULL," +
                "${UserEntry.COLUMN_USERID} TEXT NOT NULL UNIQUE)"

    const val SQL_DELETE_USER_TABLE =
        "DROP TABLE IF EXISTS ${UserEntry.TABLE_NAME}"
}


object HeartRateContract {
    object HeartRateEntry: BaseColumns {
        const val TABLE_NAME = "heartrate"
        const val COLUMN_MIN = "min"
        const val COLUMN_MAX = "max"
        const val COLUMN_NAME = "type"
        const val COLUMN_DATETIME = "datetime"
        const val COLUMN_USER_ID = "fk_user_id"
    }

    const val SQL_CREATE_HEARTRATE_TABLE =
        "CREATE TABLE ${HeartRateEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY NOT NULL," +
                "${HeartRateEntry.COLUMN_MAX} INTEGER," +
                "${HeartRateEntry.COLUMN_MIN} INTEGER," +
                "${HeartRateEntry.COLUMN_NAME} TEXT NOT NULL," +
                "${HeartRateEntry.COLUMN_DATETIME} DATETIME NOT NULL," +
                "${HeartRateEntry.COLUMN_USER_ID} INTEGER NOT NULL," +
                "FOREIGN KEY(${HeartRateEntry.COLUMN_USER_ID}) REFERENCES ${UserContract.UserEntry.TABLE_NAME}(${BaseColumns._ID}))"

    const val SQL_DELETE_HEARTRATE_TABLE =
        "DROP TABLE IF EXISTS ${HeartRateEntry.TABLE_NAME}"
}
