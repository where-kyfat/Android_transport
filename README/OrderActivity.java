package com.example.android_transport;

//используемые библиотеки
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class OrderActivity extends AppCompatActivity {

	//Это очень плохо хранить их тут, но красота требует жертв
    String[] transport_type = { "Тентованный", "Рефрижератор", "Самосвал", "Платформа" };

    Context mainContext;
    String title;
    String text;
    String from;
    String to;
    String attach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // вертикальная
		
		/* данный блок (до "----") является своеобразным конструктором выдвигающейся панели
			с выбором типа транспорта. Её пришлось создавать здесь, а не в xml из-за скупой
			кастомизации в xml. */
        mainContext = this;
        attach = "";
        setContentView(R.layout.activity_order);

        // Получаем экземпляр элемента Spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        // Настраиваем адаптер
        ArrayAdapter<?> adapter =
                new ArrayAdapter<String>(this,
                        R.layout.row, R.id.type_transport, transport_type);

        // Вызываем адаптер
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
		//-----------------------------------------------------------------
		
		//обработчик нажатия на кнопку заказа
        ((Button)findViewById(R.id.buttonOrderComplete)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sender_mail_async async_sending = new sender_mail_async();
                async_sending.execute();
            }
        });
    }
	
	//Класс содержащий все необходимое для отправки сообщения
    private class sender_mail_async extends AsyncTask<Object, String, Boolean> {
		//Поле состояния диалогового окна
        ProgressDialog WaitingDialog;
		
		//Функция, вызываемая перед выполнением отправки
        @Override 
        protected void onPreExecute() {
            WaitingDialog = ProgressDialog.show(OrderActivity.this, "Отправка данных",
                    "Отправляем сообщение...", true);
        }
		
		//Функция, вызываемая после выполнения отправки
        @Override
        protected void onPostExecute(Boolean result) {
            WaitingDialog.dismiss();
            Toast.makeText(mainContext, "Отправка завершена!", Toast.LENGTH_LONG).show();
            ((Activity)mainContext).finish();
        }
		
		//Функция, отправки
        @Override
        protected Boolean doInBackground(Object... params) {

            try {
                String[] choose = getResources().getStringArray(R.array.type_of_transport);
				
				/*Для отправки сообщения необходимо ввести 4 строковых параметра:
				заголовок, текст, от, кому*/
				
				/*Для тихой отправки сообщения необходимо отправлять его от чего-то имени
				(от чей-то почты), иначе пришлось бы вызывать на клиенте приложение почты
				и все равно указывать от кого оно отправляется (клиенту пришлось бы) */
				
                title = "Заказ";
				
				//Тут должен был быть парсер
                text = "Имя заказчика: " + System.getProperty("line.separator") +
                            ((EditText)findViewById(R.id.editText1)).getText().toString() +
                        System.getProperty("line.separator") + "Номер телефона: " +
                            ((EditText)findViewById(R.id.editText2)).getText().toString() +
                        System.getProperty("line.separator") + "E-mail: " +
                            ((EditText)findViewById(R.id.editText3)).getText().toString() +
                        System.getProperty("line.separator") + "Пункт погрузки: " +
                            ((EditText)findViewById(R.id.editText4)).getText().toString() +
                        System.getProperty("line.separator") + "Пункт выгрузки: " +
                            ((EditText)findViewById(R.id.editText5)).getText().toString() +
                        System.getProperty("line.separator") + "Вид транспорта: " +
                            ((Spinner)findViewById(R.id.spinner1)).getSelectedItem().toString() +
                        System.getProperty("line.separator") + "Вес: " +
                            ((EditText)findViewById(R.id.editText6)).getText().toString() +
                        System.getProperty("line.separator") + "Объём: " +
                            ((EditText)findViewById(R.id.editText7)).getText().toString() +
                        System.getProperty("line.separator") + "Дата перевозки: " +
                            ((EditText)findViewById(R.id.editText8)).getText().toString() +
                        System.getProperty("line.separator") + "Комментарии: " +
                            ((EditText)findViewById(R.id.editText9)).getText().toString();

                from = "transport.order123@gmail.com";
                to = "expustrans@list.ru";
				
				//Авторизация в почту: логин и пароль в параметрах (плохой тон)
                MailSenderClass sender = new MailSenderClass("transport.order123@gmail.com",
                        "zaq12WSX34");
				
				//Функция отправки сообщения
                sender.sendMail(title, text, from, to);
            } catch (Exception e) {
                Toast.makeText(mainContext, "ошибка отправки сообщения!", Toast.LENGTH_SHORT).show();
            }

            return false;
        }
    }
}
