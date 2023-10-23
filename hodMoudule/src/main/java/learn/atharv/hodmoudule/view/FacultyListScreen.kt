package learn.atharv.hodmoudule.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.R
import learn.atharv.hodmoudule.databinding.ActivityFacultyListScreenBinding
import learn.atharv.hodmoudule.model.response.HodMyFacultiesResponse
import learn.atharv.hodmoudule.viewmodel.HodFacultyListViewModel
import learn.atharv.hodmoudule.viewmodel.HodStudentListViewModel
import learn.atharv.hodmoudule.viewmodel.ViewmodelFactory

class FacultyListScreen : AppCompatActivity() {

    private lateinit var binding : ActivityFacultyListScreenBinding
    private lateinit var viewModel: HodFacultyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewmodelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HodFacultyListViewModel::class.java]

        viewModel.fetchFacultyList().observe(this) {
            binding.cvFacultyList.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    facultyList(it!!)
                    binding.pbFacultyList.visibility = View.GONE
                }
            }
        }

    }
}

@Composable
fun facultyList(facultyList: List<HodMyFacultiesResponse.Faculty>) {

//    Text(text = "vishal")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        var filterText by remember { mutableStateOf("") }

        SearchBar(filterText = filterText) { newText ->
            filterText = newText
        }

        LazyColumn(modifier =
        Modifier.fillMaxSize()
            .padding(16.dp)
        ) {
            itemsIndexed(facultyList) { index, faculty ->

                if (faculty.name.contains(
                        filterText,
                        ignoreCase = true
                    ) || filterText.isBlank()
                ) {
                    Column(modifier =
                    Modifier.fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Row(modifier =
                        Modifier.fillMaxHeight()
                        ) {
                            Text(text = faculty.name,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.source_serif))
                            )
                        }
                    }
                }
            }
        }

    }
}