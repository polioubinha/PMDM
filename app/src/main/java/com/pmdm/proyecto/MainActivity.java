package com.pmdm.proyecto;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        // Configurar AutoCompleteTextView
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                obtenerSugerencias()
        );
        autoCompleteTextView.setAdapter(autoCompleteAdapter);

        // Configurar Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Configurar el menú contextual
        View rootView = findViewById(android.R.id.content);
        registerForContextMenu(rootView);

        rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openContextMenu(v);
                return true;
            }
        });

        // Configurar el clic del botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarAccion();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_show_info) {
            mostrarInformacion();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private List<String> obtenerSugerencias() {
        List<String> sugerencias = new ArrayList<>();
        sugerencias.add("Android 12");
        sugerencias.add("iOS 15");
        sugerencias.add("Windows 11");
        sugerencias.add("Linux Ubuntu 22.04");
        return sugerencias;
    }


    private void realizarAccion() {
        // Lógica para realizar la acción cuando se presiona el botón
        // Obtenemos el texto del AutoCompleteTextView y Spinner aquí
        String textoAutoComplete = ((AutoCompleteTextView) findViewById(R.id.autoCompleteTextView)).getText().toString();
        String seleccionSpinner = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();

        // Obtenemos el resultado de las opciones anteriores
        String resultado = "Texto: " + textoAutoComplete + ", Seleccion: " + seleccionSpinner;

        // Mostrar el resultado en el TextView
        textView.setText(resultado);
    }

    private void mostrarInformacion() {
        // Crear y configurar el cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage("Ejemplo de menu contextual siuuuu");

        // Configurar el botón "Aceptar" del cuadro de diálogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerrar el cuadro de diálogo al hacer clic en "Aceptar"
                dialog.dismiss();
            }
        });

        // Mostrar el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}