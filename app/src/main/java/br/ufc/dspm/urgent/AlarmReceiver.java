package br.ufc.dspm.urgent;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    private static int time = 0;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (time != 0) {

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

        }

        ++time;

        scheduleAlarm(context);

    }

    public void scheduleAlarm(Context context) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC,
                System.currentTimeMillis() + 30000, pendingIntent);

    }

}
