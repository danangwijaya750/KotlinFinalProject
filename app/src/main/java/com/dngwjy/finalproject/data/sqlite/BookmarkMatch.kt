package com.dngwjy.finalproject.data.sqlite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookmarkMatch(val id: Int, val idEvent: String, val homeId:String,
                    val awayId:String, val home:String,
                    val away:String, val awayScore:String,
                    val dateEvent: String,val homeScore: String
/*                    val homeForm: String,val awayForm: String,
                    val homeKeep: String,val awayKeep: String,
                    val homeDef: String,val awayDef: String,
                    val homeMid: String,val awayMid: String,
                    val homeFw: String,val awayFw: String,
                    val homeSh: String,val awaySh: String*/):Parcelable {
	companion object {
		const val TABLE:String="TB_BOOKMARK"
		const val ID:String="ID"
		const val EVENT_ID:String="EventId"
		const val HOME_ID:String="homeId"
		const val AWAY_ID:String="awayID"
		const val HOME:String="home"
		const val AWAY:String="away"
		const val DATE:String="date"
		const  val HOME_SCORE:String="homeScore"
		const val AWAY_SCORE:String="awayScore"
		const val HOME_FORM:String= "homeForm"
		const val AWAY_FORM :String="awayForm"
		const val HOME_KEE:String= "homeKeep"
		const val AWAY_KEE:String= "awayKeep"
		const val HOME_DEF:String="homeDef"
		const val AWAY_DEF:String="awayDef"
		const val HOME_MID:String="homeMid"
		const val AWAY_MID:String="awayMid"
		const val HOME_FW:String="homeFw"
		const val AWAY_FW:String= "awayfw"
		const val HOME_SH:String= "homeShot"
		const val AWAY_SH:String= "awayShot"
	}
}