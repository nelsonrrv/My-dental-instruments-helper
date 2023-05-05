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


public class AddInstrumentsD extends Activity implements OnClickListener {


    private EditText add_instrumentos, add_cantidad, add_marca;
    private ListView lv_instrumentos;
    private Button btnAdd, btnCancel, btnList;
    ArrayAdapter instrumentsArrayAdapter;
    DataBaseHelperD dataBaseHelperD;
    AdaptadorInstrumentosD adapter;
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
                    instruments = new Instruments(-1, add_instrumentos.getText().toString(), add_marca.getText().toString(), Integer.parseInt(add_cantidad.getText().toString()) );


                } catch (NumberFormatException e) {
                    Toast.makeText(AddInstrumentsD.this, "Error agregando instrumental", Toast.LENGTH_SHORT).show();
                    instruments = new Instruments(-1, "error", "error", 0);
                }

                DataBaseHelperD dataBaseHelperD = new DataBaseHelperD(AddInstrumentsD.this);

                boolean success = dataBaseHelperD.addOneD(instruments);

                Toast.makeText(AddInstrumentsD.this, "Instrumento agregado=" + success, Toast.LENGTH_SHORT).show();

                add_instrumentos.setText("");
                add_marca.setText("");
                add_cantidad.setText("");
            }
        });

        ///button listeners
        btnList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseHelperD = new DataBaseHelperD(AddInstrumentsD.this);

                adapter = new AdaptadorInstrumentosD(AddInstrumentsD.this, dataBaseHelperD);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(AddInstrumentsD.this);
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

                                    Intent intent = new Intent(getApplicationContext(), ModificarInstrumentosD.class);

                                    intent.putExtra("_ID", posicionInstrument.getId());

                                    startActivity(intent);

                                } else if (l == 1) {
                                    /// Borrar pacientes
                                    dataBaseHelperD.deleteOneD(posicionInstrument);

                                    dataBaseHelperD = new DataBaseHelperD(AddInstrumentsD.this);

                                    adapter = new AdaptadorInstrumentosD(AddInstrumentsD.this, dataBaseHelperD);

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


    private void MostrarListaInstrumentos(DataBaseHelperD dataBaseHelper2) {
        instrumentsArrayAdapter = new ArrayAdapter<Instruments>(AddInstrumentsD.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryoneD());
        lv_instrumentos.setAdapter(instrumentsArrayAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}