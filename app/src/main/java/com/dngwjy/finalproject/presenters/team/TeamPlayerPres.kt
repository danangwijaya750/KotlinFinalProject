package com.dngwjy.finalproject.presenters.team

import android.util.Log
import com.dngwjy.finalproject.api.ApiReqObject
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.response.PlayerResponses
import com.dngwjy.finalproject.views.teams.TeamPlayersView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPlayerPres(val view:TeamPlayersView,val request: ApiRequest,val gson: Gson) {
        fun getPlayers(id:String){
            view.LoadingData()
            doAsync {
            val data = gson.fromJson(request.SendingReq(ApiReqObject.getPlayer(id)),PlayerResponses::class.java)
                Log.d("data player",data.player.size.toString())
                uiThread {
                    view.EndLoad()
                    view.ShowData(data.player)
                }
            }

        }
}