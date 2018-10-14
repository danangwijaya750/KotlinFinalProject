package com.dngwjy.finalproject.api

import java.net.URL

class ApiRequest {
    fun SendingReq(target:String):String{
        return URL(target).readText()
    }
}