package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyPunchRecordResponse(
    @SerializedName("punch")
    val punch: List<Punch>
) {
    data class Punch(
        @SerializedName("att_id")
        val attId: Int,
        @SerializedName("date_d")
        val dateD: String,
        @SerializedName("faculty_clg_id")
        val facultyClgId: String,
        @SerializedName("puch_time")
        val puchTime: String,
        @SerializedName("punch_type_id")
        val punchTypeId: String
    )
}