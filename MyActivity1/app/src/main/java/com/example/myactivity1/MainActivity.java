package com.example.myactivity1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SMS_REQUEST = 2;
    private static final int CALL_REQUEST = 1;
    private static final int COMPARE_REQUEST = 3;
    EditText txtPhone;
    EditText txtMessage;
    EditText txtValue1;
    EditText txtValue2;
    EditText txtUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPhone = findViewById(R.id.edit_phone);
        txtMessage = findViewById(R.id.edit_message);
        txtValue1 = findViewById(R.id.edit_a);
        txtValue2 = findViewById(R.id.edit_b);
        txtUrl = findViewById(R.id.edit_url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COMPARE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                double max = data.getDoubleExtra("RESULT", 0);
                Toast.makeText(this, "Bigger number: " + max, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCall();
                } else {
                    //permission denied
                }
                return;
            }
            case SMS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMessage(txtPhone.getText().toString(), txtMessage.getText().toString());
                } else {
                    //permission denied
                }
                return;
            }
        }
    }

    public void onClickBtnCall(View view) {
        startCall();
    }

    private void startCall() {

        String phoneNumber = txtPhone.getText().toString();
        callNumber(phoneNumber);
    }

    private void callNumber(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST);
            return;
        }
        startActivity(callIntent);
    }

    private void sendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
    }

    public static boolean checkPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void onClickBtnMessage(View view) {
        if (!checkPermissions(this, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                        SMS_REQUEST);
            }
            return;
        }
        sendMessage(txtPhone.getText().toString(), txtMessage.getText().toString());
    }

    private void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent urlIntent = new Intent((Intent.ACTION_VIEW));
        urlIntent.setData(uri);
        startActivity(urlIntent);
    }

    public void onClickUrlBtn(View view) {
        openUrl(txtUrl.getText().toString());
    }

    private void compare(double v1, double v2) {
        Intent compareIntent = new Intent(this, OtherAcitivty.class);
        compareIntent.putExtra("V1", v1);
        compareIntent.putExtra("V2", v2);
        startActivityForResult(compareIntent, COMPARE_REQUEST);
    }


    public void onClickBtnCompare(View view) {
        double a = Double.parseDouble(txtValue1.getText().toString());
        double b = Double.parseDouble(txtValue2.getText().toString());
        compare(a, b);
    }
}
