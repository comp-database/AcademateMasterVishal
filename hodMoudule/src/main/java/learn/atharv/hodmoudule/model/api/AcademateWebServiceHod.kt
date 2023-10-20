package learn.atharv.hodmoudule.model.api

import android.content.Context
import learn.atharv.hodmoudule.model.body.HodLoginBody
import learn.atharv.hodmoudule.model.response.HodFacultyApprovedLeavesResponse
import learn.atharv.hodmoudule.model.response.HodLoginResponse
import learn.atharv.hodmoudule.model.response.HodMyFacultiesResponse
import learn.atharv.hodmoudule.model.response.HodMyStudentsResponse
import learn.atharv.hodmoudule.model.response.HodPresentFacultyCountResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class AcademateWebServiceHod(context: Context) {

    val client: OkHttpClient by lazy { // it is used or created only when it is called (lazy)
        OkHttpClient.Builder()
            .addInterceptor {

                val request = it.request().newBuilder()
                    .apply {
//                            if (token != null) {
                        addHeader(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImhvZGNvbXBzQHB2cHBjb2UuYWMuaW4iLCJpYXQiOjE2OTc3NTgwMDksImV4cCI6MTcwMDM1MDAwOX0.Uey02W_dl-YdUrPFwADdBxPRH7aEnMr8r1Qz-pHe-hk"
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
            .baseUrl("https://hod.vppcoe.getflytechnologies.com/auth/")
            .build()

    }
    val api: AcademateApi by lazy {
        retrofit.create(AcademateApi::class.java)
    }
    interface AcademateApi{
        @POST("login")
        suspend fun postHodTokenLogin(@Body data: HodLoginBody): Response<HodLoginResponse>

        @GET("getMyFaculties ")
        suspend fun getFacultyList() : Response<HodMyFacultiesResponse>

        @GET("myStudents")
        suspend fun getStudentList() : Response<HodMyStudentsResponse>

        @GET("getApprovedLeaves")
        suspend fun getFacultyApprovedList() : Response<HodFacultyApprovedLeavesResponse>

        @GET("presentFaculty")
        suspend fun FacultyPresentCount() : Response<HodPresentFacultyCountResponse>

    }
}