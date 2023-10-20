package learn.atharv.studentmoudule.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.studentmoudule.model.AcademateRepositoryStudent
import learn.atharv.studentmoudule.model.response.payment.StudentSingleFeeDataResponse

class StudentPaymentViewModel(context: Context) : ViewModel() {
    val repository = AcademateRepositoryStudent(context)

    val totalBalanceFeeStatus = MutableLiveData<feeStatus>()
    val singleFeeHead = MutableLiveData<StudentSingleFeeDataResponse.StudentSingleFeeDataResponseItem?>()

    fun totalANDbalanceFees() : MutableLiveData<feeStatus>{
        viewModelScope.launch {
            val totalAmount = repository.totalFees()?.totalAmount
            val balanceAmount = repository.balanceFees()?.balanceAmount
            var totalAmountPaid = 0
            if (balanceAmount==0){
                totalAmountPaid = totalAmount!!.toInt()
            }else{
                totalAmountPaid = totalAmount!!.toInt()-balanceAmount!!.toInt()
            }
            val finalSubmit = repository.totalFees()?.status
            val content = feeStatus(totalAmountPaid = totalAmountPaid.toString(),totalAmount = totalAmount.toString(), balanceAmount = balanceAmount.toString(),finalSubmit = finalSubmit!!.toInt())

            totalBalanceFeeStatus.value = content
        }
        return totalBalanceFeeStatus
    }

    fun singleFeeHeads() : MutableLiveData<StudentSingleFeeDataResponse.StudentSingleFeeDataResponseItem?> {
        viewModelScope.launch{
            val response = repository.getSingleFeeData()
            if (response.isSuccessful){
                val content = response.body()?.get(0)
                singleFeeHead.value = content
            }
        }
        return singleFeeHead
    }

}
data class feeStatus(var totalAmountPaid : String ,var totalAmount : String,var balanceAmount : String,var finalSubmit : Int)