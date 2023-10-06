package learn.atharv.facultymoudule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty

class ViewmodelFactory(private val repository: AcademateRepositoryFaculty) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}