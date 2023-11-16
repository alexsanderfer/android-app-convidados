/*
 * Copyright (c) 2023. Created by Alexsander at 11/11. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexsanderdev.convidados.constants.DataBaseConstants
import com.alexsanderdev.convidados.databinding.ActivityGuestFormBinding
import com.alexsanderdev.convidados.model.GuestModel
import com.alexsanderdev.convidados.ui.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.fabSalvar.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }
        viewModel.saveGuest.observe(this) {
            if (it.success) {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.fab_salvar) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)
            finish()
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}