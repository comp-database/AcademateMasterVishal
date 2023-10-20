package learn.atharv.facultymoudule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import learn.atharv.facultymoudule.viewmodel.MainActivityViewModel

class FacultyMainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_main)

//        viewModel = MainActivityViewModel()
//        val token = intent.getStringExtra("token").toString()
//        viewModel.getFacultyPersonalDataTokenised(token)
//        viewModel.BasicReponse.observe(this){
//            if (it.isSuccessful){
//                findViewById<TextView>(R.id.neww).text = it.body().toString()
//            }
//        }
//        val repository = AcademateRepositoryFaculty()
//        val viewModelFactory = ViewmodelFactory(repository)
//        val availableLeaves = findViewById<Spinner>(R.id.spinner1)
//        val alternates = findViewById<Spinner>(R.id.spinner2)
//        viewModel = ViewModelProvider(this,viewModelFactory)[MainActivityViewModel::class.java]
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
    }
}
//TODO : Maintain the data class array that will consist of all required parameters for the post request the leave
//    }
//}