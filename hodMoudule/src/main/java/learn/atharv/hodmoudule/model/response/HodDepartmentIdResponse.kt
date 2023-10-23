package learn.atharv.hodmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HodDepartmentIdResponse(
    @SerializedName("depart_id")
    val departId: Int
)