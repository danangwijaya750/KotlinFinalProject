package com.dngwjy.finalproject.views.teams

import com.dngwjy.finalproject.data.models.Players

interface TeamPlayersView {
	fun LoadingData()
	fun EndLoad()
	fun ShowData(data:List<Players>)
	fun init()
}