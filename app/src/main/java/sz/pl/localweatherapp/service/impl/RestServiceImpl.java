package sz.pl.localweatherapp.service.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.pl.localweatherapp.DetailActivity;
import sz.pl.localweatherapp.R;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        return service.getWeatherByCityId(id, Locale.getDefault().getLanguage(), API_UNITS, API_KEY);
    }

    /**
     * Method Fills the TextView with data from the server
     */
    public void getWeatherInformation(long cityId, final Context context) {

        callForWeather(cityId).enqueue(new Callback<WeatherMapDto>() {
            @Override
            public void onResponse(@NonNull Call<WeatherMapDto> call, @NonNull Response<WeatherMapDto> response) {
                if (response.isSuccessful()) {
                    WeatherMapDto dto = response.body();
                    try {

                        ((DetailActivity) context).setCityField(dto.getName());
                        ((DetailActivity) context).setUpdateField(DateFormat.getDateTimeInstance().format(new Date((long) (dto.getDt() * 1000))));
                        ((DetailActivity) context).setWeatherIcon(dto.getIcon());
                        ((DetailActivity) context).setCurrentTemperatureField(String.format("%s " + context.getString(R.string.celsius), dto.getMain().getTemp()));
                        ((DetailActivity) context).setDetailField(String.format(
                                "%s\n" + context.getString(R.string.humidity) + " %s%%\n" + context.getString(R.string.pressure) + " %s",
                                dto.getWeather().getDescription(),
                                dto.getMain().getHumidity(),
                                dto.getMain().getPressure()));

                    } catch (NullPointerException ex) {
                        handleError(context);
                    }
                } else {
                    onFailure(call, new NullPointerException());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherMapDto> call, @NonNull Throwable t) {
                handleError(context);
            }
        });
    }


    /**
     * Support for the retrofit error
     */
    private void handleError(Context context) {
        Toast.makeText(context, (R.string.get_weather_information_error), Toast.LENGTH_LONG).show();
        ((DetailActivity) context).finish();
    }
}
