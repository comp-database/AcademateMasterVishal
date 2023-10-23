package learn.atharv.academatemaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        window.isStatusBarContrastEnforced = true
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

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