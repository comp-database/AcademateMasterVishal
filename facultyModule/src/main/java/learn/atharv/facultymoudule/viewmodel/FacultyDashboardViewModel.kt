package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyDashboardResponse

class FacultyDashboardViewModel(private val repository: AcademateRepositoryFaculty = AcademateRepositoryFaculty(),) : ViewModel() {
    var BasicDataResponse: MutableLiveData<String> = MutableLiveData()
    var DashboardResponse: MutableLiveData<FacultyDashboardResponse.Leave?> = MutableLiveData()
    var AlternateResponse: MutableLiveData<List<FacultyDashboardResponse.Alternate?>?> = MutableLiveData()

    private val _AlternateData = MutableLiveData<List<FacultyDashboardResponse.Alternate?>?>()
    val AlternateData: LiveData<List<FacultyDashboardResponse.Alternate?>?> = _AlternateData
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
    fun getFacultyDashboardAlternate(uid: String){
        viewModelScope.launch {
            val data = repository.getFacultyDashboardAlternate(uid)
            _AlternateData.postValue(data)
        }
    }
}