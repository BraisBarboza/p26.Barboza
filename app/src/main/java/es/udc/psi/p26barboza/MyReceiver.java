package es.udc.psi.p26barboza;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            String log = "Action: " + intent.getAction() + "\n";
            log = log + "URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n";
            Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
            Log.d("_TAG", log);
    }
}