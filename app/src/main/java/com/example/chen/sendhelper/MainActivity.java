package com.example.chen.sendhelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView phoneTitle = findViewById(R.id.phoneTitle);
        final EditText phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        final EditText messageEditText = (EditText) findViewById(R.id.messageEditText);

        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneTextValue = phoneEditText.getText().toString();
                if (!isValidMobile(phoneTextValue)) return;
                String messageTextValue = messageEditText.getText().toString();
                String url = "https://api.whatsapp.com/send?phone=972"
                        + phoneTextValue.substring(1) + "&text=" + messageTextValue;

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                // Code here executes on main thread after user presses button
            }
        });

    }

    private boolean isValidMobile(String phone) {
        boolean check=false;
        if (phone==null || phone.equals("")) return false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() != 10 && phone.charAt(0)!='0') {
                check = false;
                Toast.makeText(getApplicationContext(), "This is not a valid number", Toast.LENGTH_SHORT).show();
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }


}
