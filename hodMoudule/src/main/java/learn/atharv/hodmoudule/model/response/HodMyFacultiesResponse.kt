package learn.atharv.hodmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HodMyFacultiesResponse(
    @SerializedName("FacultyList")
    val facultyList: List<Faculty>
) {
    data class Faculty(
        @SerializedName("contact")
        val contact: String,
        @SerializedName("depart_id")
        val departId: Int,
        @SerializedName("depart_name")
        val departName: String,
        @SerializedName("faculty_clg_id")
        val facultyClgId: String,
        @SerializedName("faculty_id")
        val facultyId: Int,
        @SerializedName("ftname")
        val ftname: String,
        @SerializedName("ftype_id")
        val ftypeId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("role1")
        val role1: String
    )
}