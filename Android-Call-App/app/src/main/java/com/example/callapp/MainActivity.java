package com.example.callapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private TextView callLogText, smsText;
    private Button callButton, smsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callLogText = findViewById(R.id.callLogText);
        smsText = findViewById(R.id.smsText);
        callButton = findViewById(R.id.callButton);
        smsButton = findViewById(R.id.smsButton);

        // Ask for permissions
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS
        }, REQUEST_CODE);

        // Read Call Logs
        getCallLogs();

        // Read SMS
        getSmsMessages();

        // Button to Make a Call
        callButton.setOnClickListener(v -> makeCall("+1234567890"));

        // Button to Send SMS
        smsButton.setOnClickListener(v -> sendSms("+1234567890", "Hello from Android!"));
    }

    // Get Call Logs
    private void getCallLogs() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor != null && cursor.moveToFirst()) {
            String callDetails = "";
            do {
                String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                callDetails += "Number: " + number + ", Type: " + type + "\n";
            } while (cursor.moveToNext());
            callLogText.setText(callDetails);
            cursor.close();
        }
    }

    // Get SMS Messages
    private void getSmsMessages() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Cursor cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null, null, Telephony.Sms.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder smsDetails = new StringBuilder();
            do {
                String address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                smsDetails.append("From: ").append(address).append("\nMessage: ").append(body).append("\n\n");
            } while (cursor.moveToNext());
            smsText.setText(smsDetails.toString());
            cursor.close();
        }
    }

    // Make a Call
    private void makeCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }

    // Send SMS
    private void sendSms(String phoneNumber, String message) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
    }

    // Handle Permission Results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            getCallLogs();
            getSmsMessages();
        }
    }
}
package com.example.callapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.example.callapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.example.callapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
