/*
 * Copyright (c) 2023. Created by Alexsander at 11/12. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alexsanderdev.convidados.constants.DataBaseConstants

class GuestDataBase(context: Context) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                    DataBaseConstants.GUEST.COLUMNS.ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                    DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}