package com.dngwjy.finalproject.activities

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.models.DetailMatchModels
import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.data.models.Teams
import com.dngwjy.finalproject.data.sqlite.BookmarkMatch
import com.dngwjy.finalproject.data.sqlite.database
import com.dngwjy.finalproject.presenters.match.DetailMatchPresenter
import com.dngwjy.finalproject.views.match.DetailMatchView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick

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
	lateinit var btn:Button
    override fun LoadData() {

    }

    override fun FinishLoad() {

    }

    override fun ShowData(data: List<Event>) {
        dataEvent.addAll(data)
        dateMatch.text=dataEvent[0].dateEvent
        //.Bind Home data
	    var idHomeTeam :String=""; var idAwayTeam :String=""
	    var strHomeTeam :String=""; var strAwayTeam :String=""
	    var dateEvent :String=""; var intHomeScore :String=""
	    var intAwayScore :String=""

        homeTeam.text=dataEvent[0].strHomeTeam; idHomeTeam=dataEvent[0].idHomeTeam.toString();idAwayTeam=dataEvent[0].toString()
        homeScr.text=dataEvent[0].intHomeScore.toString();
	    intHomeScore=dataEvent[0].intHomeScore.toString();
	    intAwayScore=dataEvent[0].intAwayScore.toString()
        homeDefend.text=dataEvent[0].strHomeLineupDefense;strHomeTeam=dataEvent[0].strHomeTeam.toString();
	    strAwayTeam=dataEvent[0].strAwayTeam.toString();dateEvent=dataEvent[0].dateEvent.toString()
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

	    details= DetailMatchModels(dataEvent[0].idEvent.toString(),idHomeTeam,
			    idAwayTeam,strHomeTeam,strAwayTeam,
			    intHomeScore,intAwayScore,dateEvent)
    }

companion object {
	var caller:String=""
}

lateinit var presenter: DetailMatchPresenter
    val request=ApiRequest()
    val gson=Gson()
	lateinit var details:DetailMatchModels
	lateinit var idEvent:String
	var isAdded:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        presenter= DetailMatchPresenter(this, request,gson)
	    if(caller=="match"){
		    val data=intent.extras.getParcelable("idMatch") as Event
		    idEvent=data.idEvent.toString()
		    presenter.GetDetaildata(data.idEvent.toString())
	    }
	    else if(caller=="fav"){
		    val data1=intent.extras.getParcelable("data") as BookmarkMatch
		    idEvent=data1.idEvent.toString()
		    Log.d("data", "fav id event $idEvent")
		    presenter.GetDetaildata(data1.idEvent.toString())
	    }
	    Log.d("idEvent","id eventya $idEvent")
	    supportActionBar!!.title="Detail Match"
	    btn=findViewById(R.id.btnBook)
	    btn.onClick {
		    if(!isAdded){
			    addBookmark()
		    }else if(isAdded){
			    removeBookmark()
		    }
	    }
	    getState()

    }
	fun addBookmark(){
		try{
			database.use {
				insert(BookmarkMatch.TABLE, BookmarkMatch.EVENT_ID to details.eventId,
						BookmarkMatch.HOME_ID to details.idHomeTeam, BookmarkMatch.AWAY_ID to details.idAwayTeam,
						BookmarkMatch.HOME to details.strHomeTeam, BookmarkMatch.AWAY to details.strAwayTeam,
						BookmarkMatch.DATE to details.dateEvent, BookmarkMatch.HOME_SCORE to details.intHomeScore,
						BookmarkMatch.AWAY_SCORE to details.intAwayScore
				)
			}
			getState()
			Toast.makeText(this,"Added to Bookmark", Toast.LENGTH_SHORT).show()
		}catch (ex: SQLiteConstraintException){
			Log.d("error sql",ex.toString())
		}
	}
	fun getState(){
		database.use {
			val res = select(BookmarkMatch.TABLE)
					.whereArgs("EventId={id}", "id" to idEvent)
			val bookmark = res.parseList(classParser<BookmarkMatch>())
			if (!bookmark.isEmpty()){
				isAdded = true;setBookmark(true)}
			else{
				isAdded=false;setBookmark(false)
			}
		}
	}
	fun setBookmark(state:Boolean){
		if(state){
			btn.text="Remove"
		}else if(!state){
			btn.text="Add"
		}
	}
	fun removeBookmark(){
		try{
			database.use {
				delete(BookmarkMatch.TABLE,"(EventId={id})","id" to idEvent)
			}
			getState()
			Toast.makeText(this,"Remove Form Bookmark", Toast.LENGTH_SHORT).show()
		}catch (ex: SQLiteConstraintException){
			Log.d("error sql",ex.toString())
		}
	}
}
