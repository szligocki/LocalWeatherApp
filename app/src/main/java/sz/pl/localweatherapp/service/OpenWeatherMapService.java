package sz.pl.localweatherapp.service;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;
import sz.pl.localweatherapp.dto.city.WeatherMapDto;

public interface OpenWeatherMapService {

    @GET("weather")
    Call<WeatherMapDto> getWeatherByCityId(
            @Query("id") long cityId,
            @Query("lang") String lang,
            @Query("units") String units,
            @Query("appid") String appid);
}

