package com.example.almacenamientos_de_datos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_notas);
        String archivos [] = fileList();

        if (ArchivoNuevo(archivos, "ficheros.txt")) {
            try {
                InputStreamReader objeto = new InputStreamReader(openFileInput("ficheros.txt"));
                BufferedReader br = new BufferedReader(objeto);
                String lineas = br.readLine();
                String ficherosCompletos = "";

                while(lineas != null) {
                ficherosCompletos = ficherosCompletos + lineas + "\n";
                lineas = br.readLine();
                }
                br.close();
                objeto.close();
                et1.setText(ficherosCompletos);

            }catch (IOException e) {

            }
        }
    }

    private boolean ArchivoNuevo (String archivos [], String NombreArchivo) {
        for(int i = 0; i < archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
            return false;
    }

    //Metodo para el boton guardar
    public void guardar (View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("ficheros.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {

        }

        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        finish();
      }
      
    }
