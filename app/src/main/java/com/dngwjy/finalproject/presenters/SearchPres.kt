package com.dngwjy.finalproject.presenters

import android.util.Log
import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.response.EventResponse
import com.dngwjy.finalproject.data.models.response.TeamsResponse
import com.dngwjy.finalproject.views.SearchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPres(val view:SearchView,val request:ApiRequest,val gson:Gson) {
 fun SearchEvet(name:String){
	 view.LoadingData()
	 doAsync {
		 val data=gson.fromJson(request.SendingReq(ApiReqObject.searchEvent(name)),EventResponse::class.java)
		 if (data==null){
			 Log.d("search length","data is null")
		 }else  { Log.d("search length",data.events.toString())
		 }
		 uiThread {
			 view.FinsihLoading()
			 view.ShowDataEvent(data.events)
		 }
	 }
 }
	fun SearchTeam(name:String){
		view.LoadingData()
		doAsync {
			val data=gson.fromJson(request.SendingReq(ApiReqObject.SearchTeamUrl(name)),TeamsResponse::class.java)
			Log.d("search team length", data.teams.size.toString())
			uiThread {
				view.FinsihLoading()
				view.ShowDataTeam(data.teams)
			}
		}
	}

}