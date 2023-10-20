package learn.atharv.hodmoudule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.atharv.hodmoudule.model.AcademateRepositoryHod
import learn.atharv.hodmoudule.model.api.AcademateWebServiceHod

class HodLoginActivity : AppCompatActivity() {
    private lateinit var academateWebService: AcademateWebServiceHod
    val repositoryHod = AcademateRepositoryHod(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hod_login)
        academateWebService = AcademateWebServiceHod(this)
//        val repositoryHod = AcademateRepositoryHod(applicationContext)
        calltestAPI()
    }

    private fun calltestAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryHod.getFacultyList()
            Log.d("facultyList",response.toString())
        }
    }
}