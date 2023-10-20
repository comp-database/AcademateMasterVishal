package learn.atharv.hrmoudule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hrmoudule.model.AcademateRepositoryHr

class ViewmodelFactory(private val repository: AcademateRepositoryHr) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> {
//                // Create and return an instance of MainActivityViewModel
//                MainActivityViewModel(repository) as T
//            }
            //1
            modelClass.isAssignableFrom(HrDashboardViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                HrDashboardViewModel(repository) as T
            }
            //2
            modelClass.isAssignableFrom(HrAddFacultyViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                HrAddFacultyViewModel(repository) as T
            }
            //3
            modelClass.isAssignableFrom(HrApproveLeaveViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                HrApproveLeaveViewModel(repository) as T
            }
            // Add more ViewModel classes as needed
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}