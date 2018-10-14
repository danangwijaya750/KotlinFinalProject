package com.dngwjy.finalproject.views

import com.dngwjy.finalproject.data.models.Teams

interface TeamsView {
    fun Loading()
    fun LoadingFinish()
    fun ShowData(data: List<Teams>)
    fun init()
}