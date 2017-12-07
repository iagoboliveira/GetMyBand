package com.project.iago.getmyband.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.iago.getmyband.helper.ArtistBandaContract;
import com.project.iago.getmyband.helper.UserContract;
import com.project.iago.getmyband.model.Artist;
import com.project.iago.getmyband.helper.ArtistContract;
import com.project.iago.getmyband.helper.MyBandHelper;
import com.project.iago.getmyband.model.ArtistBand;
import com.project.iago.getmyband.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Note on 26/11/2017.
 */

public class DaoArtist extends MyBandHelper{

    private static final String TAG = DaoArtist.class.getSimpleName();

    public DaoArtist(Context context) {
        super(context);
    }

    /**
     * This method is to create artist record
     *
     * @param artist
     */
    public void addArtist(Artist artist, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        /*int index = checkArtist(email);
        Log.i("MyBand", "Entrou no metodo addArtist() "+index);*/
        values.put(ArtistContract.COLLUMN_ARTIST_EMAIL, email);
        values.put(ArtistContract.COLLUMN_ARTIST_NAME, artist.getArtist_name());
        values.put(ArtistContract.COLLUMN_ARTIST_AGE, artist.getArtist_age());
        values.put(ArtistContract.COLLUMN_ARTIST_PHONE, artist.getArtist_phone());

        // Inserting Row
        Log.i(TAG, "addArtist() - Vai inserir.");
        db.insert(ArtistContract.TABLE_ARTIST, null, values);
        db.close();
    }

    /**
     * This method is to fetch all artist and return the list of artist records
     *
     * @return list
     */
    public List<Artist> getAllArtist() {
        // array of columns to fetch
        String[] columns = {
                ArtistContract.COLLUMN_ARTIST_ID,
                ArtistContract.COLLUMN_ARTIST_NAME,
                ArtistContract.COLLUMN_ARTIST_AGE,
                ArtistContract.COLLUMN_ARTIST_PHONE
        };
        // sorting orders
        String sortOrder =
                ArtistContract.COLLUMN_ARTIST_NAME + " ASC";
        List<Artist> artistList = new ArrayList<Artist>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the artist table
        /**
         * Here query function is used to fetch records from artist table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT artist_id,artist_name,artist_email,artist_password FROM artist ORDER BY artist_name;
         */
        Cursor cursor = db.query(ArtistContract.TABLE_ARTIST, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Artist artist = new Artist();
                artist.setArtist_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ArtistContract.COLLUMN_ARTIST_ID))));
                artist.setArtist_name(cursor.getString(cursor.getColumnIndex(ArtistContract.COLLUMN_ARTIST_NAME)));
                artist.setArtist_age(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ArtistContract.COLLUMN_ARTIST_AGE))));
                artist.setArtist_phone(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ArtistContract.COLLUMN_ARTIST_PHONE))));
                // Adding artist record to list
                artistList.add(artist);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return artist list
        return artistList;
    }

    /**
     * This method to update artist record
     *
     * @param artist
     */
    public void updateArtist(Artist artist) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ArtistContract.COLLUMN_ARTIST_EMAIL, artist.getArtist_email());
        values.put(ArtistContract.COLLUMN_ARTIST_NAME, artist.getArtist_name());
        values.put(ArtistContract.COLLUMN_ARTIST_AGE, artist.getArtist_age());
        values.put(ArtistContract.COLLUMN_ARTIST_PHONE, artist.getArtist_phone());

        // updating row
        db.update(ArtistContract.TABLE_ARTIST, values, ArtistContract.COLLUMN_ARTIST_EMAIL + " = ?",
                new String[]{String.valueOf(artist.getArtist_email())});
        db.close();
    }

    /**
     * This method is to delete artist record
     *
     * @param artist
     */
    public void deleteArtist(Artist artist) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete artist record by id
        db.delete(ArtistContract.TABLE_ARTIST, ArtistContract.COLLUMN_ARTIST_ID + " = ?",
                new String[]{String.valueOf(artist.getArtist_id())});
        db.close();
    }

    /**
     * This method to check artist exist or not
     *
     * @param email
     * @return true/false
     */
    public int checkArtist(String email) {

        Log.i(TAG, "Entrou no metodo checkArtist() "+email);
        // array of columns to fetch
        String[] columns = {
                ArtistContract.COLLUMN_ARTIST_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = ArtistContract.COLLUMN_ARTIST_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query artist table with condition
        /**
         * Here query function is used to fetch records from artist table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT artist_id FROM artist WHERE artist_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(ArtistContract.TABLE_ARTIST, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

/*        if (cursorCount > 0) {
            if (cursor.moveToLast()) {
               Artist artist = new Artist();
               artist.setArtist_id(cursor.getColumnIndex(UserContract.COLLUMN_USER_ID));

               Log.i("MyBand", "checkArtist() Retornando o ID: "+artist.getArtist_id());
               return artist.getArtist_id();
            }
            cursor.close();
            db.close();
        }*/
        Log.i(TAG, "checkArtist() Retornando count()"+cursorCount);
        return cursorCount;
    }

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> HERE START BANDS COVER DATABASE METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
// -----------------------------------------------------------------------------------------------------------------------
    /**
     * This method is to create artistBand record
     *
     * @param nomeBanda
     * @param email
     */
    public void addArtistBand(String nomeBanda, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        /*int index = checkArtist(email);
        Log.i("MyBand", "Entrou no metodo addArtist() "+index);*/
        values.put(ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME, nomeBanda);
        values.put(ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL, email);

        // Inserting Row
        Log.i(TAG, "addArtistBand() - Vai inserir.");
        db.insert(ArtistBandaContract.TABLE_ARTIST_BAND, null, values);
        db.close();
    }

    /**
     * This method is to fetch all artist and return the list of artistBands records
     *
     * @return list
     */
    public List<ArtistBand> getAllArtistBands(String email) {
        // array of columns to fetch
        String[] columns = {
                ArtistBandaContract.COLLUMN_ARTIST_BAND_ID,
                ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME,
                ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL
        };
        // selection criteria
        String selection = ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // sorting orders
        String sortOrder =
                ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME + " ASC";
        List<ArtistBand> artistList = new ArrayList<ArtistBand>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the artist table
        /**
         * Here query function is used to fetch records from artist table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT artist_id,artist_name,artist_email,artist_password FROM artist ORDER BY artist_name;
         */
        Cursor cursor = db.query(ArtistBandaContract.TABLE_ARTIST_BAND, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ArtistBand artistBand = new ArtistBand();
                artistBand.setArtist_band_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ArtistBandaContract.COLLUMN_ARTIST_BAND_ID))));
                artistBand.setArtist_band_name(cursor.getString(cursor.getColumnIndex(ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME)));
                artistBand.setArtist_band_email(cursor.getString(cursor.getColumnIndex(ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL)));
                // Adding artist record to list
                artistList.add(artistBand);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return artist list
        return artistList;
    }


    /**
     * This method to check artist exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkHaveBand(String email) {

        Log.i(TAG, "Entrou no metodo checkHaveBand() "+email);
        // array of columns to fetch
        String[] columns = {
                ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(ArtistBandaContract.TABLE_ARTIST_BAND, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {
            Log.i(TAG, "checkHaveBand() Retornando Verdadeiro.");
            return true;
        }
        Log.i(TAG, "checkHaveBand() Retornando falso.");
        return false;
    }
}
