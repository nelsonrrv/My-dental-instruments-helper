package com.example.mydentalinstrumentshelper;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class VerInstrumentosP extends AppCompatActivity {

    private ListView lv_todos;
    DataBaseHelperP dataBaseHelperP;
    AdaptadorInstrumentosP adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lista_instrumentosp);

        lv_todos = (ListView) findViewById(R.id.lv_todosp);

        dataBaseHelperP = new DataBaseHelperP(VerInstrumentosP.this);

        adapter = new AdaptadorInstrumentosP ( VerInstrumentosP.this, dataBaseHelperP);

        lv_todos.setAdapter(adapter);

        lv_todos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Instruments posicionInstrument = (Instruments) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(VerInstrumentosP.this);
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

                                    Intent intent = new Intent(getApplicationContext(), ModificarInstrumentosP.class);

                                    intent.putExtra("_ID", posicionInstrument.getId());

                                    startActivity(intent);

                                } else if (l == 1) {
                                    /// Borrar pacientes
                                    dataBaseHelperP.deleteOneP(posicionInstrument);

                                    dataBaseHelperP = new DataBaseHelperP(VerInstrumentosP.this);

                                    adapter = new AdaptadorInstrumentosP(VerInstrumentosP.this, dataBaseHelperP);

                                    lv_todos.setAdapter(adapter);

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





}