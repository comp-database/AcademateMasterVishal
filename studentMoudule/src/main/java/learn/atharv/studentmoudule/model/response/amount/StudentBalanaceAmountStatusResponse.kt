package learn.atharv.studentmoudule.model.response.amount


import com.google.gson.annotations.SerializedName

data class StudentBalanaceAmountStatusResponse(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("success")
    val success: Boolean
) {
    data class Result(
        @SerializedName("balance_amount")
        val balanceAmount: Int,
        @SerializedName("fh_id")
        val fhId: Int,
        @SerializedName("receipt_id")
        val receiptId: Int
    )
}