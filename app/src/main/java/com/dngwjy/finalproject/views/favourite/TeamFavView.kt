package com.dngwjy.finalproject.views.favourite

import com.dngwjy.finalproject.data.sqlite.BookmarkTeam

interface TeamFavView {
	fun LoadData()
	fun FinishLoadData()
	fun init()
	fun ShowData()
}