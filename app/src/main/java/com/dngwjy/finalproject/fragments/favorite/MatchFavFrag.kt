package com.dngwjy.finalproject.fragments.favorite

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
import com.dngwjy.finalproject.activities.DetailMatch
import com.dngwjy.finalproject.data.sqlite.BookMatchAdapter
import com.dngwjy.finalproject.data.sqlite.BookmarkMatch
import com.dngwjy.finalproject.data.sqlite.database
import com.dngwjy.finalproject.views.MatchFragmentView
import com.dngwjy.finalproject.views.favourite.MatchFavView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.uiThread

class MatchFavFrag:Fragment(), MatchFavView{
	override fun ShowData() {
		LoadingData()
		doAsync {
			context?.database?.use {
				val res= select(BookmarkMatch.TABLE)
				val bookmark=res.parseList(classParser<BookmarkMatch>())

			uiThread {
				FinishLoadData()
				dataBookmark.clear()
				dataBookmark.addAll(bookmark)
				adapter.notifyDataSetChanged()
			}
		}
		}
	}
	var dataBookmark:MutableList<BookmarkMatch> = mutableListOf()
	lateinit var recyclerView: RecyclerView
	lateinit var adapter:BookMatchAdapter
	lateinit var swipeRefreshLayout: SwipeRefreshLayout
	override fun init() {
		recyclerView=rootView.findViewById(R.id.recTeamFav)
		swipeRefreshLayout=rootView.findViewById(R.id.swiping)
		adapter= BookMatchAdapter(this.context!!,dataBookmark){
			DetailMatch.caller="fav"
			var intent=Intent(this.context,DetailMatch::class.java)
			intent.putExtra("data",it)
			startActivity(intent)
		}
		val linMan=LinearLayoutManager(this.context)
		linMan.orientation=LinearLayoutManager.VERTICAL
		recyclerView.layoutManager=linMan
		recyclerView.adapter=adapter
		ShowData()
		swipeRefreshLayout.onRefresh {
			ShowData()
		}
	}

	override fun LoadingData() {
		swipeRefreshLayout.isRefreshing=true
	}

	override fun FinishLoadData() {
		swipeRefreshLayout.isRefreshing=false
	}

	lateinit var rootView:View
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		rootView=inflater.inflate(R.layout.match_fav,container,false)
		return rootView
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		init()
	}
}