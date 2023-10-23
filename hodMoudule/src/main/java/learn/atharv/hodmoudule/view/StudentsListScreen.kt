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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.R
import learn.atharv.hodmoudule.databinding.ActivityStudentsListScreenBinding
import learn.atharv.hodmoudule.model.response.HodMyStudentsResponse
import learn.atharv.hodmoudule.viewmodel.HodStudentListViewModel
import learn.atharv.hodmoudule.viewmodel.ViewmodelFactory

class StudentsListScreen : AppCompatActivity() {

    private lateinit var binding : ActivityStudentsListScreenBinding
    private lateinit var viewModel: HodStudentListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModelFactory = ViewmodelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HodStudentListViewModel::class.java]

        viewModel.fetchStudentList().observe(this) {
            binding.cvStudentsList.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    studentList(it!!)
                    binding.pbStudentList.visibility = View.GONE
                }
            }
        }
    }
}

@Composable
fun studentList(studentsList: List<HodMyStudentsResponse.Student>) {

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
            itemsIndexed(studentsList) { index, student ->

                if (student.name.contains(
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
                            Text(text = student.name,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(filterText: String, onFilterTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = filterText,
        onValueChange = { newValue ->
            onFilterTextChanged(newValue)
        },
        placeholder = { Text(text = "Search...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .then(Modifier.clip(RoundedCornerShape(28.dp))),
        singleLine = true,
        shape = RoundedCornerShape(28.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = colorResource(id = R.color.light_gray)
        ),
    )
}