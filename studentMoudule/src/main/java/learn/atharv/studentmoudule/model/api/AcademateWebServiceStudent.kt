package learn.atharv.studentmoudule.model.api

import android.content.Context
import learn.atharv.studentmoudule.model.body.StudentInitiatePaymentBody
import learn.atharv.studentmoudule.model.body.StudentLoginBody
import learn.atharv.studentmoudule.model.response.StudentLoginResponse
import learn.atharv.studentmoudule.model.response.amount.StudentBalanaceAmountStatusResponse
import learn.atharv.studentmoudule.model.response.amount.StudentTotalAmountStatusResponse
import learn.atharv.studentmoudule.model.response.basicDetails.StudentCurrentEducationalDetailsResponse
import learn.atharv.studentmoudule.model.response.basicDetails.StudentDocLinkResponse
import learn.atharv.studentmoudule.model.response.basicDetails.StudentPersonalDetailsResponse
import learn.atharv.studentmoudule.model.response.payment.StudentInitiatePaymentResponse
import learn.atharv.studentmoudule.model.response.payment.StudentSingleFeeDataResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class AcademateWebServiceStudent(context: Context) {

    //        val sharedPrefs: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val token: String? = sharedPrefs.getString("token", "")
    val client: OkHttpClient by lazy { // it is used or created only when it is called (lazy)
        OkHttpClient.Builder()
            .addInterceptor {

                val request = it.request().newBuilder()
                    .apply {
//                            if (token != null) {
                        addHeader(
                            "Authorization",
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InZ1MWYyMTIyMDU0QHB2cHBjb2UuYWMuaW4iLCJpYXQiOjE2OTc3MjE3NDksImV4cCI6MTcwMDMxMzc0OX0.6uXOh0-FKXGz1sTVmaTxUmJUnNUN2rEwhZkzHXooaQ8"
                        )
//                            }
                    }
                    .build()
                it.proceed(request)
            }
            .build()
    }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://student.vppcoe.getflytechnologies.com/auth/")
            .build()

    }
    val api: AcademateApi by lazy {
        retrofit.create(AcademateApi::class.java)
    }


    interface AcademateApi {
        @POST("login")
        suspend fun postStudentTokenLogin(@Body data: StudentLoginBody): Response<StudentLoginResponse>

        //--------------------------------------------------------------------------------------------------------
        // Home
        @GET("personalDetails")
        suspend fun getStudentPersonalDetails(): Response<StudentPersonalDetailsResponse>

        @GET("currentEducation_per")
        suspend fun getStudentCurrentEducationalDetails(): Response<StudentCurrentEducationalDetailsResponse>

        @GET("upload")
        suspend fun getStudentDocLinkDetails(): Response<StudentDocLinkResponse>

        //--------------------------------------------------------------------------------------------------------
        // Balance Fee AND Total Fee
        @GET("feeStructureStud")
        suspend fun getStudentTotalAmountStatus(): Response<StudentTotalAmountStatusResponse>

        @GET("FetchBalanceFee_uid")
        suspend fun getStudentBalanceAmountStatus(): Response<StudentBalanaceAmountStatusResponse>

        //--------------------------------------------------------------------------------------------------------
        // Payment
        @GET("single_fee_amounts")
        suspend fun getStudentSingleFeeData(): Response<StudentSingleFeeDataResponse>

        @POST("initiate_payment")
        suspend fun postStudentInitiatePayment(@Body body: StudentInitiatePaymentBody): Response<StudentInitiatePaymentResponse>
    }
}