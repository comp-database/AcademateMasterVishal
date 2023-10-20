package learn.atharv.facultymoudule.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.atharv.studentmoudule.viewmodel.StudentPaymentViewModel
import learn.atharv.studentmoudule.viewmodel.StudentProfileViewModel

class ViewmodelFactoryStudent(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(StudentProfileViewModel::class.java) -> {
                // Create and return an instance of MainActivityViewModel
                StudentProfileViewModel(context) as T
            }
            //1
            modelClass.isAssignableFrom(StudentPaymentViewModel::class.java) -> {
                // Create and return an instance of AnotherViewModel
                StudentPaymentViewModel(context) as T
            }
            // Add more ViewModel classes as needed
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}