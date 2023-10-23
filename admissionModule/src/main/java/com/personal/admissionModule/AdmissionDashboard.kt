package com.personal.admissionModule

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getfly.technologies.model.response.DownloadApplicationsResponse
import com.opencsv.bean.CsvConverter
import com.personal.admissionModule.viewmodel.AcademateRepository
import com.personal.admissionModule.viewmodel.EaseBuzzRepository
import com.personal.admissionModule.viewmodel.MainScreenViewModel
import com.personal.admissionModule.viewmodel.MainViewModelFactory
import com.personal.admissionmModule.R
import com.personal.admissionmModule.databinding.ActivityAdmissionDashboardBinding
import java.io.OutputStream

class AdmissionDashboard : AppCompatActivity() {

    private lateinit var binding : ActivityAdmissionDashboardBinding
    private lateinit var viewModel: MainScreenViewModel

    private var progressDialog: ProgressDialog? = null

    private lateinit var csvConverter: com.getfly.technologies.extras.CsvConverter

    private var pendingCS: Int = 0
    private var pendingIT: Int = 0
    private var pendingAIDS: Int = 0
    private var pendingEXTC: Int = 0

    private val notificationPermissionRequestCode = 123


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdmissionDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_blue)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_blue)

        //Notification channel for higher android versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "academate_channel_id"
            val channelName = "academate_channel_name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val uidSP = 774
