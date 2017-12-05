package com.project.iago.getmyband.helper;

import android.provider.BaseColumns;

/**
 * Created by Iago Note on 26/11/2017.
 */

public interface UserContract extends BaseColumns {

    String TABLE_USER           = "users";

    String COLLUMN_USER_ID          = "user_id";
    String COLLUMN_USER_LOGIN       = "user_login";
    String COLLUMN_USER_EMAIL       = "user_email";
    String COLLUMN_USER_PASWORD     = "user_password";



}
