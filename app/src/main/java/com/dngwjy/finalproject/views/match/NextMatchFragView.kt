package com.dngwjy.finalproject.views.match

import com.dngwjy.finalproject.data.models.Event

interface NextMatchFragView {
    fun Loading()
    fun FinishLoad()
    fun showData(data:List<Event>)
}