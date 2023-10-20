package learn.atharv.hrmoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hrmoudule.R
import learn.atharv.hrmoudule.model.AcademateRepositoryHr
import learn.atharv.hrmoudule.viewmodel.HrApproveLeaveViewModel
import learn.atharv.hrmoudule.viewmodel.ViewmodelFactory

class HrApproveLeaveFragment : Fragment() {

    companion object {
        fun newInstance() = HrApproveLeaveFragment()
    }

    private lateinit var viewModel: HrApproveLeaveViewModel
    private lateinit var repository: AcademateRepositoryHr

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hr_approve_leave, container, false)
        repository = AcademateRepositoryHr()
        val vmFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this, vmFactory).get(HrApproveLeaveViewModel::class.java)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}