//        val uidSP = AdmissionScreen.sharedPreferences.getInt("uid", 0)
        if (uidSP != 0) {
            val repository = AcademateRepository()
            val repositoryTwo = EaseBuzzRepository()
            val viewModelFactory = MainViewModelFactory(repository, repositoryTwo)
            progressDialog = ProgressDialog(this)
            csvConverter = com.getfly.technologies.extras.CsvConverter()

            viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenViewModel::class.java]

            //Get Faculty dashboard details
            viewModel.getfacultyDashboard(uidSP.toString())
            viewModel.FacultyDashboardResponse.observe(this) { FacultyDashboardResponseStatus ->
                if (FacultyDashboardResponseStatus.isSuccessful) {
                    binding.tvFirstYearCompleted.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.count1.toString()
                    binding.tvDseCompleted.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.count2.toString()
                    binding.tvSecondYearCompleted.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.count3.toString()
                    binding.tvThirdYearCompleted.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.count4.toString()
                    binding.tvFinalYearCompleted.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.count5.toString()
                    binding.tvComputerTotal.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.cs.toString()
                    binding.tvItTotal.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.it.toString()
                    binding.tvAidsTotal.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.aids.toString()
                    binding.tvExtcTotal.text =
                        "Total : " + FacultyDashboardResponseStatus.body()?.extc.toString()

                    pendingCS = FacultyDashboardResponseStatus.body()?.cs.toString().toInt()
                    pendingIT = FacultyDashboardResponseStatus.body()?.it.toString().toInt()
                    pendingAIDS = FacultyDashboardResponseStatus.body()?.aids.toString().toInt()
                    pendingEXTC = FacultyDashboardResponseStatus.body()?.extc.toString().toInt()


                    viewModel.getPendingApplication(uidSP.toString())
                    viewModel.pendingApplicationsLiveData.observe(
                        this,
                        Observer { pendingApplications ->
                            binding.tvComputerPending.text =
                                "Pending : " + pendingCS.minus(
                                    pendingApplications.get(0).pcs.toString().toInt()
                                )
                            binding.tvItPending.text =
                                "Pending : " + pendingIT.minus(
                                    pendingApplications.get(0).pit.toString().toInt()
                                )
                            binding.tvAidsPending.text =
                                "Pending : " + pendingAIDS.minus(
                                    pendingApplications.get(0).paids.toString().toInt()
                                )
                            binding.tvExtcPending.text =
                                "Pending : " + pendingEXTC.minus(
                                    pendingApplications.get(0).pextc.toString().toInt()
                                )
                        })
                }
            }

            //Download Applications
            binding.downloadComputer.setOnClickListener {
                progressDialog?.show()
                //Download applications details request
                viewModel.getDownloadApplications(uidSP.toString())
                viewModel.DownloadApplicationsResponse.observe(this) { DownloadApplicationsResponseStatus ->
                    if (DownloadApplicationsResponseStatus.isSuccessful) {
                        Log.d(
                            "download_applications_response",
                            DownloadApplicationsResponseStatus.body()?.applications?.size.toString()
                        )

                        val data: List<DownloadApplicationsResponse.Application> =
                            DownloadApplicationsResponseStatus.body()!!.applications/* Your data */
                        val fileName = "StudentApplications.csv"
                        val contentResolver: ContentResolver = applicationContext.contentResolver
                        val contentValues = ContentValues().apply {
                            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                            put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                            put(
                                MediaStore.MediaColumns.RELATIVE_PATH,
                                Environment.DIRECTORY_DOWNLOADS
                            )
                        }
                        val contentUri: Uri? = contentResolver.insert(
                            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                            contentValues
                        )

                        contentUri?.let { uri ->
                            try {
                                val outputStream: OutputStream? =
                                    contentResolver.openOutputStream(uri)

                                if (outputStream != null) {
                                    csvConverter.convertToCsv(data, outputStream)
                                    outputStream.close()
                                    // Check if notification permission is granted
                                    if (!isNotificationPermissionGranted()) {
                                        requestNotificationPermission()
                                    } else {
                                        showDownloadNotification(this, fileName, contentUri)
                                    }
                                }
                            } catch (e: Exception) {
                                Log.d("error_saving_csv", e.message.toString())
                                Toast.makeText(this, "Error saving CSV file", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        progressDialog?.dismiss()
                    } else {
                        Log.d("something_went_wrong", "Something went wrong")
                        progressDialog?.dismiss()
                    }
                }
            }

            //Download only Computer
            binding.downloadDemo.setOnClickListener {
                progressDialog?.show()
                //Download applications details request
                viewModel.getComputerApplications("Computer Engineering")
                viewModel.ComputerApplicationResponse.observe(this) { ComputerApplicationsResponseStatus ->

                    if (ComputerApplicationsResponseStatus.isSuccessful) {
                        Log.d(
                            "computer_applications_response",
                            ComputerApplicationsResponseStatus.body().toString()
                        )

//                        val data: List<DownloadApplicationsResponse.Application> =
//                            DownloadApplicationsResponseStatus.body()!!.applications/* Your data */
//                        val fileName = "StudentApplications.csv"
//                        val contentResolver: ContentResolver = applicationContext.contentResolver
//                        val contentValues = ContentValues().apply {
//                            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
//                            put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
//                            put(
//                                MediaStore.MediaColumns.RELATIVE_PATH,
//                                Environment.DIRECTORY_DOWNLOADS
//                            )
//                        }
//                        val contentUri: Uri? = contentResolver.insert(
//                            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
//                            contentValues
//                        )
//
//                        contentUri?.let { uri ->
//                            try {
//                                val outputStream: OutputStream? =
//                                    contentResolver.openOutputStream(uri)
//
//                                if (outputStream != null) {
//                                    csvConverter.convertToCsv(data, outputStream)
//                                    outputStream.close()
//                                    // Check if notification permission is granted
//                                    if (!isNotificationPermissionGranted()) {
//                                        requestNotificationPermission()
//                                    } else {
//                                        showDownloadNotification(this, fileName, contentUri)
//                                    }
//                                }
//                            } catch (e: Exception) {
//                                Log.d("error_saving_csv", e.message.toString())
//                                Toast.makeText(this, "Error saving CSV file", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                        }
//                        progressDialog?.dismiss()
//                    } else {
//                        Log.d("something_went_wrong", "Something went wrong")
//                        progressDialog?.dismiss()
                    } else {
                        Log.d("comps_problem", "Something went wrong")
                        progressDialog?.dismiss()
                    }
                }
            }

//            //Logout button
//            binding.btnLogout.setOnClickListener {
//                val editor = AdmissionScreen.sharedPreferences.edit()
//                editor.putBoolean("isStudent", false)
//                editor.putBoolean("isAdmission", false)
//                editor.apply()
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }

        }

    }

    //Notificaion + File opening Logic
    @SuppressLint("IntentReset")
    fun showDownloadNotification(context: Context, fileName: String, contentUri: Uri) {
        // Create an intent to open the downloaded file (you can customize this)
        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = contentUri
//        intent.type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        intent.setDataAndType(contentUri, "text/csv")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

//        val pendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
//            addNextIntentWithParentStack(intent)
//            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
//        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(context, "academate_channel_id")
            .setContentTitle("File Downloaded")
            .setContentText("$fileName has been downloaded.")
            .setSmallIcon(R.drawable.logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationId = 1
        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun isNotificationPermissionGranted(): Boolean {
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        return notificationManagerCompat.areNotificationsEnabled()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestNotificationPermission() {
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        startActivityForResult(intent, notificationPermissionRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == notificationPermissionRequestCode) {
            // Check if the user granted notification permission after being directed to settings
            if (isNotificationPermissionGranted()) {
                return
            } else {
                Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }

}