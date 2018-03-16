package com.androiddesdecero.networkingyouttube;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.androiddesdecero.networkingyouttube.utilidades.Tiempo;
import com.androiddesdecero.networkingyouttube.utilidades.TiempoAdapter;
import com.androiddesdecero.networkingyouttube.utilidades.TiempoLoader;
import com.androiddesdecero.networkingyouttube.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tiempo>> {


    private static final int TIEMPO_LOADER_ID = 1;
    private static final String REQUEST_URL = "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
    private TextView textView;
    private TextView textViewNoInternet;
    private TiempoAdapter tiempoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNoInternet = findViewById(R.id.textViewNoInternet);
        textViewNoInternet.setVisibility(View.GONE);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(TIEMPO_LOADER_ID, null, this);
        } else {
            textViewNoInternet.setVisibility(View.VISIBLE);
        }

        ListView tiempoListView = (ListView) findViewById(R.id.list);
        tiempoAdapter = new TiempoAdapter(this, new ArrayList<Tiempo>());
        tiempoListView.setAdapter(tiempoAdapter);
    }

    @Override
    public Loader<List<Tiempo>> onCreateLoader(int i, Bundle bundle) {
        return new TiempoLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Tiempo>> loader, List<Tiempo> tiempos) {
        tiempoAdapter.clear();
        if(tiempos != null && !tiempos.isEmpty()){
            tiempoAdapter.addAll(tiempos);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Tiempo>> loader) {
        tiempoAdapter.clear();
    }
}
