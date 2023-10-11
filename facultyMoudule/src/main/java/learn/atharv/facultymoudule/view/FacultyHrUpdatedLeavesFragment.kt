package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyHrUpdatedLeavesViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyHrUpdatedLeavesFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyHrUpdatedLeavesFragment()
    }

    private lateinit var viewModel: FacultyHrUpdatedLeavesViewModel
    private lateinit var repository: AcademateRepositoryFaculty


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_faculty_hr_updated_leaves, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FacultyHrUpdatedLeavesViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}