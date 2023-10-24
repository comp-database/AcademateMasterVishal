package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.databinding.FragmentFacultyLeaveHistoryBinding
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse
import learn.atharv.facultymoudule.viewmodel.FacultyLeaveHistoryViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyLeaveHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyLeaveHistoryFragment()
    }

    private lateinit var viewModel: FacultyLeaveHistoryViewModel
    private lateinit var repository: AcademateRepositoryFaculty
    private lateinit var binding: FragmentFacultyLeaveHistoryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFacultyLeaveHistoryBinding.inflate(layoutInflater, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FacultyLeaveHistoryViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFacultyLeaveHistory("2139")
        viewModel.FacultyLeaveHistory.observe(viewLifecycleOwner) {
            binding.composeView.apply {
                setContent {
                    LeavHistory(it!!)
                }
            }
        }
    }
}

@Composable
fun LeavHistory(historyList: List<FacultyLeaveHistoryResponse.Leave>) {
    if (historyList != null) {
        LazyColumn{
            items(historyList){
                HistoryItem(
                    fromDate = it.fromDate,
                    toDate = it.toDate,
                    LeaveType = it.leaveId.toString(),
                    alternateStatus = it.statusAlternate.toString(),
                    hrApproval = it.status.toString()
                )
            }
        }
    }
}

@Composable
fun HistoryItem(
    fromDate: String,
    toDate: String,
    LeaveType: String,
    alternateStatus: String,
    hrApproval: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.Green)
            .padding(4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()) {
                Text(fromDate.substring(0,11))
                Text(toDate.substring(0,11))
            }
            Text(LeaveType)
            Text(alternateStatus)
            Text(hrApproval)
        }
    }
}