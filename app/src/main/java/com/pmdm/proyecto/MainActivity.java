package com.pmdm.proyecto;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        // Datos para el AutoCompleteTextView
        String[] datos = {"Desarrollo de Aplicaciones Multiplataforma", "Desarrollo de Aplicaciones Web"};

        // Crear el ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, datos);

        // Vincular el AutoCompleteTextView con el ArrayAdapter
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter1);

        // Manejar la selección de elementos en el AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = (String) parent.getItemAtPosition(position);
                // Realizar acciones con el elemento seleccionado
                Toast.makeText(getApplicationContext(), "Seleccionaste: " + seleccion, Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.modulos, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}