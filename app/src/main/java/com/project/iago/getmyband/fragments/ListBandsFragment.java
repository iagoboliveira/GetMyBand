package com.project.iago.getmyband.fragments;

import android.content.Context;
import android.net.Uri;
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

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.model.Artist;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListBandsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListBandsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBandsFragment extends Fragment {
    private static final String TAG = ListBandsFragment.class.getSimpleName();
    private final String ARG_EMAIL = "ARG_EMAIL";
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

    private OnFragmentInteractionListener mListener;

    public ListBandsFragment() {
        Log.i("MyBand", TAG+" () - Construct() ");
    }

    // TODO: Rename and change types and number of parameters
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

        appCompatButtonUpdate = (AppCompatButton) view.findViewById(R.id.btnUpdate);
    }

    private void initListeners(View view) {
        appCompatButtonUpdate.setOnClickListener((View.OnClickListener) this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(fragment.getContext());
        databaseHelper = new MyBandHelper(fragment.getContext());
        daoArtist = new DaoArtist(fragment.getContext());
        artist = new Artist();

    }

    public void onButtonPressed(Uri uri) {
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
