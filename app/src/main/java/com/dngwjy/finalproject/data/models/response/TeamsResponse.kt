package com.dngwjy.finalproject.data.models.response

import com.dngwjy.finalproject.data.models.Teams
import com.google.gson.annotations.SerializedName

data class TeamsResponse(
        @SerializedName("teams")
        val teams:List<Teams>
)