package learn.atharv.facultymoudule.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import learn.atharv.facultymoudule.R
import learn.atharv.facultymoudule.model.AcademateRepositoryFaculty
import learn.atharv.facultymoudule.viewmodel.FacultyApplyLeaveViewModel
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.concurrent.TimeUnit

class FacultyApplyLeaveFragment : Fragment() {

    var to_date: String = ""
    var leave_id: Int = 1
    var half_full_day = ""
    var from_date = ""
    var no_of_date: Double = 0.2
    var alternate: Int = 2
    var uid = ""
    var doc = ""
    var reason = ""

    companion object {
        fun newInstance() = FacultyApplyLeaveFragment()
    }

    private lateinit var viewModel: FacultyApplyLeaveViewModel
    private lateinit var repository: AcademateRepositoryFaculty

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Here You Have to initialize the ALl the things those are Initiate on Create
        val view = inflater.inflate(R.layout.fragment_faculty_apply_leave, container, false)
        repository = AcademateRepositoryFaculty()
        val viewModelFactory = ViewmodelFactory(repository)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FacultyApplyLeaveViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val availableLeaves = view.findViewById<Spinner>(R.id.spinner1)
        val alternates = view.findViewById<Spinner>(R.id.spinner2)
        val secondSelection = view.findViewById<Spinner>(R.id.spinner)

//        var leave_id: Int = 1
//        var half_full_day = ""
//        var from_date = ""
//        var no_of_date: Double = 0.2
//        var alternate: Int = 2
//        var uid = ""
//        var doc = ""
//        var reason = ""

        viewModel.getFacultyLeaveData("2139")
        viewModel.FacultyLeaveDetailsResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
//                    findViewById<TextView>(R.id.alTv).text = it.body()?.facultylist.toString()
//                    findViewById<TextView>(R.id.lsTv).text = it.body()?.leaveList.toString()
                // .map { leave -> leave.lname } this function takes the things as the signle and map it to the specific required param
                Log.d("dataList", it.body()!!.leaveList.map { leave -> leave.lname }.toString())
                val adapter1 = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item,
                    it.body()!!.leaveList.map { leave -> leave.lname })
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                availableLeaves.adapter = adapter1

                availableLeaves.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            val LeaveselectedItem = it.body()!!.leaveList[position].leaveId
                            leave_id = LeaveselectedItem.toInt()
                            Log.d("leaveId", leave_id.toString())
                            // Do something with the selected item, e.g., display a Toast
//                            Toast.makeText(
//                                requireContext(), "Selected: $LeaveselectedItem", Toast.LENGTH_SHORT
//                            ).show()
                        }

                        override fun onNothingSelected(parentView: AdapterView<*>) {
                            // Do nothing when nothing is selected
//                            Toast.makeText(requireContext(), "Nothing Selected", Toast.LENGTH_SHORT)
//                                .show()
                        }
                    }

                val adapter2 = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item,
                    it.body()!!.facultylist.map { facuty -> facuty.name })
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                alternates.adapter = adapter2

                alternates.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long
                    ) {
                        val FacultyselectedItem = it.body()!!.facultylist[position].facultyId
                        // Do something with the selected item, e.g., display a Toast

                        alternate = FacultyselectedItem
                        Log.d("alternate", alternate.toString())
//                        Toast.makeText(
//                            requireContext(), "Selected: $FacultyselectedItem", Toast.LENGTH_SHORT
//                        ).show()
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>) {
                        // Do nothing when nothing is selected
//                        Toast.makeText(requireContext(), "Nothing Selected", Toast.LENGTH_SHORT)
//                            .show()
                    }
                }

            } else {
                Log.d("Error", it.errorBody().toString())
            }
        }

        // code for second selection adapter
        //TODO : if leaveId = 1,3,8 then show the second selection eles dont show second selection
        val selectionList = arrayOf("First Half - Half Day", "Second Half - Half Day", "Full Day")
        val secondSelectionAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, selectionList)
        secondSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        secondSelection.adapter = secondSelectionAdapter
        secondSelection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long
            ) {
                val selectedItem = selectionList[position]

                half_full_day = selectedItem
                Log.d("Half Full Day", half_full_day)
                // Do something with the selected item, e.g., display a Toast
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT)
//                    .show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing when nothing is selected
//                Toast.makeText(requireContext(), "Nothing Selected", Toast.LENGTH_SHORT).show()
            }
        }


        var fromDate: String = ""
        val fromdateDP = view.findViewById(R.id.fromDatedp) as ImageView
        fromdateDP.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog
            val datePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Handle the selected date
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    // You can update your UI or save the selected date as needed.
                    view.findViewById<TextView>(R.id.FromDatetv).text = selectedDate

                    // TODO : year-month-date for database insertion
                    fromDate =
                        "${selectedYear}-${selectedMonth + 1}-${selectedDay}" // Date(selectedYear,selectedMonth,selectedDay)
                    from_date = fromDate
                },
                year, month, day
            )
            // Show the DatePickerDialog
            datePicker.show()
        }
        Log.d("fromDate",from_date)



        var toDate: String = ""
        val todateDP = view.findViewById(R.id.toDatedp) as ImageView
        todateDP.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog
            val datePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Handle the selected date
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    // You can update your UI or save the selected date as needed.
                    view.findViewById<TextView>(R.id.toDatetv).text = selectedDate


                    // TODO : year-month-date
                    toDate = "${selectedYear}-${selectedMonth + 1}-${selectedDay}"
                    to_date = toDate
                    Log.d("ToDateold", toDate)
                },
                day, month, year
            )
            // Show the DatePickerDialog
            datePicker.show()
        }
        Log.d("FromDate", from_date)
        Log.d("ToDate", to_date)
