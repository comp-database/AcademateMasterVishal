package learn.atharv.studentmoudule.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.atharv.studentmoudule.model.AcademateRepositoryStudent

class StudentProfileViewModel(context: Context) : ViewModel() {
    val repository = AcademateRepositoryStudent(context)


    val resultLiveData = MutableLiveData<homeScreenContent>()

    fun homeScreenFunc() : MutableLiveData<homeScreenContent> {
        viewModelScope.launch {
            val name = repository.HomeScreenDetailsPersonal()?.name.toString()
            val dob = repository.HomeScreenDetailsPersonal()?.dob.toString()
            val caste = repository.HomeScreenDetailsPersonal()?.caste.toString()
            val gr_no = repository.HomeScreenDetailsAcademic()?.gr_no.toString()
            val year = repository.HomeScreenDetailsAcademic()?.year.toString()
            val id_no = repository.HomeScreenDetailsAcademic()?.id_no.toString()
            val branch = when (repository.HomeScreenDetailsAcademic()?.branch) {
                1 -> "Computer Engineering"
                4 -> "Information Technology"
                2 -> "AI&DS"
                else -> "Something went Wrong"
            }
            val ImageUrl = repository.HomeScreenDetailsImage()?.ImageUrl.toString()


            val content = homeScreenContent(name ,dob,caste,gr_no,year,id_no,branch,ImageUrl)
            resultLiveData.value = content
        }
        return resultLiveData
    }

}

data class homeScreenContent(
    var name: String,
    var dob: String,
    var caste: String,
    var gr_no: String,
    var year: String,
    var id_no: String,
    var branch: String,
    var ImageUrl: String
)


//data class studentHomeScreenOne(var name: String, var dob: String, var caste: String)
//data class studentHomeScreenTwo(
//    var gr_no: String, var year: String, var id_no: String, var branch: Int
//)
//
//data class studentHomeScreenThree(var ImageUrl: String)