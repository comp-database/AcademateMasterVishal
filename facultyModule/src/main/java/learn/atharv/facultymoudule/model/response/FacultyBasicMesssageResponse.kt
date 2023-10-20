package learn.atharv.facultymoudule.model.response


import com.google.gson.annotations.SerializedName

data class FacultyBasicMesssageResponse(
    @SerializedName("message")
    val message: String
)