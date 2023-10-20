package learn.atharv.studentmoudule.model.body


import com.google.gson.annotations.SerializedName

data class StudentInitiatePaymentBody(
    @SerializedName("collegeId")
    val collegeId: String,
    @SerializedName("id")
    val id: String
)