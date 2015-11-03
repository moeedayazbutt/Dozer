
package bamcorp.dozer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ReceiverBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v("Dozer", "On boot");

        if (ManagerPreferences.loadBoolean(context, ManagerPreferences.DOZER_STATUS, false))
            DisplayService.startService(context);
    }

}
