package learn.atharv.hodmoudule.model.response


import com.google.gson.annotations.SerializedName

data class HodMyStudentsResponse(
    @SerializedName("StudentList")
    val studentList: List<Student>
) {
    data class Student(
        @SerializedName("aadhar_card")
        val aadharCard: String,
        @SerializedName("aadhar_number")
        val aadharNumber: String,
        @SerializedName("academic_year")
        val academicYear: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("antiragging_form")
        val antiraggingForm: String?,
        @SerializedName("back_passbook")
        val backPassbook: String,
        @SerializedName("bank_account_number")
        val bankAccountNumber: String,
        @SerializedName("bank_name")
        val bankName: String,
        @SerializedName("bio_marks")
        val bioMarks: String?,
        @SerializedName("bname")
        val bname: String,
        @SerializedName("branch_id")
        val branchId: Int,
        @SerializedName("cancelled_app")
        val cancelledApp: Int,
        @SerializedName("cancelled_app_date")
        val cancelledAppDate: String?,
        @SerializedName("cancelled_app_document")
        val cancelledAppDocument: String?,
        @SerializedName("cap_allotment_letter")
        val capAllotmentLetter: String,
        @SerializedName("caste")
        val caste: String,
        @SerializedName("caste_certificate")
        val casteCertificate: String,
        @SerializedName("caste_validation")
        val casteValidation: String,
        @SerializedName("cat_id")
        val catId: Int?,
        @SerializedName("chemistry_score_in_hsc")
        val chemistryScoreInHsc: String?,
        @SerializedName("child_number")
        val childNumber: String,
        @SerializedName("college_admission_letter")
        val collegeAdmissionLetter: String,
        @SerializedName("communitee")
        val communitee: String,
        @SerializedName("contact")
        val contact: String,
        @SerializedName("dd_id")
        val ddId: Int?,
        @SerializedName("defence_status")
        val defenceStatus: Any?,
        @SerializedName("dep_cgpi")
        val depCgpi: String?,
        @SerializedName("dep_clg_name")
        val depClgName: String?,
        @SerializedName("dep_marks")
        val depMarks: String?,
        @SerializedName("dep_passing_year")
        val depPassingYear: String?,
        @SerializedName("dep_per")
        val depPer: String?,
        @SerializedName("dep_seat")
        val depSeat: String?,
        @SerializedName("details_of_prize")
        val detailsOfPrize: Any?,
        @SerializedName("dip_board")
        val dipBoard: String?,
        @SerializedName("dob")
        val dob: String,
        @SerializedName("doc_id")
        val docId: Int,
        @SerializedName("doc_ids")
        val docIds: Int,
        @SerializedName("domicile")
        val domicile: String,
        @SerializedName("domicile_number")
        val domicileNumber: Any?,
        @SerializedName("edu_id")
        val eduId: Int,
        @SerializedName("email")
        val email: Any?,
        @SerializedName("ews_pro")
        val ewsPro: String,
        @SerializedName("father_id")
        val fatherId: Int,
        @SerializedName("fc_center_varification")
        val fcCenterVarification: String,
        @SerializedName("fee_reciept")
        val feeReciept: String,
        @SerializedName("final_submit")
        val finalSubmit: Int,
        @SerializedName("gap_cert")
        val gapCert: String?,
        @SerializedName("gender_id")
        val genderId: Int,
        @SerializedName("gr_number")
        val grNumber: String,
        @SerializedName("guardian_id")
        val guardianId: Int,
        @SerializedName("guardian_relation")
        val guardianRelation: Any?,
        @SerializedName("HDegree")
        val hDegree: String?,
        @SerializedName("hsc_marks")
        val hscMarks: String?,
        @SerializedName("hsc_marksheet")
        val hscMarksheet: String,
        @SerializedName("hsc_name_of_board")
        val hscNameOfBoard: String?,
        @SerializedName("hsc_passing_year")
        val hscPassingYear: String?,
        @SerializedName("hsc_percentage")
        val hscPercentage: String?,
        @SerializedName("hsc_seat_year")
        val hscSeatYear: String?,
        @SerializedName("income_certificate")
        val incomeCertificate: String,
        @SerializedName("is_parent_have_domicile")
        val isParentHaveDomicile: Any?,
        @SerializedName("jee_score_card")
        val jeeScoreCard: String,
        @SerializedName("landline_number")
        val landlineNumber: Any?,
        @SerializedName("last_college_attended")
        val lastCollegeAttended: String,
        @SerializedName("lc")
        val lc: String,
        @SerializedName("married_status")
        val marriedStatus: String,
        @SerializedName("maths_score_hsc")
        val mathsScoreHsc: String?,
        @SerializedName("mht_cet_score_card")
        val mhtCetScoreCard: String,
        @SerializedName("minority")
        val minority: String,
        @SerializedName("mother_id")
        val motherId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("nationality")
        val nationality: String,
        @SerializedName("neft_id")
        val neftId: Int?,
        @SerializedName("nonCreamy")
        val nonCreamy: String,
        @SerializedName("padd_id")
        val paddId: Int,
        @SerializedName("parentSignature")
        val parentSignature: String,
        @SerializedName("part_payment")
        val partPayment: Int?,
        @SerializedName("personal_details_id")
        val personalDetailsId: Int,
        @SerializedName("personal_id")
        val personalId: Int,
        @SerializedName("photo")
        val photo: String,
        @SerializedName("physically_handicap")
        val physicallyHandicap: Any?,
        @SerializedName("physics_score_in_hsc")
        val physicsScoreInHsc: String?,
        @SerializedName("place_of_birth")
        val placeOfBirth: String,
        @SerializedName("programm_id")
        val programmId: Int,
        @SerializedName("programm_name")
        val programmName: String,
        @SerializedName("radd_id")
        val raddId: Int,
        @SerializedName("ration_card")
        val rationCard: String,
        @SerializedName("received_scholership")
        val receivedScholership: Any?,
        @SerializedName("religion")
        val religion: String,
        @SerializedName("sadd_id")
        val saddId: Int,
        @SerializedName("seat_type_id")
        val seatTypeId: Int,
        @SerializedName("sid")
        val sid: Int,
        @SerializedName("signature")
        val signature: String,
        @SerializedName("speacial_talent")
        val speacialTalent: Any?,
        @SerializedName("ssc_marks")
        val sscMarks: String,
        @SerializedName("ssc_marksheet")
        val sscMarksheet: String,
        @SerializedName("ssc_name_of_board")
        val sscNameOfBoard: String,
        @SerializedName("ssc_passing_year")
        val sscPassingYear: String,
        @SerializedName("ssc_percentage")
        val sscPercentage: String,
        @SerializedName("ssc_seat_number")
        val sscSeatNumber: String,
        @SerializedName("stud_clg_id")
        val studClgId: String,
        @SerializedName("stud_id")
        val studId: Int,
        @SerializedName("sub_caste")
        val subCaste: String,
        @SerializedName("uid")
        val uid: Int,
        @SerializedName("vocational_subject")
        val vocationalSubject: String?,
        @SerializedName("vocational_total_score_hsc")
        val vocationalTotalScoreHsc: String?
    )
}