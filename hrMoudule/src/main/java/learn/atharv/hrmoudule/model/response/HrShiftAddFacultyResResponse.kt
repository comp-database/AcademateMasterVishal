package learn.atharv.hrmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HrShiftAddFacultyResResponse(
    @SerializedName("shifts")
    val shifts: List<Shift>
) {
    data class Shift(
        @SerializedName("in_hour")
        val inHour: Int,
        @SerializedName("in_min")
        val inMin: Int,
        @SerializedName("out_hour")
        val outHour: Int,
        @SerializedName("out_min")
        val outMin: Int,
        @SerializedName("shift_id")
        val shiftId: Int,
        @SerializedName("sname")
        val sname: String
    )
}