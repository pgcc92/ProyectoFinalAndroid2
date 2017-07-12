package com.example.pierrecasamayou.appfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DetalleCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_categoria);
        Bundle bundle = getIntent().getExtras();
        this.setTitle(bundle.getString("datonom"));
        String codigocategoria = bundle.getString("datocod");


        String ruta = Conexion.rutaservidor + "productoscategoria.php";
        final ListView listado = (ListView)findViewById(R.id.listaProductos);
        try {
            URL url = new URL(ruta);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("caty",codigocategoria);

            BufferedWriter writer =new BufferedWriter(
                    new OutputStreamWriter(urlConnection.getOutputStream()));
            writer.write(builder.build().getEncodedQuery());
            writer.flush();


            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //setTitle(bufferedReader.readLine());

            JSONArray jsonArray = new JSONArray(bufferedReader.readLine());

            final ArrayList<HashMap<String,String>> lista = new ArrayList<>();
            for(int i = 0; i<= jsonArray.length()-1; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("cod",jsonObject.getString("idproducto"));
                map.put("nom",jsonObject.getString("nombre"));
                map.put("pre",jsonObject.getString("precio"));
                lista.add(map);
            }

            ListAdapter adapter = new SimpleAdapter(this,
                    lista,R.layout.item_producto,
                    new String[]{"cod","nom","pre"},
                    new int[]{R.id.tvCodigo,R.id.tvNombre,R.id.tvPrecio});

            listado.setAdapter(adapter);



            listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap<String,String> map = (HashMap<String, String>)listado.getItemAtPosition(i);
                    String procod = map.get("cod");
                    String pronom = map.get("nom");
                    String prodes = map.get("pre");
                    Toast.makeText(getApplicationContext(),pronom,Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("datocod",procod);
                    bundle.putString("datonom",pronom);
                    bundle.putString("datopre",prodes);

                    Fragment fragment = new DescripcionProducto();
                    fragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contenedor,fragment).commit();


                }
            });






        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
