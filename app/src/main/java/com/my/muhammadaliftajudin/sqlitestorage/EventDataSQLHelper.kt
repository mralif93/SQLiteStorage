package com.my.muhammadaliftajudin.sqlitestorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class EventDataSQLHelper(context: Context) : SQLiteOpenHelper(context,
    EventDataSQLHelper.DATABASE_NAME, null, EventDataSQLHelper.DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "events.db"
        private val DATABASE_VERSION = 1
        val TABLE = "users"
        val USERNAME = "username"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE(${BaseColumns._ID} integer primary key autoincrement," +
                "$USERNAME text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion >= newVersion) {
            return
        }
    }
}