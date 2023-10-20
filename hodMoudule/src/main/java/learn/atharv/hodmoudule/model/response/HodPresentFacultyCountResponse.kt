package learn.atharv.hodmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HodPresentFacultyCountResponse(
    @SerializedName("presentCount")
    val presentCount: Int
)