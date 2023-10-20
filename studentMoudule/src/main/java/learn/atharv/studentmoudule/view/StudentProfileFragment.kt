package learn.atharv.studentmoudule.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import learn.atharv.facultymoudule.viewmodel.ViewmodelFactoryStudent
import learn.atharv.studentmoudule.R
import learn.atharv.studentmoudule.databinding.FragmentStudentProfileBinding
import learn.atharv.studentmoudule.viewmodel.StudentProfileViewModel

class StudentProfileFragment : Fragment() {

    companion object {
        fun newInstance() = StudentProfileFragment()
    }

    private lateinit var viewModel: StudentProfileViewModel

    private lateinit var binding : FragmentStudentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val view = inflater.inflate(R.layout.fragment_student_profile, container, false)
        binding = FragmentStudentProfileBinding.inflate(layoutInflater, container, false)
        val viewModelFactory = ViewmodelFactoryStudent(requireContext())
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StudentProfileViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.tbToolbar)
        val toggle = ActionBarDrawerToggle(
            requireActivity(), binding.drawer, binding.tbToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_open_24)
        binding.nvSideNavigationView.bringToFront()


        binding.nvSideNavigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_menu_home -> {
                   findNavController().navigate(R.id.action_studentProfileFragment_to_studentPaymentFragment)
                }

//                R.id.navigation_menu_logout -> {
//                    auth.signOut()
//                    val intent = Intent(this, RegistrationActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    finish()
//                    startActivity(intent)
//                }
//
//                R.id.navigation_menu_update_profile -> {
//                    startActivity(Intent(this, UpdateProfileActivity::class.java))
//                }
//
//                R.id.navigation_menu_view_order_history -> {
//                    startActivity(Intent(this, OrderHistory::class.java))
//                }
            }

            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }

        viewModel.homeScreenFunc()
        viewModel.resultLiveData.observe(viewLifecycleOwner){
            Glide.with(this)
                .load(it.ImageUrl)
                .error(R.drawable.background)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerInside()
                .circleCrop()
                .into(binding.ivProfileImage)

            binding.tvUserName.text = it.name
            binding.tvUserClgId.text = it.id_no
            binding.tvUserBranch.text = it.branch
            binding.tvUserCaste.text = it.caste
            binding.tvUserCurrentAcademicYear.text = it.year
            binding.tvUserGrNumber.text = it.gr_no
            binding.tvUserDob.text = it.dob.subSequence(0,10)

            Log.d("All Data", "${it.name} , ${it.dob}  ,  ${it.year} , ${it.ImageUrl} , ${it.branch} , ${it.caste}, ${it.id_no} , ${it.gr_no} ")
        }
    }
}