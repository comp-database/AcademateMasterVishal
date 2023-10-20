package learn.atharv.hodmoudule.model

import android.content.Context
import learn.atharv.hodmoudule.model.api.AcademateWebServiceHod
import learn.atharv.hodmoudule.model.body.HodLoginBody
import learn.atharv.hodmoudule.model.response.HodFacultyApprovedLeavesResponse
import learn.atharv.hodmoudule.model.response.HodLoginResponse
import learn.atharv.hodmoudule.model.response.HodMyFacultiesResponse
import learn.atharv.hodmoudule.model.response.HodMyStudentsResponse
import retrofit2.Response

class AcademateRepositoryHod(context: Context) {
    val webServiceHod = AcademateWebServiceHod(context)

    suspend fun HandleLogin(data : HodLoginBody): Response<HodLoginResponse> {
        return webServiceHod.api.postHodTokenLogin(data)
    }

    suspend fun getFacultyList() : List<HodMyFacultiesResponse.Faculty>?{
        val response = webServiceHod.api.getFacultyList()
        if (response.isSuccessful){
            return response.body()!!.facultyList
        }else{
            return null
        }
    }

    suspend fun getStudentList() : List<HodMyStudentsResponse.Student>?{
        val response = webServiceHod.api.getStudentList()
        if (response.isSuccessful){
            return response.body()!!.studentList
        }else{
            return null
        }
    }

    suspend fun getApprovedLeavesList() : List<HodFacultyApprovedLeavesResponse.ApprovedLeave>?{
        val response = webServiceHod.api.getFacultyApprovedList()
        if (response.isSuccessful){
            return response.body()!!.approvedLeaves
        }else{
            return null
        }
    }

    suspend fun presentFacultyCount() : Int? {
        val response = webServiceHod.api.FacultyPresentCount()
        if (response.isSuccessful){
            return response.body()?.presentCount
        }else{
            return  0
        }
    }
}