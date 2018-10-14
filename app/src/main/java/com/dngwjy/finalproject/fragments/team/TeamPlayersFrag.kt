package com.dngwjy.finalproject.fragments.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dngwjy.finalproject.R

class TeamPlayersFrag : Fragment() {
    lateinit var rootView :View
	lateinit var recyclerView: RecyclerView
	lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       rootView =inflater.inflate(R.layout.team_players,container,false)
        return rootView
    }

    companion object {
        var idTeams:String?=""
    }
}