package learn.atharv.hrmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HrDashboardResponse(
    @SerializedName("absent")
    val absent: Int,
    @SerializedName("alternate_list")
    val alternateList: List<Any>,
    @SerializedName("attendance")
    val attendance: Int,
    @SerializedName("message")
    val message: String
)