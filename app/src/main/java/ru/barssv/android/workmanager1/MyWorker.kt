package ru.barssv.android.workmanager1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParameters: WorkerParameters):
    Worker(context, workerParameters) {

    companion object{
        const val CHANNEL_ID = "Channel_Id"
        const val NOTIFICATION_ID = 1

    }
    override fun doWork(): Result {


        Log.d("do work success", "doWork: Success function called")

        showNotification()


        return Result.success()
    }

    private fun showNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
                 flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, intent,0
           )

        val notification = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Life is GOOD")
            .setContentText("Subscribe on the channel")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            val channelName = "Channel Name"
            val channelDescription = "channel Description"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(CHANNEL_ID, channelName, channelImportance).apply {
                description = channelDescription
            }
            val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(applicationContext)){
            notify(NOTIFICATION_ID, notification.build())
        }

      //  val intent = Intent(applicationContext, MainActivity::class.java).apply {
      //      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      //  }
     //   val pendingIntent = PendingIntent.getActivity(
      //      applicationContext,0,intent,0
        //)

        //val builder = NotificationCompat.Builder(applicationContext,"my_inique_id")
          //  .setSmallIcon(R.drawable.ic_launcher_foreground)
            //.setContentTitle("Life is GOOD")
            //.setContentText("Subscribe on the channel")
            //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //.setAutoCancel(true)
            //.setContentIntent(pendingIntent)

       // with(NotificationManagerCompat.from(applicationContext)){
         //   notify(1, builder.build())

        //}


    }


}