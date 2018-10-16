package com.dngwjy.finalproject.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.fragments.favorite.MatchFavFrag
import com.dngwjy.finalproject.fragments.favorite.TeamFavFrag
import com.dngwjy.finalproject.views.FavView
import org.jetbrains.anko.support.v4.ctx

class FavFrag: Fragment(), FavView {
	lateinit var rootView:View
	lateinit var spinner: Spinner
	lateinit var swipeRefreshLayout: SwipeRefreshLayout
	override fun Loading() {

	}

	override fun finishLoading() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun init() {
		spinner=rootView.findViewById(R.id.spinnerFav)
		val spinnerItem=resources.getStringArray(R.array.fav)
		val spinnerAdapter= ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,spinnerItem)
		spinner.adapter=spinnerAdapter
		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				if(spinner.selectedItem.toString().equals("Match")){
					changeFragment(MatchFavFrag())
				}else if(spinner.selectedItem.toString().equals("Teams")){
					changeFragment(TeamFavFrag())
				}
			}

		}


	}

	override fun changeFragment(fragment: Fragment) {
		val transact= fragmentManager!!.beginTransaction()
		transact.replace(R.id.frameFav,fragment)
		transact.addToBackStack(null)
		transact.commit()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		rootView=inflater.inflate(R.layout.fav_frag,container,false)
		init()
		return rootView
	}
}