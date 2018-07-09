package sz.pl.localweatherapp.service;

import android.content.Context;

import sz.pl.localweatherapp.adapter.CityAdapter;

public interface DatabaseService {

    /**
     * The method responsible for generating the list of cities
     *
     * @param context view object
     * @return {@link CityAdapter}
     */
    CityAdapter generateList(Context context);

    /**
     * The method responsible for searching for cities
     *
     * @param phrase content used to search
     * @return boolean - flag
     */
    boolean search(String phrase);
}
