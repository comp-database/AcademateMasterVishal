package learn.atharv.facultymoudule.model

import learn.atharv.facultymoudule.model.api.AcademateWebServiceFaculty
import learn.atharv.facultymoudule.model.response.FacultyLeaveDataResponse
import retrofit2.Response

class AcademateRepositoryFaculty(private val webService : AcademateWebServiceFaculty = AcademateWebServiceFaculty()) {
    suspend fun getFacultyLeaveData(uid: String): Response<FacultyLeaveDataResponse> {
        return webService.api.getFacultyLeaveData(uid)
    }
}