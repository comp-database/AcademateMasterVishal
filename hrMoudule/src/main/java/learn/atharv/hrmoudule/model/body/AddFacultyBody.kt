package learn.atharv.hrmoudule.model.body

data class AddFacultyBody(
    val faculty_clg_id: String,
    val name: String,
    val contact: String,
    val ftype_id: String,
    val depart_id: String,
    val role: String,
    val joining_date: String,
    val email: String,
    val password: String,
    val shift_id: String,
)
