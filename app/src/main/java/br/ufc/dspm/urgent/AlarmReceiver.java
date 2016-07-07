package br.ufc.dspm.urgent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker("Ticker");
        builder.setContentTitle("URGENT");
        builder.setContentText("Dicas de Saúde");
        builder.setSmallIcon(R.drawable.ambulancia_icone);
        builder.setSound(soundUri);

        Intent intentNotification = new Intent(context, AtividadesDiariasActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intentNotification, 0);
        builder.setContentIntent(pIntent);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);

    //  scheduleAlarm(context);

    }

    /*

    public void scheduleAlarm(Context context) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 31);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC,
                calendar.getTimeInMillis(), pendingIntent);

    }

    */

}
