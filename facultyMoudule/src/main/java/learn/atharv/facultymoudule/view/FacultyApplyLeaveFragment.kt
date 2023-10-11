package learn.atharv.facultymoudule.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyApplyLeaveViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory

class FacultyApplyLeaveFragment : Fragment() {

    companion object {
        fun newInstance() = FacultyApplyLeaveFragment()
    }

    private lateinit var viewModel: FacultyApplyLeaveViewModel
    private lateinit var repository: AcademateRepositoryFaculty

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Here You Have to initialize the ALl the things those are Initiate on Create
        val view = inflater.inflate(R.layout.fragment_faculty_apply_leave, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FacultyApplyLeaveViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFacultyLeaveData("223")
        viewModel.FacultyData.observe(viewLifecycleOwner){Faculties->
            Log.d("Faculty List", Faculties.toString())
        }
        viewModel.LeaveData.observe(viewLifecycleOwner){Leaves->
            Log.d("Leave List", Leaves.toString())
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel



//        val availableLeaves = findViewById<Spinner>(R.id.spinner1)
//        val alternates = findViewById<Spinner>(R.id.spinner2)
//        findViewById<Button>(R.id.submit).setOnClickListener {
//            val uid : String = findViewById<EditText>(R.id.etUID).text.toString()
//            viewModel.getFacultyLeaveData(uid)
//            viewModel.FacultyLeaveDetailsResponse.observe(this){
//                if (it.isSuccessful){
////                    findViewById<TextView>(R.id.alTv).text = it.body()?.facultylist.toString()
////                    findViewById<TextView>(R.id.lsTv).text = it.body()?.leaveList.toString()
//                    // .map { leave -> leave.lname } this function takes the things as the signle and map it to the specific required param
//                    val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, it.body()!!.leaveList.map { leave -> leave.lname })
//                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    availableLeaves.adapter = adapter1
//
//                    availableLeaves.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                        override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
//                            val selectedItem = it.body()!!.leaveList[position]
//                            // Do something with the selected item, e.g., display a Toast
//                            Toast.makeText(baseContext, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
//                        }
//
//                        override fun onNothingSelected(parentView: AdapterView<*>) {
//                            // Do nothing when nothing is selected
//                            Toast.makeText(baseContext, "Nothing Selected", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                    alternates.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                        override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
//                            val selectedItem = it.body()!!.facultylist[position]
//                            // Do something with the selected item, e.g., display a Toast
//                            Toast.makeText(baseContext, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
//                        }
//
//                        override fun onNothingSelected(parentView: AdapterView<*>) {
//                            // Do nothing when nothing is selected
//                            Toast.makeText(baseContext, "Nothing Selected", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, it.body()!!.facultylist.map { facuty -> facuty.name })
//                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    alternates.adapter = adapter2
//                }else{
//                    Log.d("Error", it.errorBody().toString())
//                }
//            }
//        }
    }
}