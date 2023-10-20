package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyLeaveDataResponse(
    @SerializedName("facultylist")
    val facultylist: List<Facultylist>,
    @SerializedName("leave_list")
    val leaveList: List<Leave>
) {
    data class Facultylist(
        @SerializedName("faculty_id")
        val facultyId: Int,
        @SerializedName("name")
        val name: String
    )

    data class Leave(
        @SerializedName("leave_id")
        val leaveId: Int,
        @SerializedName("lname")
        val lname: String
    )
}