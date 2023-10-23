package learn.atharv.studentmoudule.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactoryStudent
import learn.atharv.studentmoudule.R
import learn.atharv.studentmoudule.databinding.FragmentStudentPaymentBinding
import learn.atharv.studentmoudule.model.response.payment.StudentSingleFeeDataResponse
import learn.atharv.studentmoudule.viewmodel.StudentPaymentViewModel

class StudentPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = StudentPaymentFragment()
    }

    private lateinit var viewModel: StudentPaymentViewModel
    private lateinit var binding: FragmentStudentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentPaymentBinding.inflate(layoutInflater, container, false)
        val viewModelFactory = ViewmodelFactoryStudent(requireContext())
        viewModel =
            ViewModelProvider(this, viewModelFactory)[StudentPaymentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isNetworkAvailable()) {
            viewModel.singleFeeHeads()

            viewModel.singleFeeHead.observe(viewLifecycleOwner){
                binding.cvFeeHeads.apply {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                    setContent {
                        feeHeadsList(studentsFeeHeads = it!!)
                    }
                }
                binding.pbLoading.visibility = View.GONE
            }

            viewModel.totalANDbalanceFees()
            viewModel.totalBalanceFeeStatus.observe(viewLifecycleOwner) {
                binding.tvAmountPaid.text = "₹ " + it.totalAmountPaid
                Log.d("paid", it.totalAmountPaid)
                binding.tvAmountPending.text = "₹ " + it.balanceAmount
                binding.totalFeesPayable.text = "₹ " + it.totalAmount
            }
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}



@Composable
fun feeHeadsList(studentsFeeHeads: ArrayList<StudentSingleFeeDataResponse.StudentSingleFeeDataResponseItem>) {

//    Text(text = "vishal")

    LazyColumn(modifier =
    Modifier.fillMaxSize()
        .padding(16.dp)
    ) {
        items(studentsFeeHeads) { studentFeeHead ->
            Column(modifier =
            Modifier.fillMaxWidth()
                .padding(16.dp)
            ) {
                Row(modifier =
                Modifier.fillMaxHeight()
                ) {
                    Text(text = studentFeeHead.headName,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.source_serif))
                        )
                    Text(text = "₹ " +  studentFeeHead.amount,
                        modifier = Modifier
                        .fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.source_serif))
                        )
                }
            }

        }
    }

//    Surface {
//        Column {
//            LazyColumn(modifier = Modifier
//                .padding(12.dp)) {
//                items(studentsFeeHeads) {item ->
//                    Text(text = item.headName)
//                }
//            }
//        }
//    }
}