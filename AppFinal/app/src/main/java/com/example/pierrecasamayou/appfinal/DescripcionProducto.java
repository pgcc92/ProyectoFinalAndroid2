package com.example.pierrecasamayou.appfinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class DescripcionProducto extends Fragment {

    public DescripcionProducto(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_descripcion_producto,container,false);
        TextView textView = (TextView)rootView.findViewById(R.id.textView8);
        TextView textView1 = (TextView)rootView.findViewById(R.id.textView12);
        TextView textView2 = (TextView)rootView.findViewById(R.id.textView15);
        String datosRecibidos = getArguments().getString("datocod");
        String datos2 = getArguments().getString("datonom");
        String datos3 = getArguments().getString("datopre");

        textView.setText(datosRecibidos);
        textView1.setText(datos2);
        textView2.setText(datos3);


        return rootView;
    }
}
