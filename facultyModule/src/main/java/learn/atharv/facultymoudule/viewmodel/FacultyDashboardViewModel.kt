package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyBasicMesssageResponse
import learn.atharv.facultymoudule.model.response.FacultyDashboardResponse
import retrofit2.Response

class FacultyDashboardViewModel(private val repository: AcademateRepositoryFaculty = AcademateRepositoryFaculty(),) : ViewModel() {
    var BasicDataResponse: MutableLiveData<String> = MutableLiveData()
    var DashboardResponse: MutableLiveData<FacultyDashboardResponse.Leave?> = MutableLiveData()
    var AlternateResponse: MutableLiveData<List<FacultyDashboardResponse.Alternate>> = MutableLiveData()

    var postAlternateResponse: MutableLiveData<Response<FacultyBasicMesssageResponse>> =
        MutableLiveData()

//    private val _AlternateData = MutableLiveData<List<FacultyDashboardResponse.Alternate?>?>()
//    val AlternateData: LiveData<List<FacultyDashboardResponse.Alternate?>?> = _AlternateData
    fun getBasicData(uid : String){
        viewModelScope.launch {
            val data = repository.getBasicData(uid)
            BasicDataResponse.value = data
        }
    }
    fun getFacultyDashboardLeave(uid: String){
        viewModelScope.launch{
            val data = repository.getFacultyDashboardLeave(uid)
            DashboardResponse.value = data
        }
    }
    fun getFacultyDashboardAlternate(uid: String) : MutableLiveData<List<FacultyDashboardResponse.Alternate>>{
        viewModelScope.launch {
            val data = repository.getFacultyDashboardAlternate(uid)
            AlternateResponse.value = data
        }
        return AlternateResponse
    }
    fun postAlternate(leave_app_id : Int ,status :Int){
        viewModelScope.launch {
            val response = repository.postTakeCharge(leave_app_id,status)
            postAlternateResponse.value = response
        }
    }
}