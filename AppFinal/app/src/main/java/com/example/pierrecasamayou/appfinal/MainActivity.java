package com.example.pierrecasamayou.appfinal;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ruta = Conexion.rutaservidor + "categorias.php";
        final ListView listado = (ListView)findViewById(R.id.listaCategorias);
        try {
            URL url = new URL(ruta);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //setTitle(bufferedReader.readLine());

            JSONArray jsonArray = new JSONArray(bufferedReader.readLine());

            ArrayList<HashMap<String,String>> lista = new ArrayList<>();
            for(int i = 0; i<= jsonArray.length()-1; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("cod",jsonObject.getString("idcategoria"));
                map.put("nom",jsonObject.getString("nombre"));
                map.put("des",jsonObject.getString("descripcion"));
                lista.add(map);
            }

            ListAdapter adapter = new SimpleAdapter(this,
                    lista,R.layout.item_categoria,
                    new String[]{"cod","nom","des"},
                    new int[]{R.id.tvCodigo,R.id.tvNombre,R.id.tvDescripcion});

            listado.setAdapter(adapter);

            listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap<String,String> map = (HashMap<String, String>)listado.getItemAtPosition(i);
                    String catcod = map.get("cod");
                    String catnom = map.get("nom");
                    String catdes = map.get("des");
                    Toast.makeText(getApplicationContext(),catnom,Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("datocod",catcod);
                    bundle.putString("datonom",catnom);
                    bundle.putString("datodes",catdes);
                    Intent intent = new Intent(
                            MainActivity.this,DetalleCategoria.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
