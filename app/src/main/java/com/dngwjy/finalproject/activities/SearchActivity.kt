package com.dngwjy.finalproject.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.EventAdapter
import com.dngwjy.finalproject.data.TeamsAdapter
import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.data.models.Teams
import com.dngwjy.finalproject.data.models.response.EventResponse
import com.dngwjy.finalproject.data.models.response.SearchEventResponse
import com.dngwjy.finalproject.presenters.SearchPres
import com.dngwjy.finalproject.views.SearchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchActivity : AppCompatActivity(),SearchView {
	var dataTeam:MutableList<Teams> = mutableListOf()
	var dataEvent:MutableList<Event> = mutableListOf()
	lateinit var adapterTeam:TeamsAdapter
	lateinit var adapterEvent:EventAdapter
	companion object {
		var caller=""
	}


	override fun LoadingData() {
		swiping.isRefreshing=true
	}

	override fun FinsihLoading() {
		swiping.isRefreshing=false
	}

	override fun ShowDataEvent(data: List<Event>) {
		dataEvent.clear()
		dataEvent.addAll(data)
		adapterEvent.notifyDataSetChanged()
	}

	override fun ShowDataTeam(data: List<Teams>) {
		dataTeam.clear()
		dataTeam.addAll(data)
		adapterTeam.notifyDataSetChanged()
	}
lateinit var presenter:SearchPres

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_search)
		val request=ApiRequest()
		val gson= Gson()
		presenter=SearchPres(this,request,gson)
		searchEdit.addTextChangedListener(object :TextWatcher{
			override fun afterTextChanged(s: Editable?) {

			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				if(caller.equals("match")){
					DoRequestEvent()
				}else if(caller.equals("teams")){
					DoRequestTeam()
				}
			}

		})
	}
	fun DoRequestEvent(){
		adapterEvent= EventAdapter(this,dataEvent){

		}
		val linMan= LinearLayoutManager(this)
		linMan.orientation=LinearLayoutManager.VERTICAL
		recSearch.layoutManager=linMan
		recSearch.adapter=adapterEvent
		requesting(searchEdit.text.toString())
	}
	fun DoRequestTeam(){
		adapterTeam= TeamsAdapter(this,dataTeam){
			TeamDetail.caller="teams"
			val intent= Intent(this,TeamDetail::class.java)
			intent.putExtra("idTeam",it)
			startActivity(intent)
		}
		val linMan= LinearLayoutManager(this)
		linMan.orientation=LinearLayoutManager.VERTICAL
		recSearch.layoutManager=linMan
		recSearch.adapter=adapterTeam
		presenter.SearchTeam(searchEdit.text.toString())
	}
	fun requesting(name:String){
		val gson=Gson()
		val request=ApiRequest()
		LoadingData()
		doAsync {
			val data=gson.fromJson(request.SendingReq(ApiReqObject.searchEvent(name)), SearchEventResponse::class.java)
			Log.d("Link request ",ApiReqObject.searchEvent(name))
			if (data==null){
				Log.d("search length","data is null")
			}else  { Log.d("search length",data.event.toString())
			}
			uiThread {
				FinsihLoading()
				ShowDataEvent(data.event)
			}
		}
	}
}
