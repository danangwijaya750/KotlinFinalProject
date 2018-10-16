package com.dngwjy.finalproject.fragments.team

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.activities.DetailPlayer
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.PlayersAdapter
import com.dngwjy.finalproject.data.models.Players
import com.dngwjy.finalproject.presenters.team.TeamPlayerPres
import com.dngwjy.finalproject.views.teams.TeamPlayersView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh

class TeamPlayersFrag : Fragment(), TeamPlayersView {
    override fun LoadingData() {
        swipeRefreshLayout.isRefreshing=true
    }

    override fun EndLoad() {
        swipeRefreshLayout.isRefreshing=false
    }

    override fun ShowData(data: List<Players>) {
        dataPlayer.clear()
        dataPlayer.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun init() {
        recyclerView=rootView.findViewById(R.id.recPlay)
        swipeRefreshLayout=rootView.findViewById(R.id.swipingPlayer)
        val request=ApiRequest()
        val gson=Gson()
        presenter= TeamPlayerPres(this,request,gson)
        presenter.getPlayers(idTeams)
        val layMan=LinearLayoutManager(this.context)
        layMan.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layMan
        adapter= PlayersAdapter(this.context!!,dataPlayer){
			val intent = Intent(this.context, DetailPlayer::class.java)
            intent.putExtra("playerData",it)
            startActivity(intent)
        }
        recyclerView.adapter=adapter
        swipeRefreshLayout.onRefresh {
         presenter.getPlayers(idTeams)
        }
    }
    lateinit var presenter:TeamPlayerPres
    lateinit var adapter: PlayersAdapter
    var dataPlayer:MutableList<Players> = mutableListOf()
    lateinit var rootView :View
	lateinit var recyclerView: RecyclerView
	lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       rootView =inflater.inflate(R.layout.team_players,container,false)
        return rootView
    }

    companion object {
        var idTeams:String=""
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
}