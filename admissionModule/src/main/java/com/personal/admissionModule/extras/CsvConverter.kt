package com.getfly.technologies.extras

import com.getfly.technologies.model.response.DownloadApplicationsResponse
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.Writer

class CsvConverter {

    fun convertToCsv(
        data: List<DownloadApplicationsResponse.Application>,
        outputStream: OutputStream
    ) {
        // Create a CSV writer using the provided OutputStream
        val writer: Writer = OutputStreamWriter(outputStream)

        // Write the CSV header
        writer.write(
            "aadhar_card,aadhar_number," +
                    "academic_year,address," +
                    "antiragging_form," +
                    "back_passbook," +
                    "bank_account_number," +
                    "bank_name,bio_marks," +
                    "bname,branch_id," +
                    "cancelled_app," +
                    "cap_allotment_letter," +
                    "caste,caste_certificate," +
                    "caste_validation," +
                    "cat_id," +
                    "chemistry_score_in_hsc," +
                    "child_number," +
                    "college_admission_letter," +
                    "communitee,contact," +
                    "dd_id," +
                    "defence_status," +
                    "dep_cgpi," +
                    "dep_clg_name," +
                    "dep_marks," +
                    "dep_passing_year," +
                    "dep_per," +
                    "dep_seat," +
                    "details_of_prize," +
                    "dip_board," +
                    "dob," +
                    "doc_id," +
                    "doc_ids," +
                    "domicile," +
                    "domicile_number," +
                    "edu_id," +
                    "email," +
                    "ews_pro," +
                    "father_id," +
                    "fc_center_varification," +
                    "fee_reciept," +
                    "final_submit," +
                    "gap_cert," +
                    "gender_id," +
                    "gr_number," +
                    "guardian_id," +
                    "guardian_relation," +
                    "HDegree," +
                    "hsc_marks," +
                    "hsc_marksheet," +
                    "hsc_name_of_board," +
                    "hsc_passing_year," +
                    "hsc_percentage," +
                    "hsc_seat_year," +
                    "income_certificate," +
                    "is_parent_have_domicile," +
                    "jee_score_card," +
                    "landline_number," +
                    "last_college_attended," +
                    "lc," +
                    "married_status," +
                    "maths_score_hsc," +
                    "mht_cet_score_card," +
                    "minority," +
                    "mother_id," +
                    "name," +
                    "nationality," +
                    "neft_id," +
                    "nonCreamy," +
                    "padd_id," +
                    "parentSignature," +
                    "part_payment," +
                    "personal_details_id," +
                    "personal_id," +
                    "photo," +
                    "physically_handicap," +
                    "physics_score_in_hsc," +
                    "place_of_birth," +
                    "programm_id," +
                    "programm_name," +
                    "radd_id," +
                    "ration_card," +
                    "received_scholership," +
                    "religion," +
                    "sadd_id," +
                    "seat_type_id," +
                    "sid," +
                    "signature," +
                    "speacial_talent," +
                    "ssc_marks," +
                    "ssc_marksheet," +
                    "ssc_name_of_board," +
                    "ssc_passing_year," +
                    "ssc_percentage," +
                    "ssc_seat_number," +
                    "stud_clg_id," +
                    "stud_id," +
                    "sub_caste," +
                    "uid," +
                    "vocational_subject," +
                    "vocational_total_score_hsc\n"
        )


        // Write the CSV data rows
        data.forEach { application ->
            writer.write(
                "\"${application.aadharCard}\",\"${application.aadharNumber}\",\"${application.academicYear}\",\"${application.address}\",\"${application.antiraggingForm}\",\"${application.backPassbook}\",\"${application.bankAccountNumber}\",\"${application.bankName}\",\"${application.bioMarks}\",\"${application.bname}\",${application.branchId},${application.cancelledApp},\"${application.capAllotmentLetter}\",\"${application.caste}\",\"${application.casteCertificate}\",\"${application.casteValidation}\",${application.catId ?: ""},\"${application.chemistryScoreInHsc}\",\"${application.childNumber}\",\"${application.collegeAdmissionLetter}\",\"${application.communitee}\",\"${application.contact}\",${application.ddId ?: ""},${application.defenceStatus ?: ""},\"${application.depCgpi}\",\"${application.depClgName}\",\"${application.depMarks}\",\"${application.depPassingYear}\",\"${application.depPer}\",\"${application.depSeat}\",${application.detailsOfPrize ?: ""},\"${application.dipBoard}\",\"${application.dob}\",${application.docId},${application.docIds},\"${application.domicile}\",${application.domicileNumber ?: ""},${application.eduId},${application.email ?: ""},\"${application.ewsPro}\",${application.fatherId ?: ""},\"${application.fcCenterVarification}\",\"${application.feeReciept}\",${application.finalSubmit},\"${application.gapCert}\",${application.genderId},\"${application.grNumber}\",${application.guardianId ?: ""},${application.guardianRelation ?: ""},\"${application.hDegree}\",\"${application.hscMarks}\",\"${application.hscMarksheet}\",\"${application.hscNameOfBoard}\",\"${application.hscPassingYear}\",\"${application.hscPercentage}\",\"${application.hscSeatYear}\",\"${application.incomeCertificate}\",${application.isParentHaveDomicile ?: ""},\"${application.jeeScoreCard}\",${application.landlineNumber ?: ""},\"${application.lastCollegeAttended}\",\"${application.lc}\",\"${application.marriedStatus}\",\"${application.mathsScoreHsc}\",\"${application.mhtCetScoreCard}\",\"${application.minority}\",${application.motherId ?: ""},\"${application.name}\",\"${application.nationality}\",${application.neftId ?: ""},\"${application.nonCreamy}\",${application.paddId},${application.parentSignature},\"${application.partPayment ?: ""}\",${application.personalDetailsId},${application.personalId},\"${application.photo}\",${application.physicallyHandicap ?: ""},\"${application.physicsScoreInHsc}\",\"${application.placeOfBirth}\",${application.programmId},\"${application.programmName}\",${application.raddId},\"${application.rationCard}\",${application.receivedScholership ?: ""},\"${application.religion}\",${application.saddId},${application.seatTypeId},${application.sid},\"${application.signature}\",${application.speacialTalent ?: ""},\"${application.sscMarks}\",\"${application.sscMarksheet}\",\"${application.sscNameOfBoard}\",\"${application.sscPassingYear}\",\"${application.sscPercentage}\",\"${application.sscSeatNumber}\",\"${application.studClgId ?: ""}\",${application.studId},\"${application.subCaste}\",${application.uid},\"${application.vocationalSubject ?: ""}\",\"${application.vocationalTotalScoreHsc ?: ""}\"\n"
            )
        }

        // Flush and close the writer
        writer.flush()
        writer.close()
    }
}
