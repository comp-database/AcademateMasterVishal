package learn.atharv.hodmoudule.model.response

import com.google.gson.annotations.SerializedName

data class HodLoginResponse(
    @SerializedName("androidToken")
    val androidToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)