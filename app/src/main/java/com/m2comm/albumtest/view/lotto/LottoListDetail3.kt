package com.m2comm.albumtest.view.lotto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.m2comm.albumtest.R
import kotlinx.android.synthetic.main.fragment_lotto_list_detail3.*

class LottoListDetail3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lotto_list_detail3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeild()
        initListener()
    }

    private fun initFeild() {

    }

    private fun initListener() {
        button.setOnClickListener {

            //목적지를 정의하고 inclusive 경우는
            Navigation.findNavController(it).popBackStack(R.id.lottoSaveList,false)


        }


    }

}