package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyLoginResponse(
    @SerializedName("isLogin")
    val isLogin: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("user_type")
    val userType: Int
)