//        val numberOfDays = calculateDaysBetweenDates(from_date, to_date).toDouble()

//        val currentDate = LocalDate.now()
//        val year = currentDate.year
//        val month = currentDate.monthValue
//        val day = currentDate.dayOfMonth
//        val validDays = calculateDaysBetweenDates(currentDate.toString(), toDate)
//        Log.d("No of days", numberOfDays.toString())
//        no_of_date = numberOfDays
//        Log.d("No of days", no_of_date.toString())

        var isAllow: Boolean = true
        // inside on click button
        view.findViewById<Button>(R.id.applyleavebtn).setOnClickListener {

            Log.d("ft dates","$from_date $to_date")
            val numberOfDays = calculateDaysBetweenDates(from_date, to_date).toDouble()
            no_of_date = numberOfDays
            Log.d("No of days", no_of_date.toString())

            val currentDate = LocalDate.now()
            Log.d("currentDate",currentDate.toString())
            val validDays = calculateDaysBetweenDates(currentDate.toString(), to_date)
            Log.d("valid",validDays.toString())

            if (no_of_date <= 0) {
                Toast.makeText(requireContext(), "Please Enter Valid Dates", Toast.LENGTH_SHORT)
                    .show()
                isAllow = false
            }

            if (validDays >= -2 && validDays <= 0) {
                if (leave_id != 4) {
                    Toast.makeText(
                        requireContext(),
                        "Only Medical leaves can be applied after consumption of leave",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    isAllow = true
                }
            }
            if (validDays > 0) {
                isAllow = true
            }

            //          err: "Only Medical leaves can be applied after consumption of leave",
            if (isAllow) {
                reason = view.findViewById<EditText>(R.id.etReason).text.toString()
                Log.d(
                    "POST BODY",
                    "$leave_id $half_full_day $from_date $to_date $reason $no_of_date $alternate"
                )
                viewModel.postFacultyLeaveApplication(
                    leaveId = leave_id,
                    halfFullDay = half_full_day,
                    fromDate = from_date,
                    toDate = to_date,
                    reason = reason,
                    noOfDays = no_of_date,
                    alternate = alternate,
                    uid = 2139,
                    doc = ""
                )
                viewModel.FacultyLeaveApplicationResponse.observe(viewLifecycleOwner) {
                    if (it.isSuccessful) {
                        Log.d("Message", it.body()?.message.toString())
                        findNavController().navigate(R.id.action_facultyApplyLeaveFragment_to_facultyDashboardFragment3)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Internal Server Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Something Went Wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    fun calculateDaysBetweenDates(startDate: String, endDate: String): Long {
        // Define a date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        try {
            // Parse the provided date strings into Date objects
            val startDateObj: java.util.Date = dateFormat.parse(startDate)
            val endDateObj: java.util.Date = dateFormat.parse(endDate)

            // Calculate the difference in milliseconds
            val diffInMillis: Long = endDateObj.time - startDateObj.time


            // Convert milliseconds to days
            return (TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)) + 1
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0 // Return 0 in case of an error
    }
}

//        viewModel.FacultyData.observe(viewLifecycleOwner){Faculties->
//            Log.d("Faculty List", Faculties.toString())
//        }
//        viewModel.LeaveData.observe(viewLifecycleOwner){Leaves->
//            Log.d("Leave List", Leaves.toString())
//        }