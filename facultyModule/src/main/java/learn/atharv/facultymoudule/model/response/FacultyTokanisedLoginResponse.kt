package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyTokanisedLoginResponse(
    @SerializedName("androidToken")
    val androidToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)