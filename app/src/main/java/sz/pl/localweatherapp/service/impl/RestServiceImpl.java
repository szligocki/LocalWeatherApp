package sz.pl.localweatherapp.service.impl;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.pl.localweatherapp.dto.city.WeatherMapDto;
import sz.pl.localweatherapp.service.OpenWeatherMapService;
import sz.pl.localweatherapp.service.RestService;

public class RestServiceImpl implements RestService {

    /**
     * First part of the link to OpenWeatherMap API
     */
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    /**
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
     */
    private static final String API_UNITS = "metric";

    /**
     * OpenWeatherMap API_KEY
     */
    private static final String API_KEY = "<--YOUR_OPEN_WEATHER_MAP_KEY-->";


    /**
     * @see RestService#callForWeather(long)
     */
    @Override
    public Call<WeatherMapDto> callForWeather(long id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);
        return service.getWeatherByCityId(id, Locale.getDefault().getLanguage(), API_UNITS, API_KEY);
    }
}
