package com.dngwjy.finalproject.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.data.models.Teams
import com.dngwjy.finalproject.presenters.match.DetailMatchPresenter
import com.dngwjy.finalproject.views.match.DetailMatchView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.event_list.*

class DetailMatch : AppCompatActivity(), DetailMatchView {
    override fun ShowAwayTeamsData(data: List<Teams>) {
        dataTeam.clear()
        dataTeam.addAll(data)
        Picasso.get().load(dataTeam[0].strTeamBadge).into(awayBadge)
    }

    override fun ShowHomeTeamsData(data: List<Teams>) {
        dataTeam.clear()
        dataTeam.addAll(data)
        Picasso.get().load(dataTeam[0].strTeamBadge).into(homeBadge)
    }
    var dataTeam: MutableList<Teams> = mutableListOf()
    var dataEvent : MutableList<Event> = mutableListOf()
    override fun LoadData() {

    }

    override fun FinishLoad() {

    }

    override fun ShowData(data: List<Event>) {
        dataEvent.addAll(data)
        dateMatch.text=dataEvent[0].dateEvent
        //.Bind Home data
        homeTeam.text=dataEvent[0].strHomeTeam
        homeScr.text=dataEvent[0].intHomeScore.toString()
        homeDefend.text=dataEvent[0].strHomeLineupDefense
        homeFormation.text=dataEvent[0].strHomeFormation
        homeGoals.text=dataEvent[0].strHomeGoalDetails
        homeKeeper.text=dataEvent[0].strHomeLineupGoalkeeper
        homeMid.text=dataEvent[0].strHomeLineupMidfield
        homeFor.text=dataEvent[0].strHomeLineupForward

        //. bind team badge
        presenter.GetTeamDetail(dataEvent[0].idHomeTeam.toString())
        presenter.GetAwayTeam(dataEvent[0].idAwayTeam.toString())

        //.bind Away data
        awayTeam.text=dataEvent[0].strAwayTeam
        awayScr.text=dataEvent[0].intAwayScore.toString()
        awayDefend.text=dataEvent[0].strAwayLineupDefense
        awayFormation.text=dataEvent[0].strAwayFormation
        awayGoals.text=dataEvent[0].strAwayGoalDetails
        awayKeeper.text=dataEvent[0].strAwayLineupGoalkeeper
        awayMid.text=dataEvent[0].strAwayLineupMidfield
        awayFor.text=dataEvent[0].strAwayLineupForward
    }


lateinit var presenter: DetailMatchPresenter
    val request=ApiRequest()
    val gson=Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        presenter= DetailMatchPresenter(this, request,gson)
        val id=intent.getStringExtra("idMatch")
        Log.d("Id","Id Match in acc $id")
        presenter.GetDetaildata(id)
    }

}
