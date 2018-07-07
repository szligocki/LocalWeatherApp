package sz.pl.localweatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "localweathercities.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + City.TABLE + "("
                + City.KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + City.KEY_CITY_ID + " LONG, "
                + City.KEY_COUNTRY + " TEXT, "
                + City.KEY_NAME + " TEXT, "
                + City.KEY_LAT + " LONG, "
                + City.KEY_LON + " LONG )";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + City.TABLE);
        onCreate(db);
    }
}
