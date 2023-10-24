package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyPunchRecordResponse
import retrofit2.Response

class FacultyPunchRecordViewModel(private val repository: AcademateRepositoryFaculty = AcademateRepositoryFaculty()) : ViewModel() {
    var puchRecord = MutableLiveData<Response<FacultyPunchRecordResponse>>()
    fun fetchPuchRecord(uid : String): MutableLiveData<Response<FacultyPunchRecordResponse>> {
        viewModelScope.launch {
            val response = repository.getFacultyPunchRecord(uid)
            puchRecord.value = response
        }
        return puchRecord
    }
}
//  bit complex in case of two different attendance ids need to make calculations
