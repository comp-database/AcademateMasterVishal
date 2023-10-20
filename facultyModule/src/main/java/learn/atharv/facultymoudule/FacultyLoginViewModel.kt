package learn.atharv.facultymoudule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.body.FacultyLoginData
import learn.atharv.facultymoudule.model.response.FacultyLoginResponse
import retrofit2.Response

class FacultyLoginViewModel(private val repositoryFaculty: AcademateRepositoryFaculty = AcademateRepositoryFaculty()) : ViewModel() {
    var FacultytokenLoginResponse: MutableLiveData<Response<FacultyLoginResponse>> =
        MutableLiveData()
    fun postFacultyTokenLogin(data: FacultyLoginData){
        viewModelScope.launch {
            val response = repositoryFaculty.HandleLogin(data)
            FacultytokenLoginResponse.value = response
        }
    }
}