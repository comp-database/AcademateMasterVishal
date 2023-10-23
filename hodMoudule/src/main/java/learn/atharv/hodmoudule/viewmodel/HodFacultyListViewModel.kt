package learn.atharv.hodmoudule.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.hodmoudule.model.AcademateRepositoryHod
import learn.atharv.hodmoudule.model.response.HodMyFacultiesResponse

class HodFacultyListViewModel(context: Context) : ViewModel() {
    val repository = AcademateRepositoryHod(context = context)
    val facultyList = MutableLiveData<List<HodMyFacultiesResponse.Faculty>?>()

    fun fetchFacultyList(): MutableLiveData<List<HodMyFacultiesResponse.Faculty>?> {
        viewModelScope.launch {
            val fList = repository.getFacultyList()
            facultyList.value = fList
        }
        return facultyList
    }
}
