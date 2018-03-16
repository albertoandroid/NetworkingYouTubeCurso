package com.androiddesdecero.networkingyouttube;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androiddesdecero.networkingyouttube.utilidades.Tiempo;
import com.androiddesdecero.networkingyouttube.utilidades.TiempoLoader;
import com.androiddesdecero.networkingyouttube.utilidades.Utilidades;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tiempo>> {


    private static final int TIEMPO_LOADER_ID = 1;
    private static final String REQUEST_URL = "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
    private TextView textView;
    private TextView textViewTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(TIEMPO_LOADER_ID, null, this);


    }

    @Override
    public Loader<List<Tiempo>> onCreateLoader(int i, Bundle bundle) {
        return new TiempoLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Tiempo>> loader, List<Tiempo> tiempos) {
        String fecha="";
        String temperatura="";
        for(int i=0; i<tiempos.size();i++)
        {
            fecha=fecha+tiempos.get(i).fecha;
            temperatura= temperatura+tiempos.get(i).temperatura;
        }
        textView = (TextView) findViewById(R.id.textViewMain);
        textView.setText(fecha);
        textViewTemperatura = (TextView) findViewById(R.id.textViewTemperatura);
        textViewTemperatura.setText(temperatura);


    }

    @Override
    public void onLoaderReset(Loader<List<Tiempo>> loader) {

    }
}
