package learn.atharv.academatemaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.personal.admissionModule.AdmissionDashboard
import learn.atharv.academatemaster.databinding.ActivityMainBinding
import learn.atharv.facultymoudule.FacultyMainActivity
import learn.atharv.hodmoudule.HodMainActivity
import learn.atharv.studentmoudule.StudentLoginActivity
import learn.atharv.studentmoudule.StudentMainScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, com.personal.admissionmModule.R.color.dark_blue)
        window.navigationBarColor = ContextCompat.getColor(this, com.personal.admissionmModule.R.color.dark_blue)

        binding.ivStudent.setOnClickListener {
            startActivity(Intent(this, StudentMainScreen::class.java))
        }

        binding.ivHod.setOnClickListener {
            startActivity(Intent(this, HodMainActivity::class.java))
        }

        binding.ivAlumni.setOnClickListener {
            startActivity(Intent(this, AdmissionDashboard::class.java))
        }

    }
}