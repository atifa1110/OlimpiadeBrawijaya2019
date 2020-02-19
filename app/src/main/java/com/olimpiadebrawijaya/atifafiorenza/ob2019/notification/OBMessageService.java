package com.olimpiadebrawijaya.atifafiorenza.ob2019.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;

import static com.olimpiadebrawijaya.atifafiorenza.ob2019.debug.Tag.FCM_TAG;

public class OBMessageService extends FirebaseMessagingService {

    String ADMIN_CHANNEL_ID = "admin_channel";

    @Override
    public void onNewToken(String mToken) {
        super.onNewToken(mToken);
        Log.e("TOKEN: ", mToken);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(FCM_TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(FCM_TAG, "Message data payload: " + remoteMessage.getData());
            String value0 = remoteMessage.getData().get("key0");
            Log.d(FCM_TAG, "onMessageReceived: value0: " + value0);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(FCM_TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String messageText = remoteMessage.getNotification().getBody();
            Log.d(FCM_TAG, "onMessageReceived: messageText: " + messageText);
            handleNow(messageText);
        }

        // later pake pending intent

    }


    // MARK : - handle notification message now
    private void handleNow(String messageText) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels();
        }

        NotificationManager mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Log.d(FCM_TAG, "handleNow");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_tobedetermined_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(uri);

        if (mManager != null) {
            mManager.notify(0, mBuilder.build());
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels() {
        String adminChannelName = getString(R.string.notifications_admin_channel_name);
        String adminChannelDescription = getString(R.string.notifications_admin_channel_description);

        NotificationChannel adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);

        NotificationManager mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mManager.createNotificationChannel(adminChannel);

    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
