package sz.pl.localweatherapp.service.impl;

import android.content.Context;
import android.database.Cursor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.Getter;
import sz.pl.localweatherapp.adapter.CityAdapter;
import sz.pl.localweatherapp.db.CityRepo;
import sz.pl.localweatherapp.db.DBHelper;
import sz.pl.localweatherapp.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {

    /**
     * The name of the folder being created
     */
    private static final String DATABASE_FOLDER = "databases";

    /**
     * @see Cursor
     */
    private @Getter
    Cursor listCursor;

    /**
     * A class responsible for working with a database
     */
    private CityRepo repository;

    /**
     * @see CityAdapter
     */
    private @Getter
    CityAdapter currentAdapter;

    /**
     * @see DatabaseService#generateList(Context)
     */
    @Override
    public CityAdapter generateList(Context context) {
        if (createDatabaseFolder(context)) {
            repository = new CityRepo(context);
            listCursor = repository.getCityList();
            return currentAdapter = new CityAdapter(context, listCursor, 0);
        }
        return null;
    }

    /**
     * @see DatabaseService#search(String)
     */
    @Override
    public boolean search(String phrase) {
        try {
            if ((listCursor = repository.getCityListByKeyword(phrase)) != null && currentAdapter != null) {
                currentAdapter.swapCursor(listCursor);
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Creating a /database folder
     *
     * @param context view object
     * @return {@link DatabaseServiceImpl#listCursor}
     */
    private boolean createDatabaseFolder(Context context) {
        File folder = new File(
                String.format(
                        "%s%s%s",
                        context.getFilesDir().getParent(),
                        File.separator,
                        DATABASE_FOLDER
                )
        );
        return listCursor != null || ((!folder.exists() && folder.mkdirs()) || folder.exists()) && copyDataBase(context);
    }

    /**
     * Copy file from assets to database folder
     *
     * @param context view object
     * @return boolean flag meaning state of method execution
     */
    private boolean copyDataBase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHelper.DB_NAME);
            File outFileName = context.getDatabasePath(DBHelper.DB_NAME);
            OutputStream outputStream = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
