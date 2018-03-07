package com.woodnbottle.android.temperatureconverter;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText text;
    private NotificationHelper noti;
    private static final int NOTI_PRIMARY1 = 1100;
    private static final int NOTI_SECONDARY1 = 1200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noti = new NotificationHelper(this);
        text = (EditText) findViewById(R.id.inputValue);


    }

    // this method is called at button click because we assigned the name to the
    // "OnClick" property of the button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
                RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());
                if (celsiusButton.isChecked()) {
                    String newValue = String.valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue));
                    text.setText(newValue);
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);
                    sendNotification(NOTI_SECONDARY1, inputValue + " degrees Fahrenheit = " + newValue + " degrees Celsius");

                } else {
                    String newValue = String.valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue));
                    text.setText(newValue);
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                    sendNotification(NOTI_PRIMARY1, inputValue + " degrees Celsius = " + newValue + " degrees Fahrenheit");

                }
                break;
        }
    }

    public void sendNotification(int id, String title) {
        Notification.Builder nb = null;
        switch (id) {
            case NOTI_PRIMARY1:
                nb = noti.getNotification1(title, getString(R.string.primary1_body));
                break;


            case NOTI_SECONDARY1:
                nb = noti.getNotification2(title, getString(R.string.secondary1_body));
                break;

        }
        if (nb != null) {
            noti.notify(id, nb);
        }
    }

    /**
     * Send Intent to load system Notification Settings for this app.
     */
    public void goToNotificationSettings() {
        Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(i);
    }

    /**
     * Send intent to load system Notification Settings UI for a particular channel.
     *
     * @param channel Name of channel to configure
     */
    public void goToNotificationSettings(String channel) {
        Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        startActivity(i);
    }
}