package sz.pl.localweatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sz.pl.localweatherapp.db.City;
import sz.pl.localweatherapp.dto.city.WeatherMapDto;
import sz.pl.localweatherapp.service.impl.RestServiceImpl;

public class DetailActivity extends AppCompatActivity {

    private TextView cityField;
    private TextView updatedField;
    private TextView detailsField;
    private TextView currentTemperatureField;
    private ImageView weatherIcon;

    /**
     * Instance of the rest management service
     */
    private RestServiceImpl restService = new RestServiceImpl();

    /**
     * The onCreate method initializes the list
     *
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        cityField = findViewById(R.id.city_field);
        updatedField = findViewById(R.id.updated_field);
        detailsField = findViewById(R.id.details_field);
        currentTemperatureField = findViewById(R.id.current_temperature_field);
        weatherIcon = findViewById(R.id.weather_icon);

        getWeatherInformation();
    }


    /**
     * Method Fills the TextView with data from the server
     */
    private void getWeatherInformation() {
        restService.callForWeather(getIntent().getLongExtra(City.KEY_CITY_ID, 0)).enqueue(new Callback<WeatherMapDto>() {
            @Override
            public void onResponse(@NonNull Call<WeatherMapDto> call, @NonNull Response<WeatherMapDto> response) {
                if (response.isSuccessful()) {
                    WeatherMapDto dto = response.body();
                    try {
                        assert dto != null;
                        cityField.setText(dto.getName());
                        updatedField.setText(DateFormat.getDateTimeInstance().format(new Date((long) (dto.getDt() * 1000))));
                        detailsField.setText(String.format(
                                "%s\n" + getString(R.string.humidity) + " %s%%\n" + getString(R.string.pressure) + " %s",
                                dto.getWeather().getDescription(),
                                dto.getMain().getHumidity(),
                                dto.getMain().getPressure()
                        ));
                        currentTemperatureField.setText(String.format("%s " + getString(R.string.celsius), dto.getMain().getTemp()));
                        weatherIcon.setImageResource(dto.getIcon());
                    } catch (NullPointerException ex) {
                        handleError();
                    }
                } else {
                    onFailure(call, new NullPointerException());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherMapDto> call, @NonNull Throwable t) {
                handleError();
            }
        });
    }

    /**
     * Support for the retrofit error
     */
    private void handleError() {
        Toast.makeText(getApplicationContext(), getString(R.string.get_weather_information_error), Toast.LENGTH_LONG).show();
        finish();
    }
}
