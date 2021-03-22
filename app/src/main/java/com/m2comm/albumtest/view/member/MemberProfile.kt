package com.m2comm.albumtest.view.member

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.common.Defines
import com.m2comm.albumtest.model.Todo
import com.m2comm.albumtest.view.MainActivity
import com.m2comm.albumtest.view.adapter.MemberAdapter
import com.m2comm.albumtest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_member_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import com.bumptech.glide.Glide.init as init1

class MemberProfile : Fragment() {

    lateinit var mMemberAdapter : MemberAdapter
    val arrayList = ArrayList<Todo>()

    private var mIsEnd : Boolean = true //다음페이지 유무 true = O , false = X
    private var mPage : Int = 0

    private lateinit var mMainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
    }

    private fun addData() {
        GlobalScope.launch (Dispatchers.Main) {
            for ( i in arrayList.size..arrayList.size + 30) {
                arrayList.add(Todo(i.toLong(),"title${i}","content${i}", Date().time))
            }

            mMemberAdapter.notifyDataSetChanged()
            setHasNextPage(true)
            if ( swipeRefresh.isRefreshing ) swipeRefresh.isRefreshing = false
        }
    }

    private fun init() {
//        mMainViewModel = ViewModelProvider.AndroidViewModelFactory
//            .getInstance(requireActivity().application)
//            .create(MainViewModel::class.java)

        mMainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mMemberAdapter = MemberAdapter(arrayList).apply {
            listener = object : MemberAdapter.OnMemberItemClickListener {
                override fun onMemberItemClick(position: Int) {
                    Defines.y(mMainViewModel.mIsChangeScroll.value.toString())
                    mMainViewModel.mSaveClickRow.value = position.toString()
                }
            }
        }

        recyclerList.adapter = mMemberAdapter

        addData()

    }

    private fun initListener() {
        recyclerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager

                // hasNextPage() -> 다음 페이지가 있는 경우 true
                if (hasNextPage()) {
                    val lastVisibleItem = (layoutManager as LinearLayoutManager)
                        .findLastCompletelyVisibleItemPosition()

                    // 마지막으로 보여진 아이템 position 이
                    // 전체 아이템 개수보다 2개 모자란 경우, 데이터를 loadMore 한다
                    if (layoutManager.itemCount <= lastVisibleItem + 2) {
                        setHasNextPage(false)
                        getLodingData()
                    }
                }

            }
        })

        swipeRefresh.setOnRefreshListener {
            arrayList.clear()
            addData()
        }


    }

    private fun getPage(): Int {
        mPage++
        return mPage
    }

    private fun hasNextPage(): Boolean {
        return mIsEnd
    }

    private fun setHasNextPage(b: Boolean) {
        mIsEnd = b
    }

    private fun getLodingData () {
        Defines.y("getData")
        getPage()
        addData()
    }


}