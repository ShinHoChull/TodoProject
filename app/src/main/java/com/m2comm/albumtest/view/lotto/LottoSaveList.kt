package com.m2comm.albumtest.view.lotto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.bumptech.glide.Glide.init
import com.m2comm.albumtest.R
import com.m2comm.albumtest.common.Defines
import com.m2comm.albumtest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_lotto_save_list.*


class LottoSaveList : Fragment() {

    private lateinit var mMainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lotto_save_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()

    }

    private fun init() {
        mMainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


    }

    private fun initListener() {

        button.setOnClickListener {
            Defines.y("clickNumber = ${mMainViewModel.mSaveClickRow.value}")
            findNavController().navigate(R.id.action_lottoSaveList_to_lottoListDetail)
        }

        button2.setOnClickListener {
            findNavController().navigate(R.id.action_lottoSaveList_to_lottoListDetail2)
        }


    }


}