package com.dngwjy.finalproject.views

import android.util.EventLog
import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.data.models.Teams

interface SearchView {
	fun LoadingData()
	fun FinsihLoading()
	fun ShowDataEvent(data:List<Event>)
	fun ShowDataTeam(data:List<Teams>)
}