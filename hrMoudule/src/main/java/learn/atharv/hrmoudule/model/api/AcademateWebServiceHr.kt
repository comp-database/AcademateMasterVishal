package learn.atharv.hrmoudule.model.api

import learn.atharv.facultymoudule.basicResponse
import learn.atharv.hrmoudule.model.body.AddFacultyBody
import learn.atharv.hrmoudule.model.body.UpdateLeaveStatusBody
import learn.atharv.hrmoudule.model.response.HrAddFacultyResResponse
import learn.atharv.hrmoudule.model.response.HrDashboardResponse
import learn.atharv.hrmoudule.model.response.HrLeaveApprovalResponse
import learn.atharv.hrmoudule.model.response.HrPendingListResponse
import learn.atharv.hrmoudule.model.response.HrShiftAddFacultyResResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class AcademateWebServiceHr {
    var api: AcademateApi

    init {
        val retrofit =
            Retrofit.Builder()
//                .baseUrl("https://student.vppcoe.getflytechnologies.com/auth/")
                .baseUrl("https://vppcoe-va.getflytechnologies.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(AcademateApi::class.java)
    }

    interface AcademateApi {
        @GET("api/hr/dashboard")
        suspend fun getHrDashboard(): Response<HrDashboardResponse>
        //------------------------------------------------------------------------------------

        @GET("api/hr/leaveApproval")
        suspend fun getHrPendingList(): Response<HrPendingListResponse>

        @POST("api/hr/update_leave_status")
        suspend fun postHrLeaveStatus(@Body data: UpdateLeaveStatusBody): Response<HrLeaveApprovalResponse>
        //------------------------------------------------------------------------------------

        @GET("api/hr/add_faculty_get")
        suspend fun getHrAddFacultyResList1(): Response<HrAddFacultyResResponse>

        @GET("api/hr/fetch_all_Shifts")
        suspend fun getHrAddFacultyResList2(): Response<HrShiftAddFacultyResResponse>

        @POST("api/hr/Add_Faculty")
        suspend fun postAddFaculty(@Body data: AddFacultyBody): Response<basicResponse>
        //------------------------------------------------------------------------------------
    }

}