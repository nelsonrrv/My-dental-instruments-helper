package com.example.mydentalinstrumentshelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorInstrumentosR extends BaseAdapter {

    Activity mActivity;
    DataBaseHelperR dataBaseHelperR;

    public AdaptadorInstrumentosR(Activity mActivity, DataBaseHelperR dataBaseHelperR) {
        this.mActivity = mActivity;
        this.dataBaseHelperR = dataBaseHelperR;
    }

    @Override
    public int getCount() {
        return dataBaseHelperR.getEveryoneR().size();
    }

    @Override
    public Instruments getItem(int position) {

        return dataBaseHelperR.getEveryoneR().get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View onePacienteLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePacienteLine = inflater.inflate(R.layout.instrumentos_una_linea, parent , false);

        TextView nomI = onePacienteLine.findViewById(R.id.nomIns);
        TextView cantidadI = onePacienteLine.findViewById(R.id.cantidadIns);
        TextView marcaI = onePacienteLine.findViewById(R.id.marcaIns);


        Instruments p = this.getItem(position);

        nomI.setText(p.getNombre());
        cantidadI.setText(Integer.toString(p.getCantidad()));
        marcaI.setText(p.getMarca());

        return onePacienteLine;
    }
}
