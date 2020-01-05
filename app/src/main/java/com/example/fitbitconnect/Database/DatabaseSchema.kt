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
                "${UserEntry.COLUMN_USERID} TEXT NOT NULL)"

    const val SQL_DELETE_USER_TABLE =
        "DROP TABLE IF EXISTS ${UserEntry.TABLE_NAME}"
}
