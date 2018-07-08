package sz.pl.localweatherapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CityRepo {

    private DBHelper dbHelper;

    public CityRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public Cursor getCityList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT rowid as " +
                City.KEY_ROWID + " ," +
                City.KEY_CITY_ID + " ," +
                City.KEY_NAME + " ," +
                City.KEY_COUNTRY + " ," +
                City.KEY_LAT + " ," +
                City.KEY_LON + " FROM " + City.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

    public Cursor getCityListByKeyword(String search) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  rowid as " +
                City.KEY_ROWID + "," +
                City.KEY_CITY_ID + "," +
                City.KEY_NAME + "," +
                City.KEY_COUNTRY + "," +
                City.KEY_LAT + "," +
                City.KEY_LON +
                " FROM " + City.TABLE +
                " WHERE " + City.KEY_NAME +
                " LIKE  '%" + search + "%' ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
}
