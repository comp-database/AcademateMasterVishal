package learn.atharv.studentmoudule.model

import android.content.Context
import learn.atharv.studentmoudule.model.api.AcademateWebServiceStudent
import learn.atharv.studentmoudule.model.body.StudentInitiatePaymentBody
import learn.atharv.studentmoudule.model.body.StudentLoginBody
import learn.atharv.studentmoudule.model.response.StudentLoginResponse
import learn.atharv.studentmoudule.model.response.payment.StudentInitiatePaymentResponse
import learn.atharv.studentmoudule.model.response.payment.StudentSingleFeeDataResponse
import retrofit2.Response

class AcademateRepositoryStudent(context: Context) {
    val webServiceStudent = AcademateWebServiceStudent(context)


    suspend fun HandleLogin(data : StudentLoginBody):Response<StudentLoginResponse>{
        return webServiceStudent.api.postStudentTokenLogin(data)
    }

    suspend fun HomeScreenDetailsPersonal(): studentHomeScreenOne? {
        val response = webServiceStudent.api.getStudentPersonalDetails()
        if (response.isSuccessful) {
            return studentHomeScreenOne(
                response.body()?.radd?.get(0)?.name.toString(),
                response.body()?.radd?.get(0)?.dob.toString(),
                response.body()?.radd?.get(0)?.caste.toString()
            )
        } else {
            return null
        }
    }
    suspend fun HomeScreenDetailsAcademic(): studentHomeScreenTwo? {
        val response = webServiceStudent.api.getStudentCurrentEducationalDetails()
        if (response.isSuccessful) {
            return studentHomeScreenTwo(
                gr_no = response.body()?.data?.get(0)?.grNumber.toString(),
                year = response.body()?.data?.get(0)?.academicYear.toString(),
                id_no = response.body()?.data?.get(0)?.studClgId.toString(),
                branch = response.body()?.data?.get(0)?.branchId!!.toInt()
            )
        } else {
            return null
        }
    }
    suspend fun HomeScreenDetailsImage(): studentHomeScreenThree? {
        val response = webServiceStudent.api.getStudentDocLinkDetails()
        if (response.isSuccessful) {
            return studentHomeScreenThree(ImageUrl = response.body()?.docs?.photo.toString())
        } else {
            return null
        }
    }


    suspend fun totalFees():studentToatalFeeStatus?{
        val response = webServiceStudent.api.getStudentTotalAmountStatus()
        if (response.isSuccessful){
            return studentToatalFeeStatus(status = response.body()!!.finalSubmit, totalAmount = response.body()!!.result.map { it.amount }.sum())
        }else{
            return null
        }
    }

    suspend fun balanceFees(): studentBalanceFeeStatus? {
        val response = webServiceStudent.api.getStudentBalanceAmountStatus()
        if (response.isSuccessful){
            return studentBalanceFeeStatus(balanceAmount = response.body()!!.result.map { it.balanceAmount }.sum())
        }else{
            return null
        }
    }

    suspend fun getSingleFeeData() : Response<StudentSingleFeeDataResponse>{
        return webServiceStudent.api.getStudentSingleFeeData()
    }

    suspend fun postStudentInitiatePayment(body : StudentInitiatePaymentBody) : Response<StudentInitiatePaymentResponse>{
        return webServiceStudent.api.postStudentInitiatePayment(body)
    }
}

data class studentToatalFeeStatus(var status : Int , var totalAmount : Int)

data class studentBalanceFeeStatus(var balanceAmount : Int)
data class studentHomeScreenOne(var name: String, var dob: String, var caste: String)
data class studentHomeScreenTwo(
    var gr_no: String,
    var year: String,
    var id_no: String,
    var branch: Int
)
data class studentHomeScreenThree(var ImageUrl: String)