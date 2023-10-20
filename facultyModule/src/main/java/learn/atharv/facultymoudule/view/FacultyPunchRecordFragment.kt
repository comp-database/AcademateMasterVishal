package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyPunchRecordViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyPunchRecordFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyPunchRecordFragment()
    }

    private lateinit var viewModel: FacultyPunchRecordViewModel
    private lateinit var repository: AcademateRepositoryFaculty
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faculty_punch_record, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FacultyPunchRecordViewModel::class.java)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}