package com.project.iago.getmyband.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Iago Note on 21/11/2017.
 */

public class MyBandHelper extends SQLiteOpenHelper{

    // INDICA QUANDO VAI PRECISAR DE ATUALIZACAO NO BANCO, OU SEJA, HOUVE ALTERAÇÃO NA MODELAGEM DE DADOS.
    // SE ALTERAR PARA 2 POR EXEMPLO, AO EXECUTAR O APP IRÁ ATUALIZAR O BANCO.
    private static final int DB_VERSAO = 2;
    private static final String DB_NAME = "myband_db";

    private static final String CREATE_TABLE_ARTIST =
                "CREATE TABLE "+ ArtistContract.TABLE_ARTIST +"("
            + ArtistContract.COLLUMN_ARTIST_ID          + " integer primary key autoincrement, "
            + ArtistContract.COLLUMN_ARTIST_NAME        + " text not null, "
            + ArtistContract.COLLUMN_ARTIST_AGE         + " integer not null, "
            + ArtistContract.COLLUMN_ARTIST_PHONE       + " integer not null, "
            + ArtistContract.COLLUMN_ARTIST_EMAIL       + " text not null)";

    private static final String CREATE_TABLE_USERS =
                "CREATE TABLE "+UserContract.TABLE_USER+"("
            + UserContract.COLLUMN_USER_ID      + " integer primary key autoincrement, "
            + UserContract.COLLUMN_USER_LOGIN   + " text not null, "
            + UserContract.COLLUMN_USER_EMAIL   + " text not null, "
            + UserContract.COLLUMN_USER_PASWORD + " text not null) ";

    private static final String CREATE_TABLE_ARTIST_BANDA =
            "CREATE TABLE "+ ArtistBandaContract.TABLE_ARTIST_BAND +"("
                    + ArtistBandaContract.COLLUMN_ARTIST_BAND_ID          + " integer primary key autoincrement, "
                    + ArtistBandaContract.COLLUMN_ARTIST_BAND_NAME        + " text not null, "
                    + ArtistBandaContract.COLLUMN_ARTIST_BAND_EMAIL       + " text not null)";

    // drop table sql query
    private String DROP_TABLE_ARTIST      = "DROP TABLE IF EXISTS " +ArtistContract.TABLE_ARTIST;
    private String DROP_TABLE_USER        = "DROP TABLE IF EXISTS " +UserContract.TABLE_USER;
    private String DROP_TABLE_BAND_ARTIST = "DROP TABLE IF EXISTS " +ArtistBandaContract.TABLE_ARTIST_BAND;

    public MyBandHelper(Context context) {

        super(context, DB_NAME,null,DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ARTIST);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ARTIST_BANDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
          db.execSQL(DROP_TABLE_ARTIST);
          db.execSQL(DROP_TABLE_USER);
          db.execSQL(DROP_TABLE_BAND_ARTIST);

        // Create tables again
        onCreate(db);

    }
}
