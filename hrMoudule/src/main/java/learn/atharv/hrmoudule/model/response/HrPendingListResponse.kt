package learn.atharv.hrmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HrPendingListResponse(
    @SerializedName("pendingList")
    val pendingList: List<Pending>
) {
    data class Pending(
        @SerializedName("alternate")
        val alternate: Int,
        @SerializedName("altname")
        val altname: String,
        @SerializedName("applied_date")
        val appliedDate: Any?,
        @SerializedName("clgId")
        val clgId: String,
        @SerializedName("dname")
        val dname: String,
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
        @SerializedName("name")
        val name: String,
        @SerializedName("no_of_days")
        val noOfDays: Double,
        @SerializedName("reason")
        val reason: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("signed_by_hod")
        val signedByHod: Int?,
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