package com.project.iago.getmyband.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.model.Banda;

import java.util.List;

/**
 * Created by Iago Note on 12/11/2017.
 */

public class BandaAdapter extends ArrayAdapter {

    public BandaAdapter(@NonNull Context context,@NonNull List<Banda> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Banda banda = (Banda) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_da_lista, parent);
        }

        TextView txtNome = (TextView)convertView.findViewById(R.id.txtNome);
        txtNome.setText(banda.nome);
        txtNome.setTextColor( Color.BLUE);
        TextView txtGenero = (TextView)convertView.findViewById(R.id.txtGenero);
        txtGenero.setText(banda.genero);

        return convertView;
    }
}
