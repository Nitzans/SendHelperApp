package com.example.chen.sendhelper;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        final EditText messageEditText = (EditText) findViewById(R.id.messageEditText);

        final Button infoButton = findViewById(R.id.button_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AboutActivity.class);
                startActivity(i);
                }
        });

        final Button sendButton = findViewById(R.id.button_id);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneTextValue = phoneEditText.getText().toString();
                if (!isValidMobileNumber(phoneTextValue)) return;
                String messageTextValue = messageEditText.getText().toString();
                String url = "https://api.whatsapp.com/send?phone=972"
                        + phoneTextValue.substring(1) + "&text=" + messageTextValue;

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                // Code here executes on main thread after user presses send_button
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.patterns));
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("--- No pattern ---")) {
                    messageEditText.setText("");
                } else {
                    messageEditText.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private boolean isValidMobileNumber(String phone) {
        boolean check = false;
        if (phone == null || phone.equals("")) return false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() != 10 && phone.charAt(0) != '0') {
                check = false;
                Toast.makeText(getApplicationContext(), "This is not a valid number", Toast.LENGTH_SHORT).show();
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }


}
