package com.dngwjy.finalproject.presenters.match

import android.util.Log
import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.response.EventResponse
import com.dngwjy.finalproject.data.models.response.TeamsResponse
import com.dngwjy.finalproject.views.match.DetailMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(val view :DetailMatchView, val request:ApiRequest, val gson:Gson) {
fun GetDetaildata(id:String){
    view.LoadData()
    doAsync {
        val data=gson.fromJson(request.SendingReq(ApiReqObject.getDetailMatch(id)),EventResponse::class.java)
        Log.d("data","Datanya ${data.events.size}")
        uiThread {
            view.FinishLoad()
            view.ShowData(data.events)
        }
    }
}
    fun GetTeamDetail(id:String){
        view.LoadData()
        doAsync {
            val data=gson.fromJson(request.SendingReq(ApiReqObject.TeamsDetailReq(id)),TeamsResponse::class.java)
            Log.d("data team","data tim nya ${data.teams.toString()}")
            uiThread {
                view.FinishLoad()
                view.ShowHomeTeamsData(data.teams)
            }
        }
    }

    fun GetAwayTeam(id:String){
        view.LoadData()
        doAsync {
            val data=gson.fromJson(request.SendingReq(ApiReqObject.TeamsDetailReq(id)),TeamsResponse::class.java)
            Log.d("data team","data tim nya ${data.teams.toString()}")
            uiThread {
                view.FinishLoad()
                view.ShowAwayTeamsData(data.teams)
            }
        }
    }



}