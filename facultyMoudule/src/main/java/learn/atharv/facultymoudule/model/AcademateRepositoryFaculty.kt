package learn.atharv.facultymoudule.model

import learn.atharv.facultymoudule.model.api.AcademateWebServiceFaculty
import learn.atharv.facultymoudule.model.response.FacultyBasicMesssageResponse
import learn.atharv.facultymoudule.model.response.FacultyCancelledLeavesResponse
import learn.atharv.facultymoudule.model.response.FacultyDashboardResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse
import learn.atharv.facultymoudule.model.response.FacultyPunchRecordResponse
import retrofit2.Response
import java.sql.Date

class AcademateRepositoryFaculty(private val webService: AcademateWebServiceFaculty = AcademateWebServiceFaculty()) {
    suspend fun getFacultyLeaveData(uid: String): Response<FacultyLeaveDataResponse> {
        return webService.api.getFacultyLeaveData(uid)
    }

    //    suspend fun getFacultyPreviousLeaves(faculty_clg_id: String): Response<FacultyLeaveDataResponse> {
//        return webService.api.getFacultyLeaveData(faculty_clg_id)
//    }
    suspend fun getFacultyPunchRecord(uid: String): Response<FacultyPunchRecordResponse> {
        return webService.api.getFacultyPunchRecord(uid)
    }

    suspend fun getFacultyCancelledLeaves(uid: String): Response<FacultyCancelledLeavesResponse> {
        return webService.api.getFacultyCancelledLeaves(uid)
    }

    suspend fun getFacultyLeaveHistory(uid: String): Response<FacultyLeaveHistoryResponse> {
        return webService.api.getFacultyLeaveHistory(uid)
    }

    suspend fun getFacultyDashboard(uid: String): Response<FacultyDashboardResponse> {
        return webService.api.getFacultyDashboard(uid)
    }


    // POST Requests ---->

    suspend fun postFacultyLeaveApplication(
        leaveId: Int,
        halfFullDay: String,
        fromDate: Date,
        toDate: Date,
        reason: String,
        noOfDays: Int,
        alternate: Int,
        uid: Int,
        doc: String,
    ): Response<FacultyBasicMesssageResponse> {
        return webService.api.postFacultyLeaveApplication(
            leaveId,
            halfFullDay,
            fromDate,
            toDate,
            reason,
            noOfDays,
            alternate,
            uid,
            doc
        )
    }
}