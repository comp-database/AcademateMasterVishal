package learn.atharv.studentmoudule.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
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

    private lateinit var binding: FragmentStudentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentProfileBinding.inflate(layoutInflater, container, false)
        val viewModelFactory = ViewmodelFactoryStudent(requireContext())
        viewModel =
            ViewModelProvider(this, viewModelFactory)[StudentProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.ibLogout.setOnClickListener {
//            requireActivity().finish()
//            startActivity(Intent(requireActivity(), ))
//        }

        if (isNetworkAvailable()) {
            viewModel.homeScreenFunc()
            viewModel.resultLiveData.observe(viewLifecycleOwner) {
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
                binding.tvUserDob.text = it.dob.subSequence(0, 10)

                Log.d(
                    "All Data",
                    "${it.name} , ${it.dob}  ,  ${it.year} , ${it.ImageUrl} , ${it.branch} , ${it.caste}, ${it.id_no} , ${it.gr_no} "
                )
                binding.pbLoading.visibility = View.GONE
            }
        } else {
            Toast.makeText(requireActivity(), "No internet connection", Toast.LENGTH_SHORT).show()
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