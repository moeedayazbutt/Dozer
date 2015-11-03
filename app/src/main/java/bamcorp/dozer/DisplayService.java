package bamcorp.dozer;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Moeed on 028, 28, Oct, 2015.
 */
public class DisplayService extends Service {

    private BroadcastReceiver broadcastReceiver;

    public static void startService(Context context) {
        Intent intent = new Intent(context, DisplayService.class);
        context.startService(intent);
        Toast.makeText(context, context.getResources().getString(R.string.toast_dozer_on), Toast.LENGTH_SHORT).show();
        Log.v("Dozer", "Service started");
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, DisplayService.class);
        context.stopService(intent);
        Toast.makeText(context, context.getResources().getString(R.string.toast_dozer_off), Toast.LENGTH_SHORT).show();
        Log.v("Dozer", "Service stopped");
    }


    public static boolean isRunning(Context context) {
        final String className = DisplayService.class.getName();

        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (className.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        broadcastReceiver = new ReceiverScreen();
        ReceiverScreen.registerReceiver(getApplicationContext(), broadcastReceiver);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            ReceiverScreen.unregisterReceiver(getApplicationContext(), broadcastReceiver);
        } catch (Exception ex) {

        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
