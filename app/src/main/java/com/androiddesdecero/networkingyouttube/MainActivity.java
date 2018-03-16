package com.androiddesdecero.networkingyouttube;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androiddesdecero.networkingyouttube.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    private static final int TIEMPO_LOADER_ID = 1;
    private static final String REQUEST_URL = "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TiempoAsyncTask task = new TiempoAsyncTask();
        task.execute(REQUEST_URL);
    }

    private void inicializar(String jsonResponse){
        textView = (TextView)findViewById(R.id.textViewMain);
        textView.setText(jsonResponse);
    }

    private class TiempoAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String jsonResponse = Utilidades.obtenerDatos(urls[0]);
            return jsonResponse;
        }
        @Override
            protected void onPostExecute(String result){
            inicializar(result);
        }
    }



}
