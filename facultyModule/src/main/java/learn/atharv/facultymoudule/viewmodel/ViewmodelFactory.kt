package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty

class ViewmodelFactory(private val repository: AcademateRepositoryFaculty) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> {
                // Create and return an instance of MainActivityViewModel
                MainActivityViewModel(repository) as T
            }
            //1
            modelClass.isAssignableFrom(FacultyDashboardViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                FacultyDashboardViewModel(repository) as T
            }
            //2
            modelClass.isAssignableFrom(FacultyApplyLeaveViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                FacultyApplyLeaveViewModel(repository) as T
            }
            //3
            modelClass.isAssignableFrom(FacultyPunchRecordViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                FacultyPunchRecordViewModel(repository) as T
            }
            //4
            modelClass.isAssignableFrom(FacultyLeaveHistoryViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                FacultyLeaveHistoryViewModel(repository) as T
            }
            //5
            modelClass.isAssignableFrom(FacultyHrUpdatedLeavesViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                FacultyHrUpdatedLeavesViewModel(repository) as T
            }
            // Add more ViewModel classes as needed
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}