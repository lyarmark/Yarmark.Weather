package yarmark.yarmarkweather;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LeahYarmark on 1/9/2016.
 */
public interface WeatherService {
    @GET("/data/2.5/weather?zip=11218,us&appid=82ca517c7553c491feed59f3df7b756a&units=imperial")

    Call<JsonObject> getCurrentWeather();
}
