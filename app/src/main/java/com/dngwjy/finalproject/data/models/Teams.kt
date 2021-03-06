package com.dngwjy.finalproject.data.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(@SerializedName("intStadiumCapacity")
                 val intStadiumCapacity: String? = "",
                 @SerializedName("strTeamShort")
                 val strTeamShort: String? = "",
                 @SerializedName("strSport")
                 val strSport: String = "",
                 @SerializedName("strDescriptionCN")
                 val strDescriptionCN: String? = "",
                 @SerializedName("strTeamJersey")
                 val strTeamJersey: String? = "",
                 @SerializedName("strTeamFanart2")
                 val strTeamFanart2: String? = "",
                 @SerializedName("strTeamFanart3")
                 val strTeamFanart3: String? = "",
                 @SerializedName("strTeamFanart4")
                 val strTeamFanart4: String? = "",
                 @SerializedName("strStadiumDescription")
                 val strStadiumDescription: String? = "",
                 @SerializedName("strTeamFanart1")
                 val strTeamFanart: String? = "",
                 @SerializedName("intLoved")
                 val intLoved: String? = "",
                 @SerializedName("idLeague")
                 val idLeague: String? = "",
                 @SerializedName("idSoccerXML")
                 val idSoccerXML: String? = "",
                 @SerializedName("strTeamLogo")
                 val strTeamLogo: String? = "",
                 @SerializedName("strDescriptionSE")
                 val strDescriptionSE: String? = "",
                 @SerializedName("strDescriptionJP")
                 val strDescriptionJP: String? = "",
                 @SerializedName("strDescriptionFR")
                 val strDescriptionFR: String? = "",
                 @SerializedName("strStadiumLocation")
                 val strStadiumLocation: String? = "",
                 @SerializedName("strDescriptionNL")
                 val strDescriptionNL: String? = "",
                 @SerializedName("strCountry")
                 val strCountry: String? = "",
                 @SerializedName("strRSS")
                 val strRSS: String? = "",
                 @SerializedName("strDescriptionRU")
                 val strDescriptionRU: String? = "",
                 @SerializedName("strTeamBanner")
                 val strTeamBanner: String? = "",
                 @SerializedName("strDescriptionNO")
                 val strDescriptionNO: String?="",
                 @SerializedName("strStadiumThumb")
                 val strStadiumThumb: String? = "",
                 @SerializedName("strDescriptionES")
                 val strDescriptionES: String?="",
                 @SerializedName("intFormedYear")
                 val intFormedYear: String? = "",
                 @SerializedName("strInstagram")
                 val strInstagram: String? = "",
                 @SerializedName("strDescriptionIT")
                 val strDescriptionIT: String? = "",
                 @SerializedName("idTeam")
                 val idTeam: String = "",
                 @SerializedName("strDescriptionEN")
                 val strDescriptionEN: String? = "",
                 @SerializedName("strWebsite")
                 val strWebsite: String? = "",
                 @SerializedName("strYoutube")
                 val strYoutube: String? = "",
                 @SerializedName("strDescriptionIL")
                 val strDescriptionIL: String?="",
                 @SerializedName("strLocked")
                 val strLocked: String? = "",
                 @SerializedName("strAlternate")
                 val strAlternate: String? = "",
                 @SerializedName("strTeam")
                 val strTeam: String = "",
                 @SerializedName("strTwitter")
                 val strTwitter: String? = "",
                 @SerializedName("strDescriptionHU")
                 val strDescriptionHU: String?="",
                 @SerializedName("strGender")
                 val strGender: String? = "",
                 @SerializedName("strDivision")
                 val strDivision: String?="",
                 @SerializedName("strStadium")
                 val strStadium: String? = "",
                 @SerializedName("strFacebook")
                 val strFacebook: String? = "",
                 @SerializedName("strTeamBadge")
                 val strTeamBadge: String? = "",
                 @SerializedName("strDescriptionPT")
                 val strDescriptionPT: String?="",
                 @SerializedName("strDescriptionDE")
                 val strDescriptionDE: String? = "",
                 @SerializedName("strLeague")
                 val strLeague: String? = "",
                 @SerializedName("strManager")
                 val strManager: String = "",
                 @SerializedName("strKeywords")
                 val strKeywords: String? = "",
                 @SerializedName("strDescriptionPL")
                 val strDescriptionPL: String?="") : Parcelable