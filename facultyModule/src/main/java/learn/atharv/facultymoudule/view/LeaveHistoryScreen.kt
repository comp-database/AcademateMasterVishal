package learn.atharv.facultymoudule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.databinding.ActivityLeaveHistoryScreenBinding
import learn.atharv.facultymoudule.databinding.FragmentFacultyLeaveHistoryBinding
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse
import learn.atharv.facultymoudule.viewmodel.FacultyLeaveHistoryViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class LeaveHistoryScreen : AppCompatActivity() {

    private lateinit var binding : ActivityLeaveHistoryScreenBinding
    private lateinit var viewModel: FacultyLeaveHistoryViewModel
    private lateinit var repository: AcademateRepositoryFaculty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaveHistoryScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[FacultyLeaveHistoryViewModel::class.java]

        viewModel.getFacultyLeaveHistory("203")
        viewModel.FacultyLeaveHistory.observe(this) {
            binding.composeView.apply {
                setContent {
                    LeavHistoryScreen(it!!)
                }
            }
        }

    }
}

@Composable
fun LeavHistoryScreen(historyList: List<FacultyLeaveHistoryResponse.Leave>) {
    if (historyList != null) {
        LazyColumn{
            items(historyList){
                HistoryItemScreen(
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
fun HistoryItemScreen(
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
                Text(LeaveType)
                Text(alternateStatus)
                Text(hrApproval)
            }

        }
    }
}