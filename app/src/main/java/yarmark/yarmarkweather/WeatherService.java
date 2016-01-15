package yarmark.yarmarkweather;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {

    @GET("data/2.5/forecast/daily?")
    Call<LocationWeather> getForecastWeather(@QueryMap Map<String, String> zip);
    @GET("data/2.5/weather?")
    Call<CurrentWeather> getCurrentWeather(@QueryMap Map<String, String> zip);


}