package com.example.android_transport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.DialogInterface; // для конструктора dialog
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button buttonCall, buttonOrder;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // вертикальная
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    private void addListenerOnButton(){
        buttonOrder = (Button)findViewById(R.id.buttonOrder);

        buttonOrder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.android_transport.OrderActivity");
                        startActivity(intent);
                    }
                }
        );

        buttonCall = (Button)findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialogCall_builder = new AlertDialog.Builder(MainActivity.this);
                        dialogCall_builder.setMessage("Вы хотите позвонить оператору?")
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                        AlertDialog alert = dialogCall_builder.create();
                        alert.setTitle("Набор оператора");
                        alert.show();
                    }
                }
        );
    };
}
