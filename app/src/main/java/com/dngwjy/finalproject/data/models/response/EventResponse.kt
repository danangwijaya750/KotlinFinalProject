package com.dngwjy.finalproject.data.models.response

import com.dngwjy.finalproject.data.models.Event

import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events")
        val events:List<Event>)