package com.example.mydentalinstrumentshelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(button.getContext());
                CharSequence[] items = new CharSequence[3];
                items[0] = "Agregar instrumental";
                items[1] = "Ver todos los instrumentos";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Agregar instrumental
                                    Intent intent = new Intent(getApplication(), AddInstruments.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Ver lista de instrumental
                                    Intent intent = new Intent(getApplication(), VerInstrumentosC.class);
                                    startActivity(intent);
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

    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(button.getContext());
            CharSequence[] items = new CharSequence[3];
            items[0] = "Agregar instrumental";
            items[1] = "Ver todos los instrumentos";
            items[2] = "Cancelar";

            builder.setTitle("Seleccione una opción")
                    .setItems(items, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int l) {

                            if (l == 0) {
                                /// Agregar instrumental
                                Intent intent = new Intent(getApplication(), AddInstrumentsE.class);
                                startActivity(intent);
                            } else if (l == 1) {
                                /// Ver lista de instrumental
                                Intent intent = new Intent(getApplication(), VerInstrumentosE.class);
                                startActivity(intent);
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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(button3.getContext());
                CharSequence[] items = new CharSequence[3];
                items[0] = "Agregar instrumental";
                items[1] = "Ver todos los instrumentos";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Agregar instrumental
                                    Intent intent = new Intent(getApplication(), AddInstrumentsR.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Ver lista de instrumental
                                    Intent intent = new Intent(getApplication(), VerInstrumentosR.class);
                                    startActivity(intent);
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

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(button4.getContext());
                CharSequence[] items = new CharSequence[3];
                items[0] = "Agregar instrumental";
                items[1] = "Ver todos los instrumentos";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Agregar instrumental
                                    Intent intent = new Intent(getApplication(), AddInstrumentsP.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Ver lista de instrumental
                                    Intent intent = new Intent(getApplication(), VerInstrumentosP.class);
                                    startActivity(intent);
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

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(button5.getContext());
                CharSequence[] items = new CharSequence[3];
                items[0] = "Agregar instrumental";
                items[1] = "Ver todos los instrumentos";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Agregar instrumental
                                    Intent intent = new Intent(getApplication(), AddInstrumentsD.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Ver lista de instrumental
                                    Intent intent = new Intent(getApplication(), VerInstrumentosD.class);
                                    startActivity(intent);
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