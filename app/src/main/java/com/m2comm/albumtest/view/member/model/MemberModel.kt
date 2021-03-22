package com.m2comm.albumtest.view.member.model

import com.m2comm.albumtest.model.Member
import com.m2comm.albumtest.view.member.presenter.MemberConstants
import com.m2comm.albumtest.view.member.presenter.MemberPresenter

class MemberModel (val presenter : MemberConstants.Presenter) {

    private var mMemberList : List<Member>? = null


    public fun saveData() {

    }

 //   public fun getMemberList () : List<Member> {
//        mMemberList = List<Member>()
//        return mMemberList
//    }


}