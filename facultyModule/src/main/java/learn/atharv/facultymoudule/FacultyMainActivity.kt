package learn.atharv.facultymoudule

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.personal.common.SharedPreferencesManager
import learn.atharv.facultymoudule.databinding.ActivityFacultyMainBinding
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.view.ApplyLeaveScreen
import learn.atharv.facultymoudule.view.LeaveHistoryScreen
import learn.atharv.facultymoudule.view.alternate
import learn.atharv.facultymoudule.viewmodel.FacultyDashboardViewModel
import learn.atharv.facultymoudule.viewmodel.MainActivityViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacultyMainBinding
    private lateinit var facultyDashboardViewModel : FacultyDashboardViewModel
    private lateinit var progressDialog: ProgressDialog
    private var presentFaculty : Int = 10
    private var totalFaculty : Int = 12
    private lateinit var repository: AcademateRepositoryFaculty
    var observationCount = 0
    var leave_app_id = 0
    var name =""
    var leaveType = ""
    var noOfDays = ""
    var Reason = ""
    var FROMDATE = ""
    var TODATE = ""
    var applied = ""

    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)

        showProgressDialog()


        val sharedPreferencesManager = SharedPreferencesManager(this)
//        val uid = sharedPreferencesManager.getString("uid","2134")
        val uid = "203"

        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        facultyDashboardViewModel = ViewModelProvider(this,viewModelFactory)[FacultyDashboardViewModel::class.java]


        if (uid != null) {
            facultyDashboardViewModel.getBasicData(uid)
        }
        facultyDashboardViewModel.BasicDataResponse.observe(this){
            binding.tvFacultyName.text = it
            observationCount++
            checkObservationCompletion()
        }

        if (uid != null) {
            facultyDashboardViewModel.getFacultyDashboardLeave(uid)
            observationCount++
            checkObservationCompletion()
        }
        facultyDashboardViewModel.DashboardResponse.observe(this){
            binding.tvCasualLeave.text = it?.casualLeave.toString()
            binding.tvEarnedLeave.text = it?.earnedLeave.toString()
            binding.tvMedicalLeave.text = it?.medicalLeave.toString()
            binding.tvSummerVacation.text = it?.summerVacation.toString()
            observationCount++
            checkObservationCompletion()
        }

        facultyDashboardViewModel.getFacultyDashboardAlternate("2139")
        facultyDashboardViewModel.AlternateResponse.observe(this){
            Log.d("Alternate List", it.toString())
            leave_app_id = it?.map { leaveAppId -> leaveAppId.leaveAppId }?.get(0)!!.toInt()
            name = it.map { it.name }?.get(0).toString()
            leaveType = it?.map { it?.leaveId }?.get(0).toString()
            noOfDays = it?.map { it?.noOfDays }?.get(0).toString()
            Reason = it?.map { it?.reason }?.get(0).toString()
            FROMDATE = it?.map { it?.fromDate }?.get(0).toString()
            TODATE = it?.map { it?.toDate }?.get(0).toString()
            applied = it?.map { it?.appliedDate }?.get(0).toString()
            // TODO : Need to add recyclerview here with populating the list with validate status 0
            binding.composeView.apply {
                setContent {
                    val status = alternateFaculty(
                        appId = leave_app_id.toString(),
                        name = name,
                        leaveType = leaveType,
                        noOfDays = noOfDays,
                        Reason = Reason,
                        FROMDATE = FROMDATE,
                        TODATE = TODATE
                    )
                    facultyDashboardViewModel.postAlternate(leave_app_id.toInt(),status)
                    facultyDashboardViewModel.postAlternateResponse.observe(this@FacultyMainActivity){
                        if(it.isSuccessful){
                            Toast.makeText(this@FacultyMainActivity, "Accepted", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            observationCount++
            checkObservationCompletion()
            // TODO : onClick on the each rv item calling the Post request for same
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
                R.id.navigation_menu_apply_leave -> {
                    startActivity(Intent(this, ApplyLeaveScreen::class.java))
                }

                R.id.navigation_menu_leave_history -> {
                    startActivity(Intent(this, LeaveHistoryScreen::class.java))
                }
//
//                R.id.navigation_menu_apply_leave -> {
//                    startActivity(Intent(this, FacultyListScreen::class.java))
//                }
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
        if (observationCount == 4) {
            dismissProgressDialog()
//            binding.tvAbsentFacultyCount.text = (totalFaculty - presentFaculty).toString()
        }
    }
}


@Composable
fun alternateFaculty(appId : String , name :String , leaveType : String,noOfDays : String,Reason : String,FROMDATE : String,TODATE: String): Int {
    var status by  remember  { mutableStateOf(0) }
//    val repositoryFaculty = AcademateRepositoryFaculty()
//    val viewModel : FacultyDashboardViewModel = viewModel(factory = ViewmodelFactory(repositoryFaculty))

    Text(text = appId)
    Column {
        Row {
            Column {
                Text("name")
                Text(name)
            }
            Column {
                Text("Leave Type")
                Text(leaveType)
            }
            Column {
                Text("number Of Days")
                Text(noOfDays)
            }
            Column {
                Text("Reason")
                Text(Reason)
            }
            Column {
                Text("From Date")
                Text(FROMDATE)
            }
            Column {
                Text("To Date")
                Text(TODATE)
            }
        }
        Row{
            val context = LocalContext.current
            Button(onClick = {status = 1} , modifier = Modifier.background(color = Color.Green)) {
                Text("Accept", color = Color.Black)
            }
            Button(onClick = {status = 2},modifier = Modifier.background(color = Color.Red)) {
                Text("Deny")
            }
        }
    }
    return status
}