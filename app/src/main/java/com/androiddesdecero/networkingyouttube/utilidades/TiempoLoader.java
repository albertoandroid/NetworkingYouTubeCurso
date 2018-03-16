package com.androiddesdecero.networkingyouttube.utilidades;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 16/3/18.
 */

public class TiempoLoader extends AsyncTaskLoader<List<Tiempo>> {

    private String mUrl;
    public TiempoLoader(Context context, String url){
        super(context);
        mUrl=url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Tiempo> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Tiempo> tiempos = Utilidades.obtenerDatos(mUrl);
        return tiempos;
    }
}
