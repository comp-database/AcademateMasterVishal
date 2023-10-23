package learn.atharv.hodmoudule

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.databinding.ActivityHodMainBinding
import learn.atharv.hodmoudule.model.response.HodPresentFacultyCountResponse
import learn.atharv.hodmoudule.view.FacultyListScreen
import learn.atharv.hodmoudule.view.StudentsListScreen
import learn.atharv.hodmoudule.view.studentList
import learn.atharv.hodmoudule.viewmodel.HodFacultyListViewModel
import learn.atharv.hodmoudule.viewmodel.HodHomeViewModel
import learn.atharv.hodmoudule.viewmodel.HodStudentListViewModel
import learn.atharv.hodmoudule.viewmodel.ViewmodelFactory

class HodMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHodMainBinding
    private lateinit var facultyViewModel : HodFacultyListViewModel
    private lateinit var studentViewModel : HodStudentListViewModel
    private lateinit var presentCountViewModel : HodHomeViewModel
    private lateinit var progressDialog: ProgressDialog
    private var presentFaculty : Int = 10
    private var totalFaculty : Int = 12
    var observationCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHodMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)

        showProgressDialog()

        val viewModelFactory = ViewmodelFactory(this)
        studentViewModel = ViewModelProvider(this, viewModelFactory)[HodStudentListViewModel::class.java]
        studentViewModel.fetchStudentList().observe(this) {
            val size = it?.size.toString()
            binding.tvTotalStudentCount.text = size
            observationCount++
            checkObservationCompletion()
        }

        facultyViewModel = ViewModelProvider(this, viewModelFactory)[HodFacultyListViewModel::class.java]
        facultyViewModel.fetchFacultyList().observe(this) {
            totalFaculty = it!!.size
            binding.tvFacultyCount.text = totalFaculty.toString()
            observationCount++
            checkObservationCompletion()
        }

        presentCountViewModel = ViewModelProvider(this, viewModelFactory)[HodHomeViewModel::class.java]
        presentCountViewModel.presentFacultyCount().observe(this) {
            presentFaculty = it!!
            binding.tvPresentFacultyCount.text = presentFaculty.toString()
            observationCount++
            checkObservationCompletion()
        }

        //Setting up the navigation drawer
        setSupportActionBar(binding.tbAppToolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.dlDrawer, binding.tbAppToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.dlDrawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_open_24)
        binding.nvSideNavigationViewHod.bringToFront()


        //Setup for the side navigation
        binding.nvSideNavigationViewHod.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_menu_show_student_list -> {
                    startActivity(Intent(this, StudentsListScreen::class.java))
                }

                R.id.navigation_menu_show_faculty_list -> {
                    startActivity(Intent(this, FacultyListScreen::class.java))
                }
            }

            binding.dlDrawer.closeDrawer(GravityCompat.START)
            true
        }


    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    // Call this function to dismiss the progress dialog
    private fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    private fun checkObservationCompletion() {
        if (observationCount == 3) {
            dismissProgressDialog()
            binding.tvAbsentFacultyCount.text = (totalFaculty - presentFaculty).toString()
        }
    }
}