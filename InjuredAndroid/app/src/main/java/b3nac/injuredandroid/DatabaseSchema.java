package b3nac.injuredandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DatabaseSchema {
    static final String SQL_CREATE_ENTRIES = "CREATE TABLE Thisisatest (_id INTEGER PRIMARY KEY,title TEXT,subtitle TEXT)";
    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS Thisisatest";

    public static class Add implements BaseColumns {
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String TABLE_NAME = "Thisisatest";
    }

    public static class DataBaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "Thisisatest.db";
        public static final int DATABASE_VERSION = 1;

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DatabaseSchema.SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DatabaseSchema.SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    private DatabaseSchema() {
    }
}
