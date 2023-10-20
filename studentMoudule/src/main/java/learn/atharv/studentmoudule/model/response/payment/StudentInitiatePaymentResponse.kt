package learn.atharv.studentmoudule.model.response.payment


import com.google.gson.annotations.SerializedName

data class StudentInitiatePaymentResponse(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("collegeId")
    val collegeId: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("Fh")
    val fh: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("txnid")
    val txnid: String
)