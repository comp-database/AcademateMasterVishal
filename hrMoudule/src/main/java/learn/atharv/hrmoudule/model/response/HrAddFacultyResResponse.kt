package learn.atharv.hrmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HrAddFacultyResResponse(
    @SerializedName("depart_list")
    val departList: List<Depart>,
    @SerializedName("ftype_list")
    val ftypeList: List<Ftype>,
    @SerializedName("role")
    val role: List<Role>
) {
    data class Depart(
        @SerializedName("depart_id")
        val departId: Int,
        @SerializedName("name")
        val name: String
    )

    data class Ftype(
        @SerializedName("ftname")
        val ftname: String,
        @SerializedName("ftype_id")
        val ftypeId: Int
    )

    data class Role(
        @SerializedName("name")
        val name: String,
        @SerializedName("role_id")
        val roleId: Int
    )
}