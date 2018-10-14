package com.dngwjy.finalproject.views.match

import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.data.models.Teams

interface DetailMatchView {
    fun LoadData()
    fun FinishLoad()
    fun ShowData(data:List<Event>)
    fun ShowHomeTeamsData(data:List<Teams>)
    fun ShowAwayTeamsData(data:List<Teams>)
}