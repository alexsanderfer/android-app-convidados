/*
 * Copyright (c) 2023. Created by Alexsander at 11/12. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.ui.presents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexsanderdev.convidados.GuestFormActivity
import com.alexsanderdev.convidados.constants.DataBaseConstants
import com.alexsanderdev.convidados.databinding.FragmentPresentsBinding
import com.alexsanderdev.convidados.ui.adapter.GuestsAdapter
import com.alexsanderdev.convidados.ui.listener.OnGuestListener
import com.alexsanderdev.convidados.ui.viewmodel.GuestsViewModel

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GuestsAdapter()
    private lateinit var presentsViewModel: GuestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        presentsViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        // Layout & Adapter
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(Intent(intent))
            }

            override fun onDelete(id: Int) {
                presentsViewModel.delete(id)
                presentsViewModel.getPresent()
            }
        }

        adapter.attachListener(listener)

        observe()
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        presentsViewModel.getPresent()
    }

    private fun observe() {
        presentsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}