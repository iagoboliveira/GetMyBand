package com.project.iago.getmyband.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import android.widget.Toast;

import com.project.iago.getmyband.MainActivity;
import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.model.Artist;
import com.project.iago.getmyband.model.ArtistBand;
import com.project.iago.getmyband.model.Banda;
import com.project.iago.getmyband.model.json.GetAll;
import com.project.iago.getmyband.model.json.LastFM_JSON;
import com.project.iago.getmyband.view.BandaAdapter;

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

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListBandsFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    private static final String TAG = ListBandsFragment.class.getSimpleName();
    private final String ARG_EMAIL = "ARG_EMAIL";
    //private final String ENDPOINT_URL = "https://itunes.apple.com/";
    private final String ENDPOINT_URL = "https://api.vagalume.com.br/search.art?q=";
    //private final String ENDPOINT_URL = "https://ws.audioscrobbler.com/2.0/?method=artist.search&artist=skank&api_key=eb5719fe02d4aee4bfe66329f6e7a643&format=json&limit=1";
    private final Fragment fragment = ListBandsFragment.this;

    private NestedScrollView nestedScrollView;

    private SearchView editsearch;

    private AppCompatButton appCompatButtonUpdate;

    private ListView listViewBandas;
    private ListView listViewCovers;

    private InputValidation inputValidation;
    private MyBandHelper databaseHelper;
    private Artist artist;
    private String artist_email;
    private JSONArray jsonArray;
    private List<Banda> bandas;
    private BandaAdapter adapter;
    private DaoArtist daoArtist;

    //private OnFragmentInteractionListener mListener;

    public ListBandsFragment()
    {
        Log.i("MyBand", TAG+" () - Construct() ");
    }

    public static ListBandsFragment newInstance(String param1, String param2) {
        ListBandsFragment fragment = new ListBandsFragment();
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

        View view = inflater.inflate(R.layout.fragment_list_bands, container, false);

        initViews(view);
        initListeners(view);
        initObjects();
        returnListCovers(artist_email);

        return view;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        new LongOperation().execute(newText+"&limit=5");

        ArrayAdapter<Banda> adapterBandas = new ArrayAdapter<Banda>(
                fragment.getContext(), R.layout.item_da_lista, bandas);
        adapter = new BandaAdapter(fragment.getContext(), bandas);
        //Log.i("MyBand", TAG+" () ->>>>>>>>>>>>>>>>>>>>>>> TAMANHO DO ARRAY <<<<<<<<<<<<<<<<<<<<<<<< ->"+bandas.size());
        listViewBandas.setAdapter(adapter);

        // Clicando no item da lista deverá executar o comando abaixo
        listViewBandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Banda banda = bandas.get(i);
                Toast.makeText(fragment.getContext(), "clicou", Toast.LENGTH_SHORT).show();
    /*
                    Intent it = new Intent(MainActivity.this, DetalheBandaActivity.class);

                    it.putExtra(DetalheBandaActivity.BANDA_SELECIONADA, banda);
                    startActivity(it);*/
            }
        });
        return false;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //Log.i("MyBand", TAG+" () -getJSON->"+getJSON(ENDPOINT_URL));
            String ENDPOINT_FINAL = ENDPOINT_URL+params[0];
            //Log.i("MyBand", TAG+" () -ENDPOINT_FINAL ->"+ENDPOINT_FINAL);
            JSONObject json = null;
            try {
                json = new JSONObject(getJSON(ENDPOINT_FINAL));
                JSONObject json2 = json.getJSONObject("response");
               // Log.i("MyBand", TAG+" () -json2->"+json2);
                jsonArray = json2.getJSONArray("docs");
               // Log.i("MyBand", TAG+" () -getJSONArray->"+jsonArray);

                String name;

                //List<Banda> bandas =  new ArrayList<Banda>();
                //bandas.add(new Banda("Ponto de Equilibrio", "Reggae"));
                bandas.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    name = row.getString("band");
                    Log.i("MyBand", TAG+" () -name Banda->"+name);
                    bandas.add(new Banda(name, "Genero Comum"));
                   // Log.i("MyBand", TAG+" () -Adicionou");
                }

                } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public void returnListCovers(String email){

        Log.i("returnListCovers", TAG+" () - Inicio Método.");
        List<ArtistBand> listCovers = new ArrayList<ArtistBand>();
        ArtistBand covers = new ArtistBand();
        covers.setArtist_band_name("Testando cover");
        listCovers.add(covers);
        //listViewCovers

        ArrayAdapter<ArtistBand> adapterCovers = new ArrayAdapter<ArtistBand>(
                fragment.getContext(), R.layout.item_list_cover, listCovers);


        listViewCovers.setAdapter(adapterCovers);

    }

    private void initViews(View view) {
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedHomeScrollViewCovers);
       // appCompatButtonUpdate = (AppCompatButton) view.findViewById(R.id.btnAdd);
        editsearch = (SearchView)view.findViewById(R.id.searchBand);
        listViewBandas = view.findViewById(R.id.listaBandasSearch);
        listViewCovers = view.findViewById(R.id.myListCovers);

    }

    private void initListeners(View view) {
        //appCompatButtonUpdate.setOnClickListener(this);
        editsearch.setOnQueryTextListener(this);

    }
    private void initObjects() {
        nestedScrollView = new NestedScrollView(fragment.getContext());
        inputValidation = new InputValidation(fragment.getContext());
        databaseHelper = new MyBandHelper(fragment.getContext());
        daoArtist = new DaoArtist(fragment.getContext());
        bandas =  new ArrayList<Banda>();
        adapter = new BandaAdapter(fragment.getContext(), bandas);
        artist = new Artist();

    }

    @Override
    public void onClick(View v) {
/*        switch (v.getId()) {
            case R.id.btnAdd:

                break;
        }*/

    }


/*    public void callAPI(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GetAll response = retrofit.create(GetAll.class);

            //Call<LastFM_JSON> call2 = response.getBands("skank", "eb5719fe02d4aee4bfe66329f6e7a643","json");
            Call<LastFM_JSON> call2 = response.getBands();

            retrofit2.Response<LastFM_JSON> retorno = call2.execute();
            LastFM_JSON r  = retorno.body();
            Log.i("MyBand", TAG+" () - O resultado do json ->"+r);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
