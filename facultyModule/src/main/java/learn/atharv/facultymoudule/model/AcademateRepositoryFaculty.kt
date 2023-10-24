package learn.atharv.facultymoudule.model

import learn.atharv.facultymoudule.model.api.AcademateWebServiceFaculty
import learn.atharv.facultymoudule.model.body.FacultyLoginData
import learn.atharv.facultymoudule.model.response.FacultyBasicMesssageResponse
import learn.atharv.facultymoudule.model.response.FacultyCancelledLeavesResponse
import learn.atharv.facultymoudule.model.response.FacultyDashboardResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import learn.atharv.facultymoudule.model.response.FacultyLeaveHistoryResponse
import learn.atharv.facultymoudule.model.response.FacultyLoginResponse
import learn.atharv.facultymoudule.model.response.FacultyPunchRecordResponse
import retrofit2.Response

class AcademateRepositoryFaculty(private val webService: AcademateWebServiceFaculty = AcademateWebServiceFaculty()) {

    suspend fun getBasicData(uid: String): String {
        val response = webService.api.getBasicData(uid)
        if (response.isSuccessful) {
            return response.body()?.user?.name.toString()
        } else {
            return response.errorBody().toString()
        }
    }

    suspend fun getFacultyDashboardLeave(uid: String): FacultyDashboardResponse.Leave? {
        val response = webService.api.getFacultyDashboard(uid)
        if (response.isSuccessful) {
            val leaveList = response.body()?.leaveList
            if (!leaveList.isNullOrEmpty()) {
                return leaveList[0] // Return the first leave item
            }
        }
        return FacultyDashboardResponse.Leave(casualLeave = 0.2f, compensationLeave = 0, earnedLeave = 0, medicalLeave = 0, summerVacation = 0, winterVacation = 0)
    }

    suspend fun getFacultyDashboardAlternate(uid: String): List<FacultyDashboardResponse.Alternate> {
        val response = webService.api.getFacultyDashboard(uid)
        if (response.isSuccessful) {
            val alternateList = response.body()?.alternate
            if (!alternateList.isNullOrEmpty()) {
                return alternateList // Return the first leave item
            }
        }
        return listOf<FacultyDashboardResponse.Alternate>(FacultyDashboardResponse.Alternate(alternate = 0, appliedDate = "0", docLink = "0", facultyId = 0, fromDate = "0", halfFullDay = "0", leaveAppId = 0, leaveId = 0, lname = "0",name="0", noOfDays = 0, reason = "0", signedByHod = 0, signedByPrincipal = 0, status = 0, statusAlternate = 0, toDate = "0"))
    }

    suspend fun getFacultyLeaveData(uid: String): Response<FacultyLeaveDataResponse> {
        return webService.api.getFacultyLeaveData(uid)
    }
//    suspend fun getFacultyPersonalDataTokenised(token: String): Response<basicResponse> {
//        return webService.api.getFacultyPersonalDataTokenised(token)
//    }

    //    suspend fun getFacultyPreviousLeaves(faculty_clg_id: String): Response<FacultyLeaveDataResponse> {
//        return webService.api.getFacultyLeaveData(faculty_clg_id)
//    }
    suspend fun getFacultyPunchRecord(uid: String): Response<FacultyPunchRecordResponse> {
        return webService.api.getFacultyPunchRecord(uid)
    }

    suspend fun getFacultyCancelledLeaves(uid: String): Response<FacultyCancelledLeavesResponse> {
        return webService.api.getFacultyCancelledLeaves(uid)
    }

    suspend fun getFacultyLeaveHistory(uid: String): List<FacultyLeaveHistoryResponse.Leave>? {
        val response = webService.api.getFacultyLeaveHistory(uid)
        if (response.isSuccessful) {
            val LeaveHistoryList = response.body()?.leave
            if (!LeaveHistoryList.isNullOrEmpty()) {
                return LeaveHistoryList // Return the first leave item
            }
        }
        return null
    }


    // POST Requests ---->

    suspend fun postFacultyLeaveApplication(
        leaveId: Int,
        halfFullDay: String,
        fromDate: String,
        toDate: String,
        reason: String,
        noOfDays: Double,
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

    suspend fun postTakeCharge(
        leave_app_id: Int,
        status: Int
    ): Response<FacultyBasicMesssageResponse> {
        return webService.api.postFacultyTakeCharge(leave_app_id, status)
    }

    suspend fun HandleLogin(data: FacultyLoginData): Response<FacultyLoginResponse> {
        return webService.api.HandleLogin(data)
    }


}