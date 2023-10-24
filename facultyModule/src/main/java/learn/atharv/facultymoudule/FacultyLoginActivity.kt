package learn.atharv.facultymoudule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.personal.common.SharedPreferencesManager
import learn.atharv.facultymoudule.databinding.ActivityFacultyLoginBinding
import learn.atharv.facultymoudule.model.body.FacultyLoginData

class FacultyLoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFacultyLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFacultyLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferencesManager = SharedPreferencesManager(this)
        var uid : String = ""
        var isLogin : Boolean = false
        val viewModel = FacultyLoginViewModel()
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.postFacultyTokenLogin(FacultyLoginData(email,password))
            Log.d("email", email)
            Log.d("password", password)
            viewModel.FacultytokenLoginResponse.observe(this){
                if(it.isSuccessful){
                    uid = it.body()?.uid.toString()
                    isLogin = it.body()!!.isLogin
                    sharedPreferencesManager.saveString("uid", uid)
                    sharedPreferencesManager.saveBoolean("isLogin",isLogin)
                    sharedPreferencesManager.saveInt("userType",it.body()!!.userType)
//                    token = it.body()?.token.toString()
//                    Log.d("token", token)
                    val intent  = Intent(this , FacultyMainActivity::class.java)
                    intent.putExtra("uid", uid)
                    intent.putExtra("isLogin", isLogin.toString())
                    startActivity(intent)
                } else{
                    Log.d("Error",it.errorBody().toString())
                    Toast.makeText(this, "Some thing Went Wrong ${it.errorBody().toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}