package learn.atharv.hodmoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.R
import learn.atharv.hodmoudule.viewmodel.HodFacultyListViewModel

class HodFacultyListFragment : Fragment() {

    companion object {
        fun newInstance() = HodFacultyListFragment()
    }

    private lateinit var viewModel: HodFacultyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hod_faculty_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HodFacultyListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}