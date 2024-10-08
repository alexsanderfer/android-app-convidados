/*
 * Copyright (c) 2023. Created by Alexsander at 11/12. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexsanderdev.convidados.model.GuestModel
import com.alexsanderdev.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GuestRepository = GuestRepository(application.applicationContext)
    private val listAllGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuest

    fun getAll() {
        listAllGuest.value = repository.getAll()
    }
    fun getAbsent() {
        listAllGuest.value = repository.getAbsent()
    }
    fun getPresent() {
        listAllGuest.value = repository.getPresent()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}