package learn.atharv.facultymoudule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import learn.atharv.facultymoudule.model.body.FacultyLoginData

class FacultyLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_login)

        var uid : String = ""
        var isLogin : Boolean = false
        val viewModel = FacultyLoginViewModel()
        findViewById<Button>(R.id.login).setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            viewModel.postFacultyTokenLogin(FacultyLoginData(email,password))
            Log.d("email", email)
            Log.d("password", password)
            viewModel.FacultytokenLoginResponse.observe(this){
                if(it.isSuccessful){
                    uid = it.body()?.uid.toString()
                    isLogin = it.body()!!.isLogin
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