package com.example.mydentalinstrumentshelper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


public class AddInstrumentsR extends Activity implements OnClickListener {


    private EditText add_instrumentos, add_cantidad, add_marca;
    private ListView lv_instrumentos;
    private Button btnAdd, btnCancel, btnList;
    ArrayAdapter instrumentsArrayAdapter;
    DataBaseHelperR dataBaseHelperR;
    AdaptadorInstrumentosR adapter;
    MisInstrumentos misInstrumentos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_instrumentos);

        add_instrumentos = (EditText) findViewById(R.id.add_instrumentos);
        add_cantidad = (EditText) findViewById(R.id.add_cantidad);
        add_marca = (EditText) findViewById(R.id.add_marca);
        btnAdd = (Button) findViewById(R.id.AddInstrumento);
        btnList = (Button) findViewById(R.id.verLista);
        btnCancel = (Button) findViewById(R.id.back);
        lv_instrumentos = (ListView) findViewById(R.id.lv_instrumentos);


        ///button listeners

        btnAdd.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Instruments instruments;
                try {
                    instruments = new Instruments(-1, add_instrumentos.getText().toString(), add_marca.getText().toString(), Integer.parseInt(add_cantidad.getText().toString()));


                } catch (NumberFormatException e) {
                    Toast.makeText(AddInstrumentsR.this, "Error agregando instrumental", Toast.LENGTH_SHORT).show();
                    instruments = new Instruments(-1, "error", "error", 0);
                }

                DataBaseHelperR dataBaseHelperR = new DataBaseHelperR(AddInstrumentsR.this);

                boolean success = dataBaseHelperR.addOneR(instruments);

                Toast.makeText(AddInstrumentsR.this, "Instrumento agregado=" + success, Toast.LENGTH_SHORT).show();

                add_instrumentos.setText("");
                add_marca.setText("");
                add_cantidad.setText("");
            }
        });

        ///button listeners
        btnList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseHelperR = new DataBaseHelperR(AddInstrumentsR.this);

                adapter = new AdaptadorInstrumentosR(AddInstrumentsR.this, dataBaseHelperR);

                lv_instrumentos.setAdapter(adapter);


                Toast.makeText(getApplicationContext(), "Lista desplegada", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });


        lv_instrumentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Instruments posicionInstrument = (Instruments) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddInstrumentsR.this);
                CharSequence[] items = new CharSequence[3];
                items[0] = "Modificar instrumental";
                items[1] = "Borrar instrumental";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Modificar pacientes

                                    Intent intent = new Intent(getApplicationContext(), ModificarInstrumentosR.class);

                                    intent.putExtra("_ID", posicionInstrument.getId());

                                    startActivity(intent);

                                } else if (l == 1) {
                                    /// Borrar pacientes
                                    dataBaseHelperR.deleteOneR(posicionInstrument);

                                    dataBaseHelperR = new DataBaseHelperR(AddInstrumentsR.this);

                                    adapter = new AdaptadorInstrumentosR(AddInstrumentsR.this, dataBaseHelperR);

                                    lv_instrumentos.setAdapter(adapter);

                                    Toast.makeText(getApplicationContext(), "Instrumental borrado=" + posicionInstrument.toString(), Toast.LENGTH_SHORT).show();

                                } else {
                                    //// Cancelar
                                    return;
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }


    private void MostrarListaInstrumentos(DataBaseHelperR dataBaseHelper2) {
        instrumentsArrayAdapter = new ArrayAdapter<Instruments>(AddInstrumentsR.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryoneR());
        lv_instrumentos.setAdapter(instrumentsArrayAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}