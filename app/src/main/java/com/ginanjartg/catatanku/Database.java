package com.ginanjartg.catatanku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Note.db";
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_CATEGORY = "category";
    private static final String COLUMN_NAME_NOTE = "note";

    protected final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME_TITLE + " TEXT,"
                    + COLUMN_NAME_CATEGORY + " TEXT,"
                    + COLUMN_NAME_NOTE + " TEXT"
                    + ");";

    protected final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    protected final String SQL_SELECT_ALL_NOTE = "SELECT * FROM " + TABLE_NAME;

    protected final String SQL_DELETE_BY_ID = "DELETE FROM " + TABLE_NAME
            + " WHERE "
            + COLUMN_NAME_ID
            + " = ";

    protected final String SQL_SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME
            + " WHERE "
            + COLUMN_NAME_ID
            + " = ";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_NOTE = "notes";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public String selectAll() {
        return SQL_SELECT_ALL_NOTE;
    }

    public void deleteById(SQLiteDatabase db, int id) {
        db.execSQL("DELETE FROM " + TABLE_NAME
                + " WHERE "
                + COLUMN_NAME_ID
                + "=" + id);
    }

    public String selectById(int id) {
        return SQL_SELECT_BY_ID + id;
    }

    public void insertToTable(SQLiteDatabase db, String title, String category, String notes) {
        db.execSQL("INSERT INTO " + TABLE_NAME
                + "("
                + COLUMN_NAME_TITLE + ", "
                + COLUMN_NAME_CATEGORY + ", "
                + COLUMN_NAME_NOTE
                + ") VALUES "
                + "( '" + title + "', '" + category + "', '" + notes + "')");
    }
    public void updateTable(SQLiteDatabase db, String title, String category, String notes, int id) {
        db.execSQL(
                "UPDATE " + TABLE_NAME
                        + " SET "
                        + COLUMN_NAME_TITLE + "= '" + title + "', "
                        + COLUMN_NAME_CATEGORY + "= '" + category + "', "
                        + COLUMN_NAME_NOTE + "= '" + notes + "'"
                        + " WHERE "
                        + COLUMN_NAME_ID + "=" + id
        );
    }
}
