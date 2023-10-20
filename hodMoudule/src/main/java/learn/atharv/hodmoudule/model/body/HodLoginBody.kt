package learn.atharv.hodmoudule.model.body

import com.google.gson.annotations.SerializedName
data class HodLoginBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
