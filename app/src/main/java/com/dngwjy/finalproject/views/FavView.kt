package com.dngwjy.finalproject.views

import android.support.v4.app.Fragment

interface FavView{
	fun Loading()
	fun finishLoading()
	fun init()
	fun changeFragment(fragment: Fragment)
}