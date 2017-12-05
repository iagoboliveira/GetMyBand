package com.project.iago.getmyband.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.helper.UserContract;
import com.project.iago.getmyband.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Note on 02/12/2017.
 */

public class DaoUser extends MyBandHelper {

    public DaoUser(Context context) {
        super(context);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.COLLUMN_USER_LOGIN, user.getLogin());
        values.put(UserContract.COLLUMN_USER_EMAIL, user.getEmail());
        values.put(UserContract.COLLUMN_USER_PASWORD, user.getPassword());

        // Inserting Row
        db.insert(UserContract.TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                UserContract.COLLUMN_USER_ID,
                UserContract.COLLUMN_USER_LOGIN,
                UserContract.COLLUMN_USER_EMAIL,
                UserContract.COLLUMN_USER_PASWORD
        };
        // sorting orders
        String sortOrder =
                UserContract.COLLUMN_USER_LOGIN + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(UserContract.TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserContract.COLLUMN_USER_ID))));
                user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.COLLUMN_USER_LOGIN)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.COLLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.COLLUMN_USER_PASWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.COLLUMN_USER_LOGIN, user.getLogin());
        values.put(UserContract.COLLUMN_USER_EMAIL, user.getEmail());
        values.put(UserContract.COLLUMN_USER_PASWORD, user.getPassword());

        // updating row
        db.update(UserContract.TABLE_USER, values, UserContract.COLLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(UserContract.TABLE_USER, UserContract.COLLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        Log.i("MyBand", "Entrou no metodo checkUser() "+email);
        // array of columns to fetch
        String[] columns = {
                UserContract.COLLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = UserContract.COLLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(UserContract.TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        Log.i("MyBand", "Entrou no metodo checkUser(String email, String password) " +
                "email: "+email+" pass:"+password);
        // array of columns to fetch
        String[] columns = {
                UserContract.COLLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserContract.COLLUMN_USER_EMAIL + " = ?" + " AND " + UserContract.COLLUMN_USER_PASWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(UserContract.TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
