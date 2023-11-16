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
    private val repository = GuestRepository.getInstance(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<SuccessOrFailure>()
    val saveGuest: LiveData<SuccessOrFailure> = _saveGuest

    fun save(guest: GuestModel) {
        val successOrFailure = SuccessOrFailure(true, "")
        if (guest.id == 0) {
            successOrFailure.success = repository.insert(guest)
        } else {
            successOrFailure.success = repository.update(guest)
        }
    }

    fun update(guest: GuestModel) {
        repository.update(guest)
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }


}