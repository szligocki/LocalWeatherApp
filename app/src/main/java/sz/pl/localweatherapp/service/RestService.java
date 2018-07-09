package sz.pl.localweatherapp.service;

import retrofit2.Call;
import sz.pl.localweatherapp.dto.city.WeatherMapDto;

public interface RestService {

    /**
     * Request to OpenWeatherMap API
     *
     * @param id identification key of City object
     * @return WeatherMapDto
     */
    Call<WeatherMapDto> callForWeather(long id);

}
