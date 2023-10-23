package learn.atharv.hodmoudule.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.hodmoudule.model.AcademateRepositoryHod
import learn.atharv.hodmoudule.model.response.HodMyStudentsResponse

class HodStudentListViewModel(context: Context) : ViewModel() {
    val repository = AcademateRepositoryHod(context)
    val studentList = MutableLiveData<List<HodMyStudentsResponse.Student>?>()

    fun fetchStudentList(): MutableLiveData<List<HodMyStudentsResponse.Student>?> {
        viewModelScope.launch {
            val fList = repository.getStudentList()
            studentList.value = fList
        }
        return studentList
    }
}