package yarmark.yarmarkweather;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by LeahYarmark on 1/9/2016.
 */
public interface WeatherService {
    @GET("data/2.5/forecast/daily?zip=&mode=json&units=imperial&cnt=16&appid=2de143494c0b295cca9337e1e96b00e0")
   //@GET("data/2.5/forecast/daily?)

//    Call<JsonObject> getLocationWeather(@QueryMap("zip") Map<String, String>);
    Call<JsonObject> getLocationWeather(@Query("zip") String zip);

}
