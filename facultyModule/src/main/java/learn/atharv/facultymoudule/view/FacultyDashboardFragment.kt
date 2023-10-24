package learn.atharv.facultymoudule.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.personal.common.SharedPreferencesManager
import learn.atharv.facultymoudule.FacultyLoginActivity
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.databinding.FragmentFacultyDashboardBinding
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyDashboardViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyDashboardFragment : Fragment() {
    companion object {
        fun newInstance() = FacultyDashboardFragment()
    }

    private lateinit var viewModel: FacultyDashboardViewModel
    private lateinit var repository: AcademateRepositoryFaculty
    private lateinit var binding: FragmentFacultyDashboardBinding
    var leave_app_id = 0
    var name =""
    var leaveType = ""
    var noOfDays = ""
    var Reason = ""
    var FROMDATE = ""
    var TODATE = ""
    var applied = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentFacultyDashboardBinding.inflate(layoutInflater, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FacultyDashboardViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferencesManager = SharedPreferencesManager(requireContext())
        val uid = sharedPreferencesManager.getString("uid","0")

        if (uid != null) {
            viewModel.getBasicData(uid)
        }
        viewModel.BasicDataResponse.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.tvFacultyName).text = it
        }

        if (uid != null) {
            viewModel.getFacultyDashboardLeave(uid)
        }
        viewModel.DashboardResponse.observe(viewLifecycleOwner){
//            view.findViewById<TextView>(R.id.cl).text = it?.casualLeave.toString()
//            view.findViewById<TextView>(R.id.el).text = it?.earnedLeave.toString()
//            view.findViewById<TextView>(R.id.ml).text = it?.medicalLeave.toString()
//            view.findViewById<TextView>(R.id.sv).text = it?.summerVacation.toString()
//            view.findViewById<TextView>(R.id.wv).text = it?.winterVacation.toString()
//            view.findViewById<TextView>(R.id.cmpl).text = it?.compensationLeave.toString()
        }
        view.findViewById<Button>(R.id.naviagate).setOnClickListener {
            findNavController().navigate(R.id.action_facultyDashboardFragment_to_facultyLeaveHistoryFragment2)
        }
        binding.logout.setOnClickListener {
            val isLogin = false
            sharedPreferencesManager.saveBoolean("isLogin",false)
            val intent = Intent(requireActivity(),FacultyLoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Optional: Close the current activity
            // Clear the back stack (optional)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Toast.makeText(requireContext(), "Logout Successfully", Toast.LENGTH_SHORT).show()
        }
        viewModel.getFacultyDashboardAlternate("2139")
        viewModel.AlternateResponse.observe(viewLifecycleOwner){
            Log.d("Alternate List", it.toString())
            leave_app_id = it?.map { leaveAppId -> leaveAppId.leaveAppId }?.get(0)!!.toInt()
            name = it.map { it?.name }?.get(0).toString()
            leaveType = it?.map { it?.leaveId }?.get(0).toString()
            noOfDays = it?.map { it?.noOfDays }?.get(0).toString()
            Reason = it?.map { it?.reason }?.get(0).toString()
            FROMDATE = it?.map { it?.fromDate }?.get(0).toString()
            TODATE = it?.map { it?.toDate }?.get(0).toString()
            applied = it?.map { it?.appliedDate }?.get(0).toString()
            // TODO : Need to add recyclerview here with populating the list with validate status 0
            binding.composeView.apply {
                setContent {
                    val status = alternate(
                        appId = leave_app_id.toString(),
                        name = name,
                        leaveType = leaveType,
                        noOfDays = noOfDays,
                        Reason = Reason,
                        FROMDATE = FROMDATE,
                        TODATE = TODATE
                    )
                    viewModel.postAlternate(leave_app_id.toInt(),status)
                    viewModel.postAlternateResponse.observe(viewLifecycleOwner){
                        if(it.isSuccessful){
                            Toast.makeText(requireContext(), "Accepted", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            // TODO : onClick on the each rv item calling the Post request for same
        }
    }

}


@Composable
fun alternate(appId : String , name :String , leaveType : String,noOfDays : String,Reason : String,FROMDATE : String,TODATE: String): Int {
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