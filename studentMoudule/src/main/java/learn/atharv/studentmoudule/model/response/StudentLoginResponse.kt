package learn.atharv.studentmoudule.model.response


import com.google.gson.annotations.SerializedName

data class StudentLoginResponse(
    @SerializedName("androidToken")
    val androidToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)