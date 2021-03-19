package com.m2comm.albumtest.view.lotto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.m2comm.albumtest.R
import kotlinx.android.synthetic.main.fragment_lotto_list_detail2.*

class LottoListDetail2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lotto_list_detail2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()

    }

    private fun initListener() {
        button.setOnClickListener {
            findNavController().navigate(R.id.action_lottoListDetail2_to_lotoListDetail3)
        }
    }


}