package sz.pl.localweatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sz.pl.localweatherapp.adapter.CityAdapter;
import sz.pl.localweatherapp.db.CityRepo;
import sz.pl.localweatherapp.db.DBHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String DATABASE_FOLDER = "databases";

    private CityAdapter cityAdapter;
    private ListView listView;
    private Cursor cursor;
    private CityRepo cityRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prepareData();
        if (cursor == null) {
            createDatabasesFolder();
        }
        listView = findViewById(R.id.cityList);
        listView.setAdapter(cityAdapter);
        listView.setOnItemClickListener(this);
    }


    private void prepareData() {
        cityRepo = new CityRepo(this);
        cursor = cityRepo.getCityList();
        cityAdapter = new CityAdapter(MainActivity.this, cursor, 0);
    }

    private void createDatabasesFolder() {
        File folder = new File(getApplicationContext().getFilesDir().getParent() +
                File.separator + DATABASE_FOLDER);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            try {
                copyDataBase(DBHelper.DB_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyDataBase(String dbName) throws IOException {

        InputStream inputStream = getAssets().open(dbName);
        File outFileName = getDatabasePath(dbName);
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();

        prepareData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                cursor = cityRepo.getCityListByKeyword(s);
                cityAdapter.swapCursor(cursor);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                cursor = cityRepo.getCityListByKeyword(s);
                if (cursor != null) {
                    cityAdapter.swapCursor(cursor);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
