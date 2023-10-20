package learn.atharv.hrmoudule.model.body

data class UpdateLeaveStatusBody(
    val app_id: Int,
    val status: Int,
    val leave_id: Int,
    val faculty_id : Int,
    val no_of_days : Int ,
    val role : String ,
    val uid : String
)
