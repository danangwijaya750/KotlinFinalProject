package com.dngwjy.finalproject.fragments.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.views.teams.TeamOverviewView

class TeamOverViewFrag : Fragment(),TeamOverviewView {
	lateinit var textDescipt:TextView
	override fun init() {
		textDescipt=rootView.findViewById(R.id.overText)
		Log.d("teams Descript in frag", teamDesciption)
		textDescipt.text= teamDesciption
	}

	override fun LoadingData() {

	}

	override fun EndLoad() {

	}

	override fun ShowData() {

	}

	lateinit var rootView :View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.team_overview,container,false)
	    init()
    return rootView
    }
    companion object {
        var idTeams:String=""
        var teamDesciption:String=""
    }

}