package com.example.pierrecasamayou.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener {

    EditText txtusuario, txtpassword, txtnombres, txtfecnac, txtemail, txtcelular;
    Button btnregistrar, btnvolver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        txtusuario = (EditText) findViewById(R.id.txtusu);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtfecnac = (EditText) findViewById(R.id.txtdate);
        txtemail = (EditText) findViewById(R.id.txtmail);
        txtcelular = (EditText) findViewById(R.id.txtcelular);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        btnvolver = (Button) findViewById(R.id.btnvolver);

       btnregistrar.setOnClickListener(this);
        btnvolver.setOnClickListener(this);

    }

    public void Guardar_DATA(String usuario, String password, String nombres,String fecnac,String email, String celular){
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "http://192.168.1.110:8888/AppFinal/InsertarUsuario.php";
        RequestParams parametros = new RequestParams();
        parametros.put("usuario",usuario);
        parametros.put("password",password);
        parametros.put("nombres",nombres);
        parametros.put("fecnac",fecnac);
        parametros.put("email",email);
        parametros.put("celular",celular);
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode ==200){
                    String resultado = new String (responseBody);
                    if (resultado.equalsIgnoreCase("1")){
                        Toast.makeText(getApplicationContext(), "Se registro Exitosamente!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "No se registro, Verifique los datos", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    @Override
    public void onClick(View view) {

        if(view==btnregistrar){
            String usuario = this.txtusuario.getText().toString();
            String password = this.txtpassword.getText().toString();
            String nombres = this.txtnombres.getText().toString();
            String fecnac = this.txtfecnac.getText().toString();
            String email = this.txtemail.getText().toString();
            String celular = this.txtcelular.getText().toString();
            Guardar_DATA(usuario,password,nombres,fecnac,email,celular);
        }
        if(view==btnvolver){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }

    }
}
