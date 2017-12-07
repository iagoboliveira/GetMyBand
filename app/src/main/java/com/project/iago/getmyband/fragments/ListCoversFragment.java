package com.project.iago.getmyband.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.model.Artist;
import com.project.iago.getmyband.model.ArtistBand;
import com.project.iago.getmyband.model.Banda;
import com.project.iago.getmyband.view.BandaAdapter;
import com.project.iago.getmyband.view.CoversAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ListCoversFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = ListCoversFragment.class.getSimpleName();
    private final Fragment fragment = ListCoversFragment.this;
    private final String ARG_EMAIL = "ARG_EMAIL";

    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonUpdate;

    private ListView listViewCovers;

    private InputValidation inputValidation;
    private MyBandHelper databaseHelper;
    private Artist artist;
    private String artist_email;
    private JSONArray jsonArray;
    private TextView textViewBanda;
    private List<Banda> bandas;
    private CoversAdapter covers;
    private ArtistBand artistBand;
    private List<ArtistBand> listCovers;
    private DaoArtist daoArtist;

    //private OnFragmentInteractionListener mListener;

    public ListCoversFragment()
    {
        Log.i("MyBand", TAG+" () - Construct() ");
    }

    public static ListCoversFragment newInstance(String param1, String param2) {
        ListCoversFragment fragment = new ListCoversFragment();
        Bundle args = new Bundle();/*
        args.putString(param1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b=getArguments();
        artist_email = b.getString(ARG_EMAIL);
        Log.i("MyBand", TAG+" () - Email do camarada->"+artist_email);
        View view;
        view = inflater.inflate(R.layout.fragment_list_covers, container, false);

        initViews(view);
        initListeners(view);
        initObjects();
        returnListCovers(artist_email);
        return view;

    }

    public void returnListCovers(String email){

        Log.i("MyBand", TAG+"returnListCovers () - Inicio metodo");

        listCovers.addAll(daoArtist.getAllArtistBands(email));

        ArrayList arr = new ArrayList();
        int count = 0;
        for( ArtistBand numero : listCovers )
        {
            Log.i("MyBand", TAG+" >>>> "+numero.getArtist_band_name());
            arr.add(count, numero.getArtist_band_name());
            count++;
        }
        ArrayAdapter<ArtistBand> adapterCovers = new ArrayAdapter<ArtistBand>(
                fragment.getContext(), R.layout.item_list_cover, listCovers);

        Log.i("MyBand", TAG+"returnListCovers () - New adapter");
        /*artistBand.setArtist_band_name("Banda Cover TEste");
        artistBand.setArtist_band_email(email);
        artistBand.setArtist_band_id(2);*/

        covers.clear();
        covers.addAll(listCovers);
        //Log.i("MyBand", TAG+" () ->>>>>>>>>>>>>>>>>>>>>>> TAMANHO DO ARRAY <<<<<<<<<<<<<<<<<<<<<<<< ->"+bandas.size());
        Log.i("MyBand", TAG+"returnListCovers () - set adapter");
        listViewCovers.setAdapter(adapterCovers);

    }

    private void initViews(View view) {
      // nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollViewMyCoverss);
       // appCompatButtonUpdate = (AppCompatButton) view.findViewById(R.id.btnAdd);
        listViewCovers = view.findViewById(R.id.myListCovers);
        artistBand = new ArtistBand();
        listCovers = new ArrayList<ArtistBand>();
        covers =  new CoversAdapter(fragment.getContext(), listCovers);
        textViewBanda = view.findViewById(R.id.txtNomeBandCoverUnit);

    }

    private void initListeners(View view) {

    }
    private void initObjects() {
        nestedScrollView = new NestedScrollView(fragment.getContext());
        inputValidation = new InputValidation(fragment.getContext());
        databaseHelper = new MyBandHelper(fragment.getContext());
        daoArtist = new DaoArtist(fragment.getContext());
        //bandas =  new ArrayList<Banda>();
        covers = new CoversAdapter(fragment.getContext(), listCovers);
        //listCovers = new ArrayList<ArtistBand>();
        //covers =  new CoversAdapter(fragment.getContext(), listCovers);
        artist = new Artist();
    }

    @Override
    public void onClick(View v) {
/*        switch (v.getId()) {
            case R.id.btnAdd:

                break;
        }*/

    }
}
