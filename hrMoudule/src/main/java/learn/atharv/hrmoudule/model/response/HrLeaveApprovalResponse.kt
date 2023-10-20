package learn.atharv.hrmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HrLeaveApprovalResponse(
    @SerializedName("app_id")
    val appId: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)