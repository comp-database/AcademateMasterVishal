package learn.atharv.hodmoudule.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.hodmoudule.model.AcademateRepositoryHod

class HodHomeViewModel(context: Context) : ViewModel() {
    val DepartmentName = MutableLiveData<String>()
    val PresentFacultyCount = MutableLiveData<Int?>()
    val repositoryHod = AcademateRepositoryHod(context = context)
//    fun departmentName () : MutableLiveData<String>{
//        viewModelScope.launch {
//            val id = repositoryHod.getDepartmentID()
//            val departname = when(id){
//                10 -> "Computer Department"
//                1 -> "Information Technology"
//                8 -> "AI&DS"
//                11 -> "EXTC"
//                else -> "Invalid Department"
//            }
//            DepartmentName.value = departname
//        }
//        return DepartmentName
//    }
    fun presentFacultyCount(): MutableLiveData<Int?> {
        viewModelScope.launch {
            val count = repositoryHod.presentFacultyCount()
            PresentFacultyCount.value = count
        }
        return PresentFacultyCount
    }

}