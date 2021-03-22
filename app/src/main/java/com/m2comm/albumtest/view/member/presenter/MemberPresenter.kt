package com.m2comm.albumtest.view.member.presenter

import com.m2comm.albumtest.model.Member
import com.m2comm.albumtest.view.member.MemberProfile
import com.m2comm.albumtest.view.member.model.MemberModel

class MemberPresenter(view : MemberConstants.View) : MemberConstants.Presenter {

    private var mMemberV : MemberConstants.View? = null
    private val mMemberM : MemberModel = MemberModel(this)

    private var mMemberList : List<Member>? = null

    init {
        mMemberV = view
    }


    override fun loginVerification(id: String, pw: String): Boolean {


        return true
    }

    override fun pwFind(id: String): String {
        return ""
    }

    override fun idFind(UniqueNumber: String) {

    }
}