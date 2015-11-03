package bamcorp.dozer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextView textView;

    @Override
    protected void onResume() {
        super.onResume();

        refreshStatus();
    }

    public static void tryRoot() {
        Log.v("Dozer", "Attempting root");
        try {
            Runtime.getRuntime().exec("su");
            Log.v("Dozer", "Root command sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tryDoze() {
        Log.v("Dozer", "Attempting doze");
        try {
            Runtime.getRuntime().exec("su -c dumpsys deviceidle force-idle");
            Log.v("Dozer", "Doze command sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void refreshStatus() {

        boolean status  = ManagerPreferences.loadBoolean(context, ManagerPreferences.DOZER_STATUS, false);

        if(status && !DisplayService.isRunning(context))
            DisplayService.startService(context);

        if (DisplayService.isRunning(context)) {
            textView.setText(getResources().getString(R.string.dozer_on));
        } else {
            textView.setText(getResources().getString(R.string.dozer_off));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        textView = (TextView) findViewById(R.id.buttonDoze);

        //Toast.makeText(context, "status " + ManagerPreferences.loadBoolean(context, ManagerPreferences.DOZER_STATUS, false), Toast.LENGTH_SHORT).show();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DisplayService.isRunning(context)) {
                    DisplayService.stopService(context);
                    ManagerPreferences.saveBoolean(context, ManagerPreferences.DOZER_STATUS, false);
                } else {
                    tryRoot();
                    DisplayService.startService(context);
                    ManagerPreferences.saveBoolean(context, ManagerPreferences.DOZER_STATUS, true);
                }

                refreshStatus();
            }
        });
    }

}
