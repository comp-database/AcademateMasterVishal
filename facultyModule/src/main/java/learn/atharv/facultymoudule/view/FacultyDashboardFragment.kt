package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyDashboardViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyDashboardFragment()
    }

    private lateinit var viewModel: FacultyDashboardViewModel
    private lateinit var repository: AcademateRepositoryFaculty

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_faculty_dashboard, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FacultyDashboardViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBasicData("2139")
        viewModel.BasicDataResponse.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.tvFacultyName).text = it
        }
        viewModel.getFacultyDashboardLeave("2139")
        viewModel.DashboardResponse.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.cl).text = it?.casualLeave.toString()
            view.findViewById<TextView>(R.id.el).text = it?.earnedLeave.toString()
            view.findViewById<TextView>(R.id.ml).text = it?.medicalLeave.toString()
            view.findViewById<TextView>(R.id.sv).text = it?.summerVacation.toString()
            view.findViewById<TextView>(R.id.wv).text = it?.winterVacation.toString()
            view.findViewById<TextView>(R.id.cmpl).text = it?.compensationLeave.toString()
        }
        view.findViewById<Button>(R.id.naviagate).setOnClickListener {
            findNavController().navigate(R.id.action_facultyDashboardFragment_to_facultyApplyLeaveFragment4)
        }


        var leave_app_id : String = ""
        viewModel.getFacultyDashboardAlternate("2139")
        viewModel.AlternateData.observe(viewLifecycleOwner){
            Log.d("Alternate List", it.toString())
            leave_app_id = it?.map { leaveAppId -> leaveAppId?.leaveAppId }.toString()
            // TODO : Need to add recyclerview here with populating the list with validate status 0

            // TODO : onClick on the each rv item calling the Post request for same
        }


    }

}