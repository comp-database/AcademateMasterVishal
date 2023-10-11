package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyLeaveHistoryViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyLeaveHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyLeaveHistoryFragment()
    }

    private lateinit var viewModel: FacultyLeaveHistoryViewModel
    private lateinit var repository: AcademateRepositoryFaculty


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.fragment_faculty_leave_history, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FacultyLeaveHistoryViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}