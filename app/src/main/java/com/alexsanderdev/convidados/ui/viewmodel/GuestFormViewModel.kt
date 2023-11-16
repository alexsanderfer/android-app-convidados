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
import com.alexsanderdev.convidados.model.SuccessOrFailure
import com.alexsanderdev.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val saveGuestLiveData = MutableLiveData<SuccessOrFailure>()
    val saveGuest: LiveData<SuccessOrFailure> = saveGuestLiveData

    fun save(guest: GuestModel) {
        val success: Boolean = if (guest.id == 0) {
            repository.insert(guest)
        } else {
            repository.update(guest)
        }
        val successOrFailure = SuccessOrFailure(success, guest.id == 0)
        saveGuestLiveData.value = successOrFailure
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }


}