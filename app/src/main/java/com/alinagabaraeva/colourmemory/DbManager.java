package com.alinagabaraeva.colourmemory;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by alinagabaraeva on 9/16/15.
 */
public class DbManager {
    private ScoreDbHelper dbHelper;
    private SQLiteDatabase database;

    public DbManager(Context context) {
        dbHelper = new ScoreDbHelper(context);
        // Gets the data repository in write mode
        database = dbHelper.getWritableDatabase();
    }

    public void insertPlayer(ContentValues queryValues) {
        long newRowId;
        newRowId = database.insertOrThrow(
                PlayerContract.ScoreEntry.TABLE_NAME,
                PlayerContract.ScoreEntry.COLUMN_NAME_NULLABLE,
                queryValues);
        if (newRowId == -1) {
            throw new SQLException("RowId = -1");
        }
    }

    public void close() {
        dbHelper.close();
        database = null;
        Log.d("TAG", "Database Closed");
    }
}
