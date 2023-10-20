package learn.atharv.studentmoudule.model.response.payment


import com.google.gson.annotations.SerializedName

class StudentSingleFeeDataResponse : ArrayList<StudentSingleFeeDataResponse.StudentSingleFeeDataResponseItem>(){
    data class StudentSingleFeeDataResponseItem(
        @SerializedName("amount")
        val amount: String,
        @SerializedName("fh_id")
        val fhId: Int,
        @SerializedName("head_name")
        val headName: String,
        @SerializedName("sfh_id")
        val sfhId: Int
    )
}