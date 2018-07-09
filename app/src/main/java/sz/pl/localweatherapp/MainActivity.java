package sz.pl.localweatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import sz.pl.localweatherapp.db.City;
import sz.pl.localweatherapp.service.impl.DatabaseServiceImpl;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * Instance of the database management service
     */
    private DatabaseServiceImpl databaseService = new DatabaseServiceImpl();

    /**
     * The onCreate method initializes the list
     *
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.cityList);
        listView.setAdapter(databaseService.generateList(this));
        listView.setOnItemClickListener(this);
    }

    /**
     * Support for entering text in the search bar
     *
     * @param menu {@link Menu}
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return databaseService.search(s);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return databaseService.search(s);
            }
        });
        return true;
    }

    /**
     * Support for clicking on a list element
     *
     * @param adapterView {@link AdapterView}
     * @param view        {@link View}
     * @param i           position
     * @param l           id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(
                new Intent(this, DetailActivity.class).putExtra(
                        City.KEY_CITY_ID,
                        Long.parseLong(databaseService.getListCursor().getString(
                                databaseService.getListCursor().getColumnIndex(City.KEY_CITY_ID)
                        ))
                )
        );
    }
}
