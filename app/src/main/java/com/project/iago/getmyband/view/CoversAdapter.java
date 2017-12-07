package com.project.iago.getmyband.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.fragments.ListBandsFragment;
import com.project.iago.getmyband.model.ArtistBand;
import com.project.iago.getmyband.model.Banda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Note on 12/11/2017.
 */

public class CoversAdapter extends ArrayAdapter {

    private static final String TAG = CoversAdapter.class.getSimpleName();
    public CoversAdapter(@NonNull Context context,@NonNull List<ArtistBand> objects) {
        //bandas.addAll(objects);
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArtistBand banda = (ArtistBand) getItem(position);

        int count = getCount();
       // Log.i(TAG, "getView () - Inicio metodo"+banda.getArtist_band_name());

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list_cover, null);
       //     Log.i(TAG, "getView () - Nulo");
        }

        Log.i(TAG, "getView () - nome banda"+banda.getArtist_band_name());
        TextView txtNome = (TextView)convertView.findViewById(R.id.txtNomeBandCoverUnit);
        txtNome.setText(banda.getArtist_band_name());
        txtNome.setTextColor( Color.CYAN);

       // Log.i(TAG, "getView () - Nao Nulo");
        return convertView;
    }


}
