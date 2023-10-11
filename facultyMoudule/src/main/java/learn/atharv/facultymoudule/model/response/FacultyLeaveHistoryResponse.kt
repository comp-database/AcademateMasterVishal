package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyLeaveHistoryResponse(
    @SerializedName("leave")
    val leave: List<Leave>
) {
    data class Leave(
        @SerializedName("alternate")
        val alternate: Int,
        @SerializedName("applied_date")
        val appliedDate: Any?,
        @SerializedName("doc_link")
        val docLink: Any?,
        @SerializedName("faculty_id")
        val facultyId: Int,
        @SerializedName("from_date")
        val fromDate: String,
        @SerializedName("half_full_day")
        val halfFullDay: String?,
        @SerializedName("leave_app_id")
        val leaveAppId: Int,
        @SerializedName("leave_id")
        val leaveId: Int,
        @SerializedName("lname")
        val lname: String,
        @SerializedName("no_of_days")
        val noOfDays: Int,
        @SerializedName("reason")
        val reason: String,
        @SerializedName("signed_by_hod")
        val signedByHod: Any?,
        @SerializedName("signed_by_principal")
        val signedByPrincipal: Any?,
        @SerializedName("status")
        val status: Int,
        @SerializedName("status_alternate")
        val statusAlternate: Int,
        @SerializedName("to_date")
        val toDate: String
    )
}