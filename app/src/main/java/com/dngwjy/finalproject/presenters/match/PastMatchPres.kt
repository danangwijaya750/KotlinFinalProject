package com.dngwjy.finalproject.presenters.match

import android.util.Log
import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.response.EventResponse
import com.dngwjy.finalproject.views.match.PastMatchFragView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PastMatchPres(val view: PastMatchFragView, val request: ApiRequest, val gson: Gson) {
    fun getData(liga:String){
        view.Loading()
        doAsync {
         val data=gson.fromJson(request.SendingReq(ApiReqObject.PastMatchReq(liga)),EventResponse::class.java)
            Log.d("data ",data.toString())
            uiThread {
                view.FinishLoad()
                view.showData(data.events)
            }
        }

    }

}