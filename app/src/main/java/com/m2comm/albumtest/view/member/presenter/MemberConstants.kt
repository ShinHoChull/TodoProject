package com.m2comm.albumtest.view.member.presenter

public interface MemberConstants {

    interface View {
        //View
        fun showResult(result : Boolean)
        fun idShowResult(result : String)
        fun pwShowResult(result : String)
    }

    public interface Presenter {
        fun loginVerification( id : String , pw : String) : Boolean
        fun pwFind(id : String) : String
        fun idFind(UniqueNumber : String)
    }

}