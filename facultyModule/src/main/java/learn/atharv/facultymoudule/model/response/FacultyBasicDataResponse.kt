package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyBasicDataResponse(
    @SerializedName("user")
    val user: User
) {
    data class User(
        @SerializedName("name")
        val name: String,
        @SerializedName("role")
        val role: Any?
    )
}