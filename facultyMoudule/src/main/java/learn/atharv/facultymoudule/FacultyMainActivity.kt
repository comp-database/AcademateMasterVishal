package learn.atharv.facultymoudule

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.MainActivityViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyMainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_main)
        val repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainActivityViewModel::class.java]
        findViewById<Button>(R.id.submit).setOnClickListener {
            val uid : String = findViewById<EditText>(R.id.etUID).text.toString()
            viewModel.getFacultyLeaveData(uid)
            viewModel.FacultyLeaveDetailsResponse.observe(this){
                if (it.isSuccessful){
                    findViewById<TextView>(R.id.tvAlternate).text = it.body()?.facultylist.toString()
                    findViewById<TextView>(R.id.tvAvailableLeaves).text = it.body()?.leaveList.toString()
                }else{
                    findViewById<TextView>(R.id.tvAlternate).text = it.errorBody().toString()
                }
            }
        }
    }
}