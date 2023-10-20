package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.basicResponse
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import retrofit2.Response

class MainActivityViewModel(private val repository: AcademateRepositoryFaculty = AcademateRepositoryFaculty()) : ViewModel(){
    var FacultyLeaveDetailsResponse: MutableLiveData<Response<FacultyLeaveDataResponse>> =
        MutableLiveData()

    var BasicReponse: MutableLiveData<Response<basicResponse>> =
        MutableLiveData()
    fun getFacultyLeaveData(uid: String) {
        viewModelScope.launch {
            val response = repository.getFacultyLeaveData(uid)
            FacultyLeaveDetailsResponse.value = response
        }
    }

//    fun getFacultyPersonalDataTokenised(token : String){
//        viewModelScope.launch {
//            val response = repository.getFacultyPersonalDataTokenised(token)
//            BasicReponse.value = response
//
//        }
//    }
}