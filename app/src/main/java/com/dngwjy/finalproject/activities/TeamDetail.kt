package com.dngwjy.finalproject.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.data.models.Teams
import com.dngwjy.finalproject.data.sqlite.BookmarkMatch
import com.dngwjy.finalproject.data.sqlite.BookmarkTeam
import com.dngwjy.finalproject.data.sqlite.database
import com.dngwjy.finalproject.fragments.team.TeamOverViewFrag
import com.dngwjy.finalproject.fragments.team.TeamPlayersFrag
import com.dngwjy.finalproject.presenters.team.TeamDetailPres
import com.dngwjy.finalproject.views.TeamsDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.teams_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamDetail : AppCompatActivity(), TeamsDetailView {
	override fun init() {
		val adapterView = ViewPageAdapter(supportFragmentManager)
		pagger.adapter = adapterView

		teamTab.setupWithViewPager(pagger)
		if(caller.equals("teams")){
			val data = intent.extras.getParcelable("idTeam") as Teams
			Log.d("idTeams nya", data.idTeam)
			supportActionBar!!.title = data.strTeam
			Log.d("image", data.strTeamBadge)
			Log.d("Team descript", data.strDescriptionEN.toString())
			idTeam=data.idTeam
			showData(idTeam,data.strTeam,data.strTeamBadge.toString(),data.strStadium.toString(),data.intFormedYear.toString(),data.strDescriptionEN.toString())

		}else if(caller.equals("fav")){
			val data =intent.extras.getParcelable("data")as BookmarkTeam
			idTeam=data.TeamId
			showData(idTeam,data.TeamName,data.TeamBadge,data.TeamStadium,data.TeamYear,data.TeamDesc)
		}
		btnBook.onClick {
		if(isAdded){
			removeBookmark()
		}else if(!isAdded){
			addBookmark()
		}
		}

		getState()
	}
	fun showData(id:String,tim:String,badge:String,stadium:String,year:String,desc:String){
		TeamOverViewFrag.teamDesciption = desc
		TeamPlayersFrag.idTeams=id
		teamName.text = tim
		teamStadium.text =stadium
		teamsYear.text = year
		TeamPlayersFrag.idTeams = id
		LoadImage(badge)
		details= BookmarkTeam(id.toInt(),id,tim,desc,badge,stadium,year)
	}
companion object {
	var caller:String=""
}
	override fun LoadImage(url: String?) {
		Picasso.get().load(url).into(teamImage)
	}

	lateinit var details: BookmarkTeam
	lateinit var idTeam: String
	var isAdded: Boolean = false
	lateinit var presenter: TeamDetailPres
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_team_detail)
		init()
	}

	class ViewPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
		override fun getItem(p0: Int): Fragment {
			return when (p0) {
				0 -> {
					TeamOverViewFrag()
				}
				else -> {
					return TeamPlayersFrag()
				}

			}
		}

		override fun getCount(): Int {
			return 2
		}

		override fun getPageTitle(position: Int): CharSequence? {
			return when (position) {
				0 -> "Overview"
				else -> {
					"Players"
				}
			}
		}
	}

	fun addBookmark() {
		try {
			database.use {
				insert(BookmarkTeam.TABLE, BookmarkTeam.TEAM_ID to details.TeamId,
						BookmarkTeam.TEAM_NAME to details.TeamName,
						BookmarkTeam.TEAM_DESC to details.TeamDesc, BookmarkTeam.TEAM_BADGE to details.TeamBadge
						,BookmarkTeam.TEAM_STADIUM to details.TeamStadium,BookmarkTeam.TEAM_YEAR to details.TeamYear
				)
			}
			getState()
			Toast.makeText(this, "Added to Bookmark", Toast.LENGTH_SHORT).show()
		} catch (ex: SQLiteConstraintException) {
			Log.d("error sql", ex.toString())
		}
	}

	fun getState() {
		database.use {
			val res = select(BookmarkTeam.TABLE)
					.whereArgs("TeamsID={id}", "id" to idTeam)
			val bookmark = res.parseList(classParser<BookmarkTeam>())
			if (!bookmark.isEmpty()) {
				isAdded = true;setBookmark(true)
			} else {
				isAdded = false;setBookmark(false)
			}
		}
	}

	fun setBookmark(state: Boolean) {
		if (state) {
			btnBook.text = "Remove"
		} else if (!state) {
			btnBook.text = "Add"
		}
	}

	fun removeBookmark() {
		try {
			database.use {
				delete(BookmarkTeam.TABLE, "(TeamsID={id})", "id" to idTeam)
			}
			getState()
			Toast.makeText(this, "Remove Form Bookmark", Toast.LENGTH_SHORT).show()
		} catch (ex: SQLiteConstraintException) {
			Log.d("error sql", ex.toString())
		}
	}

}
