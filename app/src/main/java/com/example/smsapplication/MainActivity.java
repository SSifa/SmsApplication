package com.example.smsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = findViewById(R.id.btnSendSMS);
        txtphoneNo = findViewById(R.id.editTextPhoneNo);
        txtMessage = findViewById(R.id.editTextSMS);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }
    protected void sendSMSMessage() {
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            }
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,
                    null, message, null,
                    null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is
// present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}