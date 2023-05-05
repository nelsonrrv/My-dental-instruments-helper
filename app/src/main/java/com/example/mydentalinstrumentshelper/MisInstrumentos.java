package com.example.mydentalinstrumentshelper;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class MisInstrumentos {

    DataBaseHelperC dataBaseHelperC;

    List<Instruments> miListaInstrumentos;


    public MisInstrumentos(List<Instruments> miListaInstrumentos) {
        this.miListaInstrumentos = miListaInstrumentos;
    }

    public MisInstrumentos(){
        this.miListaInstrumentos = new ArrayList<>();


    }

    public List<Instruments> getMiListaInstrumentos() {
        return miListaInstrumentos;
    }

    public void setMiListaInstrumentos(List<Instruments> miListaInstrumentos) {
        this.miListaInstrumentos = miListaInstrumentos;
    }
}
