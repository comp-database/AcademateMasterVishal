package learn.atharv.facultymoudule.model.api

import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class AcademateWebServiceFaculty {
    var api: AcademateApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vppcoe-va.getflytechnologies.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AcademateApi::class.java)
    }
    interface AcademateApi {
        @GET("api/faculty/get_allowed_leaves")
        suspend fun getFacultyLeaveData(
            @Query("uid")
            uid : String?
        ): Response<FacultyLeaveDataResponse>
    }

}