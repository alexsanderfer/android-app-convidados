/*
 * Copyright (c) 2023. Created by Alexsander at 11/16. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.repository

import androidx.room.*
import com.alexsanderdev.convidados.model.GuestModel

@Dao
interface GuestDAO {
    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>

}