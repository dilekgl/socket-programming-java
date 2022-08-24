package com.example.sokcetactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText sendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        sendText=(EditText)  findViewById(R.id.editText);
    }
    public void send (View v){
        SocketToPc send  = new SocketToPc();
        send.execute(sendText.getText().toString());

    }
    class SocketToPc extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... strings) {
            Socket socket;
            InputStreamReader isr;
            BufferedReader bufferedReader;
            PrintWriter printWriter;
            String results;

            try {
                socket =new Socket("95.0.234.100",3674);
                isr = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(isr);

                printWriter= new PrintWriter(socket.getOutputStream());
                printWriter.println(strings[0]);
                printWriter.flush();

                results = bufferedReader.readLine();
                socket.close();
                isr.close();
                bufferedReader.close();
                return results;

            }
            catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
         //return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

}