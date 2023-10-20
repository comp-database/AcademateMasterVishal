package learn.atharv.studentmoudule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.atharv.studentmoudule.model.api.AcademateWebServiceStudent
import learn.atharv.studentmoudule.model.body.StudentInitiatePaymentBody

class StudentLoginActivity : AppCompatActivity() {
    private lateinit var academateWebService: AcademateWebServiceStudent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        academateWebService = AcademateWebServiceStudent(this)
        callPersonalDetailsApi()

    }
    private fun callPersonalDetailsApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = academateWebService.api.postStudentInitiatePayment(
                StudentInitiatePaymentBody("VU1F2122010","4")
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                // Handle personal details response

                Log.d("Personal Details", responseBody.toString())
            } else {
                // Handle error
            }
        }
    }
}