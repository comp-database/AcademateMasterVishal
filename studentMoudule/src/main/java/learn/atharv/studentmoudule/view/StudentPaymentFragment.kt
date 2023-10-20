package learn.atharv.studentmoudule.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactoryStudent
import learn.atharv.studentmoudule.R
import learn.atharv.studentmoudule.databinding.FragmentStudentPaymentBinding
import learn.atharv.studentmoudule.viewmodel.StudentPaymentViewModel

class StudentPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = StudentPaymentFragment()
    }

    private lateinit var viewModel: StudentPaymentViewModel
    private lateinit var binding : FragmentStudentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentPaymentBinding.inflate(layoutInflater, container, false)
        val viewModelFactory = ViewmodelFactoryStudent(requireContext())
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StudentPaymentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.singleFeeHeads()
        viewModel.singleFeeHead.observe(viewLifecycleOwner){
        }

        viewModel.totalANDbalanceFees()
        viewModel.totalBalanceFeeStatus.observe(viewLifecycleOwner){
            binding.tvAmountPaid.text = it.totalAmountPaid
            Log.d("paid",it.totalAmountPaid)
            binding.tvAmountPending.text = it.balanceAmount
            binding.totalFeesPayable.text = it.totalAmount
        }
        binding.ibNavigateBack.setOnClickListener {
            findNavController().navigate(R.id.action_studentPaymentFragment_to_studentProfileFragment)
        }
    }


}