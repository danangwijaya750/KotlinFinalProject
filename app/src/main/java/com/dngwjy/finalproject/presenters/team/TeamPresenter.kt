package com.dngwjy.finalproject.presenters.team

import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.response.TeamsResponse
import com.dngwjy.finalproject.views.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(val view:TeamsView, val request: ApiRequest,val gson: Gson) {
    fun getLeauge(){
    view.Loading()
        doAsync {
            val data=gson.fromJson(request.SendingReq(ApiReqObject.LigaReq()),TeamsResponse::class.java)
            uiThread {
            view.LoadingFinish()

            }
        }
    }
    fun getTeam(liga : String){
        view.Loading()
        doAsync {
            val data=gson.fromJson(request.SendingReq(ApiReqObject.TeamsReq(liga)),TeamsResponse::class.java)
            //Log.d("Data json",data.teams.toString())
            uiThread {
                view.LoadingFinish()
                view.ShowData(data.teams)
            }
        }
    }
}