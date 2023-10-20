package learn.atharv.academatemaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import learn.atharv.academatemaster.databinding.ActivityMainBinding
import learn.atharv.facultymoudule.FacultyMainActivity
import learn.atharv.hodmoudule.HodMainActivity
import learn.atharv.studentmoudule.StudentLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivStudent.setOnClickListener {
            startActivity(Intent(this, StudentLoginActivity::class.java))
        }

        binding.ivHod.setOnClickListener {
            startActivity(Intent(this, HodMainActivity::class.java))
        }
    }
}