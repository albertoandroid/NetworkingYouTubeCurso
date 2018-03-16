package com.androiddesdecero.networkingyouttube;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androiddesdecero.networkingyouttube.utilidades.Tiempo;
import com.androiddesdecero.networkingyouttube.utilidades.Utilidades;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int TIEMPO_LOADER_ID = 1;
    private static final String REQUEST_URL = "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
    private TextView textView;
    private TextView textViewTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TiempoAsyncTask task = new TiempoAsyncTask();
        task.execute(REQUEST_URL);
    }

    private void inicializar(Tiempo tiempo){
        textView = (TextView)findViewById(R.id.textViewMain);
        textViewTemperatura = (TextView)findViewById(R.id.textViewTemperatura);
        textView.setText(tiempo.temperatura);
        textViewTemperatura.setText(tiempo.fecha);
    }

    private class TiempoAsyncTask extends AsyncTask<String, Void, Tiempo>{

        @Override
        protected Tiempo doInBackground(String... urls) {
            Tiempo jsonResponse = Utilidades.obtenerDatos(urls[0]);
            return jsonResponse;
        }
        @Override
            protected void onPostExecute(Tiempo result){
            inicializar(result);
        }
    }



}
