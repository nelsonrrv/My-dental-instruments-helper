package com.example.mydentalinstrumentshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarInstrumentosE extends Activity {

    public EditText mod_instruments, mod_cantidad, mod_marca;
    public TextView id_instrument;
    private Button btn, btn1;
    public String id;
    Instruments instruments;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_instrumentos);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");

        id_instrument = (TextView) findViewById(R.id.id_instrument);
        mod_instruments = (EditText) findViewById(R.id.mod_instruments);
        mod_cantidad = (EditText) findViewById(R.id.mod_cantidad);
        mod_marca = (EditText) findViewById(R.id.mod_marca);
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);

        id_instrument.setText(id);

        btn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    instruments = new Instruments( Integer.parseInt(id_instrument.getText().toString()), mod_instruments.getText().toString(), mod_marca.getText().toString(), Integer.parseInt(mod_cantidad.getText().toString()));


                } catch (NumberFormatException e) {
                    Toast.makeText(ModificarInstrumentosE.this, "Error modificando instrumento", Toast.LENGTH_SHORT).show();
                    instruments = new Instruments( -1, "error", "error", 0);
                }

                DataBaseHelperE dataBaseHelperE = new DataBaseHelperE(ModificarInstrumentosE.this);

                boolean success = dataBaseHelperE.modificarOneE(instruments);

                Toast.makeText( ModificarInstrumentosE.this, "Instrumento modificado="+success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(), AddInstruments.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AddInstrumentsE.class);
                startActivity(intent);

            }
        });

    }
}