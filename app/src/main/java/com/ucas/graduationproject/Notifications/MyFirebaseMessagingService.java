package com.ucas.graduationproject.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ucas.graduationproject.Activities.HomeActivity;
import com.ucas.graduationproject.R;

import java.util.Objects;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String TAG = "MyFirebaseMessagingService";
    private String CHANNEL_ID = "id";
    private String CHANNEL_NAME = "Channel One";
    private int importance = NotificationManager.IMPORTANCE_DEFAULT;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        if (remoteMessage.getNotification() != null) {

            notification(remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody());

        }

    }

    private void notification(String title, String body) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription("Description");
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            channel.setVibrationPattern(new long[]{1000, 200, 400, 500, 700, 250});
            channel.enableLights(true);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }

}
