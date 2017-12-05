package com.project.iago.getmyband.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;
import com.project.iago.getmyband.dao.DaoUser;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.login.RegisterActivity;
import com.project.iago.getmyband.model.Artist;
import com.project.iago.getmyband.model.User;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = HomeFragment.class.getSimpleName();
    private final String ARG_EMAIL = "ARG_EMAIL";
    private final Fragment fragment = HomeFragment.this;

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

    public HomeFragment() {
        Log.i("MyBand", TAG+" () - Construct() ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b=getArguments();
        artist_email = b.getString(ARG_EMAIL);
        Log.i("MyBand", TAG+" () - onCreateView chegou foi->"+artist_email);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        initListeners(view);
        initObjects();
        return view;
    }

    /**
     * This method is to initialize views
     */
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

    /**
     * This method is to initialize listeners
     */
    private void initListeners(View view) {
        appCompatButtonUpdate.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(fragment.getContext());
        databaseHelper = new MyBandHelper(fragment.getContext());
        daoArtist = new DaoArtist(fragment.getContext());
        artist = new Artist();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                postDataToSQLite();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAge, textInputLayoutAge, getString(R.string.error_message_age))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone, getString(R.string.error_message_phone))) {
            return;
        }

        int returnValidate = daoArtist.checkArtist(artist_email);
        Log.i("MyBand", TAG+" () - postDataToSQLite() retorno validação ->"+returnValidate);
        if (returnValidate == 0) {

            artist.setArtist_name(textInputEditTextName.getText().toString().trim());
            artist.setArtist_age(Integer.parseInt(textInputEditTextAge.getText().toString().trim()));
            artist.setArtist_phone(Integer.parseInt(textInputEditTextPhone.getText().toString().trim()));

            daoArtist.addArtist(artist, artist_email);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_update), Snackbar.LENGTH_LONG).show();
            //emptyInputEditText();


        } else if(returnValidate == 1){
            Log.i("MyBand", TAG+" () - postDataToSQLite() valida 1 ->"+returnValidate);

            artist.setArtist_name(textInputEditTextName.getText().toString().trim());
            artist.setArtist_age(Integer.parseInt(textInputEditTextAge.getText().toString().trim()));
            artist.setArtist_phone(Integer.parseInt(textInputEditTextPhone.getText().toString().trim()));
            artist.setArtist_email(artist_email);

            daoArtist.updateArtist(artist);
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.success_update), Snackbar.LENGTH_LONG).show();
        }else{
            Log.i("MyBand", TAG+" () - postDataToSQLite() validate else ->"+returnValidate);
            Snackbar.make(nestedScrollView, getString(R.string.error_fatal), Snackbar.LENGTH_LONG).show();

        }


    }

    /**
     * This method is to empty all input edit text
     */
/*    private void emptyInputEditText() {
        textInputEditTextLogin.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }*/
}