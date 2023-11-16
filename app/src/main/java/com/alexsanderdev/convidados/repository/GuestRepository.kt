/*
 * Copyright (c) 2023. Created by Alexsander at 11/12. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.alexsanderdev.convidados.constants.DataBaseConstants
import com.alexsanderdev.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {
    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            guestDataBase.writableDatabase.insert(DataBaseConstants.GUEST.TABLE_NAME, null, ContentValues().apply {
                put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
                put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, if (guest.presence) 1 else 0)
            })
            true
        } catch (e: Exception) {
            false

        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            with(guestDataBase.writableDatabase) {
                update(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    ContentValues().apply {
                        put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, if (guest.presence) 1 else 0)
                        put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
                    },
                    "${DataBaseConstants.GUEST.COLUMNS.ID} = ?",
                    arrayOf(guest.id.toString())
                )
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(guestID: Int): Boolean {
        return try {
            with(guestDataBase.writableDatabase) {
                delete(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    "${DataBaseConstants.GUEST.COLUMNS.ID} = ?",
                    arrayOf(guestID.toString())
                )
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )


            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null,
                null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection,
                args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    guest = (GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return guest
        }
        return guest
    }

    fun getPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    list.add(GuestModel(id, name, presence == 0))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }
        return list
    }

}