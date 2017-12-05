package com.project.iago.getmyband.fragments;

import android.content.Context;
import android.net.Uri;
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

import com.google.gson.Gson;
import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.model.Artist;
import com.project.iago.getmyband.model.json.GetAll;
import com.project.iago.getmyband.model.json.JsonVagalume;
import com.project.iago.getmyband.model.json.Response;

import java.io.IOException;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListBandsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = ListBandsFragment.class.getSimpleName();
    private final String ARG_EMAIL = "ARG_EMAIL";
    private final String ENDPOINT_URL = "http://api.vagalume.com.br/";
    private final Fragment fragment = ListBandsFragment.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutAge;
    private TextInputLayout textInputLayoutPhone;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextAge;
    private TextInputEditText textInputEditTextPhone;

    private AppCompatButton appCompatButtonUpdate;

    private InputValidation inputValidation;
    private MyBandHelper databaseHelper;
    private DaoArtist daoArtist;
    private Artist artist;
    private String artist_email;

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

        return view;

    }

    private void initViews(View view) {
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedHomeScrollView);

        textInputLayoutName = (TextInputLayout) view.findViewById(R.id.txtHomeName);
        textInputLayoutAge = (TextInputLayout) view.findViewById(R.id.txtHomeAge);
        textInputLayoutPhone = (TextInputLayout) view.findViewById(R.id.txtHomePhone);

        textInputEditTextName = (TextInputEditText) view.findViewById(R.id.inputHomeName);
        textInputEditTextAge = (TextInputEditText) view.findViewById(R.id.inputHomeAge);
        textInputEditTextPhone = (TextInputEditText) view.findViewById(R.id.inputHomePhone);

        appCompatButtonUpdate = (AppCompatButton) view.findViewById(R.id.btnAdd);
    }

    private void initListeners(View view) {
        appCompatButtonUpdate.setOnClickListener(this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(fragment.getContext());
        databaseHelper = new MyBandHelper(fragment.getContext());
        daoArtist = new DaoArtist(fragment.getContext());
        artist = new Artist();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                new LongOperation().execute("");
                break;
        }

    }
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            callAPI();
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

    public void callAPI(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GetAll response = retrofit.create(GetAll.class);

            Call<JsonVagalume> call2 = response.getBands("redhot", 5);

            retrofit2.Response<JsonVagalume> retorno = call2.execute();
            JsonVagalume r  = retorno.body();
            Log.i("MyBand", TAG+" () - O resultado do jeison ->"+r);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }*/
}
