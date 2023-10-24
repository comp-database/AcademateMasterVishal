package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse

class FacultyLeaveHistoryViewModel(private val repository : AcademateRepositoryFaculty =  AcademateRepositoryFaculty()) : ViewModel() {
    var FacultyLeaveHistory = MutableLiveData<List<FacultyLeaveHistoryResponse.Leave>?>()

    fun getFacultyLeaveHistory(uid : String) : MutableLiveData<List<FacultyLeaveHistoryResponse.Leave>?>{
        viewModelScope.launch {
            val response = repository.getFacultyLeaveHistory(uid)
            FacultyLeaveHistory.value = response
        }
        return  FacultyLeaveHistory
    }
}