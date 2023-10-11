package learn.atharv.facultymoudule.model.api

import learn.atharv.facultymoudule.model.response.FacultyBasicMesssageResponse
import learn.atharv.facultymoudule.model.response.FacultyCancelledLeavesResponse
import learn.atharv.facultymoudule.model.response.FacultyDashboardResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse
import learn.atharv.facultymoudule.model.response.FacultyPunchRecordResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.sql.Date

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
    // GET REQUEST

        // get the previous data regarding the leave association
        @GET("api/faculty/get_allowed_leaves")
        suspend fun getFacultyLeaveData(
            @Query("uid")
            uid: String?
        ): Response<FacultyLeaveDataResponse>

        // Previous faculty Leave data
//        @GET("/api/faculty/previous_leave")
//        suspend fun getFacultyPreviousLeaves(
//            @Query("faculty_clg_id")
//            faculty_clg_id: String?
//        ): Response<FacultyLeaveDataResponse>

        // To Get the Punch record of the faculty
        @GET("/api/faculty/fetchPunchRecord")
        suspend fun getFacultyPunchRecord(
            @Query("uid")
            uid: String?
        ): Response<FacultyPunchRecordResponse>

        // To get the Cancelled leaves --> Leaves are updated when HR Updates the Leaves
        @GET("/api/faculty/faculty_cancelled_leave")
        suspend fun getFacultyCancelledLeaves(
            @Query("uid")
            uid: String?
        ): Response<FacultyCancelledLeavesResponse>

        // To get leave history
        @GET("/api/faculty/leave_hisotry")
        suspend fun getFacultyLeaveHistory(
            @Query("uid")
            uid: String?
        ): Response<FacultyLeaveHistoryResponse>


        // To get the DashBoard
        @GET("/api/faculty/dashboard")
        suspend fun getFacultyDashboard(
            @Query("uid")
            uid: String?
        ): Response<FacultyDashboardResponse>
// -------------------------------------------------------------------------------------------------------------
    // POST REQUESTS
        // apply for leave application post
        @FormUrlEncoded
        @POST("api/faculty/Apply_leave")
        suspend fun postFacultyLeaveApplication(
            @Field("leave_id") leaveId: Int,
            @Field("half_full_day") halfFullDay: String,
            @Field("from_date") fromDate: Date,
            @Field("to_date") toDate: Date,
            @Field("reason") reason: String,
            @Field("no_of_date") noOfDays: Int,
            @Field("alternate") alternate: Int,
            @Field("uid") uid: Int,
            @Field("doc") doc: String,
        ): Response<FacultyBasicMesssageResponse> //message -->  Success

        // taking charge of alternate
        @FormUrlEncoded
        @POST("/api/faculty/takeCharge")
        suspend fun postFacultyTakeCharge(
            @Field("app_id") leave_app_id: Int,
            @Field("status") status: Int // status 1 => for charge taken
        ): Response<FacultyBasicMesssageResponse> //message -->  Successfully Updated

    }


}