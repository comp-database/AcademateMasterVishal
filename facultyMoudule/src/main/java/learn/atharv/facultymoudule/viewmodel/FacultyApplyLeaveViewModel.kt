package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyBasicMesssageResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import retrofit2.Response
import java.sql.Date

class FacultyApplyLeaveViewModel(private val repository: AcademateRepositoryFaculty) :
    ViewModel() {
    // TODO: Implement the ViewModel
    var FacultyLeaveDetailsResponse: MutableLiveData<Response<FacultyLeaveDataResponse>> =
        MutableLiveData()
    var FacultyLeaveApplicationResponse: MutableLiveData<Response<FacultyBasicMesssageResponse>> =
        MutableLiveData()

    private val _LeaveData = MutableLiveData<List<String>>()
    val LeaveData: LiveData<List<String>> = _LeaveData

    private val _FacultyData = MutableLiveData<List<String>>()
    val FacultyData: LiveData<List<String>> = _FacultyData
    fun getFacultyLeaveData(uid: String) {
        viewModelScope.launch {
            val leaveList = repository.getFacultyLeaveData(uid).body()?.leaveList?.map { leave -> leave.lname }
            val facultyList = repository.getFacultyLeaveData(uid).body()?.facultylist?.map { faculty -> faculty.name }
            _FacultyData.postValue(facultyList!!)
            _LeaveData.postValue(leaveList!!)
        }
    }

    fun postFacultyLeaveApplication(
        leaveId: Int,
        halfFullDay: String,
        fromDate: Date,
        toDate: Date,
        reason: String,
        noOfDays: Int,
        alternate: Int,
        uid: Int,
        doc: String,
    ) {
        viewModelScope.launch {
            val response = repository.postFacultyLeaveApplication(
                leaveId,
                halfFullDay,
                fromDate,
                toDate,
                reason,
                noOfDays,
                alternate,
                uid,
                doc
            )
            FacultyLeaveApplicationResponse.value = response
        }
    }


}