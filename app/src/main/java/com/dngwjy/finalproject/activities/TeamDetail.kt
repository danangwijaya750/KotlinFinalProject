package com.dngwjy.finalproject.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.data.models.Teams
import com.dngwjy.finalproject.fragments.team.TeamOverViewFrag
import com.dngwjy.finalproject.fragments.team.TeamPlayersFrag
import com.dngwjy.finalproject.presenters.team.TeamDetailPres
import com.dngwjy.finalproject.views.TeamsDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetail : AppCompatActivity(), TeamsDetailView {
    override fun init() {
        val adapterView= ViewPageAdapter(supportFragmentManager)
        pagger.adapter=adapterView
        teamTab.setupWithViewPager(pagger)
        val data=intent.extras.getParcelable("idTeam") as Teams
        Log.d("idTeams nya",data.idTeam)
       LoadImage(data.strTeamBadge)
        Log.d("image",data.strTeamBadge)
        Log.d("Team descript",data.strDescriptionEN.toString())
        TeamOverViewFrag.teamDesciption=data.strDescriptionEN.toString()
        TeamPlayersFrag.idTeams=data.idTeam
    }

    override fun LoadImage(url: String?) {
        Picasso.get().load(url).into(teamImage)
    }
lateinit var presenter: TeamDetailPres
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        init()
    }

    class ViewPageAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm){
        override fun getItem(p0: Int): Fragment {
            return when (p0) {
                0 -> {
                    TeamOverViewFrag()
                }
                else ->{
                    return TeamPlayersFrag()
                }

            }
        }

        override fun getCount(): Int {
            return 2
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0->"Overview"
                else->{
                    "Players"
                }
            }
        }
    }
}
