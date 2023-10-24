package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class FacultyPunchRecordFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyPunchRecordFragment()
    }

//    private lateinit var viewModel: FacultyPunchRecordViewModel
//    private lateinit var repository: AcademateRepositoryFaculty
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MyComposable()
            }
        }
//        val view = inflater.inflate(R.layout.fragment_faculty_punch_record, container, false)
//        repository = AcademateRepositoryFaculty()
//        val viewModelFactory = ViewmodelFactory(repository)
//        viewModel = ViewModelProvider(this,viewModelFactory).get(FacultyPunchRecordViewModel::class.java)
//        return view
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }

}

@Composable
fun MyComposable() {
    Text(text = "Hello, Jetpack Compose!")
}