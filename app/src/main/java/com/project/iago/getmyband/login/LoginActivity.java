package com.project.iago.getmyband.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.project.iago.getmyband.dao.DaoUser;
import com.project.iago.getmyband.helper.InputValidation;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.R;
import com.project.iago.getmyband.view.MainUsersActivity;
import com.project.iago.getmyband.view.NavigationActivity;
import com.project.iago.getmyband.view.PagesActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private MyBandHelper databaseHelper;
    private DaoUser daoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.txtLogin_email);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.txtLogin_pass);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.inputLogin_email);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.inputLogin_pass);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.btnLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.txtLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new MyBandHelper(activity);
        inputValidation = new InputValidation(activity);
        daoUser = new DaoUser(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                verifyFromSQLite();
                break;
            case R.id.txtLoginLink:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (daoUser.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            String email_user = textInputEditTextEmail.getText().toString().trim();
            Intent accountsIntent = new Intent(activity, PagesActivity.class);
            accountsIntent.putExtra("EMAIL", email_user);
            emptyInputEditText();

            Log.i("MyBand", "verifyFromSQLite() - Vai iniciar a Activity MainUsersActivity " +
                    "email: "+email_user);
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}