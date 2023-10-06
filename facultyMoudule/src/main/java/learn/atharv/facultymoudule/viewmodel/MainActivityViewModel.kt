package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import retrofit2.Response

class MainActivityViewModel(private val repository: AcademateRepositoryFaculty,) : ViewModel(){
    var FacultyLeaveDetailsResponse: MutableLiveData<Response<FacultyLeaveDataResponse>> =
        MutableLiveData()
    fun getFacultyLeaveData(uid: String) {
        viewModelScope.launch {
            val response = repository.getFacultyLeaveData(uid)
            FacultyLeaveDetailsResponse.value = response
        }
    }
}