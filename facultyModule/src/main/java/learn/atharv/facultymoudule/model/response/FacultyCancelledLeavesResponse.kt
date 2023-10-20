package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyCancelledLeavesResponse(
    @SerializedName("balanceLeave")
    val balanceLeave: List<BalanceLeave>,
    @SerializedName("message")
    val message: String
) {
    data class BalanceLeave(
        @SerializedName("Casual Leave")
        val casualLeave: Int,
        @SerializedName("Compensation Leave")
        val compensationLeave: Int,
        @SerializedName("Earned Leave")
        val earnedLeave: Int,
        @SerializedName("faculty_id")
        val facultyId: Int,
        @SerializedName("fla_id")
        val flaId: Int, // faculty leave application id
        @SerializedName("Medical Leave")
        val medicalLeave: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("remark")
        val remark: String?,
        @SerializedName("Special Leave")
        val specialLeave: Int,
        @SerializedName("Summer Vacation")
        val summerVacation: Int,
        @SerializedName("used_earned_leaves")
        val usedEarnedLeaves: Any?,
        @SerializedName("Winter Vacation")
        val winterVacation: Int
    )
}