package es.udc.psi.p26barboza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;
    MyService localService;
    Intent intent;
    public String getExtra_key() {
        return extra_key;
    }

    public void setExtra_key(String extra_key) {
        this.extra_key = extra_key;
    }

    public static String extra_key="KEY";

    Button boton;
    String broadcast_string ="es.udc.PSI.broadcast.GENERAL";
    EditText et;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setBroadcast();
    }

    private void setUI() {
        boton=findViewById(R.id.myEvent_button);
        et=findViewById(R.id.et);
        sw=findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    intent = new Intent(getApplicationContext(), MyService.class);
                    int count= Integer.valueOf(et.getText().toString());
                    intent.putExtra(extra_key, count);
                    startService(intent);
                }else{
                    stopService(intent);
                }
            }
        });
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(broadcast_string);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
    private void setBroadcast(){
        myReceiver= new MyReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(broadcast_string);
        registerReceiver(myReceiver, filter);
    }
}