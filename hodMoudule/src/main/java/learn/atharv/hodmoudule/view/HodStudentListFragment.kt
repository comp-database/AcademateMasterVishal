package learn.atharv.hodmoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.viewmodel.HodStudentListViewModel
import learn.atharv.hodmoudule.R

class HodStudentListFragment : Fragment() {

    companion object {
        fun newInstance() = HodStudentListFragment()
    }

    private lateinit var viewModel: HodStudentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hod_student_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HodStudentListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}