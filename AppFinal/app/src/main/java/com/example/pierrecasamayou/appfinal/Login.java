package com.example.pierrecasamayou.appfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnIngresar, btnNuevo;
    EditText txtusuario, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusuario = (EditText) findViewById(R.id.txtuser);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);


        btnIngresar.setOnClickListener(this);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegistrarUsuario.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View view) {

        Thread tr = new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosGET(txtusuario.getText().toString(),txtpassword.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r =objDatosJson(resultado);
                        if(r>0){
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("doclogin",txtusuario.getText().toString());
                            startActivity(i);

                        }else {
                            Toast.makeText(getApplicationContext(),"Usuario o Password Incorrecto",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        };
        tr.start();
    }




    public String enviarDatosGET(String usuario, String password) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try {
            url = new URL(Conexion.rutaservidor+"conexion.php?usuario="+usuario+"&password="+password);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            resul = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    resul.append(linea);
                }
            }

        } catch (Exception e) {
            System.err.println(e);
            resul = new StringBuilder("error");

        }
        return resul.toString();

    }

    public int objDatosJson(String response) {

        int resp = 0;

        try {
            JSONArray json = new JSONArray(response);

            if (json.length() > 0) {
                resp = 1;
            }

        } catch (Exception e) {

        }
        return resp;
    }

}
