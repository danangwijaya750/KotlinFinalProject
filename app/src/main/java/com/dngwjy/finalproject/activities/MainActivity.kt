package com.dngwjy.finalproject.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.fragments.FavFrag
import com.dngwjy.finalproject.fragments.MatchFrag
import com.dngwjy.finalproject.fragments.TeamsFrag
import com.dngwjy.finalproject.fragments.favorite.MatchFavFrag
import com.dngwjy.finalproject.fragments.match.NextFrag
import com.dngwjy.finalproject.fragments.match.PastFrag
import com.dngwjy.finalproject.views.MainActivityView

class MainActivity : AppCompatActivity(), MainActivityView {
    override fun init() {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navigate:BottomNavigationView=findViewById(R.id.navigate)
        navigate.setOnNavigationItemSelectedListener {
          if (it.itemId== R.id.matchMenu){
              replaceFrag(MatchFrag())
          }else if(it.itemId== R.id.teamsMenu){
              replaceFrag(TeamsFrag())
              Log.d("tag1","teams menu")
          }else if(it.itemId== R.id.favMenu){
              replaceFrag(FavFrag())
              Log.d("tag1","fav menu")
          }
true
        }
        navigate.selectedItemId=R.id.matchMenu
    }
    fun replaceFrag( frag:Fragment){
        val trans=supportFragmentManager.beginTransaction()
        trans.replace(R.id.deploy,frag)
        trans.addToBackStack(null)
        trans.commit()
    }



}
