package com.example.android_transport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.DialogInterface; // для конструктора dialog
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button buttonCall, buttonOrder;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //создание окна
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // вертикальная
        setContentView(R.layout.activity_main);//создание содержимого окна
        addListenerOnButton();/*обработчик событий (ожидает действий от пользователя и 
			обрабатывает их)*/
		
		//обработчик кнопки вызова
        Button mDialButton = (Button) findViewById(R.id.buttonCall);
        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = "89185864386"; //сам телефон (это дурной тон)
                String dial = "tel:" + phoneNo;
				//по сути открытие "вызова" с введенными параметрами
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });
    }
	
	//обработчик событий
    private void addListenerOnButton(){
		//поле состояния кнопки Order
        buttonOrder = (Button)findViewById(R.id.buttonOrder);
		
		//обработчик клика на Order
        buttonOrder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.android_transport.OrderActivity");
                        startActivity(intent); //запуск конструктора окна OrderActivity
                    }
                }
        );

		//поле состояния кнопки Call
        buttonCall = (Button)findViewById(R.id.buttonCall);
		
		//обработчик клика на Call
        buttonCall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
						//запуск конструктора диалогового окна
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
