package dev.vaibhav.mbits.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreathingToolApiResponse(
    val status: Status,
    val data: Data
)

@JsonClass(generateAdapter = true)
data class Status(
    val code: Int,
    @Json(name = "msg")
    val message: String,
    val retry: Boolean ,
    @Json(name = "err")
    val error: String
)

@JsonClass(generateAdapter = true)
data class Data(
    val breathingToolData: BreathingToolData ,
    val breathingProgressData: BreathingProgressData
)

@JsonClass(generateAdapter = true)
data class BreathingToolData(
    val id: String ,
    val uid: String ,
    val type: Int ,
    val code: String,
    @Json(name = "prc")
    val practice: List<Practice> ,
    val wea: Boolean
)

@JsonClass(generateAdapter = true)
data class Practice(
    @Json(name = "ttl")
    val title: String ,
    @Json(name = "dsc")
    val description: String ,
    val values: List<Value> ,
    val isMultiSel: Boolean,
    val type: Int,
    val code: String
)

@JsonClass(generateAdapter = true)
data class Value(
    val id: String ,
    val code: String,
    @Json(name = "ttl")
    val title: String ,
    @Json(name = "dsc")
    val description: String ,
    val url: String
)

@JsonClass(generateAdapter = true)
data class BreathingProgressData(
    val id: String ,
    val uid: String ,
    val date: Long ,
    @Json(name = "tgt")
    val target: Int ,
    @Json(name = "ach")
    val achieved: Int,
    @Json(name = "rcm")
    val recommended: Int
)


/*
@JsonClass(generateAdapter = true)
data class BreathingToolApiResponse(
    val status: Status,
    val data: Data
)

@JsonClass(generateAdapter = true)
data class Status(
    val code: Int = 0,
    @Json(name = "msg")
    val message: String = "",
    val retry: Boolean = false,
    @Json(name = "err")
    val error: String = ""
)

@JsonClass(generateAdapter = true)
data class Data(
    val breathingToolData: BreathingToolData = BreathingToolData(),
    val breathingProgressData: BreathingProgressData = BreathingProgressData()
)

@JsonClass(generateAdapter = true)
data class BreathingToolData(
    val id: String ="",
    val uid: String ="",
    val type: Int = 0,
    val code: String = "",
    @Json(name = "prc")
    val practice: List<Practice> = listOf(),
    val wea: Boolean = false
)

@JsonClass(generateAdapter = true)
data class Practice(
    @Json(name = "ttl")
    val title: String ="",
    @Json(name = "dsc")
    val description: String ="",
    val values: List<Value> = listOf(),
    val isMultiSel: Boolean = false,
    val type: Int = 0,
    val code: String = ""
)

@JsonClass(generateAdapter = true)
data class Value(
    val id: String ="",
    val code: String = "",
    @Json(name = "ttl")
    val title: String ="",
    @Json(name = "dsc")
    val description: String ="",
    val url: String =""
)

@JsonClass(generateAdapter = true)
data class BreathingProgressData(
    val id: String = "",
    val uid: String ="",
    val date: Long = 0,
    @Json(name = "tgt")
    val target: Int = 0,
    @Json(name = "ach")
    val achieved: Int = 0,
    @Json(name = "rcm")
    val recommended: Int = 0
)
 */