
package bamcorp.dozer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


public class ReceiverScreen extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
		{
			Log.v("Dozer", "Screen off");

			MainActivity.tryDoze();
		}
		else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			Log.v("Dozer", "Screen on");
		}
	}


	public static void registerReceiver(final Context context, final BroadcastReceiver broadcastReceiver)
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		//filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

		context.registerReceiver(broadcastReceiver, filter);
	}


	public static void unregisterReceiver(final Context context, final BroadcastReceiver broadcastReceiver)
	{
		context.unregisterReceiver(broadcastReceiver);
	}

}
